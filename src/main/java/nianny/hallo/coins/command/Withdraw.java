package nianny.hallo.coins.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import nianny.hallo.coins.HalloCoinItem;
import nianny.hallo.coins.HalloCoins;

import java.util.Map;


public class Withdraw {
    private static final SimpleCommandExceptionType FAILED_EXCEPTION = new SimpleCommandExceptionType(new TranslatableText("commands.withdrawal.failed"));
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated){
        dispatcher.register(CommandManager.literal("withdraw")
                .then(((RequiredArgumentBuilder)(CommandManager.argument("value", IntegerArgumentType.integer())))
                .executes(context -> run(context, IntegerArgumentType.getInteger(context,"value")))));
    }

    private static int run(CommandContext<ServerCommandSource> context, int value) throws CommandSyntaxException{
        PlayerEntity player = context.getSource().getPlayer();
        if(!HalloCoins.mp.containsKey(player.getUuid())) throw new AssertionError("UUID not found!!!");
        if(HalloCoins.mp.get(player.getUuid()) < value ){
            throw FAILED_EXCEPTION.create();
        }else{
            Map<String,Integer> mp = HalloCoinItem.withdraw(player,value);
            for(var m:mp.entrySet()){
                if(HalloCoins.convert.containsKey(m.getKey())){
                    player.giveItemStack(new ItemStack(HalloCoins.convert.get(m.getKey()),m.getValue()));
                }
            }
        }
        context.getSource().sendFeedback(new LiteralText("Successfully withdrawed "+value + " hallocoins from your bank!! :D"),false);
        return 1;
    }
}
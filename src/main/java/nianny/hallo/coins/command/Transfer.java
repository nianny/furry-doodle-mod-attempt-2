package nianny.hallo.coins.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import nianny.hallo.coins.HalloCoinItem;
import nianny.hallo.coins.HalloCoins;

public class Transfer {
    private static final SimpleCommandExceptionType FAILED_EXCEPTION = new SimpleCommandExceptionType(new TranslatableText("commands.transfer.failed"));
    private static final SimpleCommandExceptionType TRANSFER_TO_SELF = new SimpleCommandExceptionType(new TranslatableText("commands.transfer.self_transfer"));
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated){
        dispatcher.register((LiteralArgumentBuilder<ServerCommandSource>) CommandManager.literal("transfer")
                .then(CommandManager.argument("player", EntityArgumentType.entity())
                .then(((RequiredArgumentBuilder)(CommandManager.argument("value", IntegerArgumentType.integer())))
                        .executes(context -> run(context, EntityArgumentType.getPlayer(context,"player"),IntegerArgumentType.getInteger(context,"value"))))));
    }

    private static int run(CommandContext<ServerCommandSource> context, PlayerEntity transfer, int value) throws CommandSyntaxException{
        PlayerEntity player = context.getSource().getPlayer();
        if(!HalloCoins.mp.containsKey(player.getUuid())) throw new AssertionError("UUID not found!!!");
        if(player.getUuid() == transfer.getUuid()) throw TRANSFER_TO_SELF.create();
        else if(HalloCoins.mp.get(player.getUuid()) < value ){
            throw FAILED_EXCEPTION.create();
        } else{
            HalloCoinItem.transfer(player, transfer, value);
        }
        context.getSource().sendFeedback(new LiteralText("Successfully transferred "+value + " hallocoins to " + transfer),true);
        transfer.sendMessage(new LiteralText("Hallooooo :D " + player + " has transferred "+ value + " hallocoins to you!!! Yay!" ),false);
        return 1;
    }
}
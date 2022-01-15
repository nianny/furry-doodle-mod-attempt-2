package nianny.hallo.coins.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import nianny.hallo.coins.HalloCoinItem;
import nianny.hallo.coins.HalloCoins;


public class Deposit {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated){
        dispatcher.register(CommandManager.literal("deposit").executes(Deposit::run));
    }
    public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException{
        PlayerEntity player = context.getSource().getPlayer();
        if(!HalloCoins.mp.containsKey(player.getUuid())) throw new AssertionError("UUID not found!!!");
        ItemStack itemSt = player.getMainHandStack();
        String item = itemSt.getItem().toString();
        int t = itemSt.getCount();
        if(!HalloCoinItem.val.containsKey(item)){
            context.getSource().sendError(new LiteralText("You need to hold hallocoins on your main hand to run this command!"));
            return -1;
        }
        HalloCoinItem.Deposit(item, t, player);
        player.getMainHandStack().decrement(itemSt.getCount());
        context.getSource().sendFeedback(new LiteralText("Successfully deposited the hallocoins into your bank!! :D"),false);
        return 1;
    }
}

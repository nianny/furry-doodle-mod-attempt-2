package nianny.hallo.coins.util;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import nianny.hallo.coins.command.*;

public class CommandRegistration {
    public static void registerCommands(){
        CommandRegistrationCallback.EVENT.register(Deposit::register);
        CommandRegistrationCallback.EVENT.register(Withdraw::register);
        CommandRegistrationCallback.EVENT.register(Transfer::register);
    }
}

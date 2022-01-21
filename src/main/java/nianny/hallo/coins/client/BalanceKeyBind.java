package nianny.hallo.coins.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import nianny.hallo.coins.gui.CheckBalance;
import nianny.hallo.coins.gui.HalloScreen;
import org.lwjgl.glfw.GLFW;

public class BalanceKeyBind implements ClientModInitializer {
    private static KeyBinding checkBal = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.hallocoins.checkbalance",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_B,
            "category.hallocoins.hallocoins"
    ));
    
    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while(checkBal.wasPressed()) {
                client.setScreen(new HalloScreen(new CheckBalance(client.player)));
            }
        });
    }
}
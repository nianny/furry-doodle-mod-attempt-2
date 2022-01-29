package nianny.hallo.coins.gui;

import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import nianny.hallo.coins.client.BalanceKeyBind;

public class HalloScreen extends CottonClientScreen {
    public HalloScreen(GuiDescription description) {
        super(description);
    }
    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if(BalanceKeyBind.checkBal.matchesKey(keyCode, scanCode)){
            if (client != null) {
                client.setScreen(null);
            }
            return true;
        }
        return false;
    }
}

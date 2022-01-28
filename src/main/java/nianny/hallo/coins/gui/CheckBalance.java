package nianny.hallo.coins.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WDynamicLabel;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.player.PlayerEntity;
import nianny.hallo.coins.HalloCoins;

public class CheckBalance extends LightweightGuiDescription {
    public CheckBalance(PlayerEntity user){
        WGridPanel hallo = new WGridPanel();
        setRootPanel(hallo);
        hallo.setSize(300,200);
        WDynamicLabel bal = new WDynamicLabel(() -> I18n.translate("text.hallocoins.balance", HalloCoins.mp.get(user.getUuid())));
        hallo.add(bal,1,1);
    }
}

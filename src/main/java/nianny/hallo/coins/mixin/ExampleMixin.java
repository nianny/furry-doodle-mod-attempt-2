package nianny.hallo.coins.mixin;

import net.minecraft.entity.player.PlayerEntity;
import nianny.hallo.coins.HalloCoins;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class ExampleMixin {
	PlayerEntity that = (PlayerEntity)(Object)this;
	@Inject(at = @At("HEAD"), method = "tick")
	public void tick(CallbackInfo info) {
		if(!HalloCoins.mp.containsKey(that.getUuid())) HalloCoins.mp.put(that.getUuid(), 0); //add to map
	}
}

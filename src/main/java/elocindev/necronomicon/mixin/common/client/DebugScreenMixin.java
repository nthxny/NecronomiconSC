package elocindev.necronomicon.mixin.common.client;

import java.util.List;
import net.minecraft.client.gui.components.DebugScreenOverlay;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import elocindev.necronomicon.CommonInitializer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(DebugScreenOverlay.class)
public class DebugScreenMixin {
    @Inject(method = "getGameInformation", at = @At("RETURN"))
	protected void getLeftText(CallbackInfoReturnable<List<String>> info) {
        info.getReturnValue().add("Necronomicon API v"+CommonInitializer.VERSION);
	}
}
package com.alfakynz.autoguiscale.mixin;

import com.alfakynz.autoguiscale.AutoGuiScale;
import com.alfakynz.autoguiscale.config.Config;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Window.class)
public class GuiScaleMixin {

    @Inject(method = "calculateScale", at = @At("RETURN"), cancellable = true)
    private void modifyAutoScale(int i, boolean bl, CallbackInfoReturnable<Integer> cir) {
        if (!Config.ENABLED) return;
        Options options = Minecraft.getInstance().options;
        if (options.guiScale().get() == 0) {
            int computedScale = cir.getReturnValue();
            cir.setReturnValue(Math.max(Config.MINIMUM, computedScale - Config.REDUCED));
            AutoGuiScale.LOGGER.info("Adjusted GUI scale from {} to {}", computedScale, cir.getReturnValue());
        }
    }
}
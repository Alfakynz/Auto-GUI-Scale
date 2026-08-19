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
        if (!Config.ENABLED.value) return;
        Options options = Minecraft.getInstance().options;
        if (options.guiScale().get() == 0) {
            int computedScale = cir.getReturnValue();
            double value = Math.max(Config.MINIMUM.value, computedScale / Config.DIVIDED.value) - Config.REDUCED.value;
            value = Config.ROUND.value ? value : value + 1;
            cir.setReturnValue(Config.ROUND.value ? Math.round((float) value) : (int) value);
            if (Config.DEBUG.value) AutoGuiScale.LOGGER.info("Adjusted GUI scale from {} to {} ({})", computedScale, cir.getReturnValue(), Config.ROUND.value ? String.valueOf(value) : (value - 1) + " + 1");
        }
    }
}
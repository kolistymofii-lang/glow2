package com.example.mixin;

import com.example.GlowMod;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class GlowMixin {
    private boolean glowTarget() {
        Entity self = (Entity) (Object) this;
        Minecraft mc = Minecraft.getInstance();
        return GlowMod.enabled
                && self instanceof Player
                && mc.player != null
                && self != mc.player
                && mc.player.distanceTo(self) <= 200;
    }

    @Inject(method = "isCurrentlyGlowing", at = @At("HEAD"), cancellable = true)
    private void forceGlow(CallbackInfoReturnable<Boolean> cir) {
        if (glowTarget()) cir.setReturnValue(true);
    }

    @Inject(method = "getTeamColor", at = @At("HEAD"), cancellable = true)
    private void purpleOutline(CallbackInfoReturnable<Integer> cir) {
        if (glowTarget()) cir.setReturnValue(0xBF00FF);
    }
}

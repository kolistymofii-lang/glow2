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
    @Inject(method = "isCurrentlyGlowing", at = @At("HEAD"), cancellable = true)
    private void forceGlow(CallbackInfoReturnable<Boolean> cir) {
        if (!GlowMod.enabled) return;
        Entity self = (Entity) (Object) this;
        Minecraft mc = Minecraft.getInstance();
        if (self instanceof Player
                && mc.player != null
                && self != mc.player
                && mc.player.distanceTo(self) <= 200) {
            cir.setReturnValue(true);
        }
    }
}

package com.example;

import com.mojang.brigadier.Command;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.network.chat.Component;

public class GlowMod implements ClientModInitializer {
    public static boolean enabled = true;

    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) ->
            dispatcher.register(ClientCommandManager.literal("glow").executes(ctx -> {
                enabled = !enabled;
                ctx.getSource().sendFeedback(Component.literal("Glow: " + (enabled ? "ON" : "OFF")));
                return Command.SINGLE_SUCCESS;
            }))
        );
    }
}

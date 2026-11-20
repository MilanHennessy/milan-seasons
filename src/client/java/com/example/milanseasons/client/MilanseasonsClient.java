package com.example.milanseasons.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;

public class MilanseasonsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        HudElementRegistry.attachElementBefore(
                VanillaHudElements.CHAT,
                Identifier.fromNamespaceAndPath("seasons", "season_hud"),
                MilanseasonsClient::render
        );
    }

    private static void render(
            net.minecraft.client.gui.GuiGraphicsExtractor graphics,
            net.minecraft.client.DeltaTracker deltaTracker) {

        Minecraft mc = Minecraft.getInstance();

        // Don't render if we're not in a world
        if (mc.player == null) {
            return;
        }

        // Draw our season text
        graphics.text(
                mc.font,
                "Spring",
                8,
                8,
                0xFFFFFFFF,
                true
        );

        graphics.text(
                mc.font,
                "Day 12",
                8,
                20,
                0xFFFFFFFF,
                true
        );

        graphics.text(
                mc.font,
                "Year 1",
                8,
                32,
                0xFFFFFFFF,
                true
        );
    }
}
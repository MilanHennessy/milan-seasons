
package com.example.milanseasons.client;

import com.example.milanseasons.Milanseasons;
import com.example.milanseasons.Season;
import com.example.milanseasons.SeasonManager;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.Minecraft;

public class MilanseasonsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        HudElementRegistry.attachElementBefore(
                VanillaHudElements.CHAT,
                Milanseasons.id("season_hud"),
                MilanseasonsClient::render
        );
    }

    private static void render(
            net.minecraft.client.gui.GuiGraphicsExtractor graphics,
            net.minecraft.client.DeltaTracker deltaTracker) {

        Minecraft mc = Minecraft.getInstance();

        if (mc.player == null || mc.level == null) {
            return;
        }

        // Get the current Minecraft world day (zero-based)
		long worldDay = mc.level.getOverworldClockTime() / 24000L;

        // Calculate season information
        Season season = SeasonManager.getSeason(worldDay);
        int day = SeasonManager.getDayOfSeason(worldDay);
        int year = SeasonManager.getYear(worldDay);

        // Display season information
        graphics.text(
                mc.font,
                season.getDisplayName(),
                8, 8,
                0xFFFFFFFF,
                true
        );

        graphics.text(
                mc.font,
                "Day " + day,
                8, 20,
                0xFFFFFFFF,
                true
        );

        graphics.text(
                mc.font,
                "Year " + year,
                8, 32,
                0xFFFFFFFF,
                true
        );
    }
}

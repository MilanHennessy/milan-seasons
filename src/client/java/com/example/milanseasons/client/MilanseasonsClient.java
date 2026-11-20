package com.example.milanseasons.client;

import com.example.milanseasons.Milanseasons;
import com.example.milanseasons.Season;
import com.example.milanseasons.SeasonManager;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.Minecraft;

import java.util.List;

import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.util.ARGB;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;

public class MilanseasonsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        HudElementRegistry.attachElementBefore(
                VanillaHudElements.CHAT,
                Milanseasons.id("season_hud"),
                MilanseasonsClient::render
        );

		registerSeasonalColors();

		SeasonVisualUpdater.register();
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
	
	private static void registerSeasonalColors() {

		BlockColorRegistry.register(
				List.of(new BlockTintSource() {

					@Override
					public int colorInWorld(
							BlockState state,
							BlockAndTintGetter level,
							BlockPos pos) {

						Minecraft mc = Minecraft.getInstance();

						if (mc.level == null) {
							return ARGB.opaque(0x75B84A);
						}

						long worldDay =
								mc.level.getOverworldClockTime() / 24000L;

						Season season = SeasonManager.getSeason(worldDay);

						int color = switch (season) {
							case SPRING -> 0x8DBB65;
							case SUMMER -> 0x75B84A;
							case AUTUMN -> 0xC58B45;
							case WINTER -> 0xB8C8D0;
						};

						return ARGB.opaque(color);
					}

					@Override
					public int color(BlockState state) {
						return ARGB.opaque(0x75B84A);
					}

				}),

				Blocks.GRASS_BLOCK,
				Blocks.SHORT_GRASS,
				Blocks.FERN,
				Blocks.OAK_LEAVES,
				Blocks.BIRCH_LEAVES,
				Blocks.JUNGLE_LEAVES,
				Blocks.ACACIA_LEAVES,
				Blocks.DARK_OAK_LEAVES,
				Blocks.MANGROVE_LEAVES,
				Blocks.CHERRY_LEAVES,
				Blocks.AZALEA_LEAVES,
				Blocks.FLOWERING_AZALEA_LEAVES
		);
	}

}

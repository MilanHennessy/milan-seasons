package com.example.milanseasons.client;

import com.example.milanseasons.Season;
import com.example.milanseasons.SeasonManager;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;

public class SeasonVisualUpdater {

    private static Season previousSeason = null;

    public static void register() {

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            if (client.level == null) {
                previousSeason = null;
                return;
            }

            long worldDay =
                    client.level.getOverworldClockTime() / 24000L;

            Season currentSeason =
                    SeasonManager.getSeason(worldDay);

            if (previousSeason != null &&
                    previousSeason != currentSeason) {

                client.levelRenderer.allChanged();
            }

            previousSeason = currentSeason;
        });
    }
}
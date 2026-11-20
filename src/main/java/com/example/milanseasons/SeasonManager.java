package com.example.milanseasons;

public class SeasonManager {

    public static final int DAYS_PER_SEASON = 7;
    public static final int SEASONS_PER_YEAR = 4;
    public static final int DAYS_PER_YEAR =
            DAYS_PER_SEASON * SEASONS_PER_YEAR;

    public static Season getSeason(long worldDay) {
        int seasonIndex = (int) ((worldDay / DAYS_PER_SEASON) % SEASONS_PER_YEAR);

        return Season.values()[seasonIndex];
    }

    public static int getDayOfSeason(long worldDay) {
        return (int) (worldDay % DAYS_PER_SEASON) + 1;
    }

    public static int getYear(long worldDay) {
        return (int) (worldDay / DAYS_PER_YEAR) + 1;
    }
}
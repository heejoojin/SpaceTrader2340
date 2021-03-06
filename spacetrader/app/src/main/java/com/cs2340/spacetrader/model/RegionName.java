package com.cs2340.spacetrader.model;

import java.util.Random;
import androidx.annotation.NonNull;

/**
 * Enumerated class representing all possible
 * region names within the game. Names adapted from Star Trek
 */
public enum RegionName {
    //enum stuff here

    // Chose names randomly from the list of STAR TREK STUFF that our professor gave us
    ACAMAR("Acamar"), BALOSNEE("Balosnee"), CALONDIA("Calondia"),
    DALED("Daled"), ENDOR("Endor"), FROLIX("Frolix"),
    GEMULON("Gemulon"), HADES("Hades"), IRALIUS("Iralius"),
    JAPORI("Japori"), KLAATU("Klaatu"), LAVE("Lave");

    private final String region;

    /**
     * Constructor setting up the name of the region
     * @param region of enum string
     */
    RegionName(String region) {
        this.region = region;
    }

    /**
     * Obtains a random name from the list
     * @return the corresponding name from the list given a random number
     */
    public static RegionName getRandom() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

    /**
     * Overridden toString method
     * @return name of the region
     */
    @Override
    @NonNull
    public String toString() {
        return region;
    }
}

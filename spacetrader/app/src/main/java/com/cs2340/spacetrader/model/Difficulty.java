package com.cs2340.spacetrader.model;

import androidx.annotation.NonNull;

/**
 * Enumerated class to set the difficulty of the game
 */

public enum Difficulty {

    BEGINNER("Beginner"), EASY("Easy"), NORMAL("Normal"), HARD("Hard"), IMPOSSIBLE("Impossible");

    private final String difficulty;

    /**
     * Constructor to set the difficulty of the game
     * @param difficulty of enum string
     */
    Difficulty(String difficulty) {
        this.difficulty = difficulty;
    }

//    /**
//     * Gets difficulty
//     * @return name of difficulty
//     */
//    public String getDifficulty() {
//        return difficulty;
//    }
//
//    /**
//     * Sets difficulty
//     */
//    public void setDifficulty(String difficulty) {
//        this.difficulty = difficulty;
//    }

    /**
     * Overridden toString method
     * @return the difficulty of the game
     */
    @Override
    @NonNull
    public String toString() {
        return difficulty;
    }
}

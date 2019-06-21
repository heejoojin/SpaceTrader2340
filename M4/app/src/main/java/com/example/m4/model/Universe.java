package com.example.m4.model;

import java.util.ArrayList;

public class Universe {

    //variables for a universe object
    private ArrayList<Region> regions = new ArrayList<>();
    private int numRegions;
    private int numPlanets;
    private final int HEIGHT = 150;
    private final int WIDTH = 100;


    //constructs a Universe object
    public Universe(int numRegions, int numPlanets) {
        this.numRegions = numRegions;
        this.numPlanets = numPlanets;
    }

    //populates the Universe by calling dropRegions(), then dropPlanets()
    public void populate() {
        dropRegions(numRegions);
        dropPlanets(numPlanets);
    }

    /**
     * Drops a certain number of regions into the universe. This method
     * ensures that the regions are not too close together.
     * @param numRegions
     */
    private void dropRegions(int numRegions) {
        for (int i = 0; i < numRegions; i++) {}
    }

    //adds a region object to the universe
    private void addRegion(Region region) {
        regions.add(region);
        numRegions++;
    }

    /**
     * Drop planets into the universe. This method assigns each planet to
     * a region in the regions ArrayList based on proximity. Again, the
     * planets are not dropped completely randomly.
     * @param numPlanets number of planets to drop into the universe
     */
    private void dropPlanets(int numPlanets) {
        for (int i = 0; i < numPlanets; i++) {}
    }

    //getters and setters for the variables
    public ArrayList<Region> getRegions() {
        return regions;
    }

    public void setRegions(ArrayList<Region> regions) {
        this.regions = regions;
    }

    public int getNumRegions() {
        return numRegions;
    }

    public void setNumRegions(int numRegions) {
        this.numRegions = numRegions;
    }

    public int getNumPlanets() {
        return numPlanets;
    }

    public void setNumPlanets(int numPlanets) {
        this.numPlanets = numPlanets;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getWIDTH() {
        return WIDTH;
    }
}

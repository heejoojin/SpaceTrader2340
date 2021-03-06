package com.cs2340.spacetrader.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Class representing the player's ship, containing variables such as the items owned
 */
@SuppressWarnings("ALL")
public class Ship {

    private final String name;
    private final ArrayList<String> weapons =
            new ArrayList<>(Arrays.asList("Pulse laser", "Beam laser",
                    "Military laser", "No weapon"));
    private final ArrayList<String> shields =
            new ArrayList<>(Arrays.asList("Energy shield", "Reflective shield", "No shield"));
    private final ArrayList<String> gadgets =
            new ArrayList<>(Arrays.asList("Navigation system",
                    "Auto-repair system", "Targeting System", "No gadget"));
    private final ArrayList<String> escape_pods =
            new ArrayList<>(Arrays.asList("Escape pod", "No escape pod"));
    private final ArrayList<String> insurances =
            new ArrayList<>(Arrays.asList("Insurance", "No insurance"));

    private final String weapon;
    private final String shield;
    private final String gadget;
    private final String escape_pod;
    private final String insurance;
//    private String special_weapon;

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    /**
     * Constructor setting up the name of the ship and the price within the market
     * @param name name of the ship
     */
    public Ship(String name) {
        this.name = name;
        this.weapon = weapons.get(new Random().nextInt(weapons.size()));
        this.shield = shields.get(new Random().nextInt(shields.size()));
        this.gadget = gadgets.get(new Random().nextInt(gadgets.size()));
        this.escape_pod = escape_pods.get(new Random().nextInt(escape_pods.size()));
        this.insurance = insurances.get(new Random().nextInt(insurances.size()));
//        this.special_weapon = "No special Weapon";

//        import com.google.firebase.database.DatabaseReference;
//        import com.google.firebase.database.FirebaseDatabase;
//        private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    }

    // getters and setters

    /**
     * get Name
     * @return String of name
     */
    public String getName() {
        return name;
    }

    /**
     * get weapon
     * @return String of weapon
     */
    public String getWeapon() {
        return weapon;
    }

    /**
     * get Shield
     * @return String of sheild
     */
    public String getShield() {
        return shield;
    }

    /**
     * get Gadget
     * @return String of gadget
     */
    public String getGadget() {
        return gadget;
    }

    /**
     * get Escape Pod
     * @return String of escape pod
     */
    public String getEscapePod() {
        return escape_pod;
    }

    /**
     * get Insurance
     * @return String of insurance
     */
    public String getInsurance() {
        return insurance;
    }

//    /**
//     * get Special Weapon
//     * @return String of special weapon
//     */
//    public String getSpecialWeapon() {
//        return special_weapon;
//    }

//    /**
//     * set Special Weapon
//     * @param special_weapon string of special weapon
//     */
//    public void setSpecialWeapon(String special_weapon) {
//        this.special_weapon = special_weapon;
//    }

    @Override
    public String toString() {
        String equipment = String.format("%s,\n%s,\n%s,\n%s,\n%s",
                weapon,
                shield,
                gadget,
                escape_pod,
                insurance);

        return equipment;
    }
}

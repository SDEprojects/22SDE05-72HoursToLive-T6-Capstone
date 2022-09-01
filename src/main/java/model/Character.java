package main.java.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Character {
    private String description;
    private List<String> inventory;
    private int health;

    private int damage;



    //Constructors
    public Character() {
        description = "";
        inventory = List.of();
        health = 0;

    }

    public Character(String description, String inventory, int health, HashMap<String, String> charList) {
        this.description = description;
        this.inventory = Collections.singletonList(inventory);
        this.health = health;
    }


    //Getters and Setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getInventory() {
        return inventory;
    }

    public void setInventory(List<String> inventory) {
        this.inventory = inventory;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public String toString() {
        return "CharactersDemoEb{" +
                "description='" + description + '\'' +
                ", inventory='" + inventory + '\'' +
                ", health=" + health +
                '}';
    }
}

package main.java.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Character {
    private String name;

    private String location;
    private int health;
    private int attackPower;
    private List<String> inventory;

    public Character(){
        this("", "", 0, 0, new ArrayList<String>());
    }

    public Character(String name, String location, int health, int attackPower, List<String> inventory) {
        this.name = name;
        this.location = location;
        this.health = health;
        this.attackPower = attackPower;
        this.inventory = inventory;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
        if (this.health < 0){
            this.health = 0;
        }
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public List<String> getInventory() {
        return inventory;
    }

    public void setInventory(List<String> inventory) {
        this.inventory = inventory;
    }


    public void attack(Character enemy) {
        enemy.gotAttacked(this);
    }

    public void gotAttacked(Character enemy){
        this.health -= enemy.attackPower;
    }

    @Override
    public String toString() {
        return  this.getClass().getSimpleName() +"{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", health=" + health +
                ", attackPower=" + attackPower +
                ", inventory=" + inventory +
                '}';
    }
}
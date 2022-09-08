package main.java.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Werewolf extends Character{


    public Werewolf() {
        this("A werewolf", "", 100, 10, new ArrayList<String>(), 10);

    }

    public Werewolf(String name, String location, int health, int attackPower, List<String> inventory, int armorRating){
        super(name, location, health, attackPower, new LinkedList<>(), 0);
    }
    @Override
    public void gotAttacked(Character enemy){

        int attack = enemy.getAttackPower();
        Random r = new Random();
        int low = 1;
        int high = 6;
        this.setHealth(this.getHealth() - (attack - (r.nextInt(high-low) + low - 4)));

    }


}
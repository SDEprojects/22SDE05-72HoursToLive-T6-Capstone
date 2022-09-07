package main.java.model;

import java.util.LinkedList;
import java.util.List;

public class Werewolf extends Character{

    public Werewolf() {
    }

    public Werewolf(String name, String location, int health, int attackPower, List<String> inventory, int armorRating){
        super(name, location, health, attackPower, new LinkedList<>(), 0);
    }
    @Override
    public void gotAttacked(Character enemy){

        int attack = enemy.getAttackPower();

        this.setHealth(this.getHealth() - attack);

    }


}
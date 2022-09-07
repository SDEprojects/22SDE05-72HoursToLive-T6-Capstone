package main.java.model;

import java.util.LinkedList;
import java.util.Random;

public class WerewolfKing extends Werewolf{
    private Random random;

    public WerewolfKing(){
        super("Wolf king", "Throne Room", 10, 40, new LinkedList<String>(), 10);
        //etInventory().add("golden key");
        random = new Random();
        this.getInventory().add("blood sample");
    }

    @Override
    public void attack(Character enemy){
        super.attack(enemy);
        System.out.println("The Wolf King is attacking!");

        if (random.nextBoolean()){
            specialAttack(enemy);
        }

    }

    @Override
    public void gotAttacked(Character enemy){

        if (getHealth() < 30 && random.nextBoolean()){
            System.out.println("The Wolf King has deflected your attack");
            return;
        }
        super.gotAttacked(enemy);
    }


    public void specialAttack(Character enemy){
        enemy.getInventory().clear();
        System.out.println("The Wolf King has caused you lost all the items!");
    }
}
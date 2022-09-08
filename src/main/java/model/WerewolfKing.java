package main.java.model;

import main.java.controller.GameController;

import java.util.LinkedList;
import java.util.Random;

public class WerewolfKing extends Werewolf{
    private Random random;

    public WerewolfKing(){
        super("The Wolf King", "Throne Room", 100, 12, new LinkedList<String>(), 10);
        random = new Random();
        this.getInventory().add("blood sample");
    }

    @Override
    public void attack(Character enemy){
        super.attack(enemy);
        if (random.nextBoolean()){
            specialAttack(enemy);
        }

    }

    @Override
    public void gotAttacked(Character enemy){
        int val = random.nextInt(5) + 1;

        if (getHealth() < 70 && val == 1){
            System.out.println("The mighty Wolf King has deflected your attack!\n");
            return;
        }
        super.gotAttacked(enemy);
    }


    public void specialAttack(Character enemy){
        enemy.getInventory().clear();
        if(GameController.player.getInventory().size() > 0) {
            System.out.println("The Wolf King reaches for your inventory bag and crushes it with his bare hands. All items you had are now lost!");
        }
        }
}
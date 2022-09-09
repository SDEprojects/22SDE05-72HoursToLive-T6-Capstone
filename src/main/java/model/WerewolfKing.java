package main.java.model;

import java.util.LinkedList;
import java.util.Random;
import java.util.ResourceBundle;

public class WerewolfKing extends Werewolf{
    private Random random;
    private static final ResourceBundle bundle = ResourceBundle.getBundle("main.resources.strings");

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
            System.out.println(bundle.getString("werewolfKing_deflects1"));
            return;
        }
        super.gotAttacked(enemy);
    }


    public void specialAttack(Character enemy){
        if(enemy.getInventory().size() > 0) {
            System.out.println(bundle.getString("werewolfKing_destroys1"));
            enemy.getInventory().clear();
            sleep(1500);
        }
        }

    private static void sleep(int timer) {
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
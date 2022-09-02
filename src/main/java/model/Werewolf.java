package main.java.model;

public class Werewolf extends Character{

    @Override
    public void gotAttacked(Character enemy){

        int attack = enemy.getAttackPower();

        this.setHealth(this.getHealth() - attack);



    }


}
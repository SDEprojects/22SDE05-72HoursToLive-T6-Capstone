package main.java.model;

public class Werewolf extends Character{
    public Werewolf(){
        super();
        setAttackPower(25);
    }
    @Override
    public void gotAttacked(Character enemy){

        int attack = enemy.getAttackPower();

        this.setHealth(this.getHealth() - attack);



    }


}
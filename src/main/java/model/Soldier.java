package main.java.model;

import java.util.ArrayList;
import java.util.List;

class Soldier extends Character{
    boolean visible;
    boolean armor;

    public Soldier(){
        super();
        visible = true;
        armor = false;
    }

    public Soldier(String name, String location, int health, int attackPower, List<String> inventory){
        super(name, location, health, attackPower, inventory);
        visible = true;
        armor = false;
    }

    @Override
    public void gotAttacked(Character enemy){
        if (!visible){
            System.out.println(getName() + " has invisible cloak");
            return;
        }

        int attack = enemy.getAttackPower();
        if (armor == true){
            armor = false;
            System.out.println(getName() + "was protect by armor");
            return;
        }

        setHealth(getHealth() - attack);

    }

    public void pickup(String item){
        getInventory().add(item);
    }



    public boolean useItems(String item){
        if (!getInventory().contains(item)){
            return false;
        }

        if (item.equals("magic potion")){
            getInventory().remove("magic potion");
            setHealth(100);

        }else if (item.equals("armor")){
            armor = true;
            getInventory().remove("armor");
            return true;
        }else if (item.equals("invisibility cloak")){
            visible = false;
            getInventory().remove("invisibility cloak");
            return true;

        }else if (item.equals("sword")){
            setAttackPower(getAttackPower() + 50);
            getInventory().remove("sword");
            return true;

        }

        return false;

    }

    @Override
    public String toString() {
        return super.toString() +
                "visible=" + visible +
                ", armor=" + armor +
                '}';
    }

    public static void main(String[] args) {
        Soldier rob = new Soldier("Rob", "Throne Room", 50, 50, new ArrayList<String>());
        rob.pickup("key");
        System.out.println(rob);
        System.out.println("----------------------------------------------------------------------------");
        rob.pickup("magic potion");
        System.out.println(rob);
        System.out.println("----------------------------------------------------------------------------");
        Werewolf wolf1 = new Werewolf();
        wolf1.attack(rob);
        System.out.println(rob);
        System.out.println("----------------------------------------------------------------------------");
        rob.pickup("invisibility cloak");
        rob.useItems("invisibility cloak");
        wolf1.attack(rob);
        System.out.println(rob);

        System.out.println("----------------------------------------------------------------------------");
        rob.pickup("magic potion");
        rob.useItems("magic potion");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println(rob);
    }




}
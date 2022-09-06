package main.java.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Soldier extends Character{
    boolean visible;
    boolean armor;
    public Soldier(){
        super();
        visible = false;
        armor = false;
    }
    public Soldier(String name, String location, int health, int attackPower, List<String> inventory, int armorRating) {
        super(name, location, health, attackPower, inventory,armorRating);
        visible = false;
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
    public void useItems(String item){
        ArrayList<String> heavyArmor = new ArrayList<>(Arrays.asList("breastplate", "helmet", "shield","greaves"));
        ArrayList<String> lightArmor = new ArrayList<>(Arrays.asList("boots", "gloves",
                "pants", "shirt","belt","bracers","cloak","robe"));
        ArrayList<String> damageItems = new ArrayList<>(Arrays.asList("sword", "ring",
                "amulet", "trinket"));

        Random r = new Random();
        int low = 1;
        int med = 4;
        int high = 6;

        if (heavyArmor.contains(item)){
            System.out.println("You put on the " + item + ". You feel protected!");
            setArmorRating(getArmorRating() + r.nextInt(high-low) + low);
            getInventory().remove(item);
        }
        else if (damageItems.contains(item)){
            System.out.println("You put on the " + item + ". You feel a new sense of power running through your veins!");
            setAttackPower(getAttackPower() + r.nextInt(high-med) + med);
            getInventory().remove(item);

        }
        else if (lightArmor.contains(item)){
            System.out.println("You put on the " + item + ". You feel a little extra protection.");
            setArmorRating(getArmorRating() + r.nextInt(med-low) + low);
            getInventory().remove(item);
        }
        else if (item.equals("health potion")){
            System.out.println("You have used a health potion! You feel invigorated and your health is full!");
            setHealth(100);
            getInventory().remove("health potion");
        }else if (item.equals("armor")){
            armor = true;
            System.out.println("You equip your armor. You feel stronger!");
            getInventory().remove("armor");
        }else if (item.equals("invisibility cloak")){
            visible = false;
            System.out.println("You vanish into the shadows...");
            getInventory().remove("invisibility cloak");
        }else if (item.equals("sword")){
            setAttackPower(getAttackPower() + 20);
            getInventory().remove("sword");
        }
        else{
            System.out.println("Item does nothing!");
        }
    }
    @Override
    public String toString() {
        return super.toString() +
                "visible=" + visible +
                ", armor=" + armor +
                '}';
    }
//    public static void main(String[] args) {
//        Soldier rob = new Soldier("Rob", "Throne Room", 50, 50, new ArrayList<String>());
//        rob.pickup("key");
//        System.out.println(rob);
//        rob.pickup("magic potion");
//        System.out.println(rob);
//        Werewolf wolf1 = new Werewolf();
//        wolf1.attack(rob);
//        System.out.println(rob);
//    }
}
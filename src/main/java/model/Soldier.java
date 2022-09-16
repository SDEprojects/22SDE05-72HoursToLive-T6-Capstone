package main.java.model;

import main.java.view.Story;
import main.java.view.TextColor;

import java.util.*;

public class Soldier extends Character{
    boolean visible;
    boolean armor;
    private static final ResourceBundle bundle = ResourceBundle.getBundle("main.resources.strings");
    public Soldier(){
        super();
        visible = true;
        armor = false;
    }

    public Soldier(String name, String location, int health, int attackPower, List<String> inventory, int armorRating) {
        super(name, location, health, attackPower, inventory,armorRating);
        visible = true;
        armor = false;
    }

    /**
     * method used to attack the enemy. Uses the character class method to track the werewolf's health
     * @param enemy
     */
    @Override
    public void attack(Character enemy){
        System.out.println(TextColor.GREEN+bundle.getString("soldier_attack"));
        super.attack(enemy);
        System.out.println(TextColor.GREEN+bundle.getString("werewolf_health") + enemy.getHealth() + "!\n");
        sleep(750);
    }

    /**
     * configures enemies attack dmg based upon RNG and story difficulty
     * @param enemy
     */
    @Override
    public void gotAttacked(Character enemy){
        if (!visible){
            System.out.println(getName() + bundle.getString("soldier_invisible"));
            return;
        }
        int attack = enemy.getAttackPower();
        if (armor == true){
            armor = false;
            System.out.println(getName() + bundle.getString("armor_protection"));
            return;
        }
        Random r = new Random();
        int low = 1;
        int high = 6;
        setHealth(getHealth() - (((attack * 10) / getArmorRating()) - (r.nextInt(high-low) + low)+4) - Story.difficulty);
    }
    public void pickup(String item){
        getInventory().add(item);
    }

    /**allow player to use the item to boost stats. Also deletes the item from the inventory after the use
     * @param item
     */
    public void useItems(String item) {
        //use database structure to match the item from items json
        ArrayList<String> heavyArmor = new ArrayList<>(Arrays.asList("breastplate", "helmet", "shield", "greaves"));
        ArrayList<String> lightArmor = new ArrayList<>(Arrays.asList("boots", "gloves",
                "pants", "shirt", "belt", "bracers", "cloak", "robe"));
        ArrayList<String> damageItems = new ArrayList<>(Arrays.asList("sword", "ring",
                "amulet", "trinket"));

        Random r = new Random();
        int low = 1;
        int med = 4;
        int high = 6;


        String[] armorRandom = {bundle.getString("armor_use1"), bundle.getString("armor_use2"), bundle.getString("armor_use3"),bundle.getString("armor_use4"), bundle.getString("armor_use5"), bundle.getString("armor_use6"),bundle.getString("armor_use7")};
        String armorRandomResponse = armorRandom[r.nextInt(armorRandom.length)];

        String[] weaponRandom = {bundle.getString("weapon_use1"), bundle.getString("weapon_use2"), bundle.getString("weapon_use3"),bundle.getString("weapon_use4"), bundle.getString("weapon_use5"), bundle.getString("weapon_use6"),bundle.getString("weapon_use7")};
        String weaponRandomResponse = weaponRandom[r.nextInt(weaponRandom.length)];

        if (heavyArmor.contains(item)) {
            System.out.println(TextColor.GREEN+bundle.getString("armor_use0") + item + armorRandomResponse+TextColor.RESET);
            setArmorRating(getArmorRating() + r.nextInt(high - low) + low);
            getInventory().remove(item);
        } else if (damageItems.contains(item)) {
            System.out.println(TextColor.GREEN+bundle.getString("weapon_use0") + item + weaponRandomResponse+TextColor.RESET);
            setAttackPower(getAttackPower() + r.nextInt(high - med) + med);
            getInventory().remove(item);

        } else if (lightArmor.contains(item)) {
            System.out.println(TextColor.GREEN+bundle.getString("armor_use0") + item + armorRandomResponse+TextColor.RESET);
            setArmorRating(getArmorRating() + r.nextInt(med - low) + low);
            getInventory().remove(item);
        } else if (item.equals("health potion")) {
            System.out.println(TextColor.GREEN+bundle.getString("health_potion")+TextColor.RESET);
            setHealth(100);
            getInventory().remove("health potion");

        } else if (item.equals("blood sample")) {
                System.out.println(TextColor.GREEN+bundle.getString("blood_sample"));
        }
        else if (item.equals("armor")){
            armor = true;
            System.out.println(bundle.getString("armor_eq"));
            getInventory().remove("armor");
        }else if (item.equals("invisibility cloak")){
            visible = false;
            System.out.println(bundle.getString("vanish"));
            getInventory().remove("invisibility cloak");
        }else if (item.equals("sword")){
            setAttackPower(getAttackPower() + 20);
            getInventory().remove("sword");
        }
        else{
            System.out.println(bundle.getString("item_useless"));
        }
    }
    @Override
    public String toString() {
        return super.toString() +
                "visible=" + visible +
                ", armor=" + armor +
                '}';
    }

    public void sleep(int timer) {
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
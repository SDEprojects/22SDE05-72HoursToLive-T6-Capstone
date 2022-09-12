package main.java.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SoldierTest {

    @Test
    public void runAllTests(){
        attackDefault();
        attackDeath();
        attackHighArmor();
        pickup();
        useItems();
    }

    @Test
    public void attackDefault() {
        int min = 86;
        int max = 92;
        Werewolf werewolf = new Werewolf();
        Soldier soldier = new Soldier();
        soldier.setArmorRating(10);
        soldier.setHealth(100);
        werewolf.attack(soldier);
        System.out.println("Standard Armor attack test: health is: " + soldier.getHealth());
        assertTrue(min <= soldier.getHealth() && soldier.getHealth() <= max);
    }

    @Test
    public void attackDeath() {

        Werewolf werewolf = new Werewolf();
        Soldier soldier = new Soldier();
        soldier.setArmorRating(10);
        soldier.setHealth(3);
        werewolf.attack(soldier);
        System.out.println("Death test, health is: " + soldier.getHealth());
        assertEquals(0, soldier.getHealth());
    }

    @Test
    public void attackHighArmor() {
        int min = 93;
        int max = 99;
        Werewolf werewolf = new Werewolf();
        Soldier soldier = new Soldier();
        soldier.setArmorRating(40);
        soldier.setHealth(100);
        werewolf.attack(soldier);
        System.out.println("High Armor attack test, health is: " + soldier.getHealth());
        assertTrue(min <= soldier.getHealth() && soldier.getHealth() <= max);
    }

    @Test
    public void pickup() {
        Soldier soldier = new Soldier();
        soldier.pickup("sword");
        System.out.println("Inventory contains sword test. Inventory is: " + soldier.getInventory());
        assertTrue(soldier.getInventory().contains("sword"));
    }

    @Test
    public void useItems() {
        Soldier soldier = new Soldier();
        soldier.setHealth(50);
        soldier.setArmorRating(10);
        soldier.useItems("health potion");
        System.out.println("Health potion test. Health is: " + soldier.getHealth());
        assertEquals(100, soldier.getHealth());
    }

}
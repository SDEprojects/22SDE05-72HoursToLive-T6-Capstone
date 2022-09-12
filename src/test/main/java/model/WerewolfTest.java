package main.java.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class WerewolfTest {

    @Test
    public void attack() {
        Werewolf w1 = new Werewolf();
        Soldier s1 = new Soldier();
        int startingHealth = s1.getHealth();
        w1.attack(s1);
        assertTrue(startingHealth > s1.getHealth());
    }

    @Test
    public void gotAttacked1() {
        Werewolf w1 = new Werewolf();
        int startingHealth = w1.getHealth();
        w1.gotAttacked(new Soldier());
        assertNotEquals(startingHealth, w1.getHealth());

    }

    @Test
    public void gotAttacked2() {
        Werewolf w1 = new Werewolf();
        int startingHealth = w1.getHealth();
        w1.gotAttacked(new Werewolf());
        assertNotEquals(startingHealth, w1.getHealth());
    }

}
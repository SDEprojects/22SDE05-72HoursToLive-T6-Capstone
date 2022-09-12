package main.java.model;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;


import static org.junit.Assert.assertEquals;

public class WerewolfKingTest {

    @Test
    public void attack() {
        WerewolfKing w1 = new WerewolfKing();
        Soldier s1 = new Soldier();
        int startingHealth = s1.getHealth();
        w1.attack(s1);
        assertTrue(startingHealth > s1.getHealth());
    }

    @Test
    public void gotAttacked() {
        WerewolfKing w1 = new WerewolfKing();
        int startingHealth = w1.getHealth();
        w1.gotAttacked(new Soldier());
        assertNotEquals(startingHealth, w1.getHealth());
    }

    @Test
    public void specialAttack() {
        WerewolfKing king = new WerewolfKing();
        Soldier s1 = new Soldier();
        s1.getInventory().addAll(Arrays.asList("trinket", "blood sample", "sword"));
        assertEquals(3, s1.getInventory().size());
        king.specialAttack(s1);
        assertEquals(0, s1.getInventory().size());

    }


}
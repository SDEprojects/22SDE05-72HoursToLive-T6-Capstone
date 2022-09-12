package main.java.model;

import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.*;

public class RoomTest {

    @Test
    public void getConnectedRooms() {
        Room r1 = new Room();
        r1.setConnectedRooms(Map.of("North", "Room2",
                                    "South", "Room3",
                                    "East", "",
                                    "West", "Room4"));

        assertEquals("Room2", r1.getConnectedRooms().get("North"));
        assertEquals("Room3", r1.getConnectedRooms().get("South"));
        assertEquals("", r1.getConnectedRooms().get("East"));
        assertEquals("Room4", r1.getConnectedRooms().get("West"));
    }

    @Test
    public void getItems() {
        Room r1 = new Room();
        r1.setItems(Arrays.asList("blood sample", "sword", "trinket"));

        assertEquals(3, r1.getItems().size());
        assertTrue(r1.getItems().contains("trinket"));
        assertTrue(r1.getItems().contains("sword"));
        assertTrue(r1.getItems().contains("blood sample"));



    }
}
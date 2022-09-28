package main.java.GUI;

import main.java.controller.Response;
import main.java.model.Room;
import main.java.model.RoomMovement;
import org.junit.Before;
import org.junit.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class ControllerTest {
    Controller gameController;
    Room room;

    @Before
    public void setUp() throws IOException {
        Controller.startGame();
        gameController = new Controller();
        room = RoomMovement.roomSwitcher;
        HashMap<String, String> connectedRooms = new HashMap<>();
        connectedRooms.put("north", "Dining Room");
        connectedRooms.put("south", "Castle Grounds");
        connectedRooms.put("east", "Pantry");
        connectedRooms.put("west", "Parapet");
        java.util.List<String> items = new ArrayList<>();
        items.add("blood sample");

        room.setName("Ballroom");
        room.setDescription("The Ball Room is a large formal room inside a building for holding large parties called balls. ");
        room.setConnectedRooms(connectedRooms);
        room.setItems(items);
    }

    @Test
    public void movementNorthTest() throws IOException, UnsupportedAudioFileException, LineUnavailableException, FontFormatException {
        String northExpected = "Dining Room";
        Room goNorth = gameController.implementMove(new Response("go", "north", ""), room);

        assertEquals(northExpected, goNorth.getName());
    }

    @Test
    public void movementSouthTest() throws IOException, UnsupportedAudioFileException, LineUnavailableException, FontFormatException {
        String southExpected = "Castle Grounds";
        Room goSouth = gameController.implementMove(new Response("go", "south", ""), room);

        assertEquals(southExpected, goSouth.getName());

    }

    @Test
    public void movementEastTest() throws IOException, UnsupportedAudioFileException, LineUnavailableException, FontFormatException {
        String eastExpected = "Pantry";
        Room goEast = gameController.implementMove(new Response("go", "east", ""), room);

        assertEquals(eastExpected, goEast.getName());
    }

    @Test
    public void movementWestTest() throws IOException, UnsupportedAudioFileException, LineUnavailableException, FontFormatException {
        String westExpected = "Parapet";
        Room goWest = gameController.implementMove(new Response("go", "west", ""), room);

        assertEquals(westExpected, goWest.getName());

    }

    @Test
    public void addToInventory(){
        int expected = 1;
        List<String> inventory = gameController.addToInventory(new Response("pickup", "", "blood sample"), room);

        assertEquals(expected, inventory.size());
        assertTrue(inventory.contains("blood sample"));

    }
}
package main.java.GUI;

import main.java.controller.Response;
import main.java.model.Room;
import main.java.model.RoomMovement;
import org.junit.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

import static org.junit.Assert.*;

public class ControllerTest {

    @Test
    public void movementNorthTest() throws IOException, UnsupportedAudioFileException, LineUnavailableException, FontFormatException {
        Controller.startGame();
        Controller gameController = new Controller();
        Room room = RoomMovement.roomSwitcher;
        String northExpected = room.getConnectedRooms().get("north");

        Room goNorth = gameController.implementMove(new Response("go", "north", ""), room);

        if (goNorth != null) {
            assertEquals(northExpected, goNorth.getName());
        }else {
            try {
                throw new NullPointerException();
            } catch (NullPointerException expected) {
                // go team!
            }
        }
    }

    @Test
    public void movementSouthTest() throws IOException, UnsupportedAudioFileException, LineUnavailableException, FontFormatException {
        Controller.startGame();
        Controller gameController = new Controller();
        Room room = RoomMovement.roomSwitcher;
        String southExpected = room.getConnectedRooms().get("south");

        Room goSouth = gameController.implementMove(new Response("go", "south", ""), room);

        if (goSouth != null) {
            assertEquals(southExpected, goSouth.getName());
        }else {
            try {
                throw new NullPointerException();
            } catch (NullPointerException expected) {
                // go team!
            }
        }
    }

    @Test
    public void movementEastTest() throws IOException, UnsupportedAudioFileException, LineUnavailableException, FontFormatException {
        Controller.startGame();
        Controller gameController = new Controller();
        Room room = RoomMovement.roomSwitcher;
        String eastExpected = room.getConnectedRooms().get("east");

        Room goEast = gameController.implementMove(new Response("go", "east", ""), room);

        if (goEast != null) {
            assertEquals(eastExpected, goEast.getName());
        }else {
            try {
                throw new NullPointerException();
            } catch (NullPointerException expected) {
                // go team!
            }
        }

    }

    @Test
    public void movementWestTest() throws IOException, UnsupportedAudioFileException, LineUnavailableException, FontFormatException {
        Controller.startGame();
        Controller gameController = new Controller();
        Room room = RoomMovement.roomSwitcher;
        String westExpected = room.getConnectedRooms().get("west");

        Room goWest = gameController.implementMove(new Response("go", "west", ""), room);

        if (goWest != null) {
            assertEquals(westExpected, goWest.getName());
        }else {
            try {
                throw new NullPointerException();
            } catch (NullPointerException expected) {
                // go team!
            }

        }
    }
}
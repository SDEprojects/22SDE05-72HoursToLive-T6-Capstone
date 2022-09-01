package main.java.controller;

import main.java.model.Room;
import main.java.model.RoomMovement;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class GameController {




    public void userChoice() throws IOException {

        while (true) {
            String currentRoom = RoomMovement.currentRoom;
            Room roomSwitcher = RoomMovement.roomSwitcher;

            Scanner scanner = new Scanner(System.in);
            System.out.println("\nWhat would you like to do?");
            String choice = (scanner.nextLine()).toLowerCase();
            TextParser textParser = new TextParser();
            Response r1 = textParser.getCommands(choice);
            try {
                if (!r1.isValid() || r1.getLocation().length() == 0) {
                    System.out.println("invalid response, try \"go east\"");
                } else if (r1.getVerb().equalsIgnoreCase("go")
                        && !Objects.equals(roomSwitcher.getConnectedRooms().get(r1.getLocation()), "None")){
                    RoomMovement.switchRooms(r1.getLocation());
                    break;
                }
                else {
                    System.out.println("Nothing happened! Try another direction!");
                }
            } catch (NullPointerException e) {
                System.out.println("That is not a valid input!");
            }


    }
    }
}

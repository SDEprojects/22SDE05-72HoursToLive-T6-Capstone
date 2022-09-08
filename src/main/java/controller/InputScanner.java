package main.java.controller;

import main.java.model.Room;
import main.java.model.RoomMovement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class InputScanner {


    public static Response getValidResponse() throws IOException {
        Room room = RoomMovement.roomSwitcher;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWhat would you like to do?");
        String choice = (scanner.nextLine()).toLowerCase();
        TextParser textParser = new TextParser();
        Response r1 = textParser.getCommands(choice);
        ArrayList<String> validDirections = new ArrayList<>(Arrays.asList("north", "south", "east", "west"));
        while (true) {
            try {
                if (!r1.isValid()) {
                    System.out.println("invalid response, try \"go east\"");
                    break;
                } else if (r1.getVerb().equals("go") && Objects.equals(room.getConnectedRooms().get(r1.getLocation()), "None")) {
                    System.out.println("You can't go that way, try another direction!");
                    sleep(1000);
                    r1 = getValidResponse();
                    break;
                }
                else if (r1.getVerb().equals("go") && !(validDirections.contains(r1.getLocation()))) {
                    System.out.println("You must choose a valid direction! Valid directions are: north, south, east, and west.");
                    r1 = getValidResponse();
                    break;
                }
                return r1;

            } catch (NullPointerException e) {
                System.out.println("That is not a valid input! INSIDE RESPONSE");
                break;
            }
        }
        return r1;
    }
    private static void sleep(int timer) {
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

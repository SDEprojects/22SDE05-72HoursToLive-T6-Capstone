package main.java.controller;

import main.java.model.Room;
import main.java.model.RoomMovement;
import main.java.model.Soldier;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class GameController {
    public static Soldier player = new Soldier();




    public void userChoice() throws IOException {

        while (true) {

            try {
                String currentRoom = RoomMovement.currentRoom;
                Room room = RoomMovement.roomSwitcher;


                Scanner scanner = new Scanner(System.in);
                System.out.println("\nWhat would you like to do?");
                String choice = (scanner.nextLine()).toLowerCase();
                TextParser textParser = new TextParser();
                Response r1 = textParser.getCommands(choice);
//                System.out.println(r1.getVerb()+" THIS IS GET VERB");
//                System.out.println(r1.getLocation() + " THIS IS GET LOCATION");
//                System.out.println(r1.getNoun() + "YO THIS IS GETNOUN");
                if (!r1.isValid()) {
                    System.out.println("invalid response, try \"go east\"");
                }
                else if (r1.getVerb().equalsIgnoreCase("go")
                        && !Objects.equals(room.getConnectedRooms().get(r1.getLocation()), "None")){
                    RoomMovement.switchRooms(r1.getLocation());
                    break;
                }
                else if (r1.getVerb().equalsIgnoreCase("use") && r1.getVerb().length() > 0){
                    if (player.getInventory().contains(r1.getNoun())){
                        player.useItems(r1.getNoun());
                        player.getInventory().remove(r1.getNoun());
                        sleep(1000);
                        RoomMovement.menu();

                    }else {
                        System.out.println("You don't have that item");
                        sleep(1000);
                        RoomMovement.menu();
                    }
                }
                else if (r1.getVerb().equalsIgnoreCase("look")){
                    System.out.println(room.getDescription());
                    System.out.println("\nYou look around the room to see if you can find anything...");
                    System.out.println("Looking...");
                    sleep(1000);

                    for (String key : room.getItems()){
                        sleep(1000);
                        System.out.println("You see a "+key + "!");
                    }
                    sleep(1000);
                    RoomMovement.menu();
                }

                else if (r1.getVerb().equalsIgnoreCase("help")){
                    System.out.println("\nYou can go to a room by typing \"go [direction]\"\n" +

                            "You can use an item by typing \"use [item]\"\n" +
                            "You can look at the room by typing \"look\"\n" +
                            "You can quit the game by typing \"quit\"\n");
                    System.out.println("Directions are:");
                    for (String key : room.getConnectedRooms().keySet()){
                        System.out.println(key);
                    }
                    sleep(2000);
                    RoomMovement.menu();

                }

                else if (r1.getVerb().equalsIgnoreCase("pickup")){
//                    System.out.println("YEP PICKUP");
                    if (room.getItems().contains(r1.getNoun())){
                        player.pickup(r1.getNoun());
                        room.getItems().remove(r1.getNoun());
                        System.out.println("You picked up a " + r1.getNoun()+"! It has been added to your inventory");
                        sleep(1000);
                        RoomMovement.menu();
                    }else {
                        System.out.println("That item doesn't exist in this room");
                    }
                }

                else if (r1.getVerb().equalsIgnoreCase("quit")) {
                    System.out.println("Quitting the game...Thanks for playing!");
                    System.exit(0);
                }


                else {
                    System.out.println("\nNothing happened! Try \"go east\" to move, or \"help\" for commands");
                }

            } catch (NullPointerException e) {
                System.out.println("That is not a valid input!");
            }


    }
    }

    public void sleep(int timer) {
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

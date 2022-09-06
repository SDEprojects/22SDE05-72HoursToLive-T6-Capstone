package main.java.controller;

import main.java.model.Room;
import main.java.model.RoomMovement;
import main.java.model.Soldier;
import main.java.model.Werewolf;

import java.io.IOException;
import java.util.*;

public class GameController {
    public static Soldier player = new Soldier();




    public void userChoice() throws IOException {
        String currentRoom = RoomMovement.currentRoom;
        HashMap<String, List<Werewolf>> monsterMap = getMonsterMap(currentRoom);


        while (true) {
            try {
                if (!monsterMap.get(currentRoom).isEmpty()){
                    Werewolf wolf = monsterMap.get(currentRoom).get(0);
                    wolf.attack(player);
                    System.out.println("werewolf is attacking you!");
                }
                Room room = RoomMovement.roomSwitcher;

//                Scanner scanner = new Scanner(System.in);
//                System.out.println("\nWhat would you like to do?");
//                String choice = (scanner.nextLine()).toLowerCase();
//                TextParser textParser = new TextParser();
//                Response r1 = textParser.getCommands(choice);
                Response r1 = getValidResponse();
                if (!r1.isValid()) {
                    System.out.println("invalid response, try \"go east\"");
                }
                else if (r1.getVerb().equalsIgnoreCase("go") && Objects.equals(room.getConnectedRooms().get(r1.getLocation()), "None")){
                    System.out.println("You can't go that way, try another direction!");
                }
                else if (r1.getVerb().equalsIgnoreCase("go")
                        && !Objects.equals(room.getConnectedRooms().get(r1.getLocation()), "None")){
                    RoomMovement.switchRooms(r1.getLocation());
                    break;
                }
                else if (r1.getVerb().equalsIgnoreCase("use") && r1.getVerb().length() > 0){
                    if (player.getInventory().contains(r1.getNoun())){
                        player.useItems(r1.getNoun());
                        sleep(1000);
                        RoomMovement.menu();

                    }else {
                        System.out.println("You don't have that item!");
                        sleep(1000);
                        RoomMovement.menu();
                    }
                }
                else if (r1.getVerb().equalsIgnoreCase("look")){
                    System.out.println(room.getDescription());
                    System.out.println("\nYou look around the room to see if you can find anything...");
                    System.out.println("Looking...");
                    sleep(500);
                    if (room.getItems().size()<1) {
                        System.out.println("You don't see anything of interest.");
                    }
                    else{
                        for (String key : room.getItems()){
                            sleep(1000);
                            System.out.println("You see the "+key + "!");
                        }
                    }
                    sleep(1000);
                    RoomMovement.menu();
                }

                else if (r1.getVerb().equalsIgnoreCase("pickup")){
                    if (player.getInventory().size() > 2){
                        System.out.println("You can't carry anymore items! Try using an item in your inventory.");

                    }
                    else if (room.getItems().contains(r1.getNoun())){
                        player.pickup(r1.getNoun());
                        room.getItems().remove(r1.getNoun());
                        System.out.println("You picked up the " + r1.getNoun()+"! It has been added to your inventory.");
                        sleep(1000);

                    }else {
                        System.out.println("That item doesn't exist in this room");
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

                }else if (r1.getVerb().equals("attack")){
                    //check null case
                    Werewolf w1 = monsterMap.get(currentRoom).get(0);
                    player.attack(w1);

                }
                else if (r1.getVerb().equalsIgnoreCase("quit")) {
                    System.out.println("Quitting the game...Thanks for playing!");
                    System.exit(0);
                }


                else {
                    System.out.println("\nNothing happened! Try \"go [direction]\" to move, or \"help\" for commands");
                }

            } catch (NullPointerException e) {
                System.out.println("That is not a valid input!");
            }


    }
    }
    public static Response getValidResponse() throws IOException {
        Room room = RoomMovement.roomSwitcher;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWhat would you like to do?");
        String choice = (scanner.nextLine()).toLowerCase();
        TextParser textParser = new TextParser();
        Response r1 = textParser.getCommands(choice);
        while (true){
            try {
                if (!r1.isValid()) {
                    System.out.println("invalid response, try \"go east\"");
                } else if (r1.getVerb().equalsIgnoreCase("go") && Objects.equals(room.getConnectedRooms().get(r1.getLocation()), "None")) {
                    System.out.println("You can't go that way, try another direction!");
                } else {
                    break;
                }
            }
            catch (NullPointerException e) {
                System.out.println("That is not a valid input!");
            }
        }
        return r1;
    }
    public static HashMap<String, List<Werewolf>> getMonsterMap(String room){
        Random random = new Random();
        HashMap<String, Room> allMap = RoomMovement.getAllRooms();
        HashMap<String, List<Werewolf>> monsterMap = new HashMap<>();
        for (String key : allMap.keySet()){
            monsterMap.put(key, new LinkedList<Werewolf>());
            if (random.nextBoolean() && !key.equals(room)){
                monsterMap.get(key).add(new Werewolf());
            }
        }

        System.out.println(monsterMap);
        return monsterMap;
    }


    public void sleep(int timer) {
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

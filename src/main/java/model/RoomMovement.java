package main.java.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.controller.GameController;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;


public class RoomMovement {
    public static String currentRoom;
    public static Room roomSwitcher;

    public static String roomNorth;
    public static String roomEast;
    public static String roomSouth;
    public static String roomWest;

    //    Room room = new Room();
    ObjectMapper mapper = new ObjectMapper();
    TypeReference<HashMap<String, Room>> typeRef  = new TypeReference<HashMap<String, Room>>() {};
    static HashMap<String, Room> allRooms;

    {
        try {
            allRooms = new ObjectMapper().readValue(new File("src/main/resources/rooms.json"), typeRef);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method generates a random room that will be used as the FIRST room
     * the user starts in.
     */
    public void firstRoom(){
        currentRoom = allRooms.keySet().toArray()[(int) (Math.random() * allRooms.size())] + "";
        Room room = allRooms.get(currentRoom);
        roomSwitcher = room;
        System.out.println("You have entered the " + room.getName()+ ".");
        sleep(750);
        System.out.println(room.getDescription());
        menu();
    }


    public static void switchRooms(String location) throws IOException {
        currentRoom = roomSwitcher.getConnectedRooms().get(location);
        Room room = allRooms.get(currentRoom);
        roomSwitcher = room;
        System.out.println("You have entered the " + room.getName()+ ".");
        sleep(750);
        System.out.println(room.getDescription());
        menu();
    }



    public static String askRoom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWhich room do you want to go to?");
        return scanner.nextLine();
    }


    public static void menu() {
        String menu = "=======================";
        Soldier player = GameController.player;
//        String room = randomRoom();
//        Room room = allRooms.get(firstRoom());
        Room room = allRooms.get(currentRoom);
        roomSwitcher = room;
//        System.out.println("You have entered the " + room.getName()+ ".");
//        sleep(750);
//        System.out.println(room.getDescription());

//        sleep(1500);
        System.out.printf("%s %12s %12s %12s %12s %12s", menu, menu, menu, menu, menu,menu);
        System.out.printf("%nPlayer%-17s Inventory:%-16sHealth:%-17s East:%-18s South:%-17s West:%-16s", ":", player.getInventory(), player.getHealth(),
                room.getConnectedRooms().get("east"), room.getConnectedRooms().get("south"), room.getConnectedRooms().get("west"));

        System.out.printf("%nRooms%-18s Current:%-16sNorth:%-17s East:%-18s South:%-17s West:%-16s", ":", room.getName(), room.getConnectedRooms().get("north"),
                room.getConnectedRooms().get("east"), room.getConnectedRooms().get("south"), room.getConnectedRooms().get("west"));



// THIS PRINT WILL BE DELETED SOON
        System.out.printf("%nPlayer%-17s Room Item 1:%-16sNorth:%-17s East:%-18s South:%-17s West:%-16s%n", ":", room.getItems(), room.getConnectedRooms().get("north"),
                room.getConnectedRooms().get("east"), room.getConnectedRooms().get("south"), room.getConnectedRooms().get("west"));
//        System.out.printf("%s %12s %12s %12s %12s %12s", menu, menu, menu, menu, menu,menu);
//        System.out.printf("%nMove%-19s Press%-18s %-23d %-23d %-23d %-22d %n",":", ":",1,2,3,4);

//        System.out.printf("%s %10d %15d %15d %14sAction: %s\n", "Enter:", 1, 2, 3, "", "aa");
        System.out.printf("%s %12s %12s %12s %12s %12s\n", menu, menu, menu, menu, menu,menu);
    }

    public String textStream(String text, int speed) {
        for (int i = 0; i < text.length(); i++) {
            System.out.printf("%c", text.charAt(i));
            sleep(speed);
        }
        return text;
    }

    public static void sleep(int timer) {
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

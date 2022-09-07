package main.java.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
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
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream resources = classLoader.getResourceAsStream("main/resources/rooms.json");
            allRooms = new ObjectMapper().readValue(resources, typeRef);
            //allRooms = new ObjectMapper().readValue(new File("src/main/resources/rooms.json"), typeRef);
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
        System.out.println("\nYou have entered the " + room.getName()+ ".");
        sleep(750);
        System.out.println(room.getDescription() + "\n");
        sleep(150);

    }


    public static void switchRooms(String location) throws IOException {
        currentRoom = roomSwitcher.getConnectedRooms().get(location);
        Room room = allRooms.get(currentRoom);
        roomSwitcher = room;
        System.out.println("\nYou have entered the " + room.getName()+ ".");
        sleep(750);
        System.out.println(room.getDescription() + "\n");
        sleep(150);
    }



    public static String askRoom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWhich room do you want to go to?");
        return scanner.nextLine();
    }




    public static HashMap<String, Room> getAllRooms(){

        return allRooms;

    }


    public static void sleep(int timer) {
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

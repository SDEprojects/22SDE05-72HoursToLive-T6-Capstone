package main.java.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;


public class RoomMovement1 {
    public static String currentRoom;
    public static Room roomSwitcher;
    private static final ResourceBundle bundle = ResourceBundle.getBundle("main.resources.strings");

    //    Room room = new Room();
    ObjectMapper mapper = new ObjectMapper();
    TypeReference<HashMap<String, Room>> typeRef  = new TypeReference<HashMap<String, Room>>() {};
    TypeReference<HashMap<String, HashSet<String>>> typeRef2 = new TypeReference<HashMap<String, HashSet<String>>>() {};

    private static HashMap<String, HashSet<String>> itemMap;
    static HashMap<String, Room> allRooms;

    {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream resources = classLoader.getResourceAsStream("rooms.json");
            allRooms = new ObjectMapper().readValue(resources, typeRef);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * This method generates a random room that will be used as the FIRST room
     * the user starts in.
     */
    public void firstRoom(){
        do {
            currentRoom = allRooms.keySet().toArray()[(int) (Math.random() * allRooms.size())] + "";
        } while (currentRoom.equalsIgnoreCase("Throne Room"));
        Room room = allRooms.get(currentRoom);
        roomSwitcher = room;
        textStream(bundle.getString("firstRoom_text1"),110);
        sleep(1000);
        System.out.println(bundle.getString("firstRoom_text2"));
        sleep(2000);
        System.out.println(bundle.getString("firstRoom_text3"));
        sleep(2350);
        System.out.println(bundle.getString("firstRoom_text4") + room.getName()+ ".");
        sleep(750);
        System.out.println(room.getDescription() + "\n");
        sleep(550);

    }


    public static void switchRooms(String location) throws IOException {
        currentRoom = roomSwitcher.getConnectedRooms().get(location);
        Room room = allRooms.get(currentRoom);
        roomSwitcher = room;
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
    private String textStream(String text, int speed) {
        for (int i = 0; i < text.length(); i++) {
            System.out.printf("%c", text.charAt(i));
            sleep(speed);
        }
        return text;
    }
}

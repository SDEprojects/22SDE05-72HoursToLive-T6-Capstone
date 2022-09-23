package main.java.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.GUI.UpdatePanel;
import main.java.client.Client;
import main.java.view.TextColor;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class RoomMovement {
    public static String currentRoom;
    public static Room roomSwitcher;


    //    Room room = new Room();
    ObjectMapper mapper = new ObjectMapper();
    TypeReference<HashMap<String, Room>> typeRef = new TypeReference<HashMap<String, Room>>() {
    };
    TypeReference<HashMap<String, HashSet<String>>> typeRef2 = new TypeReference<HashMap<String, HashSet<String>>>() {
    };

    private static HashMap<String, HashSet<String>> itemMap;
    static HashMap<String, Room> allRooms;
    private static final ResourceBundle bundle = ResourceBundle.getBundle("main.resources.strings");


    {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream resources = classLoader.getResourceAsStream("main/resources/rooms.json");
            allRooms = new ObjectMapper().readValue(resources, typeRef);
            //allRooms = new ObjectMapper().readValue(new File("src/main/resources/rooms.json"), typeRef);
            InputStream items = classLoader.getResourceAsStream("main/resources/items.json");
            itemMap = new ObjectMapper().readValue(items, typeRef2);

            populateRoomWithItems(2);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method will populate the rooms with items that are not the Trophy and blood sample.
     *
     * @param numItems
     */
    private static void populateRoomWithItems(int numItems) {
        ArrayList<String> keyList = new ArrayList<>(itemMap.keySet());
        Random random = new Random();
        keyList.remove("Trophy");
        keyList.remove("blood sample");
        for (String key : allRooms.keySet()) {
            HashSet<String> itemSet = new HashSet<>();
            while (itemSet.size() < numItems) {
                int pos = random.nextInt(keyList.size());
                itemSet.add(keyList.get(pos));
            }
            allRooms.get(key).getItems().addAll(itemSet);
        }
    }


    /**
     * This method generates a random room that will be used as the FIRST room
     * the user starts in.
     */
    public void firstRoom() {
        do {
            currentRoom = allRooms.keySet().toArray()[(int) (Math.random() * allRooms.size())] + "";
        } while (currentRoom.equalsIgnoreCase("Throne Room"));
        Room room = allRooms.get(currentRoom);
        roomSwitcher = room;

        String text = (bundle.getString("firstRoom_text1")) +
                (bundle.getString("firstRoom_text2")) +
                (bundle.getString("firstRoom_text3")) +
                (bundle.getString("firstRoom_text4"));

        if (Client.psvmIsGUI) {
            System.out.println("text on screen");
        } else {
            textStream(TextColor.BLUE + bundle.getString("firstRoom_text1"), 110);
            sleep(1000);
            System.out.println(TextColor.BLUE + bundle.getString("firstRoom_text2"));
            sleep(2000);
            System.out.println(TextColor.BLUE + bundle.getString("firstRoom_text3"));
            sleep(2350);
            System.out.println(TextColor.BLUE + bundle.getString("firstRoom_text4") + TextColor.WHITE + room.getName() + ".");
            sleep(750);
            System.out.println(TextColor.BLUE + room.getDescription() + TextColor.RESET + "\n");
            sleep(550);
        }
    }


    /**
     * method switches the current room with another room. It is being called by the game controller.
     *
     * @param location
     * @throws IOException
     */
    public static void switchRooms(String location) throws IOException {
        currentRoom = roomSwitcher.getConnectedRooms().get(location);
        Room room = allRooms.get(currentRoom);
        roomSwitcher = room;
    }


    public static HashMap<String, Room> getAllRooms() {
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

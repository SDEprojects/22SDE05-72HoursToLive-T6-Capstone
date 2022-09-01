package main.java.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.controller.Response;
import main.java.controller.TextParser;

import java.io.File;
import java.io.IOException;
import java.util.*;


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
        menu();
    }


    public void switchRooms() throws IOException {

        String userDirection = askRoom();

        TextParser textParser = new TextParser();
        System.out.println(userDirection);
        Response r1 = textParser.getCommands(userDirection);
        System.out.println(r1);
        if (!r1.isValid() || r1.getLocation().length() == 0){
            System.out.println("invalid response");
        }

        userDirection = r1.getLocation();
        userDirection = java.lang.Character.toUpperCase(userDirection.charAt(0)) + userDirection.substring(1);
//        char ch = java.lang.Character.toUpperCase(direction.charAt(0));
//        userDirection = ch + direction.substring(1);
//        System.out.println(direction);



        List<String> match = Arrays.asList("North", "East", "South", "West");

        while (true) {
            try {
                if (match.stream().anyMatch(userDirection::equals) && !Objects.equals(roomSwitcher.getConnectedRooms().get(userDirection), "None")) {
                    currentRoom = roomSwitcher.getConnectedRooms().get(userDirection);
                    menu();
                    userDirection = askRoom();
                    userDirection = textParser.getCommands(userDirection).getLocation();
                    userDirection = java.lang.Character.toUpperCase(userDirection.charAt(0)) + userDirection.substring(1);

                }
                else {
                    System.out.println("You can't go that way!");
                    userDirection = askRoom();

                }
            } catch (NullPointerException e) {
                System.out.println("That is not a valid input!");
                userDirection = askRoom();
            }
        }
    }

//    public void switchRooms() {
//
//        String userDirection = askRoom();
//        List<String> match = Arrays.asList("North", "East", "South", "West");
//        List<String> number = Arrays.asList("1","2","3","4");
//
//        while (true) {
//            try {
//                if (number.stream().anyMatch(userDirection::equals) && !Objects.equals(roomSwitcher.getConnectedRooms().get(match.get(Integer.parseInt(userDirection)-1)), "None")) {
//                    currentRoom = roomSwitcher.getConnectedRooms().get(match.get(Integer.parseInt(userDirection)-1));
//                    menu();
//                    userDirection = askRoom();
//
//                }
//                else {
//                    System.out.println("You can't go that way!");
//                    userDirection = askRoom();
//
//                }
//            } catch (NullPointerException e) {
//                System.out.println("That is not a valid input!");
//                userDirection = askRoom();
//            }
//        }
//    }




    public String askRoom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWhich room do you want to go to?");
        return scanner.nextLine();
    }


    public void menu() {
        String menu = "=======================";
//        String room = randomRoom();
//        Room room = allRooms.get(firstRoom());
        Room room = allRooms.get(currentRoom);
        roomSwitcher = room;
        System.out.println("You have entered the " + room.getName()+ ".");
        sleep(750);
        System.out.println(room.getDescription());

        sleep(1500);
        System.out.printf("%nRooms%-18s Current:%-16sNorth:%-17s East:%-18s South:%-17s West:%-16s%n", ":", room.getName(), room.getConnectedRooms().get("North"),
                room.getConnectedRooms().get("East"), room.getConnectedRooms().get("South"), room.getConnectedRooms().get("West"));
        System.out.printf("%s %12s %12s %12s %12s %12s", menu, menu, menu, menu, menu,menu);
        System.out.printf("%nMove%-19s Press%-18s %-23d %-23d %-23d %-22d %n",":", ":",1,2,3,4);

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

    public void sleep(int timer) {
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

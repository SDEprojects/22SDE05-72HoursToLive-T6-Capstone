package main.java.controller;

import main.java.model.Room;
import main.java.model.RoomMovement;
import main.java.view.TextColor;

import java.io.IOException;
import java.util.*;

public class InputScanner {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("main.resources.strings");

    public static Response getValidResponse() throws IOException {
        Room room = RoomMovement.roomSwitcher;
        Scanner scanner = new Scanner(System.in);
        System.out.println(TextColor.YELLOW + bundle.getString("input_scanner_ask") + TextColor.RESET);
        for (int i = 0; i < 5; ++i) System.out.println();
        String choice = (scanner.nextLine()).toLowerCase();
        TextParser textParser = new TextParser();
        Response r1 = textParser.getCommands(choice);
        ArrayList<String> validDirections = new ArrayList<>(Arrays.asList("north", "south", "east", "west"));
        while (true) {
            try {
                if (!r1.isValid()) {
                    System.out.println(TextColor.RED + bundle.getString("invalid_input1"));
                    sleep(200);
                    r1 = getValidResponse();
                    break;
                } else if (r1.getVerb().equals("go") && Objects.equals(room.getConnectedRooms().get(r1.getLocation()), "None")) {
                    System.out.println(TextColor.RED +bundle.getString("invalid_input6"));
                    sleep(200);
                    r1 = getValidResponse();
                    break;
                } else if (r1.getVerb().equals("go") && !(validDirections.contains(r1.getLocation()))) {
                    System.out.println(TextColor.RED +bundle.getString("invalid_input5"));
                    r1 = getValidResponse();
                    break;
                }
                return r1;

            } catch (NullPointerException e) {
                System.out.println(TextColor.RED +bundle.getString("invalid_input1"));
                sleep(200);
                r1 = getValidResponse();
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

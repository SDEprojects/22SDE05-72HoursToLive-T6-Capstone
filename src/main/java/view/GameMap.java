package main.java.view;

import main.java.model.Room;
import main.java.model.RoomMovement;

import java.util.ArrayList;
import java.util.HashMap;

public class GameMap {

    public static void showMap(){
        String room = RoomMovement.currentRoom;
        HashMap<String, Room> allRooms = RoomMovement.getAllRooms();
        ArrayList<String> roomList = new ArrayList<>(allRooms.keySet());
        int index = roomList.indexOf(room);
        int size = roomList.get(index).length();
        String yourLocation = "";
        for (int i = 0; i < size-9; i++){
            yourLocation += " ";
        }
        roomList.set(index, "* YOU *"+yourLocation);


        String menu = "==========================";
        System.out.printf("\n%-33s %-13s","",roomList.get(13));
        System.out.printf("\n%s %12s %12s\n", menu, menu, menu);
        System.out.printf("%-5s %-12s %22s %30s","",roomList.get(9),roomList.get(10),roomList.get(4));
        System.out.printf("\n%s %12s %12s\n", menu, menu, menu);
        System.out.printf("%-6s %-12s %25s %24s","",roomList.get(14),roomList.get(3),roomList.get(12));
        System.out.printf("\n%s %12s %12s\n", menu, menu, menu);
        System.out.printf("%-7s %-12s %25s %23s","",roomList.get(0),roomList.get(11),roomList.get(8));
        System.out.printf("\n%s %12s %12s\n", menu, menu, menu);
        System.out.printf("%-7s %-12s %23s %25s","",roomList.get(7),roomList.get(1),roomList.get(5));
        System.out.printf("\n%s %12s %12s\n", menu, menu, menu);
        System.out.printf("%-7s %-11s %27s %25s","",roomList.get(2),roomList.get(6),roomList.get(15));
        System.out.printf("\n%s %12s %12s", menu, menu, menu);
    }
}

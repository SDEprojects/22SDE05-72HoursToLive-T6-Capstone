package main.java.view;

import main.java.controller.GameController;
import main.java.model.Room;
import main.java.model.RoomMovement;
import main.java.model.Soldier;
import org.w3c.dom.Text;

public class View {


    public static void menu() {
        String cyan = TextColor.CYAN;
        if (GameController.timer % 7 == 0 && GameController.timer > 0 && GameController.moonTrigger) {
            textStream("\n\u001B[31mAHHHHHHHH-WOOOOOOOOO!!\n\n", 140);
            System.out.println(Art.wolfHowl + TextColor.RESET);
            System.out.print("\nOh no! You look into the sky and see that tonight");
            textStream("...is a....\n\n", 290);
            System.out.println(TextColor.RED + Art.fullMoon);
            sleep(500);
            System.out.println(TextColor.YELLOW + "The werewolves are more powerful tonight! Be careful!\n" + TextColor.RESET);
            sleep(750);
            GameController.moonTrigger = false;
        }
        Room room = RoomMovement.roomSwitcher;

        sleep(1000);
        String menu = "===========================";
        Soldier player = GameController.player;
        System.out.printf("%s %12s %12s %12s %12s %12s", TextColor.CYAN + menu, menu, menu, menu, menu,menu);
        System.out.printf("%nPlayer Info%-16s Health:%-20s Armor Rating:%-14s Attack Power:%-14s Hours Remaining:%-11s Inventory:%-13s ", ":",  player.getHealth(),
                player.getArmorRating(),player.getAttackPower(),72-(GameController.timer*3)+".00",player.getInventory().toString().replace("[","").replace("]",""));
        System.out.printf("%nRoom Info%-18s Current:%-20sNorth:%-21s East:%-22s South:%-21s West:%-16s%n", ":", room.getName(), room.getConnectedRooms().get("north"),
                room.getConnectedRooms().get("east"), room.getConnectedRooms().get("south"), room.getConnectedRooms().get("west"));

//        System.out.printf("%nROOM ITEMS%-13s %-16s%n", ":", room.getItems().toString().replace("[","").replace("]",""));
        System.out.printf("%s %12s %12s %12s %12s %12s\n", menu, menu, menu, menu, menu,menu + TextColor.RESET);
    sleep(500);

    }
    private static void sleep(int timer) {
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private static String textStream(String text, int speed) {
        for (int i = 0; i < text.length(); i++) {
            System.out.printf("%c", text.charAt(i));
            sleep(speed);
        }
        return text;
    }

}

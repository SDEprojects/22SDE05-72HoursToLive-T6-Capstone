package main.java.controller;

import main.java.model.Room;
import main.java.model.RoomMovement;
import main.java.model.Soldier;
import main.java.model.Werewolf;
import main.java.view.View;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GameController {
    public static Soldier player = new Soldier();
    public static int timer = 0;


    public void userChoice() throws IOException {
        String currentRoom = RoomMovement.currentRoom;
        HashMap<String, List<Werewolf>> monsterMap = getMonsterMap(currentRoom);
        boolean werewolfCanAttack = true;




        while (player.getHealth() > 0 && timer < 24) {
            try {
                if (timer>0 && (timer%7==0 || timer%8==0)) {
                    monsterMap.values().forEach(monsters -> {
                        monsters.forEach(monster -> {
                            monster.setAttackPower(15);
                        });
                    });
                }
                else {
                    monsterMap.values().forEach(monsters -> {
                        monsters.forEach(monster -> {
                            monster.setAttackPower(10);
                        });
                    });
                }

                currentRoom = RoomMovement.currentRoom;
                if (!monsterMap.get(currentRoom).isEmpty() && werewolfCanAttack) {
                    Werewolf wolf = monsterMap.get(currentRoom).get(0);
                    wolf.attack(player);
                    sleep(300);
                    System.out.println("A werewolf is attacking you!");
                    System.out.println("Your health is now " + player.getHealth()+"!\n");
                    sleep(750);
                    werewolfCanAttack = false;
                }
                View.menu();
                Room room = RoomMovement.roomSwitcher;
                Response r1 = InputScanner.getValidResponse();
                if (player.getHealth() <= 0 || timer == 24) {
                    break;
                }
                else if (r1.getVerb().equalsIgnoreCase("use") && currentRoom.equalsIgnoreCase("Time Portal")){
                    if (r1.getNoun().equalsIgnoreCase("blood sample")){
                        player.pickup("Trophy");

                        break;
                    }
                }
                switch (r1.getVerb()) {
                    case "go":
                        werewolfCanAttack = true;
                        RoomMovement.switchRooms(r1.getLocation());
                        timer++;

                        break;
                    case "pickup":
                        if (player.getInventory().size() > 2) {
                            System.out.println("You can't carry anymore items! Try using an item in your inventory.");

                        } else if (room.getItems().contains(r1.getNoun())) {
                            player.pickup(r1.getNoun());
                            room.getItems().remove(r1.getNoun());
                            System.out.println("You picked up the " + r1.getNoun() + "! It has been added to your inventory.");
                        } else {
                            System.out.println("That item doesn't exist in this room");
                        }
                        sleep(1000);
                        werewolfCanAttack = false;
                        break;
                    case "look":
                        System.out.println(room.getDescription());
                        System.out.println("\nYou look around the room to see if you can find anything...");
                        System.out.println("Looking...");
                        sleep(500);
                        if (room.getItems().size() < 1) {
                            System.out.println("You don't see anything of interest.");
                        } else {
                            for (String key : room.getItems()) {
                                sleep(1000);
                                System.out.println("You see the " + key + "!");
                            }
                        }
                        sleep(1000);
                        werewolfCanAttack = false;
                        break;
                    case "use":
                        if (player.getInventory().contains(r1.getNoun())) {
                            player.useItems(r1.getNoun());
                            sleep(1000);
                        } else {
                            System.out.println("You don't have that item!");
                            sleep(1000);
                        }
                        werewolfCanAttack = false;
                        break;
                    case "attack":
                        Werewolf w1 = monsterMap.get(currentRoom).get(0);
                        player.attack(w1);
                        if (w1.getHealth() <= 0) {
                            monsterMap.get(currentRoom).remove(0);
                            System.out.println("You killed the werewolf!");
                            sleep(1000);
                        }
                        werewolfCanAttack = true;
                        break;

                    case "inventory":
                        if (player.getInventory().size() < 1) {
                            System.out.println("You don't have any items in your inventory.");
                        } else {
                            System.out.println("You have the following items in your inventory:");
                            for (String key : player.getInventory()) {
                                sleep(300);
                                System.out.println(key);
                            }
                        }
                        sleep(500);
                        werewolfCanAttack = false;
                        break;
                    case "help":
                        System.out.println("\nYou can go to a room by typing \"go [direction]\"\n" +

                                "You can use an item by typing \"use [item]\"\n" +
                                "You can look at the room by typing \"look\"\n" +
                                "You can check your inventory by typing \"inventory\"\n" +
                                "You can quit the game by typing \"quit\"\n");
                        System.out.println("Directions are:");
                        for (String key : room.getConnectedRooms().keySet()) {
                            System.out.println(key);
                        }
                        sleep(1200);
                        System.out.println("\nReturning to game...\n");
                        werewolfCanAttack = false;
                        sleep(1500);
                        for (int i = 0; i < 50; ++i) System.out.println();
                        break;
                    case "quit":
                        System.out.println("Quitting the game...Thanks for playing!");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("That is not a valid input! YOOOOO");
                        werewolfCanAttack = false;
                        break;
                }

            } catch (NullPointerException e) {
                System.out.println("That is not a valid input!");
                break;
            }


        }
    }

    public static HashMap<String, List<Werewolf>> getMonsterMap(String room) {
        Random random = new Random();
        HashMap<String, Room> allMap = RoomMovement.getAllRooms();
        HashMap<String, List<Werewolf>> monsterMap = new HashMap<>();
        for (String key : allMap.keySet()) {
            monsterMap.put(key, new LinkedList<Werewolf>());
            if (random.nextBoolean() && !key.equals(room)) {
                monsterMap.get(key).add(new Werewolf());
            }
        }

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

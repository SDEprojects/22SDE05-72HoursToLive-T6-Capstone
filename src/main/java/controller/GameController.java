package main.java.controller;

import main.java.model.*;
import main.java.view.GameMap;
import main.java.view.Music;
import main.java.view.View;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.*;

public class GameController {
    public static Soldier player = new Soldier();
    public static int timer = 0;
    public static boolean moonTrigger = true;
    private String currentRoom = RoomMovement.currentRoom;
    private HashMap<String, List<Werewolf>> monsterMap = getMonsterMap(currentRoom);
    private static final ResourceBundle bundle = ResourceBundle.getBundle("main.resources.strings");
    private static boolean werewolfCanAttack = true;
    public static boolean wolfKingPrompt = true;


    public void userChoice() throws IOException {
        while (player.getHealth() > 0 && timer < 24) {
            try {
                Random ran = new Random();

                String[] werewolfAttack = {bundle.getString("werewolf_attack1"), bundle.getString("werewolf_attack2"), bundle.getString("werewolf_attack3")};
                String werewolfAttackResponse = werewolfAttack[ran.nextInt(werewolfAttack.length)];

                checkFullMoon();

                currentRoom = RoomMovement.currentRoom;
                if (!monsterMap.get(currentRoom).isEmpty() && werewolfCanAttack) {
                    Werewolf wolf = monsterMap.get(currentRoom).get(0);
                    wolf.attack(player);
                    sleep(300);
                    System.out.println(wolf.getName() + " " + werewolfAttackResponse);
                    System.out.println(bundle.getString("health_status1") + player.getHealth()+"!\n");
                    sleep(750);
                    werewolfCanAttack = false;
                }
                /* Will end the loop if players health hits 0 or timer runs out. */
                if (player.getHealth() <= 0 || timer >= 24) {
                    break;
                }
                if (timer>19){
//                    System.out.println("You only have " + (72-(timer*3)) + " hours left to escape! Hurry!");
                    System.out.println(bundle.getString("hours_status1") + (72-(timer*3)) + bundle.getString("hours_status2"));
                    sleep(750);
                }

                View.menu();
                Room room = RoomMovement.roomSwitcher;
                Response r1 = InputScanner.getValidResponse();
                for (int i = 0; i < 50; ++i) System.out.println();

                if (r1.getVerb().equalsIgnoreCase("use") && currentRoom.equalsIgnoreCase("Time Portal")){
                    if (r1.getNoun().equalsIgnoreCase("blood sample")){
                        player.pickup("Trophy");
                        break;
                    }
                }
                switch (r1.getVerb()) {
                    case "go":
                        moonTrigger = true;
                        werewolfCanAttack = true;
                        RoomMovement.switchRooms(r1.getLocation());
                        room = RoomMovement.roomSwitcher;
//                        System.out.println("\nYou have entered the " + room.getName() + ".");
                        System.out.println(bundle.getString("go1")+ room.getName() + ".");
                        sleep(750);
                        System.out.println(room.getDescription() + "\n");
                        sleep(750);
                        timer++;
                        if (room.getName().equalsIgnoreCase("Throne Room") && wolfKingPrompt){
                            System.out.println("You see The Werewolf King sitting on the throne. He looks at you with a menacing glare as he moves with lightning speed lunging toward you!\n");
                            sleep(2000);
                            wolfKingPrompt = false;
                        }

                        break;
                    case "pickup":
                        if (player.getInventory().size() > 2) {
//                            System.out.println("You can't carry anymore items! Try using an item in your inventory.");
                            System.out.println(bundle.getString("pickup1"));

                        } else if (room.getItems().contains(r1.getNoun())) {
                            player.pickup(r1.getNoun());
                            room.getItems().remove(r1.getNoun());
//                            System.out.println("You picked up the " + r1.getNoun() + "! It has been added to your inventory.");
                            System.out.println(bundle.getString("pickup2") + r1.getNoun() + bundle.getString("pickup3"));
                        } else {
//                            System.out.println("That item doesn't exist in this room");
                            System.out.println(bundle.getString("pickup4"));
                        }
                        sleep(500);
                        werewolfCanAttack = true;
                        break;
                    case "look":
                        System.out.println("\n"+room.getDescription());
                        sleep(500);
//                        System.out.println("\nYou look around the room to see if you can find anything...");
                        System.out.println(bundle.getString("look1"));
                        sleep(500);
//                        System.out.println("\nLooking...\n");
                        System.out.println(bundle.getString("look2"));
                        sleep(500);
                        if (room.getItems().size() < 1) {
//                            System.out.println("You don't see anything of interest.");
                            System.out.println(bundle.getString("look3"));
                        } else {
                            for (String key : room.getItems()) {
                                sleep(1000);
//                                System.out.println("You see the " + key + "!");
                                System.out.println(bundle.getString("look4") + key + "!");
                            }
                            System.out.println("\n");
                        }
                        sleep(1000);
                        werewolfCanAttack = true;
                        break;
                    case "use":
                        if (player.getInventory().contains(r1.getNoun())) {
                            player.useItems(r1.getNoun());
                            sleep(1000);
                        } else {
//                            System.out.println("You don't have that item!");
                            System.out.println(bundle.getString("use1"));
                            sleep(1000);
                        }
                        werewolfCanAttack = false;
                        break;
                    case "attack":
                        if (monsterMap.get(currentRoom).isEmpty()) {
//                            System.out.println("This is no werewolf to attack!\n");
                            System.out.println(bundle.getString("attack1"));
                            break;
                        }
                        Werewolf w1 = monsterMap.get(currentRoom).get(0);
                        player.attack(w1);
                        if (w1.getHealth() <= 0) {
                            monsterMap.get(currentRoom).remove(0);

                            if (w1.getInventory().size() >0){
                            for (String item : w1.getInventory()){
//                                System.out.println("The Werewolf King is dead! A sample of his blood spills on the floor!");
                                String[] werewolfKingDead = {bundle.getString("werewolfKing_dead1"), bundle.getString("werewolfKing_dead2"), bundle.getString("werewolfKing_dead3")};
                                String werewolfKing_deadResponse = werewolfKingDead[ran.nextInt(werewolfKingDead.length)];
                                System.out.println(werewolfKing_deadResponse);
                                room.getItems().add(item); }
                            }
                            else {
//                                System.out.println("You killed the werewolf!\n");
                                String[] werewolfDead = {bundle.getString("werewolf_dead1"), bundle.getString("werewolf_dead2"), bundle.getString("werewolf_dead3")};
                                String werewolf_deadResponse = werewolfDead[ran.nextInt(werewolfDead.length)];
                                System.out.println(werewolf_deadResponse);

                            }
                            sleep(1000);
                        }
                        werewolfCanAttack = true;
                        break;

                    case "inventory":
                        if (player.getInventory().size() < 1) {
//                            System.out.println("You don't have any items in your inventory.");
                            System.out.println(bundle.getString("inventory_0"));
                        } else {
//                            System.out.println("You have the following items in your inventory:");
                            System.out.println(bundle.getString("inventory_items"));
                            for (String key : player.getInventory()) {
                                sleep(300);
                                System.out.println(key);
                            }
                        }
                        sleep(500);
                        werewolfCanAttack = false;
                        break;
                    case "help":
                        werewolfCanAttack = false;
                        System.out.println(bundle.getString("help_menu"));
                        Scanner helpScanner = new Scanner(System.in);
                        if (helpScanner.hasNextLine()) {
                            for (int i = 0; i < 50; ++i) System.out.println();
                            break;
                        }
                    case "map":
                        werewolfCanAttack = false;
                        System.out.println("You open the map and see the following rooms:");
                        sleep(1500);
                        GameMap.showMap();
                        System.out.println("\n\nPress enter to return to the game...");
                        Scanner mapScanner = new Scanner(System.in);
                        if (mapScanner.hasNextLine()) {
                            for (int i = 0; i < 50; ++i) System.out.println();
                            break;
                        }
                    case "quit":
//                        System.out.println("Quitting the game...Thanks for playing!");
                        System.out.println(bundle.getString("quit_menu1"));
                        System.exit(0);
                        break;
                    case "music":
                        Music.playerSelectMusic();
                        break;

                    default:
//                        System.out.println("That is not a valid input!");
                        System.out.println(bundle.getString("invalid_input1"));
                        werewolfCanAttack = false;
                        break;
                }

            } catch (NullPointerException e) {
                break;
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }


        }
    }

    public static HashMap<String, List<Werewolf>> getMonsterMap(String room) {
        Random random = new Random();
        HashMap<String, Room> allMap = RoomMovement.getAllRooms();
        HashMap<String, List<Werewolf>> monsterMap = new HashMap<>();
        for (String key : allMap.keySet()) {
            monsterMap.put(key, new LinkedList<Werewolf>());
            if (key.equals("Throne Room")) {
                monsterMap.get(key).add(new WerewolfKing());
            }
            else if (random.nextBoolean() && !key.equals(room)) {
                monsterMap.get(key).add(new Werewolf());
            }
        }
        return monsterMap;
    }
    public void checkFullMoon(){
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
    }

    public void sleep(int timer) {
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

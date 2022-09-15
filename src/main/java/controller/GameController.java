package main.java.controller;

import main.java.model.*;
import main.java.view.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.*;

public class GameController {
    //variables and object instances
    public static Soldier player = new Soldier();
    public static int timer = 0;
    public static boolean moonTrigger = true;
    private String currentRoom = RoomMovement.currentRoom;
    private HashMap<String, List<Werewolf>> monsterMap = getMonsterMap(currentRoom);
    private static final ResourceBundle bundle = ResourceBundle.getBundle("main.resources.strings");
    private static boolean werewolfCanAttack = true;
    public static boolean wolfKingPrompt = true;


    /**
     *switch cases implemented to go through the user commands and go, use, move, look, or look at menu.
     * validation to check if wolf attack, and full moon is possible.
     * With the logic for music class. All the user needs to input is music, and it will implement the opposite of on/off.
     * @throws IOException
     */
    public void userChoice() throws IOException {
        while (player.getHealth() > 0 && timer < 24) {
            try {
                Random ran = new Random();

                String[] werewolfAttack = {TextColor.RED + bundle.getString("werewolf_attack1"), bundle.getString("werewolf_attack2"), bundle.getString("werewolf_attack3") + TextColor.RESET};
                String werewolfAttackResponse = werewolfAttack[ran.nextInt(werewolfAttack.length)];

                checkFullMoon();

                currentRoom = RoomMovement.currentRoom;
                if (!monsterMap.get(currentRoom).isEmpty() && werewolfCanAttack) {
                    Werewolf wolf = monsterMap.get(currentRoom).get(0);
                    wolf.attack(player);
                    sleep(300);
                    System.out.println(TextColor.RED + wolf.getName() + " " + werewolfAttackResponse + TextColor.RESET);
                    System.out.println(TextColor.YELLOW + bundle.getString("health_status1") + player.getHealth() + "!\n" + TextColor.RESET);
                    sleep(750);
                    werewolfCanAttack = false;
                }
                /* Will end the loop if players health hits 0 or timer runs out. */
                if (player.getHealth() <= 0 || timer >= 24) {
                    break;
                }
                if (timer > 19) {
                    System.out.println(TextColor.RED + bundle.getString("hours_status1") + (72 - (timer * 3)) + " " + bundle.getString("hours_status2") + TextColor.RESET);
                    sleep(750);
                }
                //call the menu from view class
                View.menu();
                Room room = RoomMovement.roomSwitcher;
                Response r1 = InputScanner.getValidResponse();
                for (int i = 0; i < 70; ++i) System.out.println();// used to clear screen

                if (r1.getVerb().equalsIgnoreCase("use") && currentRoom.equalsIgnoreCase("Time Portal") && player.getInventory().contains(r1.getNoun())) {
                    if (r1.getNoun().equalsIgnoreCase("blood sample")) {
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
                        System.out.println(TextColor.BLUE + bundle.getString("go1") + room.getName() + "." + TextColor.RESET);
                        sleep(750);
                        System.out.println(TextColor.BLUE + room.getDescription() + "\n" + TextColor.RESET);
                        sleep(750);
                        timer++;
                        if (room.getName().equalsIgnoreCase("Throne Room") && wolfKingPrompt) {
                            System.out.println(TextColor.RED + bundle.getString("werewolfKing_attack1") + TextColor.RESET);
                            sleep(2000);
                            wolfKingPrompt = false;
                        }

                        break;
                    case "pickup":
                        if (player.getInventory().size() > 2) {
                            werewolfCanAttack = false;
                            System.out.println(TextColor.RED + bundle.getString("pickup1") + TextColor.RESET);

                        } else if (room.getItems().contains(r1.getNoun())) {
                            player.pickup(r1.getNoun());
                            room.getItems().remove(r1.getNoun());
                            werewolfCanAttack = true;
                            System.out.println(TextColor.GREEN + bundle.getString("pickup2") + r1.getNoun() + bundle.getString("pickup3") + TextColor.RESET);
                        } else {
                            werewolfCanAttack = false;
                            System.out.println(TextColor.RED + bundle.getString("pickup4") + TextColor.RESET);
                        }
                        sleep(500);
                        break;
                    case "look":
                        System.out.println("\n" + TextColor.BLUE + room.getDescription());
                        sleep(500);
                        System.out.println(TextColor.WHITE + bundle.getString("look1"));
                        sleep(500);
                        System.out.println(TextColor.WHITE + bundle.getString("look2"));
                        sleep(500);
                        if (room.getItems().size() < 1) {
                            System.out.println(TextColor.WHITE + bundle.getString("look3") + TextColor.RESET);
                        } else {
                            for (String key : room.getItems()) {
                                sleep(1000);
                                System.out.println(TextColor.WHITE + bundle.getString("look4") + key + "!");
                            }
                            System.out.println("\n" + TextColor.RESET);
                        }
                        sleep(1000);
                        werewolfCanAttack = true;
                        break;
                    case "use":
                        if (player.getInventory().contains(r1.getNoun())) {
                            player.useItems(r1.getNoun());
                            sleep(1000);
                        } else {
                            System.out.println(TextColor.RED + bundle.getString("use1") + TextColor.RESET);
                            sleep(1000);
                        }
                        werewolfCanAttack = false;
                        break;
                    case "attack":
                        if (monsterMap.get(currentRoom).isEmpty()) {
                            System.out.println(bundle.getString("attack1"));
                            break;
                        }
                        Werewolf w1 = monsterMap.get(currentRoom).get(0);
                        player.attack(w1);
                        if (w1.getHealth() <= 0) {
                            monsterMap.get(currentRoom).remove(0);

                            if (w1.getInventory().size() > 0) {
                                for (String item : w1.getInventory()) {
                                    String[] werewolfKingDead = {bundle.getString("werewolfKing_dead1"), bundle.getString("werewolfKing_dead2"), bundle.getString("werewolfKing_dead3")};
                                    String werewolfKing_deadResponse = werewolfKingDead[ran.nextInt(werewolfKingDead.length)];
                                    System.out.println(werewolfKing_deadResponse);
                                    room.getItems().add(item);
                                }
                            } else {
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
                            System.out.println(bundle.getString("inventory_0"));
                        } else {
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
                        System.out.println(TextColor.RED + Story.infoMenu + "\n" + Story.infoBanner + Story.infoMenu);
                        System.out.println(TextColor.WHITE + bundle.getString("help_menu"));
                        Scanner helpScanner = new Scanner(System.in);
                        if (helpScanner.hasNextLine()) {
                            for (int i = 0; i < 70; ++i) System.out.println();
                            break;
                        }
                    case "map":
                        werewolfCanAttack = false;
                        GameMap.showMap();
                        System.out.println(TextColor.YELLOW + "\n\n" + bundle.getString("return_to_game"));
                        Scanner mapScanner = new Scanner(System.in);
                        if (mapScanner.hasNextLine()) {
                            for (int i = 0; i < 70; ++i) System.out.println();
                            break;
                        }
                    case "quit":
//                        System.out.println("Quitting the game...Thanks for playing!");
                        System.out.println(TextColor.WHITE + bundle.getString("quit_menu1"));
                        System.exit(0);
                        break;
                    case "music":
                        Music.playerSelectMusic();
                        break;

                    default:
//                        System.out.println("That is not a valid input!");
                        System.out.println(TextColor.RED + bundle.getString("invalid_input1"));
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

    /**
     *
     * @param room
     * @return
     */
    public static HashMap<String, List<Werewolf>> getMonsterMap(String room) {
        Random random = new Random();
        HashMap<String, Room> allMap = RoomMovement.getAllRooms();
        HashMap<String, List<Werewolf>> monsterMap = new HashMap<>();
        for (String key : allMap.keySet()) {
            monsterMap.put(key, new LinkedList<Werewolf>());
            if (key.equals("Throne Room")) {
                monsterMap.get(key).add(new WerewolfKing());
            } else if (random.nextBoolean() && !key.equals(room)) {
                monsterMap.get(key).add(new Werewolf());
            }
        }
        return monsterMap;
    }

    /**
     * when the timer is divisible by 7 or 8 then all monsters get attack power to 15
     */
    public void checkFullMoon() {
        if (timer > 0 && (timer % 7 == 0 || timer % 8 == 0)) {
            monsterMap.values().forEach(monsters -> {
                monsters.forEach(monster -> {
                    monster.setAttackPower(15);
                });
            });
        } else {
            monsterMap.values().forEach(monsters -> {
                monsters.forEach(monster -> {
                    monster.setAttackPower(10);
                });
            });
        }
    }
    /**
     * method called throughout the program to freeze the console for a certain amount of time.
     * @param timer - use to slow the program down and put output on a time based schedule
     */
    public void sleep(int timer) {
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

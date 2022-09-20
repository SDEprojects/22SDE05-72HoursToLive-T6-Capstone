package main.java.GUI;

import main.java.controller.GameController;
import main.java.controller.Response;
import main.java.model.*;
import main.java.view.*;

import java.io.IOException;
import java.util.*;
import java.util.List;

public class Controller {

    //variables and object instances
    public static Soldier player = new Soldier();
    public static int timer = 0;
    public static boolean moonTrigger = true;
    private String currentRoom = RoomMovement.currentRoom;
    private HashMap<String, List<Werewolf>> monsterMap = getMonsterMap(currentRoom);
    private static final ResourceBundle bundle = ResourceBundle.getBundle("main.resources.strings");
    private static boolean werewolfCanAttack = true;
    private static boolean wolfKingPrompt = true;
    private Random ran = new Random();


    /**
     * Create an emptyInventory Array list to set the players inventory to empty at the start of the game
     * Set the player stats from the game controller class. The player is a character.
     * Also implement ending game qualifiers inside a while loop.If no ending game qualifiers then call
     * game controller for user input
     *
     * @throws IOException
     */
    public void startGame() throws IOException {
        List<String> emptyInventory = new ArrayList<>();
        Controller.wolfKingPrompt = true;
        Controller.player.setHealth(100);
        Controller.player.setAttackPower(10);
        Controller.player.setArmorRating(10);
        Controller.timer = 0;
        Controller.player.setInventory(emptyInventory);

        RoomMovement movement = new RoomMovement();
        movement.firstRoom();
    }

    /**
     * output game over screens and give the user a chance to play another game. or exit
     * the game which is exiting the entire program.
     * End game method to
     */
    public void endGame() {
        System.out.println(TextColor.WHITE + bundle.getString("game_over1"));
        System.out.println(TextColor.WHITE + bundle.getString("game_over2") + TextColor.RESET);
//        Scanner scanner = new Scanner(System.in);
//This will be replaced by a call to the proper ending scree (win, die, timeout) followed by play again
//        while (true) {
//            String input = scanner.nextLine();
//            if (input.equalsIgnoreCase("yes")) {
//                try {
//                    for (int i = 0; i < 70; ++i) System.out.println();
//                    Client.repeatGameGUI();
//                    break;
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (UnsupportedAudioFileException | LineUnavailableException | FontFormatException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//            else if (input.equalsIgnoreCase("no")) {
//                System.out.println(TextColor.WHITE+"\nGoodbye!");
//                System.exit(0);
//                break;
//            }
//            else {
//                System.out.println(TextColor.RED+bundle.getString("replay")+TextColor.RESET);
//            }
//        }
    }

    /**
     * serves as the GUI facing component in place of the inputScanner and textParser
     * to pass commands to the userChoice method
     */
    public void handleUserClick(Response buttonResponse) throws IOException {
        if (Controller.player.getHealth() <= 0) {
            System.out.println(TextColor.RED + bundle.getString("player_dead1") + TextColor.RESET);
// todo Add failure screen with 'you died'
            endGame();
        }
        else if (Controller.timer == 24) {
            System.out.println(TextColor.RED + bundle.getString("time_out1"));
            System.out.println(TextColor.RED + bundle.getString("time_out2") + TextColor.RESET);
// todo Add failure screen with 'timed out'
            endGame();
        }
        else {
            userChoice(buttonResponse);

            String[] werewolfAttack = {TextColor.RED + bundle.getString("werewolf_attack1"), bundle.getString("werewolf_attack2"), bundle.getString("werewolf_attack3") + TextColor.RESET};
            String werewolfAttackResponse = werewolfAttack[ran.nextInt(werewolfAttack.length)];
            checkFullMoon();
            if (GameController.player.getInventory().contains("Trophy")) {
// todo Add success screen
                System.out.println(TextColor.GREEN + bundle.getString("trophy_response1"));
                System.out.println(TextColor.GREEN + bundle.getString("trophy_response2") + TextColor.RESET);
                endGame();
            }
            currentRoom = RoomMovement.currentRoom;
            if (currentRoom.equalsIgnoreCase("Throne Room") && wolfKingPrompt) {
// todo Replace with GUI output
                System.out.println(TextColor.RED + bundle.getString("werewolfKing_attack1") + TextColor.RESET);
                wolfKingPrompt = false;
            }
            if (!monsterMap.get(currentRoom).isEmpty() && werewolfCanAttack) {
                Werewolf wolf = monsterMap.get(currentRoom).get(0);
                wolf.attack(player);
// todo Replace with GUI output i.e. make werewolf visible out decrease and health panel decrease
                System.out.println(TextColor.RED + wolf.getName() + " " + werewolfAttackResponse + TextColor.RESET);
                System.out.println(TextColor.YELLOW + bundle.getString("health_status1") + player.getHealth() + "!\n" + TextColor.RESET);
                werewolfCanAttack = false;
            }
            if (player.getHealth() <= 0 || timer >= 24) {
// todo Needs to call failure screen with 'you died output'
                endGame();
            }
            if (timer > 19) {
// todo Replace with GUI output
                System.out.println(TextColor.RED + bundle.getString("hours_status1") + (72 - (timer * 3)) + " " + bundle.getString("hours_status2") + TextColor.RESET);
            }
            //call the menu from view class
// todo needs to be replaced by GUI response not View.menu response
            View.menu();
        }
    }

    /**
     * switch cases implemented to go through the user commands and go, use, move, look, or look at menu.
     * validation to check if wolf attack, and full moon is possible.
     * With the logic for music class. All the user needs to input is music, and it will implement the opposite of on/off.
     *
     * @throws IOException
     */
    private void userChoice(Response buttonResponse) throws IOException {
        while (player.getHealth() > 0 && timer < 24) {
            try {
                Room room = RoomMovement.roomSwitcher;
                Response r1 = buttonResponse;
                if (r1.getVerb().equalsIgnoreCase("use") && currentRoom.equalsIgnoreCase("Time Portal") && player.getInventory().contains(r1.getNoun())) {
                    if (r1.getNoun().equalsIgnoreCase("blood sample")) {
                        player.pickup("Trophy");
                        break;
                    }
                }
                switch (r1.getVerb()) {
                    case "go":
                        timer++;
                        if (timer == 24) {
// todo need to call failure screen with 'time ran out'
                            endGame();
                        } else {
                            moonTrigger = true;
                            werewolfCanAttack = true;
                            RoomMovement.switchRooms(r1.getLocation());
                            room = RoomMovement.roomSwitcher;
// todo Replace with GUI output change picture, description output, locations panel, and make applicable items visible in RoomMovement class
                            System.out.println(TextColor.BLUE + bundle.getString("go1") + room.getName() + "." + TextColor.RESET);
                            System.out.println(TextColor.BLUE + room.getDescription() + "\n" + TextColor.RESET);
                            break;
                        }
                    case "pickup":
                        if (player.getInventory().size() > 2) {
                            werewolfCanAttack = false;
// todo Replace with GUI output
                            System.out.println(TextColor.RED + bundle.getString("pickup1") + TextColor.RESET);

                        } else if (room.getItems().contains(r1.getNoun())) {
                            player.pickup(r1.getNoun());
                            room.getItems().remove(r1.getNoun());
                            werewolfCanAttack = true;
// todo Replace with GUI output, add item as a button in inventory panel
                            System.out.println(TextColor.GREEN + bundle.getString("pickup2") + r1.getNoun() + bundle.getString("pickup3") + TextColor.RESET);
                        }
                        break;
                    case "use":
                        player.useItems(r1.getNoun());
// todo update the armor, attack
                        werewolfCanAttack = false;
                        break;
                    case "attack":
                        Werewolf w1 = monsterMap.get(currentRoom).get(0);
                        player.attack(w1);
                        if (w1.getHealth() <= 0) {
                            monsterMap.get(currentRoom).remove(0);
// todo Remove werewolf from location picture
                            if (w1.getInventory().size() > 0) {
                                for (String item : w1.getInventory()) {
                                    String[] werewolfKingDead = {bundle.getString("werewolfKing_dead1"), bundle.getString("werewolfKing_dead2"), bundle.getString("werewolfKing_dead3")};
                                    String werewolfKing_deadResponse = werewolfKingDead[ran.nextInt(werewolfKingDead.length)];
// todo Replace with a GUI output
                                    System.out.println(werewolfKing_deadResponse);
                                    room.getItems().add(item);
                                }
                            } else {
                                String[] werewolfDead = {bundle.getString("werewolf_dead1"), bundle.getString("werewolf_dead2"), bundle.getString("werewolf_dead3")};
                                String werewolf_deadResponse = werewolfDead[ran.nextInt(werewolfDead.length)];
// todo Replace with a GUI output
                                System.out.println(werewolf_deadResponse);
                            }
                        }
                        werewolfCanAttack = true;
                        break;
                    case "inventory":
                        if (player.getInventory().size() < 1) {
                            System.out.println(bundle.getString("inventory_0"));
                        } else {
                            System.out.println(bundle.getString("inventory_items"));
                            for (String key : player.getInventory()) {
                                System.out.println(key);
                            }
                        }
                        werewolfCanAttack = false;
                        break;
// Shouldn't need default in switch case if response is not user defined
//                    default:
////                        System.out.println("That is not a valid input!");
//                        System.out.println(TextColor.RED + bundle.getString("invalid_input1"));
//                        werewolfCanAttack = false;
//                        break;
                }
            } catch (NullPointerException e) {
                break;
            }
        }
    }

    /**
     * @return
     * @param: roomd
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
}

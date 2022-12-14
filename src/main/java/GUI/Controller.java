package main.java.GUI;

import main.java.controller.GameController;
import main.java.controller.Response;
import main.java.model.*;
import main.java.view.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Controller {

    //variables and object instances
    public static Soldier player = new Soldier();
    public static int timer = 0;
    private static String currentRoom = RoomMovement.currentRoom;
    final HashMap<String, List<Werewolf>> monsterMap = getMonsterMap(currentRoom);
    private static final ResourceBundle bundle = ResourceBundle.getBundle("main.resources.strings");
    private static boolean werewolfCanAttack = true;
    private static boolean wolfKingPrompt = true;
    private static Random ran = new Random();

    /**
     * Create an emptyInventory Array list to set the players inventory to empty at the start of the game
     * Set the player stats from the game controller class. The player is a character.
     * Also implement ending game qualifiers inside a while loop.If no ending game qualifiers then call
     * game controller for user input
     *
     * @throws IOException
     */
    public static RoomMovement startGame() throws IOException {
        List<String> emptyInventory = new ArrayList<>();
        Controller.wolfKingPrompt = true;
        Controller.player.setHealth(100);
        Controller.player.setAttackPower(10);
        Controller.player.setArmorRating(10);
        Controller.timer = 0;
        Controller.player.setInventory(emptyInventory);

        RoomMovement movement = new RoomMovement();
        movement.firstRoom();

        return movement;
    }

    /**
     * serves as the GUI facing component in place of the inputScanner and textParser
     * to pass commands to the userChoice method
     */
    public void handleUserClick(Response buttonResponse, Room room, Controller gameController) throws IOException {
            userChoice(buttonResponse, room, gameController);
    }

    /**
     * switch cases implemented to go through the user commands and go, use, move, look, or look at menu.
     * validation to check if wolf attack, and full moon is possible.
     * With the logic for music class. All the user needs to input is music, and it will implement the opposite of on/off.
     *
     * @throws IOException
     */
    private void userChoice(Response buttonResponse, Room room, Controller gameController) throws IOException {
            try {
                String currentRoom = room.getName();
                if (buttonResponse.getVerb().equalsIgnoreCase("use") && currentRoom.equalsIgnoreCase("Time Portal") && player.getInventory().contains(buttonResponse.getNoun())) {
                    if (buttonResponse.getNoun().equalsIgnoreCase("blood sample")) {
                        new EndingMenu("win");
                        Music.playStartAudio("win");
                    }
                }
                switch (buttonResponse.getVerb()) {
                    case "go":
                        room = implementMove(buttonResponse, room);

                        UpdatePanel.updateLocation(room);
                        UpdatePanel.updateCompass(room, gameController, monsterMap);
                        UpdatePanel.updateHealthAndTimePanel(player.getHealth(), timer);
                        UpdatePanel.updateImagePanel(room, monsterMap, gameController, player.getInventory());
                        UpdatePanel.updateDescriptionPanel(room);
                        UpdatePanel.updateInventory(room, player.getInventory(), gameController);
                        checkAttack(room, gameController);
                        break;
                    case "pickup":
                        if (player.getInventory().size() > 2) {
                            werewolfCanAttack = false;
                            UpdatePanel.updateDescriptionPanelText(bundle.getString("pickup1"));
                            UpdatePanel.updateImagePanel(room, monsterMap, gameController, player.getInventory());


                        } else if (room.getItems().contains(buttonResponse.getNoun())) {
                            List<String> newInventory = addToInventory(buttonResponse, room);
                            UpdatePanel.updateImagePanel(room, monsterMap, gameController, newInventory);
                            UpdatePanel.updateDescriptionPanelText(bundle.getString("pickup2") + buttonResponse.getNoun() + bundle.getString("pickup3"));
                            UpdatePanel.updateInventory(room, newInventory, gameController);
                            UpdatePanel.updateCompass(room, gameController, monsterMap);
                        }
                        checkAttack(room, gameController);
                        break;
                    case "use":
                        player.useItems(buttonResponse.getNoun());
                        UpdatePanel.updateInventory(room,player.getInventory(), gameController);
                        UpdatePanel.updateHealthAndTimePanel(player.getHealth(), timer);
                        werewolfCanAttack = false;
                        break;
                    case "attack":
                        Werewolf w1 = monsterMap.get(currentRoom).get(0);
                        player.attack(w1);
                        if (w1.getHealth() <= 0) {
                            monsterMap.get(currentRoom).remove(0);
                            UpdatePanel.updateImagePanel(room, monsterMap, gameController, player.getInventory());
                            UpdatePanel.updateCompass(room, gameController, monsterMap);
                            if (w1.getInventory().size() > 0) {
                                for (String item : w1.getInventory()) {
                                    String[] werewolfKingDead = {bundle.getString("werewolfKing_dead1"), bundle.getString("werewolfKing_dead2"), bundle.getString("werewolfKing_dead3")};
                                    String werewolfKing_deadResponse = werewolfKingDead[ran.nextInt(werewolfKingDead.length)];
                                    UpdatePanel.updateDescriptionPanelText(werewolfKing_deadResponse);
                                    room.getItems().add(item);
                                }
                            } else {
                                String[] werewolfDead = {bundle.getString("werewolf_dead1"), bundle.getString("werewolf_dead2"), bundle.getString("werewolf_dead3")};
                                String werewolf_deadResponse = werewolfDead[ran.nextInt(werewolfDead.length)];
                                UpdatePanel.updateDescriptionPanelText(werewolf_deadResponse);
                            }
                        }
                        werewolfCanAttack = true;

                        checkAttack(room, gameController);
                        break;
                }
            } catch (NullPointerException ignored) {
            } catch (UnsupportedAudioFileException | LineUnavailableException | FontFormatException e) {
                throw new RuntimeException(e);
            }
        }

    protected Room implementMove(Response buttonResponse, Room room) throws IOException, FontFormatException, UnsupportedAudioFileException, LineUnavailableException {
        timer++;
        if (timer == 24) {
            new EndingMenu("time out");
            Music.playStartAudio("time-up");
        } else {

            //check for the full moon
            checkFullMoon();
            if (Controller.timer % 7 == 0) {
                FullMoon.fullMoon();
                Music.playStartAudio("wolf-howl");
            }

            werewolfCanAttack = true;
            Music.playStartAudio("open-door");
            RoomMovement.switchRooms(buttonResponse.getLocation());
            room = RoomMovement.roomSwitcher;
        }
        return room;
    }

    protected List<String> addToInventory (Response buttonResponse, Room room){
        player.pickup(buttonResponse.getNoun());
        room.getItems().remove(buttonResponse.getNoun());
        werewolfCanAttack = true;
        List<String> inventory = player.getInventory();

        return inventory;
    }
    /**
     * Checks if a werewolf can attack and checks health and time after attack
     */
    private void checkAttack(Room room, Controller gameController) throws UnsupportedAudioFileException, LineUnavailableException, IOException, FontFormatException {
        String currentRoom = room.getName();
        String[] werewolfAttack = {bundle.getString("werewolf_attack1"),bundle.getString("werewolf_attack2"),bundle.getString("werewolf_attack3")};
        String werewolfAttackResponse = werewolfAttack[ran.nextInt(werewolfAttack.length)];
        checkFullMoon();
        if (currentRoom.equalsIgnoreCase("Throne Room") && wolfKingPrompt) {
            UpdatePanel.updateDescriptionPanelText(bundle.getString("werewolfKing_attack1"));
            wolfKingPrompt = false;
        }
        //Gamedescription output call for wolf attack
        if (!monsterMap.get(currentRoom).isEmpty() && werewolfCanAttack) {
            Werewolf wolf = monsterMap.get(currentRoom).get(0);
            wolf.attack(player);
            Music.playStartAudio("wolf-attack");
            UpdatePanel.updateHealthAndTimePanel(player.getHealth(), timer);
            UpdatePanel.appendDescriptionPanelText("\n" + wolf.getName() + " " + werewolfAttackResponse + '\n' + bundle.getString("health_status1") + player.getHealth() );
            UpdatePanel.updateInventory(room, player.getInventory(), gameController);
            werewolfCanAttack = false;
        }
        if (player.getHealth() <= 0) {
            new EndingMenu("wolf_win");
            Music.playStartAudio("man-down");
        }
        if (timer > 19) {
            UpdatePanel.appendDescriptionPanelText(bundle.getString("hours_status1") + (72 - (timer * 3)) + " " + bundle.getString("hours_status2"));
        }
        //call the menu from view class
    }

    /**
     * returns hashmap of the monster map
     */
    public static HashMap<String, List<Werewolf>> getMonsterMap(String room) {
        HashMap<String, Room> allMap = RoomMovement.getAllRooms();
        HashMap<String, List<Werewolf>> monsterMap = new HashMap<>();
        for (String key : allMap.keySet()) {
            monsterMap.put(key, new LinkedList<Werewolf>());
            if (key.equals("Throne Room")) {
                monsterMap.get(key).add(new WerewolfKing());
            } else if (ran.nextBoolean() && !key.equals(room)) {
                monsterMap.get(key).add(new Werewolf());
            }
        }
        return monsterMap;
    }

    /**
     * when the timer is divisible by 7 or 8 then all monsters get attack power to 15
     */
    public void checkFullMoon() {
        if (timer > 0 && timer % 7 == 0) {
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

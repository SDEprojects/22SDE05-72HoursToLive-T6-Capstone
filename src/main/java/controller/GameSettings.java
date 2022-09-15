package main.java.controller;

import main.java.client.Client;
import main.java.model.RoomMovement;
import main.java.view.Story;
import main.java.view.TextColor;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class GameSettings {
    //variables
    public static String roomName;
    private static final ResourceBundle bundle = ResourceBundle.getBundle("main.resources.strings");

    /**
     * Create a gameStory object that will read in the titlescreen, difficulty selectory, and intro text.
     * @throws UnsupportedAudioFileException
     * @throws LineUnavailableException
     * @throws IOException
     */
    public void readGameStory() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Story gameStory = new Story();
        gameStory.titleScreen();
        gameStory.selectDifficulty();
        gameStory.introText();
    }

    /**
     * Create an emptyInventory Array list to set the players inventory to empty at the start of the game
     *Set the player stats from the game controller class. The player is a character.
     * Also implement ending game qualifiers inside a while loop.If no ending game qualifiers then call
     * game controller for user input
     * @throws IOException
     */
    public void startGame() throws IOException {
        List<String> emptyInventory = new ArrayList<>();
        GameController.wolfKingPrompt = true;
        GameController.player.setHealth(100);
        GameController.player.setAttackPower(10);
        GameController.player.setArmorRating(10);
        GameController.timer = 0;
        GameController.player.setInventory(emptyInventory);

        RoomMovement movement = new RoomMovement();
        movement.firstRoom();
        GameController gameController = new GameController();
        while (true) {
            if (GameController.player.getHealth() <= 0) {
                System.out.println(TextColor.RED+bundle.getString("player_dead1")+TextColor.RESET);
                sleep(1000);
                break;
            }
            else if (GameController.timer== 24){
                System.out.println(TextColor.RED+bundle.getString("time_out1"));
                sleep(1000);
                System.out.println(TextColor.RED+bundle.getString("time_out2")+TextColor.RESET);
                break;
            }
            else if (GameController.player.getInventory().contains("Trophy")) {
                sleep(500);
                System.out.println(TextColor.GREEN+bundle.getString("trophy_response1"));
                sleep(1500);
                System.out.println(TextColor.GREEN+bundle.getString("trophy_response2")+TextColor.RESET);
                sleep(1500);
                break;
            }
            else {
                gameController.userChoice();
            }
        }
    }

    /**
     * output game over screens and give the user a chance to play another game. or exit
     * the game which is exiting the entire program.
     * End game method to
     */
    public void endGame() {
        System.out.println(TextColor.WHITE+bundle.getString("game_over1"));
        sleep(1000);
        System.out.println(TextColor.WHITE+bundle.getString("game_over2") +TextColor.RESET);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("yes")) {
                try {
                    for (int i = 0; i < 70; ++i) System.out.println();
                    Client.repeatGame();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (input.equalsIgnoreCase("no")) {
                System.out.println(TextColor.WHITE+"\nGoodbye!");
                System.exit(0);
                break;
            }
            else {
                System.out.println(TextColor.RED+bundle.getString("replay")+TextColor.RESET);
            }
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

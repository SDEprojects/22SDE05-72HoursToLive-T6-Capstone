package main.java.controller;

import main.java.client.Client;
import main.java.model.RoomMovement;
import main.java.view.Story;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameSettings {
    public static String roomName;

    public void readGameStory() {
        Story gameStory = new Story();
        gameStory.titleScreen();
        gameStory.selectDifficulty();
        gameStory.introText();
    }
    public void startGame() throws IOException {
        List<String> emptyInventory = new ArrayList<>();
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
                System.out.println("You have been eaten by the werewolves!");
                sleep(1000);
                break;
            }
            else if (GameController.timer== 24){
                System.out.println("You have run out of time!");
                sleep(1000);
                System.out.println("The whole castle begins to shake as the Time Portal closes. You are trapped in the past forever!");
                break;
            }
            else if (GameController.player.getInventory().contains("Trophy")) {
                sleep(500);
                System.out.println("The room begins to shake as you step through the time portal!");
                sleep(2000);
                System.out.println("You have won the game!");
                break;
            }
            else {
                gameController.userChoice();
            }
        }
    }
    public void endGame() {
        System.out.println("\nThanks for playing!");
        System.out.println("\nPlay again? Enter yes or no.\n");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("yes")) {
                try {
                    for (int i = 0; i < 50; ++i) System.out.println();
                    Client.repeatGame();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (input.equalsIgnoreCase("no")) {
                System.out.println("\nGoodbye!");
                break;
            }
            else {
                System.out.println("Please enter yes or no.");
            }
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

package main.java.controller;

import main.java.model.RoomMovement;
import main.java.view.Story;

import java.io.IOException;

public class GameSettings {
    public static String roomName;

    public void readGameStory() {
        Story gameStory = new Story();
        gameStory.titleScreen();
        gameStory.introText();


    }
    public void startGame() throws IOException {
        RoomMovement movement = new RoomMovement();
        GameController gameController = new GameController();
        movement.firstRoom();
        while (true) {
            if (GameController.player.getHealth() <= 0) {
                System.out.println("You have died!");
                break;
            }
            else if (GameController.timer== 24){
                System.out.println("You have run out of time!");
                break;
            }
            else if (GameController.player.getInventory().contains("Trophy")) {
                System.out.println("You have won the game!");
                break;
            }
            else {
                gameController.userChoice();
            }
        }


    }
    public void endGame() {
        System.out.println("Game Over!");
    }
}

package main.java.controller;

import main.java.model.RoomMovement;
import main.java.view.Story;

import java.io.IOException;

public class GameSettings {
    public static String roomName;

    public void readGameStory() {
        Story gameStory = new Story();
        gameStory.titleScreen();
        gameStory.selectDifficulty();
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
                System.out.println("The room begins to shake as you step through the time portal!");
                break;
            }
            else {
                gameController.userChoice();
            }
        }


    }
    public void endGame() {
        System.out.println("Game Over!");
        System.out.println("Thanks for playing!");
        System.out.println("Play again?");
    }
}

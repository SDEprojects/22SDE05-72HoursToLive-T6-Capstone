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
            gameController.userChoice();
        }


    }
}

package main.java.client;


import main.java.controller.GameSettings;

import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException {
        GameSettings gameSettings = new GameSettings();
//        gameSettings.readGameStory();
        gameSettings.startGame();
        gameSettings.endGame();
    }
    public static void repeatGame() throws IOException {
        GameSettings gameSettings = new GameSettings();
        gameSettings.startGame();
        gameSettings.endGame();
    }
}


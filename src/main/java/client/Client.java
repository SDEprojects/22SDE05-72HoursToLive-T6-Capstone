package main.java.client;


import main.java.controller.GameSettings;
import main.java.view.Music;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        GameSettings gameSettings = new GameSettings();
        Music music = new Music();
        music.playMusic();
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


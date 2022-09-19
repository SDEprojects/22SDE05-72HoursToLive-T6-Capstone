package main.java.client;


import main.java.GUI.GUI;
import main.java.controller.GameSettings;
import main.java.view.Music;
import main.java.view.Story;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

public class Client {

    /* This is the main class of the game. It is responsible for starting the game. */
//    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
//        GameSettings gameSettings = new GameSettings();
//        Music music = new Music();
//        music.playMusic();
//        gameSettings.readGameStory();
//        gameSettings.startGame();
//        gameSettings.endGame();
//
//    }
    public static void repeatGame() throws IOException {
        GameSettings gameSettings = new GameSettings();
        Story story = new Story();
        story.selectDifficulty();
        gameSettings.startGame();
        gameSettings.endGame();
    }

    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException, FontFormatException {
        new GUI();
    }
}


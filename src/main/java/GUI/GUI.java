package main.java.GUI;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

public class GUI {




    public GUI() throws IOException, FontFormatException, UnsupportedAudioFileException, LineUnavailableException {
        new TitleScreen();
    }

    public static void main(String[] args) throws IOException, FontFormatException, UnsupportedAudioFileException, LineUnavailableException {
        new GUI();
    }
}

package main.java.GUI;

import java.awt.*;
import java.io.IOException;

public class GUI {




    public GUI() throws IOException, FontFormatException {
        new TitleScreen();
    }

    public static void main(String[] args) throws IOException, FontFormatException {
        new GUI();
    }
}

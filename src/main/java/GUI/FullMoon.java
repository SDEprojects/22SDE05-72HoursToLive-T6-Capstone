package main.java.GUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FullMoon {

    public static void fullMoon() throws IOException, FontFormatException {
        URL fullMoonImageStream = ClassLoader.getSystemClassLoader().getResource("Images/FullMoon6.jpeg");

        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("Fonts/BloodyTerror-GOW9Z.ttf");
        Font fullMoonFont = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(24f);
        InputStream stream1 = ClassLoader.getSystemClassLoader().getResourceAsStream("Fonts/BloodyTerror-GOW9Z.ttf");
        Font fullMoonFont1 = Font.createFont(Font.TRUETYPE_FONT, stream1).deriveFont(20f);

        JDialog fMoon = new JDialog();
        //fMoon.setUndecorated(true);
        JLabel fullMoonImageLabel = new JLabel(new ImageIcon(fullMoonImageStream));

        JTextArea fullMoonTextLabel = new JTextArea("AHHHHHHHH-WOOOOOOOOO!!");
        fullMoonTextLabel.setBounds(125, 0,600,100);
        fullMoonTextLabel.setFont(fullMoonFont);
        fullMoonTextLabel.setForeground(Color.red);
        fullMoonTextLabel.setOpaque(false);

        JTextArea fullMoonTextLabel1 = new JTextArea("The werewolves are more powerful tonight! Be careful!");
        fullMoonTextLabel1.setBounds(5, 350,600,100);
        fullMoonTextLabel1.setFont(fullMoonFont1);
        fullMoonTextLabel1.setForeground(Color.red);
        fullMoonTextLabel1.setOpaque(false);


        fullMoonImageLabel.add(fullMoonTextLabel);
        fullMoonImageLabel.add(fullMoonTextLabel1);

        fMoon.add(fullMoonImageLabel);
        fMoon.pack();
        fMoon.setVisible(true);
        fMoon.setLocationRelativeTo(GUI.frame);
        fMoon.setAlwaysOnTop(true);



    }
}

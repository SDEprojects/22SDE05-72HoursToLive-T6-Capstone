package main.java.GUI;

import javax.swing.*;

import java.awt.*;
import java.net.URL;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GamePlay {

    URL hallwayImage = ClassLoader.getSystemClassLoader().getResource("Images/Hallway.jpeg");

    JPanel leftPanel;
    JPanel imagePanel;
    JButton north = new JButton("N");
    JButton south = new JButton("S");
    JButton east = new JButton("E");
    JButton west = new JButton("W");
    JPanel gameDescriptionPanel;

    JButton temp1 = new JButton("Temp1");
    JButton temp2 = new JButton("Temp2");

    JPanel rightPanel;
    JPanel healthPanel;
    JPanel locationPanel;
    JPanel inventoryPanel;
    JPanel statPanel;
    JPanel timePanel;


    public GamePlay(JFrame frame) {

        leftPanel = new JPanel();
        leftPanel.setBounds(0, 50, 700, 950);
        leftPanel.setBackground(Color.black);
        leftPanel.setLayout(null);

        imagePanel = new JPanel();
        imagePanel.setBounds(0, 50, 700, 700);
        imagePanel.setBackground(Color.black);
        imagePanel.setOpaque(true);
        ImageIcon img = new ImageIcon(hallwayImage);
        img.setImage(img.getImage().getScaledInstance(700, 700, Image.SCALE_DEFAULT));
        imagePanel.add(new JLabel(img));

        gameDescriptionPanel = new JPanel();
        gameDescriptionPanel.setBounds(0, 700, 700, 250);
        gameDescriptionPanel.setBackground(Color.blue);
        gameDescriptionPanel.setOpaque(true);

        rightPanel = new JPanel();
        rightPanel.setBounds(700, 50, 300, 950);
        rightPanel.setBackground(Color.blue);
        rightPanel.setLayout(null);

        healthPanel = new JPanel();

        healthPanel.setBounds(700, 50, 300, 75);
        healthPanel.setBackground(Color.green);
        healthPanel.setOpaque(true);

        locationPanel = new JPanel();
        locationPanel.setBounds(700, 125, 300, 250);
        locationPanel.setBackground(Color.yellow);
        locationPanel.setOpaque(true);

        inventoryPanel = new JPanel();
        inventoryPanel.setBounds(700, 375, 300, 250);
        inventoryPanel.setBackground(Color.gray);
        inventoryPanel.setOpaque(true);

        statPanel = new JPanel();
        statPanel.setBounds(700, 625, 300, 250);
        statPanel.setBackground(Color.orange);
        statPanel.setOpaque(true);

        timePanel = new JPanel();
        timePanel.setBounds(700, 875, 300, 150);
        timePanel.setBackground(Color.red);
        timePanel.setOpaque(true);

        leftPanel.add(imagePanel);
        leftPanel.add(gameDescriptionPanel);

        rightPanel.add(healthPanel);
        rightPanel.add(locationPanel);
        rightPanel.add(inventoryPanel);
        rightPanel.add(statPanel);
        rightPanel.add(timePanel);

        frame.add(leftPanel);
        frame.add(rightPanel);

        frame.setVisible(true);


    }
}

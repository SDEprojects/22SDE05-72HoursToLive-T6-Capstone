package main.java.GUI;

import javax.swing.*;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.security.cert.PolicyNode;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import static main.java.GUI.PanelSetup.imagePanel;

public class GamePlay {

    URL hallwayImage = ClassLoader.getSystemClassLoader().getResource("Images/Hallway.jpeg");

    JPanel leftPanel;
    JPanel imagePanel;
    JButton north = new JButton("N");
    JButton south = new JButton("S");
    JButton east = new JButton("E");
    JButton west = new JButton("W");
    static JPanel gameDescriptionPanel;

    JButton temp1 = new JButton("Temp1");
    JButton temp2 = new JButton("Temp2");

    JPanel rightPanel;
    JPanel healthPanel;
    JPanel locationPanel;
    JPanel inventoryPanel;
    JPanel statPanel;
    JPanel timePanel;


    public GamePlay(JFrame frame) throws IOException {
        imagePanel = PanelSetup.imagePanel();
        gameDescriptionPanel = PanelSetup.gameDescriptionPanel();
        healthPanel = PanelSetup.healthPanel();
        locationPanel = PanelSetup.locationPanel();
        inventoryPanel = PanelSetup.inventoryPanel();
        statPanel = PanelSetup.statPanel();
        timePanel = PanelSetup.timePanel();

        leftPanel = new JPanel();
        leftPanel.setBounds(0, 50, 700, 950);
        leftPanel.setBackground(Color.black);
        leftPanel.setLayout(null);

        leftPanel.add(imagePanel);
        leftPanel.add(gameDescriptionPanel);

        rightPanel = new JPanel();
        rightPanel.setBounds(700, 50, 300, 950);
        rightPanel.setBackground(Color.blue);
        rightPanel.setLayout(null);

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

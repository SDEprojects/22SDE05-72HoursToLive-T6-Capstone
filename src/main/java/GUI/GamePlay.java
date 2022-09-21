package main.java.GUI;

import main.java.model.Room;
import main.java.model.RoomMovement;

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
    JTextArea textArea;
    JLabel lbl;


    public GamePlay(JFrame frame) throws IOException {
        RoomMovement movement = Controller.startGame();
        Controller gameController = new Controller();
        Room room = RoomMovement.roomSwitcher;

        imagePanel = PanelSetup.imagePanel();
        gameDescriptionPanel = PanelSetup.gameDescriptionPanel(room);
        healthPanel = PanelSetup.healthPanel(Controller.player.getHealth());
        locationPanel = PanelSetup.locationPanel(room);
        inventoryPanel = PanelSetup.inventoryPanel();
        statPanel = PanelSetup.statPanel(Controller.player.getAttackPower(), Controller.player.getArmorRating());
        timePanel = PanelSetup.timePanel(Controller.timer);

        leftPanel = new JPanel();
        leftPanel.setBounds(0, 50, 700, 950);
        leftPanel.setBackground(Color.black);
        leftPanel.setLayout(null);

        leftPanel.add(imagePanel);
        leftPanel.add(gameDescriptionPanel);


//        lbl = new JLabel("Output : ");
//        lbl.setFont(new Font("Verdana",Font.PLAIN,18));
//        lbl.setVerticalAlignment(SwingConstants.BOTTOM);
//        gameDescriptionPanel.add(lbl);



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

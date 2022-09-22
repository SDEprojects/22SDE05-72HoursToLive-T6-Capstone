package main.java.GUI;

import main.java.model.Room;
import main.java.model.RoomMovement;

import javax.swing.*;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class GamePlay {

    JPanel leftPanel;
    static JPanel imagePanel;
    static JPanel gameDescriptionPanel;

    JPanel rightPanel;
    static JPanel healthAndTimePanel;
    static JPanel locationPanel;
    JPanel inventoryPanel;
    JPanel statPanel;
    static JPanel compassPanel;
    JPanel mapButtonPanel;


    public GamePlay(JFrame frame) throws IOException {
        RoomMovement movement = Controller.startGame();
        Controller gameController = new Controller();
        Room room = RoomMovement.roomSwitcher;

        imagePanel = PanelSetup.imagePanel(room, gameController);
        gameDescriptionPanel = PanelSetup.gameDescriptionPanel(room);
        healthAndTimePanel = PanelSetup.healthAndTimePanel(Controller.player.getHealth(), Controller.timer);
        statPanel = PanelSetup.statPanel(Controller.player.getAttackPower(), Controller.player.getArmorRating());
        locationPanel = PanelSetup.locationPanel(room);
        compassPanel = PanelSetup.compassPanel(gameController, room);
        inventoryPanel = PanelSetup.inventoryPanel();
        mapButtonPanel = Map.getMap();

        leftPanel = new JPanel();
        leftPanel.setBounds(0, 50, 700, 950);
        leftPanel.setBackground(Color.black);
        leftPanel.setOpaque(false);
        leftPanel.setLayout(null);

        leftPanel.add(imagePanel);
        leftPanel.add(gameDescriptionPanel);

        rightPanel = new JPanel();
        rightPanel.setBounds(700, 50, 300, 950);
        rightPanel.setBackground(Color.black);
        rightPanel.setLayout(null);


        rightPanel.add(healthAndTimePanel);
        rightPanel.add(statPanel);
        rightPanel.add(locationPanel);
        rightPanel.add(compassPanel);
        rightPanel.add(inventoryPanel);
        rightPanel.add(mapButtonPanel);

        frame.add(leftPanel);
        frame.add(rightPanel);

        frame.setVisible(true);
    }
}

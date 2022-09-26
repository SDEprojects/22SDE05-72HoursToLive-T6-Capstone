package main.java.GUI;

import main.java.model.Room;
import main.java.model.RoomMovement;

import javax.swing.*;

import java.awt.*;
import java.io.IOException;

public class GamePlay {

    static JPanel leftPanel;
    static Container imageContainer;
    static JPanel imagePanel;
    static JPanel gameDescriptionPanel;
    static JPanel inventoryTitlePanel;

    JPanel rightPanel;
    static JPanel healthAndTimePanel;
    static JPanel locationPanel;
    static JPanel inventoryPanel;
    static JPanel statPanel;
    static JPanel compassPanel;
    JPanel mapAndGodModePanel;

    /**
     * Generates the core GUI gameplay layout and initial values and is used throughout
     * the game until win/lose condition is met
     */
    public GamePlay() throws IOException {
        JFrame frame = GUI.frame;
        Controller.startGame();
        Controller gameController = new Controller();
        Room room = RoomMovement.roomSwitcher;

        imageContainer = new Container();
        imageContainer.setBounds(0, 0, 700, 700);
        imageContainer.setBackground(Color.black);
        imagePanel = PanelSetup.imagePanel(room, gameController);
        gameDescriptionPanel = PanelSetup.gameDescriptionPanel(room);
        healthAndTimePanel = PanelSetup.healthAndTimePanel(Controller.player.getHealth(), Controller.timer);
        statPanel = PanelSetup.statPanel(Controller.player.getAttackPower(), Controller.player.getArmorRating());
        locationPanel = PanelSetup.locationPanel(room);
        compassPanel = PanelSetup.compassPanel(gameController, room);
        inventoryTitlePanel = PanelSetup.inventoryTitlePanel();
        inventoryPanel = PanelSetup.inventoryPanel();
        mapAndGodModePanel = Map.getMap();

        imageContainer.add(imagePanel);

        leftPanel = new JPanel();
        leftPanel.setBounds(0, 50, 700, 950);
        leftPanel.setBackground(Color.black);
        leftPanel.setOpaque(false);
        leftPanel.setLayout(null);

        leftPanel.add(imageContainer);
        leftPanel.add(gameDescriptionPanel);

        rightPanel = new JPanel();
        rightPanel.setBounds(700, 50, 300, 950);
        rightPanel.setBackground(Color.black);
        rightPanel.setLayout(null);

        rightPanel.add(healthAndTimePanel);
        rightPanel.add(statPanel);
        rightPanel.add(locationPanel);
        rightPanel.add(compassPanel);
        rightPanel.add(inventoryTitlePanel);
        rightPanel.add(inventoryPanel);
        rightPanel.add(mapAndGodModePanel);

        frame.add(leftPanel);
        frame.add(rightPanel);

        frame.setVisible(true);
    }

    public static Container getImageContainer() {
        return imageContainer;
    }

    public static void setImageContainer(Container imagePanel) {
        GamePlay.imageContainer = imageContainer;
    }
}

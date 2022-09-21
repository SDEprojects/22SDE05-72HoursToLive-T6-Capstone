package main.java.GUI;

import main.java.model.Room;
import main.java.model.RoomMovement;
import main.java.view.TextColor;

import javax.swing.*;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import static main.java.GUI.PanelSetup.imagePanel;

public class GamePlay {

    private static final ResourceBundle bundle = ResourceBundle.getBundle("main.resources.strings");

    URL hallwayImage = ClassLoader.getSystemClassLoader().getResource("Images/Hallway.jpeg");

    JPanel leftPanel;
    JPanel imagePanel;
    static JPanel gameDescriptionPanel;

    JPanel rightPanel;
    static JPanel healthAndTimePanel;
    static JPanel locationPanel;
    JPanel inventoryPanel;
    JPanel statPanel;
    static JPanel compassPanel;
    JPanel timePanel;
    JTextArea textArea;
    JLabel lbl;
    JPanel mapButtonPanel;


    public GamePlay(JFrame frame) throws IOException {
        RoomMovement movement = Controller.startGame();
        Controller gameController = new Controller();
        Room room = RoomMovement.roomSwitcher;

        imagePanel = PanelSetup.imagePanel();
        gameDescriptionPanel = PanelSetup.gameDescriptionPanel(room);
        healthAndTimePanel = PanelSetup.healthAndTimePanel(Controller.player.getHealth(), Controller.timer);
        statPanel = PanelSetup.statPanel(Controller.player.getAttackPower(), Controller.player.getArmorRating());
        locationPanel = PanelSetup.locationPanel(room);
        compassPanel = PanelSetup.compassPanel(gameController, room);
        inventoryPanel = PanelSetup.inventoryPanel();

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

        /*************************************/
        mapButtonPanel = new JPanel();
        mapButtonPanel.setBounds(700, 945, 300, 75);
        mapButtonPanel.setBackground(Color.gray);
        mapButtonPanel.setOpaque(true);

        JButton mapButton = new JButton("MAP");
        mapButtonPanel.add(mapButton);

        mapButton.addActionListener(e -> {
            JFrame mapFrame = new JFrame("Map");
            mapFrame.setSize(600, 300);
            mapFrame.setVisible(true);
            mapFrame.setResizable(false);
            mapFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

            String mapRoom = RoomMovement.currentRoom;
            HashMap<String, Room> allRooms = RoomMovement.getAllRooms();
            ArrayList<String> roomList = new ArrayList<>(allRooms.keySet());
            int index = roomList.indexOf(mapRoom);
            int size = roomList.get(index).length();
            StringBuilder yourLocation = new StringBuilder();
            for (int i = 0; i < size-9; i++){
                yourLocation.append(" ");
            }
            roomList.set(index, "* YOU *" + yourLocation);

            JTextArea mapText = new JTextArea();
            mapText.setLineWrap(true);
            mapText.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
            mapText.setForeground(Color.red);
            mapText.setWrapStyleWord(true);
            mapText.setText("\t\t" + roomList.get(13));
            mapText.append("\n\t\t===============");
            mapText.append("\n" + roomList.get(9));
            mapText.append("\t\t"+ roomList.get(10));
            mapText.append("\t\t" + roomList.get(4));
            mapText.append("\n===============");
            mapText.append("\t============");
            mapText.append("\t=============");
            mapText.append("\n" + roomList.get(14));
            mapText.append("\t\t"+ roomList.get(3));
            mapText.append("\t\t" + roomList.get(12));
            mapText.append("\n===============");
            mapText.append("\t=============");
            mapText.append("\t=============");
            mapText.append("\n" + roomList.get(0));
            mapText.append("\t\t"+ roomList.get(11));
            mapText.append("\t\t" + roomList.get(8));
            mapText.append("\n===============");
            mapText.append("\t=============");
            mapText.append("\t=============");
            mapText.append("\n" + roomList.get(7));
            mapText.append("\t\t"+ roomList.get(1));
            mapText.append("\t\t" + roomList.get(5));
            mapText.append("\n===============");
            mapText.append("\t=============");
            mapText.append("\t=============");
            mapText.append("\n" + roomList.get(2));
            mapText.append("\t\t"+ roomList.get(6));
            mapText.append("\t\t" + roomList.get(15));
            mapFrame.add(mapText, BorderLayout.CENTER);


        });
        /********************************/

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

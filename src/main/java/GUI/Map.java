package main.java.GUI;

import main.java.model.Room;
import main.java.model.RoomMovement;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Responsible for generating the game map
 */
public class Map {

    public static JPanel getMap(){
        //JPanel for God Mode and Map
        JPanel mapButtonPanel = new JPanel(new GridLayout(1,3));
        mapButtonPanel.setBounds(0, 750, 700, 50);
        mapButtonPanel.setBackground(Color.gray);
        mapButtonPanel.setOpaque(false);

        //Super Soldier Button
        JButton godModeButton = new JButton("Super Soldier Activated");
        godModeButton.setBackground(Color.black);
        godModeButton.setForeground(Color.black);
        godModeButton.setOpaque(false);
        godModeButton.setBorderPainted(false);
        godModeButton.setFont(new Font("Helvetica", Font.BOLD, 16));
        mapButtonPanel.add(godModeButton);

        //Map Button
        JButton mapButton = new JButton("MAP");
        mapButton.setBackground(Color.black);
        mapButton.setForeground(Color.red);
        mapButton.setOpaque(false);
        mapButton.setBorderPainted(false);
        mapButton.setFont(new Font("Helvetica", Font.BOLD, 16));
        mapButtonPanel.add(mapButton);

        //Just placeHolder
        JButton tempButton = new JButton();
        tempButton.setBackground(Color.black);
        tempButton.setForeground(Color.black);
        tempButton.setOpaque(false);
        tempButton.setBorderPainted(false);
        tempButton.setEnabled(false);
        mapButtonPanel.add(tempButton);


        //Action Listener for Super Soldier
        godModeButton.addActionListener(e -> {
            Controller.player.setHealth(1000);
            Controller.player.setAttackPower(1000);
            Controller.timer = -10;
            UpdatePanel.updateDescriptionPanelText("You found the ester egg. Super soldier mode activated!!!!");
            UpdatePanel.updateHealthAndTimePanel(Controller.player.getHealth(),Controller.timer);
            godModeButton.setEnabled(false);
            godModeButton.setForeground(Color.red);
            //TODO: Update for player stat
        });

        //Action Listener for Map
        mapButton.addActionListener(e -> {
            JFrame mapFrame = new JFrame("Map");
            mapFrame.setSize(600, 300);
            mapFrame.setBackground(Color.black);
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
            mapText.setBackground(Color.darkGray);
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

        return mapButtonPanel;
    }
}

package main.java.GUI;

import main.java.model.Room;
import main.java.model.RoomMovement;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Map {

    public static JPanel getMap(){
        JPanel mapButtonPanel = new JPanel();
        //imagePanel.setBounds(0, 0, 700, 700);
        //mapButtonPanel.setBounds(700, 945, 300, 75);
        mapButtonPanel.setBounds(350, 750, 50, 50);
        mapButtonPanel.setBackground(Color.gray);
        mapButtonPanel.setOpaque(false);

        JButton mapButton = new JButton("MAP");
        mapButton.setBackground(Color.black);
        mapButton.setForeground(Color.red);
        mapButton.setOpaque(false);
        mapButton.setBorderPainted(false);
        mapButton.setFont(new Font("Helvetica", Font.BOLD, 16));
        mapButtonPanel.add(mapButton);

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

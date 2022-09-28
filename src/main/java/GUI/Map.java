package main.java.GUI;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Responsible for generating the game map
 */
public class Map {

    static URL mapImageStream = ClassLoader.getSystemClassLoader().getResource("Images/72HoursMap.jpeg");

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
            Controller.player.setArmorRating(1000);
            Controller.timer = -10;
            UpdatePanel.updateDescriptionPanelText("You found the ester egg. Super soldier mode activated!!!!");
            UpdatePanel.updateHealthAndTimePanel(Controller.player.getHealth(),Controller.timer);
            UpdatePanel.updateStatPanel(Controller.player.getAttackPower(),Controller.player.getArmorRating());
            godModeButton.setEnabled(false);
            godModeButton.setForeground(Color.red);
        });

        //Action Listener for Map
        mapButton.addActionListener(e -> {
            JFrame mapFrame = new JFrame("Map");
            mapFrame.setSize(600, 700);
            mapFrame.setBackground(Color.black);
            mapFrame.setVisible(true);
            mapFrame.setResizable(false);
            mapFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

            JPanel mapPanel = new JPanel();
            mapPanel.setBackground(Color.black);
            mapPanel.setBounds(0, 0, 600, 700);
            ImageIcon img = new ImageIcon(mapImageStream);
            img.setImage(img.getImage().getScaledInstance(600, 665, Image.SCALE_DEFAULT));
            mapPanel.add(new JLabel(img));

            mapFrame.add(mapPanel);
            mapFrame.setLocationRelativeTo(GUI.frame);
        });

        return mapButtonPanel;
    }
}

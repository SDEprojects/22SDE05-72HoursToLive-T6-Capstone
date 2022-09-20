package main.java.GUI;

import main.java.model.Room;
import main.java.model.RoomMovement;
import main.java.model.Werewolf;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class PanelSetup extends JPanel{

    public static JPanel imagePanel(){
        JPanel imagePanel = new JPanel();
        imagePanel.setBounds(0, 50, 700, 700);
        imagePanel.setBackground(Color.black);
        imagePanel.setOpaque(true);

//        ImageIcon img = new ImageIcon(currentRoom);
//        img.setImage(img.getImage().getScaledInstance(700, 700, Image.SCALE_DEFAULT));
//        imagePanel.add(new JLabel(img));

        return imagePanel;
    }

    public static JPanel gameDescriptionPanel() {
        JPanel gameDescriptionPanel = new JPanel();
        gameDescriptionPanel.setBounds(0, 700, 700, 250);
        gameDescriptionPanel.setBackground(Color.blue);
        gameDescriptionPanel.setOpaque(true);
        gameDescriptionPanel.setLayout(new BorderLayout());
        JLabel test = new JLabel("THIS IS A TEST");
        test.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
        gameDescriptionPanel.add(test, BorderLayout.CENTER);

        return gameDescriptionPanel;
    }

    public static JPanel healthPanel() {
        JPanel healthPanel = new JPanel();
        healthPanel.setBounds(700, 50, 300, 75);
        healthPanel.setBackground(Color.green);
        healthPanel.setOpaque(true);

        return healthPanel;
    }

    public static JPanel locationPanel() {
        JPanel locationPanel = new JPanel();
        locationPanel.setBounds(700, 125, 300, 250);
        locationPanel.setBackground(Color.yellow);
        locationPanel.setOpaque(true);

        return locationPanel;
    }

    public static JPanel inventoryPanel() {
        JPanel inventoryPanel = new JPanel();
        inventoryPanel.setBounds(700, 375, 300, 250);
        inventoryPanel.setBackground(Color.gray);
        inventoryPanel.setOpaque(true);

        return inventoryPanel;
    }

    public static JPanel statPanel() {
        JPanel statPanel = new JPanel();
        statPanel.setBounds(700, 625, 300, 250);
        statPanel.setBackground(Color.orange);
        statPanel.setOpaque(true);

        return statPanel;
    }

    public static JPanel timePanel() {
        JPanel timePanel = new JPanel();
        timePanel.setBounds(700, 875, 300, 150);
        timePanel.setBackground(Color.red);
        timePanel.setOpaque(true);

        return timePanel;
    }
}

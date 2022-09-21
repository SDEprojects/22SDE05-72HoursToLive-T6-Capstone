package main.java.GUI;

import main.java.model.Room;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class PanelSetup extends JPanel{

    static Font panelFont = new Font(Font.DIALOG, Font.BOLD, 12);

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

    public static JPanel gameDescriptionPanel(Room room) {
        JPanel gameDescriptionPanel = new JPanel();
        gameDescriptionPanel.setBounds(0, 800, 700, 150);
        gameDescriptionPanel.setBackground(Color.black);
        gameDescriptionPanel.setOpaque(true);
        gameDescriptionPanel.setLayout(new BorderLayout());

        //JTextArea implemented to wrap gameoutput and place in gamedescription panel
        JTextArea text = new JTextArea();
        text.setLineWrap(true);
        text.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
        text.setBackground(Color.black);
        text.setForeground(Color.red);
        text.setWrapStyleWord(true);
        text.setText(room.getDescription());
        gameDescriptionPanel.add(text, BorderLayout.CENTER);

        return gameDescriptionPanel;
    }

    public static JPanel healthPanel(int health) {
        JPanel healthPanel = new JPanel();
        healthPanel.setBounds(700, 50, 300, 75);
        healthPanel.setBackground(Color.darkGray);
        healthPanel.setOpaque(true);
        Color barColor;
        if (health >= 60){
            barColor= Color.green;
        }else if(health >= 30){
            barColor= Color.yellow;
        }else {
            barColor= Color.red;
        }

        JLabel currentHealth = new JLabel();
        currentHealth.setText("Health= " + health);
        currentHealth.setForeground(barColor);
        currentHealth.setFont(panelFont);

        healthPanel.add(currentHealth);
//        JProgressBar healthBar = new JProgressBar(0,100);
//        healthBar.setSize(300,75);
//        healthBar.setValue(health);
//        healthBar.setForeground(barColor);
//        healthPanel.add(healthBar);

        return healthPanel;
    }

    public static JPanel locationPanel(Room room) {
        String name = room.getName();
        HashMap connectedRooms = (HashMap) room.getConnectedRooms();
        String northRoom = connectedRooms.get("north").toString();
        String southRoom = connectedRooms.get("south").toString();
        String eastRoom = connectedRooms.get("east").toString();
        String westRoom = connectedRooms.get("west").toString();
        JPanel locationPanel = new JPanel();
        locationPanel.setBounds(700, 125, 300, 250);
        locationPanel.setBackground(Color.gray);
        locationPanel.setOpaque(true);
        locationPanel.setLayout(new GridLayout(5,1));
        JLabel currentLocation = new JLabel();
        currentLocation.setText("Current Location= " + name);
        currentLocation.setFont(panelFont);
        currentLocation.setForeground(Color.black);
        JLabel north = new JLabel();
        north.setText("North= " + northRoom);
        north.setFont(panelFont);
        north.setForeground(Color.black);
        JLabel south = new JLabel();
        south.setText("South= " + southRoom);
        south.setFont(panelFont);
        south.setForeground(Color.black);
        JLabel east = new JLabel();
        east.setText("East= " + eastRoom);
        east.setFont(panelFont);
        east.setForeground(Color.black);
        JLabel west = new JLabel();
        west.setText("West= " + westRoom);
        west.setFont(panelFont);
        west.setForeground(Color.black);

        locationPanel.add(currentLocation);
        locationPanel.add(north);
        locationPanel.add(south);
        locationPanel.add(east);
        locationPanel.add(west);

        return locationPanel;
    }

    public static JPanel inventoryPanel() {
        JPanel inventoryPanel = new JPanel();
        inventoryPanel.setBounds(700, 375, 300, 250);
        inventoryPanel.setBackground(Color.DARK_GRAY);
        inventoryPanel.setOpaque(true);

        return inventoryPanel;
    }

    public static JPanel statPanel(int attack, int armor) {
        JPanel statPanel = new JPanel();
        statPanel.setBounds(700, 625, 300, 250);
        statPanel.setBackground(Color.gray);
        statPanel.setOpaque(true);
        statPanel.setLayout(new GridLayout(2,1));

        JLabel attackPower = new JLabel();
        attackPower.setText("Attack Power= " + attack);
        attackPower.setFont(panelFont);
        attackPower.setForeground(Color.black);
        JLabel armorRating = new JLabel();
        armorRating.setText("Armor Rating= " + armor);
        armorRating.setFont(panelFont);
        armorRating.setForeground(Color.black);

        statPanel.add(attackPower);
        statPanel.add(armorRating);

        return statPanel;
    }

    public static JPanel timePanel(int timer) {
        int currentTime = 72 - (timer * 3);
        JPanel timePanel = new JPanel();
        timePanel.setBounds(700, 875, 300, 150);
        timePanel.setBackground(Color.darkGray);
        timePanel.setOpaque(true);
        Color timeColor;
            if (currentTime >= 48){
            timeColor= Color.green;
            }else if(currentTime >= 24 && currentTime <= 47){
            timeColor= Color.yellow;
            }else {
            timeColor= Color.red;
            }
        JLabel timeLabel = new JLabel();
        timeLabel.setText("Time Left= " + currentTime + " hours!!");
        timeLabel.setForeground(timeColor);
        timeLabel.setFont(panelFont);

        timePanel.add(timeLabel);

        return timePanel;
    }
}

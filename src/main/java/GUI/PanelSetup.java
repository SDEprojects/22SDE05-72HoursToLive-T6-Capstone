package main.java.GUI;

import main.java.controller.Response;
import main.java.model.Room;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class PanelSetup extends JPanel{

    static Font panelFont = new Font(Font.DIALOG, Font.BOLD, 12);
    private static ResourceBundle bundle = ResourceBundle.getBundle("main.resources.strings");;

    public static JPanel imagePanel(Room room, Controller gameController){
        JPanel imagePanel = new JPanel();
        imagePanel.setBounds(0, 0, 700, 700);
        imagePanel.setBackground(Color.black);
        imagePanel.setOpaque(true);
        String currentRoom = room.getName();
        String imgPath = "Images/" + currentRoom + ".jpeg";
        URL image = ClassLoader.getSystemClassLoader().getResource(imgPath);
        ImageIcon img = new ImageIcon(image);
        img.setImage(img.getImage().getScaledInstance(700, 700, Image.SCALE_DEFAULT));
        imagePanel.add(new JLabel(img));

        return imagePanel;
    }

    public static JPanel gameDescriptionPanel(Room room) {
        JPanel gameDescriptionPanel = new JPanel();
        gameDescriptionPanel.setBounds(0, 750, 700, 250);
        gameDescriptionPanel.setBackground(Color.black);
        gameDescriptionPanel.setOpaque(true);
        gameDescriptionPanel.setLayout(new BorderLayout());

        //JTextArea implemented to wrap gameoutput and place in gamedescription panel
        JTextArea text = new JTextArea();
        text.setLineWrap(true);
        text.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
        text.setBackground(Color.black);
        text.setForeground(Color.yellow);
        text.setWrapStyleWord(true);
        String firstRoomText = (bundle.getString("firstRoom_text1")) +
                (bundle.getString("firstRoom_text2")) +
                (bundle.getString("firstRoom_text3")) +
                (bundle.getString("firstRoom_text4"));
        String description = room.getDescription();
        String roomName = room.getName();
        text.setText(firstRoomText + roomName + "\n\n" + description);
        gameDescriptionPanel.add(text, BorderLayout.CENTER);

        return gameDescriptionPanel;
    }

    public static JPanel healthAndTimePanel(int health, int timer) {
        JPanel healthAndTimePanel = new JPanel();
        healthAndTimePanel.setBounds(700, 50, 300, 75);
        healthAndTimePanel.setBackground(Color.darkGray);
        healthAndTimePanel.setOpaque(true);
        healthAndTimePanel.setLayout(new GridLayout(2,1));

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

        healthAndTimePanel.add(currentHealth);
//        JProgressBar healthBar = new JProgressBar(0,100);
//        healthBar.setSize(300,75);
//        healthBar.setValue(health);
//        healthBar.setForeground(barColor);
//        healthPanel.add(healthBar);

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

        healthAndTimePanel.add(timeLabel);

        return healthAndTimePanel;
    }

    public static JPanel statPanel(int attack, int armor) {
        JPanel statPanel = new JPanel();
        statPanel.setBounds(700, 125, 300, 75);
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

    public static JPanel locationPanel(Room room) {
        String name = room.getName();
        HashMap connectedRooms = (HashMap) room.getConnectedRooms();
        String northRoom = connectedRooms.get("north").toString();
        String southRoom = connectedRooms.get("south").toString();
        String eastRoom = connectedRooms.get("east").toString();
        String westRoom = connectedRooms.get("west").toString();
        JPanel locationPanel = new JPanel();
        locationPanel.setBounds(700, 200, 300, 266);
        locationPanel.setBackground(Color.darkGray);
        locationPanel.setOpaque(true);
        locationPanel.setLayout(new GridLayout(5,1));
        JLabel currentLocation = new JLabel();
        currentLocation.setText("Current Location= " + name);
        currentLocation.setFont(panelFont);
        currentLocation.setForeground(Color.white);
        JLabel north = new JLabel();
        north.setText("North= " + northRoom);
        north.setFont(panelFont);
        north.setForeground(Color.white);
        JLabel south = new JLabel();
        south.setText("South= " + southRoom);
        south.setFont(panelFont);
        south.setForeground(Color.white);
        JLabel east = new JLabel();
        east.setText("East= " + eastRoom);
        east.setFont(panelFont);
        east.setForeground(Color.white);
        JLabel west = new JLabel();
        west.setText("West= " + westRoom);
        west.setFont(panelFont);
        west.setForeground(Color.white);

        locationPanel.add(currentLocation);
        locationPanel.add(north);
        locationPanel.add(south);
        locationPanel.add(east);
        locationPanel.add(west);

        return locationPanel;
    }

    public static JPanel compassPanel(Controller gameController, Room room) throws IOException {
        JButton north = new JButton ("N");
        JButton south = new JButton ("S");
        JButton east = new JButton ("E");
        JButton west = new JButton ("W");
        JButton empty1 = new JButton ("");
        JButton empty2 = new JButton ("");
        JButton empty3 = new JButton ("");
        JButton empty4 = new JButton ("");
        JButton empty5 = new JButton ("");
        JPanel compassPanel = new JPanel();
        compassPanel.setBounds(700, 466, 300, 270);
        compassPanel.setBackground(Color.gray);
        compassPanel.setOpaque(true);

        HashMap connectedRooms = (HashMap) room.getConnectedRooms();

        compassPanel.setLayout(new GridLayout(3, 3));
        compassPanel.add(empty1);
        empty1.setVisible(false);
        compassPanel.add(north);
        if (connectedRooms.get("north").toString().equals("None")){
            north.setEnabled(false);
        }
        compassPanel.add(empty2);
        empty2.setVisible(false);
        compassPanel.add(west);
        if (connectedRooms.get("west").toString().equals("None")){
            west.setEnabled(false);
        }
        compassPanel.add(empty3);
        empty3.setVisible(false);
        compassPanel.add(east);
        if (connectedRooms.get("east").toString().equals("None")){
            east.setEnabled(false);
        }
        compassPanel.add(empty4);
        empty4.setVisible(false);
        compassPanel.add(south);
        if (connectedRooms.get("south").toString().equals("None")){
            south.setEnabled(false);
        }
        compassPanel.add(empty5);
        empty5.setVisible(false);

        compassPanel.setForeground(Color.black);
        compassPanel.setFont(panelFont);

        north.addActionListener(e -> {
            try {
                gameController.handleUserClick(new Response("go", "north", ""), room, gameController);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        south.addActionListener(e -> {
            try {
                gameController.handleUserClick(new Response("go", "south", ""), room, gameController);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        east.addActionListener(e -> {
            try {
                gameController.handleUserClick(new Response("go", "east", ""), room, gameController);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        west.addActionListener(e -> {
            try {
                gameController.handleUserClick(new Response("go", "west", ""), room, gameController);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        return compassPanel;
    }

    public static JPanel inventoryPanel() {
        JPanel inventoryPanel = new JPanel();
        inventoryPanel.setBounds(700, 736, 300, 266);
        inventoryPanel.setBackground(Color.DARK_GRAY);
        inventoryPanel.setOpaque(true);

        return inventoryPanel;
    }


}


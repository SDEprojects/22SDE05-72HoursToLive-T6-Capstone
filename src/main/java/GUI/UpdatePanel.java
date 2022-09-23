package main.java.GUI;

import main.java.controller.Response;
import main.java.model.Room;
import main.java.model.Werewolf;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import static main.java.GUI.PanelSetup.panelFont;


public class UpdatePanel {

    static JTextArea text;
    public static void updateImagePanel(Room room, HashMap<String, List<Werewolf>> monsterMap) {
        JPanel imagePanel = GamePlay.imagePanel;
        imagePanel.removeAll();
        imagePanel.setBounds(0, 0, 700, 700);
        imagePanel.setBackground(Color.black);
        imagePanel.setOpaque(true);
        String currentRoom = room.getName();
        String imgPath;
        JPanel attackPanel = new JPanel();
        attackPanel.setBounds(300, 800, 700, 100);
        attackPanel.setOpaque(false);
        JButton attackButton = new JButton("ATTACK");
        attackButton.setForeground(Color.red);
        attackButton.setBackground(Color.black);
        attackButton.setFont(new Font("Helvetica", Font.BOLD, 28));
        attackButton.setOpaque(false);
        attackButton.setBorderPainted(false);
        attackPanel.add(attackButton);

        if (!monsterMap.get(currentRoom).isEmpty()){
            imgPath = "Images/" + currentRoom + "_WW.jpeg";
            URL image = ClassLoader.getSystemClassLoader().getResource(imgPath);
            ImageIcon img = new ImageIcon(image);
            img.setImage(img.getImage().getScaledInstance(700, 700, Image.SCALE_DEFAULT));
            imagePanel.add(new JLabel(img));
            imagePanel.add(attackPanel);
            attackButton.setVisible(true);

        }else {
            imgPath = "Images/" + currentRoom + ".jpeg";
            URL image = ClassLoader.getSystemClassLoader().getResource(imgPath);
            ImageIcon img = new ImageIcon(image);
            img.setImage(img.getImage().getScaledInstance(700, 700, Image.SCALE_DEFAULT));
            imagePanel.add(new JLabel(img));
            attackButton.setVisible(false);
        }

        GUI.frame.setVisible(true);
    }

    public static void gameDescriptionPanel(){

    }

    public static void updateHealthAndTimePanel(int health, int timer) {
        JPanel healthAndTimePanel = GamePlay.healthAndTimePanel;
        healthAndTimePanel.removeAll();
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

        GUI.frame.setVisible(true);
    }

    public static void updateLocation(Room room) {
        //todo Need to add item placement
        JPanel locationPanel = GamePlay.locationPanel;
        locationPanel.removeAll();
        String name = room.getName();
        HashMap connectedRooms = (HashMap) room.getConnectedRooms();
        String northRoom = connectedRooms.get("north").toString();
        String southRoom = connectedRooms.get("south").toString();
        String eastRoom = connectedRooms.get("east").toString();
        String westRoom = connectedRooms.get("west").toString();

        locationPanel.setBounds(700, 200, 300, 266);
        locationPanel.setBackground(Color.darkGray);
        locationPanel.setOpaque(true);
        locationPanel.setLayout(new GridLayout(5, 1));
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

        locationPanel.revalidate();
        locationPanel.repaint();
    }

    public static void updateCompass(Room room, Controller gameController, HashMap<String, List<Werewolf>> monsterMap) {
        JButton north = new JButton("N");
        JButton south = new JButton("S");
        JButton east = new JButton("E");
        JButton west = new JButton("W");
        JButton empty1 = new JButton("");
        JButton empty2 = new JButton("");
        JButton attackButton = new JButton("ATTACK!!");
        JButton empty4 = new JButton("");
        JButton empty5 = new JButton("");
        JPanel compassPanel = GamePlay.compassPanel;
        compassPanel.removeAll();
        compassPanel.repaint();

        compassPanel.setBounds(700, 466, 300, 270);
        compassPanel.setBackground(Color.gray);
        compassPanel.setOpaque(true);

        HashMap connectedRooms = (HashMap) room.getConnectedRooms();
        String currentRoom = room.getName();

        compassPanel.setLayout(new GridLayout(3, 3));
        compassPanel.add(empty1);
        empty1.setVisible(false);
        compassPanel.add(north);
        if (connectedRooms.get("north").toString().equals("None")) {
            north.setEnabled(false);
        }
        compassPanel.add(empty2);
        empty2.setVisible(false);
        compassPanel.add(west);
        if (connectedRooms.get("west").toString().equals("None")) {
            west.setEnabled(false);
        }
        attackButton.setForeground(Color.red);
        compassPanel.add(attackButton);
        if (!monsterMap.get(currentRoom).isEmpty()){
            attackButton.setVisible(true);
        }else {
            attackButton.setVisible(false);
        }
        compassPanel.add(east);
        if (connectedRooms.get("east").toString().equals("None")) {
            east.setEnabled(false);
        }
        compassPanel.add(empty4);
        empty4.setVisible(false);
        compassPanel.add(south);
        if (connectedRooms.get("south").toString().equals("None")) {
            south.setEnabled(false);
        }
        compassPanel.add(empty5);
        empty5.setVisible(false);

        compassPanel.setForeground(Color.black);
        compassPanel.setFont(panelFont);
        compassPanel.setVisible(true);

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

        attackButton.addActionListener(e -> {
            try {
                gameController.handleUserClick(new Response("attack", "", ""), room, gameController);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        GUI.frame.setVisible(true);
    }

    public static void updateInventory(){

    }

    public static void updateDescriptionPanel(Room room){
        JPanel descriptionPanel = GamePlay.gameDescriptionPanel;
        descriptionPanel.removeAll();
        descriptionPanel.setBounds(0, 800, 700, 150);
        descriptionPanel.setBackground(Color.black);
        descriptionPanel.setOpaque(true);
        descriptionPanel.setLayout(new BorderLayout());

        //JTextArea implemented to wrap gameoutput and place in gamedescription panel
        text = new JTextArea();
        text.setLineWrap(true);
        text.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
        text.setBackground(Color.black);
        text.setForeground(Color.yellow);
        text.setWrapStyleWord(true);
        text.setSelectedTextColor(Color.red);
        String description = room.getDescription();
        String roomName = room.getName();
        text.setText(roomName + ": " + description);
        descriptionPanel.add(text, BorderLayout.CENTER);
    }

    //method to print out any string parameters into description panel
    public static void updateDescriptionPanelText(String string){
        JPanel descriptionPanel = GamePlay.gameDescriptionPanel;
        descriptionPanel.removeAll();
        descriptionPanel.repaint();
        descriptionPanel.setBounds(0, 800, 700, 150);
        descriptionPanel.setBackground(Color.black);
        descriptionPanel.setOpaque(true);
        descriptionPanel.setLayout(new BorderLayout());

        //JTextArea implemented to wrap gameoutput and place in gamedescription panel
        text = new JTextArea();
        text.setLineWrap(true);
        text.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
        text.setBackground(Color.black);
        text.setForeground(Color.yellow);
        text.setWrapStyleWord(true);

        text.append(string);
        descriptionPanel.add(text, BorderLayout.CENTER);

        GUI.frame.setVisible(true);
    }


    public static void appendDescriptionPanelText(String string){
        JPanel descriptionPanel = GamePlay.gameDescriptionPanel;

        text.append("\n" + string);

        descriptionPanel.repaint();
        GUI.frame.setVisible(true);
    }
}




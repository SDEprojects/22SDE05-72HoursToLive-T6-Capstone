package main.java.GUI;

import main.java.controller.Response;
import main.java.model.Room;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class PanelSetup extends JPanel{

    static Font panelFont = new Font(Font.DIALOG, Font.BOLD, 12);
    private static ResourceBundle bundle = ResourceBundle.getBundle("main.resources.strings");;

    /**
     * Responsible for generating the gameplay initial panels
     */
    public static JPanel imagePanel(Room room, Controller gameController){
        BackgroundPanel imagePanel = new BackgroundPanel(room);
        imagePanel.setBounds(0, 0, 700, 700);
        imagePanel.setBackground(Color.black);
        imagePanel.setOpaque(true);

        List<String> roomItems = room.getItems();
        if (roomItems.isEmpty()){
            UpdatePanel.appendDescriptionPanelText(bundle.getString("look1"));
        } else {
            for (String item : roomItems) {
                JPanel newItem = new JPanel();
                newItem.setBounds(0, 150, 100, 100);
                newItem.setOpaque(false);

                URL imgPath = ClassLoader.getSystemClassLoader().getResource("Icons/" + item + ".png");
                JButton showItem = new JButton();
                showItem.setForeground(Color.cyan);
                showItem.setBackground(Color.black);
                showItem.setOpaque(false);
                showItem.setBorderPainted(false);
                ImageIcon img = new ImageIcon(imgPath);
                img.setImage(img.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
                showItem.add(new JLabel(img));
                newItem.add(showItem);
                imagePanel.add(newItem);
                showItem.addActionListener(e -> {
                    try {
                        gameController.handleUserClick(new Response("pickup", "", item), room, gameController);
                        showItem.setVisible(false);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
            }
        }
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
        text.setEditable(false);
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
        JButton north = new JButton ();
        URL northIcon = ClassLoader.getSystemClassLoader().getResource("Icons/north_button.png");
        north.setOpaque(true);
        north.setBorderPainted(false);
        ImageIcon northImage = new ImageIcon(northIcon);
        northImage.setImage(northImage.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        north.add(new JLabel(northImage));

        JButton south = new JButton ();
        URL southIcon = ClassLoader.getSystemClassLoader().getResource("Icons/south_button.png");
        south.setOpaque(true);
        south.setBorderPainted(false);
        ImageIcon southImage = new ImageIcon(southIcon);
        southImage.setImage(southImage.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        south.add(new JLabel(southImage));

        JButton east = new JButton ();
        URL eastIcon = ClassLoader.getSystemClassLoader().getResource("Icons/east_button.png");
        east.setOpaque(false);
        east.setBorderPainted(false);
        ImageIcon eastImage = new ImageIcon(eastIcon);
        eastImage.setImage(eastImage.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        east.add(new JLabel(eastImage));

        JButton west = new JButton ();
        URL westIcon = ClassLoader.getSystemClassLoader().getResource("Icons/west_button.png");
        west.setOpaque(true);
        west.setBorderPainted(false);
        ImageIcon westImage = new ImageIcon(westIcon);
        westImage.setImage(westImage.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        west.add(new JLabel(westImage));

        JButton empty1 = new JButton ("");
        JButton empty2 = new JButton ("");
        JButton empty3 = new JButton ("");
        JButton empty4 = new JButton ("");
        JButton empty5 = new JButton ("");
        JPanel compassPanel = new JPanel();
        compassPanel.setBounds(700, 466, 300, 270);
        compassPanel.setBackground(Color.gray);
        compassPanel.setOpaque(true);

        HashMap<String, String> connectedRooms = (HashMap) room.getConnectedRooms();

        compassPanel.setLayout(new GridLayout(3, 3));
        compassPanel.add(empty1);
        empty1.setVisible(false);
        compassPanel.add(north);
        if (connectedRooms.get("north").equals("None")){
            north.setOpaque(false);
            north.setEnabled(false);
        }
        compassPanel.add(empty2);
        empty2.setVisible(false);
        compassPanel.add(west);
        if (connectedRooms.get("west").equals("None")){
            west.setOpaque(false);
            west.setEnabled(false);
        }
        compassPanel.add(empty3);
        empty3.setVisible(false);
        compassPanel.add(east);
        if (connectedRooms.get("east").equals("None")){
           east.setOpaque(false);
           east.setEnabled(false);
        }
        compassPanel.add(empty4);
        empty4.setVisible(false);
        compassPanel.add(south);
        if (connectedRooms.get("south").equals("None")){
            south.setOpaque(false);
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

    public static JPanel inventoryTitlePanel(){
        JPanel inventoryTitlePanel = new JPanel();
        inventoryTitlePanel.setBounds(700, 736, 300, 50);
        inventoryTitlePanel.setBackground(Color.DARK_GRAY);
        inventoryTitlePanel.setOpaque(true);
        JLabel title = new JLabel("INVENTORY");
        title.setFont(panelFont);
        title.setForeground(Color.orange);
        inventoryTitlePanel.add(title);

        return inventoryTitlePanel;
    }
    public static JPanel inventoryPanel() {
        JPanel inventoryPanel = new JPanel();
        inventoryPanel.setBounds(700, 786, 300, 216);
        inventoryPanel.setBackground(Color.DARK_GRAY);
        inventoryPanel.setOpaque(true);

        JPanel containerPanel = new JPanel();
        containerPanel.setBounds(700, 786, 300, 216);
        containerPanel.setBackground(Color.DARK_GRAY);
        containerPanel.setOpaque(true);
        containerPanel.add(new JLabel("No Inventory Items"));
        inventoryPanel.add(containerPanel);

        return inventoryPanel;
    }


}



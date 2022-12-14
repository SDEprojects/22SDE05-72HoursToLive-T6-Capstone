package main.java.GUI;

import main.java.controller.Response;
import main.java.model.Room;
import main.java.model.Werewolf;
import main.java.view.Music;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import static main.java.GUI.PanelSetup.panelFont;


public class UpdatePanel {

    static JTextArea text;
    private static ResourceBundle bundle = ResourceBundle.getBundle("main.resources.strings");
    /**
     * Responsible for updating the gameplay initial panels
     */
    public static void updateImagePanel(Room room, HashMap<String, List<Werewolf>> monsterMap, Controller gameController, List<String> inventory) {
        Container imageContainer = GamePlay.getImageContainer();
        imageContainer.removeAll();

        BackgroundPanel imagePanel = new BackgroundPanel(room, monsterMap);
        imagePanel.setBounds(0, 0, 700, 700);
        imagePanel.setBackground(Color.black);
        imagePanel.setOpaque(true);
        String currentRoom = room.getName();

        List<String> roomItems = room.getItems();
        if (roomItems.isEmpty()) {
            UpdatePanel.appendDescriptionPanelText(bundle.getString("look1"));
        } else if (room.getName().equals("Throne Room") && !monsterMap.get(currentRoom).isEmpty()) {
            System.out.println("Throne room doesn't populate items");
        } else if (room.getName().equals("Throne Room") && monsterMap.get(currentRoom).isEmpty() && !inventory.contains("blood sample")) {
            roomItems.clear();
            roomItems.add("blood sample");
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
        } else if ((room.getName().equals("Throne Room") && monsterMap.get(currentRoom).isEmpty() && inventory.contains("blood sample"))) {
            System.out.println("Blood Sample has been picked up");
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
        imageContainer.add(imagePanel);
        imageContainer.repaint();

        GUI.frame.setVisible(true);
    }

    public static void updateHealthAndTimePanel(int health, int timer) {
        JPanel healthAndTimePanel = GamePlay.healthAndTimePanel;
        healthAndTimePanel.removeAll();
        healthAndTimePanel.setBounds(700, 50, 300, 75);
        healthAndTimePanel.setBackground(Color.darkGray);
        healthAndTimePanel.setOpaque(true);
        healthAndTimePanel.setLayout(new GridLayout(2, 1));

        Color barColor;
        if (health >= 60) {
            barColor = Color.green;
        } else if (health >= 30) {
            barColor = Color.yellow;
        } else {
            barColor = Color.red;
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
        if (currentTime >= 48) {
            timeColor = Color.green;
        } else if (currentTime >= 24 && currentTime <= 47) {
            timeColor = Color.yellow;
        } else {
            timeColor = Color.red;
        }
        JLabel timeLabel = new JLabel();
        timeLabel.setText("Time Left= " + currentTime + " hours!!");
        timeLabel.setForeground(timeColor);
        timeLabel.setFont(panelFont);

        healthAndTimePanel.add(timeLabel);

        GUI.frame.setVisible(true);
    }

    public static void updateLocation(Room room) {
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
        east.setOpaque(true);
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

        JButton empty1 = new JButton("");
        JButton empty2 = new JButton("");
        JButton attackButton = new JButton();
        attackButton.setOpaque(false);
        attackButton.setBorderPainted(false);
        URL attackIcon = ClassLoader.getSystemClassLoader().getResource("Icons/attack_button.png");
        ImageIcon attackImage = new ImageIcon(attackIcon);
        attackImage.setImage(attackImage.getImage().getScaledInstance(85, 85, Image.SCALE_DEFAULT));
        attackButton.add(new JLabel(attackImage));

        JButton empty4 = new JButton("");
        JButton empty5 = new JButton("");
        JPanel compassPanel = GamePlay.compassPanel;
        compassPanel.removeAll();
        compassPanel.repaint();

        compassPanel.setBounds(700, 466, 300, 270);
        compassPanel.setBackground(Color.gray);
        compassPanel.setOpaque(true);

        HashMap<String, String> connectedRooms = (HashMap) room.getConnectedRooms();
        String currentRoom = room.getName();

        compassPanel.setLayout(new GridLayout(3, 3));
        compassPanel.add(empty1);
        empty1.setVisible(false);
        compassPanel.add(north);
        if (connectedRooms.get("north").equals("None")) {
            north.setOpaque(false);
            north.setEnabled(false);
        }
        compassPanel.add(empty2);
        empty2.setVisible(false);
        compassPanel.add(west);
        if (connectedRooms.get("west").equals("None")) {
            west.setOpaque(false);
            west.setEnabled(false);
        }
        attackButton.setForeground(Color.red);
        compassPanel.add(attackButton);
        if (!monsterMap.get(currentRoom).isEmpty()) {
            attackButton.setVisible(true);
        } else {
            attackButton.setVisible(false);
        }
        compassPanel.add(east);
        if (connectedRooms.get("east").equals("None")) {
            east.setOpaque(false);
            east.setEnabled(false);
        }
        compassPanel.add(empty4);
        empty4.setVisible(false);
        compassPanel.add(south);
        if (connectedRooms.get("south").equals("None")) {
            south.setOpaque(false);
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
                Music.playStartAudio("sword-strike");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        GUI.frame.setVisible(true);
    }

    public static void updateInventory(Room room, List<String> inventory, Controller gameController) {
        JPanel inventoryPanel = GamePlay.inventoryPanel;
        inventoryPanel.removeAll();
        inventoryPanel.setBounds(700, 786, 300, 216);
        inventoryPanel.setBackground(Color.DARK_GRAY);
        inventoryPanel.setOpaque(true);

        JPanel containerPanel = new JPanel();
        containerPanel.setBounds(700, 786, 300, 216);
        containerPanel.setBackground(Color.DARK_GRAY);
        containerPanel.setOpaque(true);
        containerPanel.setLayout(new GridLayout(3, 2));

        if (inventory.isEmpty()) {
            JPanel newItem = new JPanel();
            newItem.add(new JLabel("No Inventory Items"));
            newItem.setOpaque(false);
            containerPanel.add(newItem);
        } else {
            for (String item : inventory) {
                JPanel newItem = new JPanel();
                newItem.setOpaque(false);

                JButton showItem = new JButton(item);
                showItem.setForeground(Color.cyan);
                showItem.setBackground(Color.black);
                showItem.setOpaque(false);
                showItem.setBorderPainted(false);
                inventoryPanel.add(showItem);
                newItem.add(showItem);
                containerPanel.add(newItem);

                showItem.addActionListener(e -> {
                    try {
                        gameController.handleUserClick(new Response("use", "", item), room, gameController);
                        showItem.setVisible(false);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
            }
        }
        inventoryPanel.add(containerPanel);
        inventoryPanel.repaint();
    }

    public static void updateDescriptionPanel(Room room) {
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
    public static void updateDescriptionPanelText(String string) {
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


    public static void appendDescriptionPanelText(String string) {
        JPanel descriptionPanel = GamePlay.gameDescriptionPanel;

        text.append("\n" + string);

        descriptionPanel.repaint();
        GUI.frame.setVisible(true);
    }

    public static void updateStatPanel(int attack, int armor) {
        JPanel statPanel = GamePlay.statPanel;
        statPanel.removeAll();
        statPanel.setBounds(700, 125, 300, 75);
        statPanel.setBackground(Color.gray);
        statPanel.setOpaque(true);
        statPanel.setLayout(new GridLayout(2, 1));

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
    }
}




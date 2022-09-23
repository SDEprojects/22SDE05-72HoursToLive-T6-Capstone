package main.java.GUI;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static main.java.client.Client.repeatGameGUI;

public class EndingMenu {

    InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("Fonts/BloodyTerror-GOW9Z.ttf");
    Font endingFont = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(75f);
    URL diedImg = ClassLoader.getSystemClassLoader().getResource("Images/lose_fight.jpg");
    URL timeOutImg = ClassLoader.getSystemClassLoader().getResource("Images/portal_closing_lose.jpeg");
    URL winImg = ClassLoader.getSystemClassLoader().getResource("Images/portal_win.jpg");
    JPanel titlePanel;
    JLabel title;
    JPanel continueGamePanel;
    JButton continueGameButton;
    JPanel imagePanel;
    JButton quitGameButton;

    public EndingMenu(String string) throws IOException, FontFormatException, UnsupportedAudioFileException, LineUnavailableException {
        URL img = null;
        String text = "";
        if (string.equals("win")) {
            img = winImg;
            text = "You saved the world!";
        } else if (string.equals("wolf_win")) {
            img = diedImg;
            text = "The werewolves win!";

        } else {
            img = timeOutImg;
            text = "Time portal closed";
        }


        JFrame frame = GUI.frame;
        frame.getContentPane().removeAll();
        frame.repaint();
        // creates the title panel, and sets title name/font
        titlePanel = new JPanel();
        titlePanel.setBounds(0, 100, 1000, 200);
        titlePanel.setBackground(Color.black);
        titlePanel.setOpaque(false);
        title = new JLabel(text);
        title.setForeground(Color.red);
        title.setFont(endingFont);
        titlePanel.add(title);


        //creates continue game button and quit game button
        continueGamePanel = new JPanel();
        continueGamePanel.setBounds(0, 800, 1000, 100);
        continueGamePanel.setOpaque(false);
        continueGameButton = new JButton("New Game");
        continueGameButton.setForeground(Color.red);
        continueGameButton.setBackground(Color.black);
        continueGameButton.setFont(new Font("Helvetica", Font.BOLD, 28));
        continueGameButton.setOpaque(false);
        continueGameButton.setBorderPainted(false);
        continueGamePanel.add(continueGameButton);

        quitGameButton = new JButton("Quit Game");
        quitGameButton.setForeground(Color.red);
        quitGameButton.setBackground(Color.black);
        quitGameButton.setFont(new Font("Helvetica", Font.BOLD, 28));
        quitGameButton.setOpaque(false);
        quitGameButton.setBorderPainted(false);
        continueGamePanel.add(quitGameButton);

        //image for werewolf fight loss
        imagePanel = new JPanel();
        imagePanel.setBackground(Color.black);
        imagePanel.setBounds(0, 0, 1000, 1000);
        ImageIcon image = new ImageIcon(img);
        image.setImage(image.getImage().getScaledInstance(1000, 1000, Image.SCALE_DEFAULT));
        imagePanel.add(new JLabel(image));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBackground(Color.black);
        contentPanel.setOpaque(true);

        contentPanel.add(continueGamePanel);
        contentPanel.add(titlePanel);
        contentPanel.add(imagePanel);

        frame.add(contentPanel);

        continueGameButton.addActionListener(e -> {
            try {
                frame.dispose();
                repeatGameGUI();
            } catch (IOException | LineUnavailableException | UnsupportedAudioFileException | FontFormatException ex) {
                throw new RuntimeException(ex);
            }
        });
        //close game if choose to quit
        quitGameButton.addActionListener(e -> {
            System.exit(0);
        });
        frame.setVisible(true);
    }
}

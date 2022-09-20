package main.java.GUI;

import main.java.view.Music;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class StartMenu extends JFrame {

    InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("Fonts/BloodyTerror-GOW9Z.ttf");
    Font titleFont = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(75f);
    URL titleImageStream = ClassLoader.getSystemClassLoader().getResource("Images/TitleScreen.jpeg");

    JPanel titlePanel;
    JLabel title;

    JPanel newGamePanel;
    JButton newGameButton;

    JPanel imagePanel;

    JPanel difficultyPanel;
    JButton easyButton = new JButton("Easy");
    JButton mediumButton = new JButton("Medium");
    JButton hardButton = new JButton("Hard");
    JButton impossibleButton = new JButton("Impossible");

    Music music = new Music();


    public StartMenu(JFrame frame) throws IOException, FontFormatException, UnsupportedAudioFileException, LineUnavailableException {
        // creates the title panel, and sets title name/font
        titlePanel = new JPanel();
        titlePanel.setBounds(0, 100, 1000, 200);
        titlePanel.setBackground(Color.black);
        titlePanel.setOpaque(false);
        title = new JLabel("72 Hours To Live ");
        title.setForeground(Color.red);
        title.setFont(titleFont);
        titlePanel.add(title);

        //creates new game button and panel
        newGamePanel = new JPanel();
        newGamePanel.setBounds(0, 800, 1000, 100);
        newGamePanel.setOpaque(false);
        newGameButton = new JButton("START GAME");
        newGameButton.setForeground(Color.red);
        newGameButton.setBackground(Color.black);
        newGameButton.setFont(new Font("Helvetica", Font.BOLD, 28));
        newGameButton.setOpaque(false);
        newGameButton.setBorderPainted(false);
        newGamePanel.add(newGameButton);

        //Adding difficulty JPanel
        difficultyPanel = new JPanel();
        difficultyPanel.setBounds(0, 600, 1000, 50);
        difficultyPanel.setOpaque(false);
        difficultyPanel.setLayout(new GridLayout(1, 4));
        // easy button
        easyButton.setForeground(Color.green);
        easyButton.setOpaque(false);
        easyButton.setBorderPainted(false);
        easyButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        easyButton.setVisible(false);
        // med button
        mediumButton.setForeground(Color.yellow);
        mediumButton.setOpaque(false);
        mediumButton.setBorderPainted(false);
        mediumButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        mediumButton.setVisible(false);
        // hard button
        hardButton.setForeground(Color.orange);
        hardButton.setOpaque(false);
        hardButton.setBorderPainted(false);
        hardButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        hardButton.setVisible(false);
        // impossible button
        impossibleButton.setForeground(Color.red);
        impossibleButton.setOpaque(false);
        impossibleButton.setBorderPainted(false);
        impossibleButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        impossibleButton.setVisible(false);
        // adding difficulty buttons to panel
        difficultyPanel.add(easyButton);
        difficultyPanel.add(mediumButton);
        difficultyPanel.add(hardButton);
        difficultyPanel.add(impossibleButton);

        // Creating the background image for the title/intro
        imagePanel = new JPanel();
        imagePanel.setBackground(Color.black);
        imagePanel.setBounds(0, 0, 1000, 1000);
        ImageIcon img = new ImageIcon(titleImageStream);
        img.setImage(img.getImage().getScaledInstance(1000, 1000, Image.SCALE_DEFAULT));
        imagePanel.add(new JLabel(img));

        //creates Jpanel that allows other panels to move within it
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBackground(Color.black);
        contentPanel.setOpaque(true);

        //adding all panels to content panel
        contentPanel.add(titlePanel);
        contentPanel.add(newGamePanel);
        contentPanel.add(difficultyPanel);
        contentPanel.add(imagePanel);

        //adding content panel to JFrame
        frame.add(contentPanel);
        frame.setVisible(true);
        music.playMusic();

//BUTTON ACTION LISTENERS
        newGameButton.addActionListener(e -> {
            newGameButton.setVisible(false);
            easyButton.setVisible(true);
            mediumButton.setVisible(true);
            hardButton.setVisible(true);
            impossibleButton.setVisible(true);
        });

        easyButton.addActionListener(e -> {
            new GamePlay(frame);
            contentPanel.setVisible(false);
            GUI.optionButtons.setOpaque(true);
        });
        mediumButton.addActionListener(e -> {
            new GamePlay(frame);
            contentPanel.setVisible(false);
            GUI.optionButtons.setOpaque(true);
        });
        hardButton.addActionListener(e -> {
            new GamePlay(frame);
            contentPanel.setVisible(false);
            GUI.optionButtons.setOpaque(true);
        });
        impossibleButton.addActionListener(e -> {
            new GamePlay(frame);
            contentPanel.setVisible(false);
            GUI.optionButtons.setOpaque(true);
        });
    }
}
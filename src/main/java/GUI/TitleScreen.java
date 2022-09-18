package main.java.GUI;

import main.java.view.Music;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class TitleScreen extends JFrame {

    JPanel newGamePanel;
    JFrame frame;
    JPanel titlePanel;
    JButton newGameButton;
    JPanel optionButtons;
    JButton musicButton = new JButton("MUSIC");
    JButton helpButton = new JButton("HELP");
    JButton quitButton = new JButton("QUIT");
    JLabel title;
    InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("Fonts/BloodyTerror-GOW9Z.ttf");
    Font titleFont = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(75f);
    URL titleImageStream = ClassLoader.getSystemClassLoader().getResource("Images/TitleScreen.jpeg");
    JPanel imagePanel;

    JPanel difficultyPanel;
    JButton easyButton = new JButton("Easy");
    JButton mediumButton = new JButton("Medium");
    JButton hardButton = new JButton("Hard");
    JButton impossibleButton = new JButton("Impossible");
    Music music = new Music();


    public TitleScreen() throws IOException, FontFormatException, UnsupportedAudioFileException, LineUnavailableException {
        // sets new frame, size, default close operation, not resizeable
        frame = new JFrame("72 Hours to Live");
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);


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

        //creates options buttons and panel
        optionButtons = new JPanel();
        optionButtons.setBounds(0, 0, 1000, 50);
        optionButtons.setOpaque(false);
        musicButton.setForeground(Color.red);
        musicButton.setBackground(Color.black);
        musicButton.setOpaque(false);
        musicButton.setBorderPainted(false);
        musicButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        optionButtons.add(musicButton);
        helpButton.setForeground(Color.red);
        helpButton.setBackground(Color.black);
        helpButton.setOpaque(false);
        helpButton.setBorderPainted(false);
        helpButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        optionButtons.add(helpButton);
        quitButton.setForeground(Color.red);
        quitButton.setBackground(Color.black);
        quitButton.setOpaque(false);
        quitButton.setBorderPainted(false);
        quitButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        optionButtons.add(quitButton);
        optionButtons.setLayout(new GridLayout(1, 3));

        //*************************************************************************
        //Adding difficulty JPanel
        difficultyPanel = new JPanel();
        difficultyPanel.setBounds(0, 600, 1000, 50);
        difficultyPanel.setOpaque(false);
        difficultyPanel.setLayout(new GridLayout(1, 4));

        easyButton.setForeground(Color.green);
        easyButton.setOpaque(false);
        easyButton.setBorderPainted(false);
        easyButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        easyButton.setVisible(false);

        mediumButton.setForeground(Color.yellow);
        mediumButton.setOpaque(false);
        mediumButton.setBorderPainted(false);
        mediumButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        mediumButton.setVisible(false);

        hardButton.setForeground(Color.orange);
        hardButton.setOpaque(false);
        hardButton.setBorderPainted(false);
        hardButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        hardButton.setVisible(false);

        impossibleButton.setForeground(Color.red);
        impossibleButton.setOpaque(false);
        impossibleButton.setBorderPainted(false);
        impossibleButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        impossibleButton.setVisible(false);

        difficultyPanel.add(easyButton);
        difficultyPanel.add(mediumButton);
        difficultyPanel.add(hardButton);
        difficultyPanel.add(impossibleButton);
        //*********************************************************************************

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
        contentPanel.add(optionButtons);
        contentPanel.add(difficultyPanel);
        contentPanel.add(imagePanel);


        //adding content panel to JFrame
        frame.add(contentPanel);
        frame.setVisible(true);
        frame.setResizable(false);
        music.playMusic();

//BUTTON ACTION LISTENERS
        newGameButton.addActionListener(e -> {
//            helpButton.setVisible(false);
//            musicButton.setVisible(false);
//            quitButton.setVisible(false);
            newGameButton.setVisible(false);
            easyButton.setVisible(true);
            mediumButton.setVisible(true);
            hardButton.setVisible(true);
            impossibleButton.setVisible(true);

        });

        musicButton.addActionListener(e -> {
            try {
                Music.playerSelectMusic();
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        quitButton.addActionListener(e -> System.exit(0));
    }
}
package main.java.GUI;

import main.java.view.Music;
import main.java.view.Story;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class StartMenu extends JFrame {

    InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("Fonts/BloodyTerror-GOW9Z.ttf");
    Font titleFont = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(75f);
    URL titleImageStream = ClassLoader.getSystemClassLoader().getResource("Images/TitleScreen.jpeg");

    private static final ResourceBundle bundle = ResourceBundle.getBundle("main.resources.strings");


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

    JPanel introPanel;
    JTextArea introText;
    JButton introContinue = new JButton("Click here to continue........");

    Music music = new Music();
    private JTextArea textArea;
    private JLabel lbl;


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
        difficultyPanel.setVisible(false);
        difficultyPanel.setLayout(new GridLayout(1, 4));
        // easy button
        easyButton.setForeground(Color.green);
        easyButton.setOpaque(false);
        easyButton.setBorderPainted(false);
        easyButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        // med button
        mediumButton.setForeground(Color.yellow);
        mediumButton.setOpaque(false);
        mediumButton.setBorderPainted(false);
        mediumButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        // hard button
        hardButton.setForeground(Color.orange);
        hardButton.setOpaque(false);
        hardButton.setBorderPainted(false);
        hardButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        // impossible button
        impossibleButton.setForeground(Color.red);
        impossibleButton.setOpaque(false);
        impossibleButton.setBorderPainted(false);
        impossibleButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        // adding difficulty buttons to panel
        difficultyPanel.add(easyButton);
        difficultyPanel.add(mediumButton);
        difficultyPanel.add(hardButton);
        difficultyPanel.add(impossibleButton);

        //Creating intro screen
        introPanel = new JPanel();
        introPanel.setBounds(0, 650, 1000, 400);
        introPanel.setOpaque(false);
        introPanel.setVisible(false);
        introText = new JTextArea();
        introText.setBounds(0, 650, 1000, 400);
        introText.setLineWrap(true);
        introText.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
        introText.setBackground(Color.black);
        introText.setForeground(Color.red);
        introText.setWrapStyleWord(true);
        introText.setOpaque(false);
        introText.setText(bundle.getString("storyline"));

        introContinue.setForeground(Color.red);
        introContinue.setOpaque(false);
        introContinue.setBorderPainted(false);
        introContinue.setFont(new Font(Font.DIALOG, Font.BOLD, 20));

        introPanel.add(introText);
        introPanel.add(introContinue);

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
        contentPanel.add(introPanel);
        contentPanel.add(imagePanel);

        //adding content panel to JFrame
        frame.add(contentPanel);
        music.playMusic();

        //BUTTON ACTION LISTENERS
        newGameButton.addActionListener(e -> {
            newGameButton.setVisible(false);
            difficultyPanel.setVisible(true);
            impossibleButton.setVisible(true);
        });

        easyButton.addActionListener(e -> {
            Story.difficulty = 0;
            difficultyPanel.setVisible(false);
            introPanel.setVisible(true);
        });
        mediumButton.addActionListener(e -> {
            Story.difficulty = 4;
            difficultyPanel.setVisible(false);
            introPanel.setVisible(true);
        });
        hardButton.addActionListener(e -> {
            Story.difficulty = 7;
            difficultyPanel.setVisible(false);
            introPanel.setVisible(true);
        });
        impossibleButton.addActionListener(e -> {
            Story.difficulty = 11;
            difficultyPanel.setVisible(false);
            introPanel.setVisible(true);
        });
        introContinue.addActionListener(e -> {
            frame.remove(contentPanel);
            GUI.optionButtons.setOpaque(true);
            try {
                new GamePlay(frame);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        frame.setVisible(true);
    }
}
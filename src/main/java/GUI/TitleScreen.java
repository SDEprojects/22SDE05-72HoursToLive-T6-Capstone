package main.java.GUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class TitleScreen extends JFrame{

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


    public TitleScreen() throws IOException, FontFormatException {
        // sets new frame, size, default close operation, not resizeable
        frame = new JFrame("72 Hours to Live");
        frame.setSize(1000, 1200);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);



        // creates the title panel, and sets title name/font
        titlePanel = new JPanel();
        titlePanel.setBounds(0,100, 1000, 200);
        titlePanel.setBackground(Color.black);
        titlePanel.setOpaque(false);
        title = new JLabel("72 Hours To Live ");
        title.setForeground(Color.red);
        title.setFont(titleFont);
        titlePanel.add(title);

        //creates new game button and panel
        newGamePanel = new JPanel();
        newGamePanel.setBounds(0,900,1000, 100);
        newGamePanel.setOpaque(false);
        newGameButton = new JButton("START GAME");
        newGameButton.setForeground(Color.red);
        newGameButton.setBackground(Color.black);
        newGameButton.setFont(new Font("Helvetica", Font.BOLD, 30));
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

        imagePanel = new JPanel();
        imagePanel.setBackground(Color.black);
        imagePanel.setBounds(0,0,1000,1200);
        ImageIcon img = new ImageIcon(titleImageStream);
        img.setImage(img.getImage().getScaledInstance(1000, 1200, Image.SCALE_DEFAULT));
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
        contentPanel.add(imagePanel);

        //adding content panel to JFrame
        frame.add(contentPanel);
    }

}
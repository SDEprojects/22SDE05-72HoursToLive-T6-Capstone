package main.java.GUI;

import main.java.view.Music;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GUI {


    private final JFrame frame;
    static JPanel optionButtons;
    JButton musicButton = new JButton("MUSIC");
    JButton helpButton = new JButton("HELP");
    JButton quitButton = new JButton("QUIT");


    public GUI() throws IOException, FontFormatException, UnsupportedAudioFileException, LineUnavailableException {
        // sets persistent frame, size, default close operation, not resizeable
        frame = new JFrame("72 Hours to Live");
        frame.setBackground(Color.black);
        frame.setSize(1000, 1000);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // sets persistent options buttons on the frame
        optionButtons = new JPanel();
        optionButtons.setBounds(0, 0, 1000, 50);
        optionButtons.setBackground(Color.black);
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





        // action listeners for music, help, and quit buttons
        musicButton.addActionListener(e -> {
            try {
                Music.playerSelectMusic();
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        quitButton.addActionListener(e -> System.exit(0));
        frame.add(optionButtons);
        new StartMenu(frame);
    }
 }

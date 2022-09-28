package main.java.GUI;

import main.java.view.Music;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static main.java.view.Music.playerSelectEffect;

public class SettingsMenu {
    JButton musicButton = new JButton("MUSIC");
    JButton sfxButton = new JButton("SoundFX");
    JButton volumeDownButton = new JButton("Music Vol -");
    JButton volumeUpButton = new JButton("Music Vol +");

    public SettingsMenu() {

        JFrame settingsFrame = new JFrame("Settings");
        settingsFrame.setSize(400, 400);
        settingsFrame.setVisible(true);
        settingsFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel settingsPanel = new JPanel();
        settingsPanel.setBounds(0, 0, 400, 400);
        settingsPanel.setBackground(Color.black);
        settingsPanel.setOpaque(true);

        musicButton.setForeground(Color.red);
        musicButton.setBackground(Color.black);
        musicButton.setOpaque(false);
        musicButton.setBorderPainted(false);
        musicButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        settingsPanel.add(musicButton);

        sfxButton.setForeground(Color.red);
        sfxButton.setBackground(Color.black);
        sfxButton.setOpaque(false);
        sfxButton.setBorderPainted(false);
        sfxButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        settingsPanel.add(sfxButton);

        volumeDownButton.setForeground(Color.red);
        volumeDownButton.setBackground(Color.black);
        volumeDownButton.setOpaque(false);
        volumeDownButton.setBorderPainted(false);
        volumeDownButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        settingsPanel.add(volumeDownButton);

        volumeUpButton.setForeground(Color.red);
        volumeUpButton.setBackground(Color.black);
        volumeUpButton.setOpaque(false);
        volumeUpButton.setBorderPainted(false);
        volumeUpButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        settingsPanel.add(volumeUpButton);
        settingsPanel.setLayout(new GridLayout(2, 2));


        volumeUpButton.addActionListener(e ->
        {
            final float volume = Music.getVolume();
            if (volume < 0.90f) {
                Music.setVolume(volume + 0.10f);
            } else {
                Music.setVolume(1.0f);
            }
        });

        volumeDownButton.addActionListener(e ->

        {
            final float volume = Music.getVolume();

            if (volume > 0.10f) {
                Music.setVolume(volume - 0.10f);
            } else {
                Music.setVolume(0.0f);
            }
        });


        musicButton.addActionListener(e ->
        {
            try {
                Music.playerSelectMusic();
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        sfxButton.addActionListener(e ->
                playerSelectEffect());

        settingsFrame.setContentPane(settingsPanel);
        settingsFrame.setLocationRelativeTo(GUI.frame);
    }
}

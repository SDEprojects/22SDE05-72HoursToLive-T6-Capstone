package main.java.view;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class Music {
    public static boolean musicOn = true;

    public void playMusic() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File musicPath = new File("src/main/resources/sound.wav");
        AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInput);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (musicOn) {
                    clip.start();
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                    if (!musicOn) {
                        clip.stop();
                    }
                }
            }
        });
        thread.start();
    }

    public static void playerSelectMusic() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Music music = new Music();
        if (Music.musicOn) {
            Music.musicOn = false;
            System.out.println("Music turned off.\n");
        } else {
            Music.musicOn = true;
            System.out.println("Music turned on.\n");
            music.playMusic();
        }
    }
}

package main.java.view;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;


public class Music {
    public static boolean musicOn = true;

    public Music() throws UnsupportedAudioFileException, IOException {
    }

    public void playMusic() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();


        InputStream music = classLoader.getResourceAsStream("main/resources/sound.wav");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(music));

        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
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

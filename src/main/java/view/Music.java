package main.java.view;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;


public class Music {
    public static boolean musicOn = true;

    public Music() throws UnsupportedAudioFileException, IOException {
    }

    /**
     * method will play music and loop until playerSelectMusic() is called
     * @throws IOException
     * @throws UnsupportedAudioFileException
     * @throws LineUnavailableException
     */
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

    /**
     * When called the music will do the opposite of what it's currently set to.
     * @throws UnsupportedAudioFileException
     * @throws LineUnavailableException
     * @throws IOException
     */
    public static void playerSelectMusic() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Music music = new Music();
        if (Music.musicOn) {
            Music.musicOn = false;
            System.out.println(TextColor.WHITE + "\nMusic turned off.\n");
        } else {
            Music.musicOn = true;
            System.out.println(TextColor.WHITE + "\nMusic turned on.\n");
            music.playMusic();
        }
    }
}

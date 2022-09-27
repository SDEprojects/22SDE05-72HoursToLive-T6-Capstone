package main.java.view;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;


public class Music {
    public static boolean musicOn = true;
    private static boolean effectsOn = true;

    private static float volume;

    static Music music;
    static Clip clip;



    static {
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            music = new Music();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    private static final ResourceBundle bundle = ResourceBundle.getBundle("main.resources.strings");

    public Music() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    }

    /**
     * method will play music and loop until playerSelectMusic() is called
     *
     * @throws IOException
     * @throws UnsupportedAudioFileException
     * @throws LineUnavailableException
     */
    public void playMusic() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        clip.stop();
        clip.close();

        InputStream music = classLoader.getResourceAsStream("Sound/sound.wav");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(music));

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
     *
     * @throws UnsupportedAudioFileException
     * @throws LineUnavailableException
     * @throws IOException
     */
    public static void playerSelectMusic() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        //Music music = new Music();
        if (Music.musicOn) {
            Music.musicOn = false;
            System.out.println(TextColor.WHITE + bundle.getString("music_off"));
        } else {
            Music.musicOn = true;
            System.out.println(TextColor.WHITE + bundle.getString("music_on"));
            music.playMusic();

        }
    }

    //method for playing any audio through resources folder
    public static void playStartAudio(String string) {
        //Music music = new Music();
        if (Music.isEffectsOn()) {
            chooseAudio(string);
        }
    }

    public static void playerSelectEffect() {
        if (Music.isEffectsOn()) {
            Music.setEffectsOn(false);
        } else {
            Music.setEffectsOn(true);
        }
    }

    //Method to get any audio file and play
    public static void chooseAudio(String string) {
        try {
            AudioInputStream audioInputStream = null;
            audioInputStream = AudioSystem.getAudioInputStream(Music.class.getResource("/main/resources/Sound/" + string + ".wav"));
            Clip clip= AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isEffectsOn() {
        return effectsOn;
    }

    public static void setEffectsOn(boolean effectsOn) {
        Music.effectsOn = effectsOn;
    }

    public static float getVolume() {
        try {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            return (float) Math.pow(10f, gainControl.getValue() / 20f);
        } catch (Exception e) {
            return 0;
        }
    }

    public static void setVolume(float level) {
        if (musicOn) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(20f * (float) Math.log10(level));
        }
    }
}
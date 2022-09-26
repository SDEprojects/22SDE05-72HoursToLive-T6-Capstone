package main.java.GUI;

import main.java.model.Room;
import main.java.model.Werewolf;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

/**
 * Class creates a panel with a background image that is painted so other items can be added to the
 * panel as needed
 */
public class BackgroundPanel extends JPanel {
    Image image;
    public BackgroundPanel(Room room)
    {
        String currentRoom = room.getName();
        URL imgPath = ClassLoader.getSystemClassLoader().getResource("Images/" + currentRoom + ".jpeg");
        try
        {
            image = javax.imageio.ImageIO.read(imgPath);
        }
        catch (Exception e) { /*handled in paintComponent()*/ }
    }

    public BackgroundPanel(Room room, HashMap<String, List<Werewolf>> monsterMap) {
        String currentRoom = room.getName();
        if (!monsterMap.get(currentRoom).isEmpty()){
            String path = "Images/" + currentRoom + "_WW.jpeg";
            URL imgPath = ClassLoader.getSystemClassLoader().getResource(path);
            try
            {
                image = javax.imageio.ImageIO.read(imgPath);
            }
            catch (Exception e) { /*handled in paintComponent()*/ }

        }else {
            String path = "Images/" + currentRoom + ".jpeg";
            URL imgPath = ClassLoader.getSystemClassLoader().getResource(path);
            try
            {
                image = javax.imageio.ImageIO.read(imgPath);
            }
            catch (Exception e) { /*handled in paintComponent()*/ }
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (image != null)
            g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
    }
}

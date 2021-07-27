package saltysea.gameobjects;

import saltysea.SaltySeaPanel;
import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import saltysea.SaltySeaGUI;

public abstract class GameObject {

    protected double x;
    protected double y;
    protected double width;
    protected double height;
    public double speed;
    public double direction; // in degrees
    protected SaltySeaGUI gui;
    protected final SaltySeaPanel panel;
    protected final Rectangle2D.Double rectangle;
    public boolean[] toAnimate; // determines which list(s) should be animated when the object's draw method is called, false by default
    protected final LinkedList<BufferedImage>[] lists; // array containing Linked lists with Buffered Images arranged in a sequence for animation

    public GameObject(double x, double y, double speed, SaltySeaGUI gui, SaltySeaPanel panel, int numberOfAnimations) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = 90;
        this.gui = gui;
        this.panel = panel;
        this.rectangle = new Rectangle2D.Double();
        this.toAnimate = new boolean[numberOfAnimations];
        this.lists = new LinkedList[numberOfAnimations];
    }

    public void draw(Graphics2D g) {
        TexturePaint texture;

        for (int i = 0; i < lists.length; i++) {
            if (toAnimate[i] == true) {
                for (BufferedImage image : lists[i]) {
                    rectangle.setFrame(x, y, image.getWidth(), image.getHeight());
                    texture = new TexturePaint(image, rectangle);
                    g.setPaint(texture);
                    g.fill(rectangle);

                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        System.out.println("Something went wrong");
                    }
                }

                toAnimate[i] = false;
            }
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public LinkedList<BufferedImage> loadImages(LinkedList<BufferedImage> l, String keyWord) {
        // the variable keyWord contains a string that describes the action 
        // images will be named with the convention "keyWord" + 1, where 1 represents the first image of the animation
        // all of the images named with the string contained in the variable keyWord are contained in a folder with the same name

        try {
            for (int i = 0; i < new File("Images//" + keyWord).list().length; i++) {
                l.add(ImageIO.read(new File("Images//" + keyWord + "//" + String.valueOf(i) + ".png")));
            }
        } catch (IOException | NullPointerException e) {
            System.out.println(keyWord + ": file not found");
        }

        return l;
    }

    public Clip loadSound(String keyWord) {
        Clip clip = null;

        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("Audio//" + keyWord + ".wav")));
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            clip = null;
        }

        return clip;
    }

    public void move(double dx, double dy) {
        x += dx;
        y += dy;

        // Check if game object is off-screen
        if (x < 0) {
            x = gui.getGamePanelWidth();
        }

        if (x > gui.getGamePanelWidth()) {
            x = 0;
        }

        if (y < 0) {
            y = gui.getGamePanelHeight();
        }

        if (y > gui.getGamePanelHeight()) {
            y = 0;
        }
    }

    public Rectangle2D.Double getRectangle() {
        return rectangle;
    }
}

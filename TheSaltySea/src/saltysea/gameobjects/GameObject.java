package saltysea.gameobjects;

import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import saltysea.SaltySeaGUI;
import saltysea.SaltySeaPanel;

public abstract class GameObject {

    protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected double speed;
    protected double direction; // in degrees
    protected SaltySeaGUI gui;
    protected final SaltySeaPanel panel;
    private Rectangle2D.Double rectangle;
    private boolean run;
    public boolean[] toAnimate; // determines which list(s) should be animated when the object's draw method is called, false by default
    protected final LinkedList<BufferedImage>[] lists; // array containing Linked lists with Buffered Images arranged in a sequence for animation

    public GameObject(double x, double y, double speed, double direction, SaltySeaGUI gui, SaltySeaPanel panel, int numberOfAnimations) {
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
                    rectangle = new Rectangle2D.Double(x, y, image.getWidth(), image.getHeight());
                    texture = new TexturePaint(image, rectangle);
                    g.setPaint(texture);
                    g.fill(rectangle);
                }

                toAnimate[i] = false;
            }
        }
    }
    
    /*@Override
    public void run() {
        while (true) {
            try {
                panel.repaint();
                Thread.sleep(17);
            } catch (InterruptedException e) {
            }
        }
    }*/
    
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public LinkedList<BufferedImage> loadAnimationImages(LinkedList<BufferedImage> l, String keyWord) {
        // the variable keyWord contains a string that describes the action 
        // images will be named with the convention "keyWord" + 1, where 1 represents the first image of the animation
        // all of the images named with the string contained in the variable keyWord are contained in a folder with the same name
        
        try {
            for (int i = 0; i < new File(keyWord).list().length; i++) {
                l.add(ImageIO.read(new File(keyWord + "//" + String.valueOf(i) + ".png")));
                System.out.println(keyWord + "//" + String.valueOf(i) + ".png");
            }
        } catch (IOException | NullPointerException e) {
            System.out.println("file not found");
        }
        
        System.out.println(l.isEmpty() + " " + new File(keyWord).list().length);

        return l;
    }

    public Clip loadSound(String keyWord) {
        Clip clip = null;

        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(keyWord + ".wav")));
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
    
    /*public Rectangle2D.Double getRectangle() {
        
    }**/
}

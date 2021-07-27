package saltysea.gameobjects;

import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import saltysea.SaltySeaPanel;
import java.util.LinkedList;
import saltysea.SaltySeaGUI;

public class Player extends GameObject {

    private final Torpedo torpedo;

    public Player(double x, double y, double speed, SaltySeaGUI gui, SaltySeaPanel panel) {
        super(x, y, speed, gui, panel, 2);
        this.torpedo = new Torpedo(x, y, 45, this.gui, this.panel);
        this.direction = 0;
        // Load image files required for animation        
        super.lists[0] = super.loadImages(new LinkedList(), "player"); // player stationary
    }

    @Override
    public void draw(Graphics2D g) {
        TexturePaint texture;
        Graphics2D gCopy = (Graphics2D) g.create();

        for (int i = 0; i < lists.length; i++) {
            if (toAnimate[i] == true) {
                for (int j = 0; j < lists[i].size(); j++) {
                    rectangle.setFrame(x, y, lists[i].get(j).getWidth(), lists[i].get(j).getHeight());
                    texture = new TexturePaint(lists[i].get(j), rectangle);
                    g.rotate(Math.toRadians(direction), x + lists[i].get(j).getWidth() / 2, y + lists[i].get(j).getHeight() / 2);
                    g.setPaint(texture);
                    g.fill(rectangle);

                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        System.out.println("Something went wrong");
                    }
                    
                    if (i == 0 && j == lists[i].size() - 1) {
                        g.rotate(-Math.toRadians(direction), x + lists[i].get(j).getWidth() / 2, y + lists[i].get(j).getHeight() / 2);
                    }
                }

                toAnimate[i] = false;
            }
        }
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double rotate) {
        direction += rotate;
        
        if (direction < 0) {
            direction += 360;
        } else if (direction > 360) {
            direction -= 360;
        }
    }

    @Override
    public void move(double dx, double dy) {
        this.x += dx;
        this.y += dy;

        if (torpedo.toAnimate[0] == false) {
            torpedo.move(dx, dy); // move torpedo with the player
        }

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
}

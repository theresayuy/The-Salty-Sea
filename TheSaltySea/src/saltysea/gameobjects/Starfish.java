package saltysea.gameobjects;

import saltysea.SaltySeaPanel;
import java.util.LinkedList;
import saltysea.SaltySeaGUI;

public class Starfish extends Enemy {

    public final int size;

    public Starfish(double x, double y, double speed, int size, SaltySeaGUI gui, SaltySeaPanel panel) {
        super(x, y, speed, 0, 0, 2 + (size > 1 ? 1 : 0), gui, panel);
        this.size = size; // 1 = small, 2 = medium, 3 = large
        // Add lists that animate movement
        this.lists[0] = super.loadImages(new LinkedList(), (size == 1 ? "small" : size == 2 ? "medium" : "large") + " starfish"); // stationary starfish

        switch (size) {
            case 1: // small
                reward = 100;
                health = 200;
                break;
            case 2: // medium
                reward = 50;
                health = 100;
                lists[1] = super.loadImages(new LinkedList(), "split to small");
                break;
            case 3: // large
                reward = 20;
                health = 40;
                lists[1] = super.loadImages(new LinkedList(), "split to medium");
                break;
        }
    }

    @Override
    public void setHealth(int increment) {
        health += increment;

        if (health <= 0) {
            switch (size) {
                case 2: // Split medium starfish to 2 small starfish
                    panel.addGameObjects(2, 0, new Starfish(this.x, this.y, 10, 1, gui, panel)); // Add small starfishes to enemies list
                    break;
                case 3: // Split large starfish to 2 medium starfish
                    panel.addGameObjects(2, 0, new Starfish(this.x, this.y, 10, 2, gui, panel)); // Add medium starfishes to enemies list
                    break;
            }
            
            toAnimate[toAnimate.length - 1] = true; // to animate blood effect
            toAnimate[1] = true; // to animate splitting            
            gui.addScore(gui.getScore() + reward);            
        }
    }
}

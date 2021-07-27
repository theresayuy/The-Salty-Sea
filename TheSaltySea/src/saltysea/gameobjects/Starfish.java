package saltysea.gameobjects;

import java.util.LinkedList;
import saltysea.SaltySeaGUI;
import saltysea.SaltySeaPanel;

public class Starfish extends Enemy {

    int size;

    public Starfish(double x, double y, double speed, int size, SaltySeaGUI gui, SaltySeaPanel panel) {
        super(x, y, speed, 0, 0, 4 + (size == 3 ? 2 : size == 2 ? 1 : 0), gui, panel);
        this.size = size; // 1 = small, 2 = medium, 3 = large

        // Add lists that animate movement
        //super.lists[0] = super.loadAnimationImages(new LinkedList(), "sf" + (size == 1 ? "s" : size == 2 ? "m" : "l") + "right");
        //super.lists[1] = super.loadAnimationImages(new LinkedList(), "sf" + (size == 1 ? "s" : size == 2 ? "m" : "l") + "left");
        //super.lists[2] = super.loadAnimationImages(new LinkedList(), "sf" + (size == 1 ? "s" : size == 2 ? "m" : "l") + "up");
        //super.lists[3] = super.loadAnimationImages(new LinkedList(), "sf" + (size == 1 ? "s" : size == 2 ? "m" : "l") + "down");

        switch (size) {
            case 1: // small
                reward = 100;
                health = 200;
                break;
            case 2: // medium
                reward = 50;
                health = 100;
                //lists[4] = super.loadAnimationImages(new LinkedList(), "split to small");
                break;
            case 3: // large
                reward = 20;
                health = 40;
                /*lists[4] = super.loadAnimationImages(new LinkedList(), "split to small");
                lists[5] = super.loadAnimationImages(new LinkedList(), "split to medium");*/
                break;
        }
    }

    @Override
    public void setHealth(int increment) {
        health += increment;

        if (health <= 0) {
            panel.removeEnemy(this); // Kills the starfish by removing it from the list of enemies
            gui.setScore(gui.getScore() + reward);

            switch (size) {
                case 2: // Split medium starfish to 2 small starfish
                    panel.addEnemies(2, new Starfish(0, 0, 0, 1, gui, panel)); // Add small starfishes to enemies list
                    super.toAnimate[4] = true;
                    break;
                case 3: // Split large starfish to 2 medium starfish
                    panel.addEnemies(2, new Starfish(0, 0, 0, 2, gui, panel)); // Add medium starfishes to enemies list
                    super.toAnimate[5] = true;
                    break;
            }
        }
    }
}

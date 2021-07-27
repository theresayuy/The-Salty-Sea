package saltysea.gameobjects;

import java.util.LinkedList;
import saltysea.SaltySeaGUI;
import saltysea.SaltySeaPanel;

public class Player extends GameObject {

    private final SaltySeaPanel panel;

    public Player(double x, double y, double speed, SaltySeaGUI gui, SaltySeaPanel panel) {
        super(x, y, speed, gui, panel, 1);
        this.panel = panel;
        // Load image files required for animation        
        super.lists[0] = super.loadAnimationImages(new LinkedList(), "player"); // player stationary
        // super.lists[2] = super.loadAnimationImages(new LinkedList(), "player throw bomb");
        // Load sound files required for animation?
    }

    public void changeHealth(int change) { // Passing 1 increases health, passing -1 decreases health
        gui.setHealth(gui.getHealth() + change);

        if (gui.getHealth() <= 0) {
            panel.endGame();
        }
    }

    public void increaseScore(int score) {
        gui.setScore(gui.getScore() + score);
    }

    public void throwBomb() {
        if (gui.getBombs() > 0) {
            gui.setBombs(gui.getBombs() - 1); // decrease number of bombs by 1
        }
    }
}

package saltysea.gameobjects;

import java.awt.Graphics2D;
import saltysea.SaltySeaGUI;
import saltysea.SaltySeaPanel;

public abstract class Enemy extends GameObject {

    // Enemy statistics
    public int reward;
    public double health;
    public final SaltySeaPanel panel;

    public Enemy(double x, double y, double speed, int reward, double health, int numberOfLists, SaltySeaGUI gui, SaltySeaPanel panel) {
        super(x, y, speed, gui, panel, numberOfLists);
        this.reward = reward;
        this.health = health;
        this.panel = panel;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(int increment) {
        health += increment;
        
        if (health <= 0) {
            panel.removeEnemy(this); // Kills the enemy by removing it from the list of enemies
            gui.setScore(gui.getScore() + reward); // Increase player's score
        }
    }
}

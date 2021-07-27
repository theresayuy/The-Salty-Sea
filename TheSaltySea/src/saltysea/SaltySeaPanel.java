package saltysea;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import saltysea.gameobjects.Clam;
import saltysea.gameobjects.Enemy;
import saltysea.gameobjects.GameObject;
import saltysea.gameobjects.Player;
import saltysea.gameobjects.PowerUp;
import saltysea.gameobjects.Pufferfish;
import saltysea.gameobjects.Squid;
import saltysea.gameobjects.Starfish;
import saltysea.listeners.KeyboardActions;
import saltysea.listeners.MouseActions;
import saltysea.listeners.TimeListener;

public class SaltySeaPanel extends JPanel {

    private final SaltySeaGUI gui;
    private int level;
    
    // GameObjects for panel to draw
    private final Player player;
    private final Clam clam;
    private final LinkedList<Enemy> enemies;
    private final LinkedList<PowerUp> powerUps;
    // GUI's listeners
    private final TimeListener tl;
    private final MouseActions ma;
    private final KeyboardActions ka;
    
    private final Image image;

    public SaltySeaPanel(SaltySeaGUI gui) throws IOException {
        this.gui = gui;
        this.level = 0;
        this.image = ImageIO.read(new File("60313822_428991834586018_6704758435151347712_n.png")); // background
        this.player = new Player(3, 3, 0, gui, this); // Modify x, y, and speed
        this.clam = new Clam(300, 300, 0, gui, this);
        this.enemies = new LinkedList();
        this.powerUps = new LinkedList();
        this.tl = new TimeListener(gui, this, player);
        this.ma = new MouseActions(this);
        this.ka = new KeyboardActions(this);
        
    }

    public void addEnemies(int quantity, Enemy e) {
        for (int i = 0; i < quantity; i++) {
            enemies.add(e);
        }
    }

    public boolean allEnemiesDead() {
        return enemies.isEmpty();
    }

    public void endGame() {

    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    public KeyboardActions getKeyListener() {
        return ka;
    }

    public MouseActions getMouseListener() {
        return ma;
    }

    public Player getPlayer() {
        return player;
    }
    
    // Manages the number of enemies and powerups available per level, resets GUI for every new level
    public void increaseLevel() {         
        // first 4 indexes represent the number of large starfish for the first 4 levels
        int[] largeSFQuantity = {4, 4, 4, 5}; 
        int[] mediumSFQuantity = {0, 1, 1, 1};
        int[] smallSFQuantity = {0, 0, 1, 1};
        int[] pufferfishQuantity = {0, 1, 2, 2};
        int[] clamQuantity = {0, 1, 1, 2};
        int[] squidQuantity = {0, 0, 1, 1};

        level += 1;

        // Add enemies required for the level into the enemies list
        enemies.clear();
        this.addEnemies(largeSFQuantity[Math.min(level - 1, 3)] + (level > 4 && level < 34 ? (level - 4) : level >= 34 ? 30 : 0), new Starfish(0, 0, 0, 3, gui, this));
        this.addEnemies(mediumSFQuantity[Math.min(level - 1, 3)] + (level > 4 && level < 34 ? (level - 4) : level >= 34 ? 30 : 0), new Starfish(0, 0, 0, 2, gui, this));
        this.addEnemies(smallSFQuantity[Math.min(level - 1, 3)] + (level > 4 && level < 34 ? (level - 4) : level >= 34 ? 30 : 0), new Starfish(0, 0, 0, 1, gui, this));
        this.addEnemies(pufferfishQuantity[Math.min(level - 1, 3)] + (level > 4 && level < 34 ? (level - 4) : level >= 34 ? 30 : 0), new Pufferfish(0, 0, 0, gui, this));
        //this.addEnemies(clamQuantity[Math.min(level - 1, 3)] + (level > 4 && level < 34 ? (level - 4) : level >= 34 ? 30 : 0), clam);
        this.addEnemies(squidQuantity[Math.min(level - 1, 3)] + (level > 4 && level < 34 ? (level - 4) : level >= 34 ? 30 : 0), new Squid(0, 0, 0, gui, this));

        for (Enemy e : enemies) {
            e.move(1, 1);
        }
        
        
        gui.setScore(0);
        player.changeHealth(1);
        tl.startTimer();
        player.toAnimate[0] = true;
        clam.toAnimate[0] = true;
        this.repaint();
    }
    
    public LinkedList<GameObject> detectCollisions() {
        
        return new LinkedList();
    }
    
    @Override
    public void paintComponent(Graphics graphics) {
        //call draw methods from Enemy.java and GameObject.java
        super.paintComponent(graphics);
        graphics.drawImage(image, 0, 0, null);
        player.draw((Graphics2D)graphics);
        clam.draw((Graphics2D)graphics);
    }
}

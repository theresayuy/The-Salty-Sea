package saltysea.gameobjects;

import java.awt.Graphics2D;
import java.util.LinkedList;
import saltysea.SaltySeaGUI;
import saltysea.SaltySeaPanel;

public class Clam extends Enemy {

    public Clam(double x, double y, double speed, SaltySeaGUI gui, SaltySeaPanel panel) {
        super(x, y, speed, 500, 400, 1, gui, panel); // modify
        // Load image files required for animation        
        super.lists[0] = super.loadAnimationImages(new LinkedList(), "clam"); // one image
        //super.lists[1] = super.loadAnimationImages(new LinkedList(), "clam shoot");
    }
}

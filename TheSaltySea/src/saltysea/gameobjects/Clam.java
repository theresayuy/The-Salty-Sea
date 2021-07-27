package saltysea.gameobjects;

import saltysea.SaltySeaPanel;
import java.util.LinkedList;
import saltysea.SaltySeaGUI;

public class Clam extends Enemy {

    public Clam(double x, double y, double speed, SaltySeaGUI gui, SaltySeaPanel panel) {
        super(x, y, speed, 500, 400, 3, gui, panel); // modify
        // Load image files required for animation        
        lists[0] = super.loadImages(new LinkedList(), "clam"); // stationary clam
        lists[1] = super.loadImages(new LinkedList(), "damaged clam"); // damaged clam
    }
}

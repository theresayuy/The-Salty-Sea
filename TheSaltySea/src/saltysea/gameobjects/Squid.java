package saltysea.gameobjects;

import saltysea.SaltySeaPanel;
import java.util.LinkedList;
import saltysea.SaltySeaGUI;

public class Squid extends Enemy {
    
    public Squid(double x, double y, double speed, SaltySeaGUI gui, SaltySeaPanel panel) {
        super(x, y, speed, 500, 400, 3, gui, panel); // modify;
        // load images
        lists[0] = super.loadImages(new LinkedList(), "squid"); // stationary squid
        lists[1] = super.loadImages(new LinkedList(), "damaged squid");
        lists[2] = super.loadImages(new LinkedList(), "blue blood"); 
    }
}

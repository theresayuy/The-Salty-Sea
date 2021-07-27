package saltysea.gameobjects;

import saltysea.SaltySeaGUI;
import saltysea.SaltySeaPanel;

public class Squid extends Enemy {
    
    public Squid(double x, double y, double speed, SaltySeaGUI gui, SaltySeaPanel panel) {
        super(x, y, speed, 500, 400, 0, gui, panel); // modify;
    }
}

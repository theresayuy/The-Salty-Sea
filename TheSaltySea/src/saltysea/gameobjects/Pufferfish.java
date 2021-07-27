package saltysea.gameobjects;

import saltysea.SaltySeaGUI;
import saltysea.SaltySeaPanel;

public class Pufferfish extends Enemy {

    public Pufferfish(double x, double y, double speed, SaltySeaGUI gui, SaltySeaPanel panel) {
        super(x, y, speed, 1000, 600, 0, gui, panel); // modify 
    }

}

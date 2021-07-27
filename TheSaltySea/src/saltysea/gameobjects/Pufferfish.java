package saltysea.gameobjects;

import saltysea.SaltySeaPanel;
import java.util.LinkedList;
import saltysea.SaltySeaGUI;

public class Pufferfish extends Enemy {

    public Pufferfish(double x, double y, double speed, SaltySeaGUI gui, SaltySeaPanel panel) {
        super(x, y, speed, 1000, 600, 3, gui, panel); // modify 
        lists[0] = super.loadImages(new LinkedList(), "pufferfish");
        lists[1] = super.loadImages(new LinkedList(), "damaged pufferfish");
    }
}

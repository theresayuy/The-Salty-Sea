package saltysea.gameobjects;

import java.util.LinkedList;
import saltysea.SaltySeaGUI;
import saltysea.SaltySeaPanel;

public class PowerUp extends GameObject {
      
    public PowerUp(double x, double y, double speed, SaltySeaGUI gui, SaltySeaPanel panel) {
        super(x, y, speed, gui, panel, 6);
        //lists[0] = super.loadAnimationImages(new LinkedList(), "heart appears");
        //lists[1] = super.loadAnimationImages(new LinkedList(), "heart disappears"); // when player collects a health
        lists[2] = super.loadAnimationImages(new LinkedList(), "bomb appears");
        lists[3] = super.loadAnimationImages(new LinkedList(), "bomb disappears"); // when player collects a bomb
        //lists[4] = super.loadAnimationImages(new LinkedList(), "bomb thrown"); // bomb travelling in a projectile   
        lists[5] = super.loadAnimationImages(new LinkedList(), "bomb explodes"); 
    }
}

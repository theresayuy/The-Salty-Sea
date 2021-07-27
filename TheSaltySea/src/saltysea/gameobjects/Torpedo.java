/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saltysea.gameobjects;

import java.util.LinkedList;
import saltysea.SaltySeaGUI;
import saltysea.SaltySeaPanel;

/**
 *
 * @author 344836325
 */
public class Torpedo extends GameObject {
    
    public Torpedo(double x, double y, double speed, SaltySeaGUI gui, SaltySeaPanel panel) {
        super(x, y, speed, gui, panel, 1); 
        // Load images in lists
        lists[0] = super.loadImages(new LinkedList(), "torpedo");
    }
    
    // torpedo is in the same direction as the player
    
//    public void shoot() {
//        this.direction = this.panel.getPlayer().getDirection();
//        this.toAnimate[0] = true; 
//    }
}

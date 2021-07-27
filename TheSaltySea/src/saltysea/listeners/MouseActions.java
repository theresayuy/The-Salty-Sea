package saltysea.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import saltysea.SaltySeaPanel;

public class MouseActions implements MouseListener, MouseMotionListener {

    private final SaltySeaPanel panel;

    public MouseActions(SaltySeaPanel panel) {
        this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                // left mouse button clicked
                // shoot
                break;
            case MouseEvent.BUTTON3:
                // right mouse button clicked
                // throw bomb
                try {
                    panel.getUnfiredBomb().fire();
                } catch (Exception exception) {
                    
                }
                break;
        }

         panel.repaint();        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}

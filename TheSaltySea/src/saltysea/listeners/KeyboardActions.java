package saltysea.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import saltysea.SaltySeaPanel;

public class KeyboardActions implements KeyListener {

    private final SaltySeaPanel panel;

    public KeyboardActions(SaltySeaPanel panel) {
        this.panel = panel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_B:
                // throw bomb
                try {
                    panel.getUnfiredBomb().fire();
                } catch (Exception exception) {
                    
                }
                break;
            case KeyEvent.VK_SPACE:
                // shoot
                break;
            case KeyEvent.VK_LEFT:
                // move left 
                panel.getPlayer().setDirection(-10);
                break;
            case KeyEvent.VK_RIGHT:
                // move right
                panel.getPlayer().setDirection(10);
                break;
            case KeyEvent.VK_DOWN:
                // deacellerate
                panel.getPlayer().speed = Math.max(panel.getPlayer().speed - 1, 0) ;
                break;
            case KeyEvent.VK_UP:
                // acellerate
                panel.getPlayer().speed += 1;
                break;
        }

        panel.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

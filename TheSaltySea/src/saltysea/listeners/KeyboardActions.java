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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_B:
                // throw bomb
                panel.getPlayer().throwBomb();
                break;
            case KeyEvent.VK_SPACE:
                // shoot
                break;
            case KeyEvent.VK_LEFT:
                panel.getPlayer().move(-1, 0);
                break;
            case KeyEvent.VK_RIGHT:
                // move right
                panel.getPlayer().move(1, 0);
                break;
            case KeyEvent.VK_UP:
                // move up
                panel.getPlayer().move(0, -1);
                break;
            case KeyEvent.VK_DOWN:
                // move down
                panel.getPlayer().move(0, 1);
                break;
        }

        panel.getPlayer().toAnimate[0] = true;
        panel.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

package saltysea.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import saltysea.SaltySeaGUI;
import saltysea.SaltySeaPanel;

public class TimeListener implements ActionListener {

    private final SaltySeaGUI gui;
    private final SaltySeaPanel panel;
    private final Timer timer;

    public TimeListener(SaltySeaGUI gui, SaltySeaPanel panel) {
        this.gui = gui;
        this.panel = panel;
        this.timer = new Timer(1000, this);
    }
    
    public void startTimer() {
        gui.setTime(true);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.setTime(false); // Increases the time by one second

        if (gui.getScore() >= 10000 && gui.getScore() % 10000 == 0) { // increases health for every 10000 points
            gui.addHealth(1);
            gui.addScore(gui.getScore() - 10000);
        }
        
        if (panel.allEnemiesDead() || gui.getHealth() == 0) {
            timer.stop();
            //panel.increaseLevel(panel.allEnemiesDead() && gui.getHealth() > 0); // The level is successfully completed if all enemies are dead before the player
        }
    }
}

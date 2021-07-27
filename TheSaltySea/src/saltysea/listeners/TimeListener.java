package saltysea.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.Timer;
import saltysea.SaltySeaGUI;
import saltysea.SaltySeaPanel;
import saltysea.gameobjects.GameObject;
import saltysea.gameobjects.Player;

public class TimeListener implements ActionListener {

    private final SaltySeaGUI gui;
    private final SaltySeaPanel panel;
    private final Player player;
    private final Timer timer;

    public TimeListener(SaltySeaGUI gui, SaltySeaPanel panel, Player player) {
        this.gui = gui;
        this.panel = panel;
        this.player = player;
        this.timer = new Timer(1000, this);
    }
    
    

    public void startTimer() {
        gui.setTime(true);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.setTime(false); // Increases the time by one second

        if (gui.getScore() >= 10000) {
            player.changeHealth(1);
            gui.setScore(gui.getScore() - 10000);
        }
        
        if (panel.allEnemiesDead()) {
            timer.stop();
            panel.increaseLevel(); // The level is successfully completed if all enemies are dead before the player
        }
    }
}

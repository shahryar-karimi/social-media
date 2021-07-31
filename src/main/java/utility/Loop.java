package utility;

import logic.Manager;

import javax.swing.Timer;
import java.awt.event.ActionEvent;

public class Loop extends Thread{
    private final Manager manager;
    private final Timer timer;

    public Loop(Manager manager) {
        this.manager = manager;
        this.timer = new Timer(5000, this::timerTask);
    }

    @Override
    public void run() {
        timer.start();
    }

    private void timerTask(ActionEvent e) {
        manager.update();
    }
}

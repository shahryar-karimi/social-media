package utility;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Loop extends Thread {
    private final Timer timer;

    public Loop(ActionListener timerTask) {
        this.timer = new Timer(1000, timerTask);
    }

    @Override
    public void run() {
        timer.start();
    }
}

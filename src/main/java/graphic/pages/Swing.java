package graphic.pages;

import graphic.MyFrame;
import logic.Logger.MyLogger;

import java.awt.event.ActionListener;

public abstract class Swing implements ActionListener {
    protected final MyLogger myLogger;

    protected MyFrame frame;

    public Swing() {
        myLogger = MyLogger.getLogger();
    }

    public abstract void run();

    public abstract void showGraphic();
}

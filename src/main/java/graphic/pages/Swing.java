package graphic.pages;

import graphic.FooterPanel;
import graphic.GraphicManager;
import graphic.MyFrame;
import logic.Logger.MyLogger;
import logic.pages.Page;

import java.awt.event.ActionListener;

public abstract class Swing extends MyFrame implements ActionListener {

    protected final MyLogger myLogger;
    private static GraphicManager manager;
    protected FooterPanel footerPanel;
    protected Page page;

    public Swing() {
        myLogger = MyLogger.getLogger();
    }

    public GraphicManager getManager() {
        if (manager == null) manager = new GraphicManager();
        return manager;
    }

    public void addSwing(Swing swing) {
        getManager().addSwing(swing);
    }

    public abstract void run();

    public abstract void showGraphic();

    public abstract void updateGraphic();
}

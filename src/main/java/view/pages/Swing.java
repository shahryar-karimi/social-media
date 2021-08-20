package view.pages;

import listener.FormListener;
import model.logger.MyLogger;
import view.MyFrame;
import view.controller.GraphicManager;
import view.myPanels.footerPanel.view.FooterPanel;

import java.awt.event.ActionListener;

public abstract class Swing extends MyFrame implements ActionListener {
    protected final MyLogger myLogger;
    protected FormListener listener;
    protected FooterPanel footerPanel;

    public Swing() {
        myLogger = MyLogger.getLogger();
    }

    public GraphicManager getManager() {
        return listener.getController().getPage().getManager().getGraphicManager();
    }

    public MyLogger getMyLogger() {
        return myLogger;
    }

    public void addSwing(Swing swing) {
        getManager().addSwing(swing);
    }

    public FormListener getListener() {
        return listener;
    }

    public abstract void run();

    public abstract void showGraphic();

    public abstract void updateGraphic();
}

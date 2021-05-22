package graphic.Pages;

import graphic.MyFrame;
import logic.Logger.MyLogger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Scanner;

public abstract class Swing implements ActionListener {
    protected final MyLogger myLogger;

    MyFrame frame;
    public Swing() {

        myLogger = MyLogger.getLogger();

        //frame = new MyFrame();
    }

    public abstract void run();

    public abstract void showGraphic();
}

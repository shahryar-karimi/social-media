package cLI;

import logic.Logger.MyLogger;

import java.util.Scanner;

public abstract class CLI {
    protected final Scanner scanner = new Scanner(System.in);
    protected final MyLogger myLogger;

    public CLI() {
        myLogger = MyLogger.getLogger();
    }

    public abstract void run();
}

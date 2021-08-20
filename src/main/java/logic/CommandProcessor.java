package logic;

import model.logger.MyLogger;

import javax.swing.*;

public class CommandProcessor {

    private Manager manager;

    public CommandProcessor() {
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void loadManager() {
        setManager(DBManger.load());
        if (getManager() == null) setManager(new Manager());
    }

    public void run() {
        MyLogger myLogger = MyLogger.getLogger();
        myLogger.debug(CommandProcessor.class.getName(), "run", "program started");
        runFrame();
    }

    private void runFrame() {
        SwingUtilities.invokeLater(() -> manager.goToLoginPage());
    }
}

package listener;

import controller.MainController;
import event.Event;

public abstract class FormListener {
    protected MainController controller;

    public abstract String eventOccurred(Event event);

    public MainController getController() {
        return controller;
    }
}

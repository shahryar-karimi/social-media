package view.pages.timeline.listener;

import event.Event;
import listener.FormListener;
import view.pages.timeline.controller.TimeLineController;

public class TimeLineListener extends FormListener {

    public TimeLineListener(TimeLineController controller) {
        this.controller = controller;
    }

    @Override
    public String eventOccurred(Event event) {
        return null;
    }
}

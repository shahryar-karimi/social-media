package view.pages.menuPage.event;

import event.Event;

public class MenuEvent extends Event {
    private final String work;

    public MenuEvent(Object source, String work) {
        super(source);
        this.work = work;
    }

    public String getWork() {
        return work;
    }
}

package view.pages.setting.events;

import event.Event;

public class CheckPasswordEvent extends Event {
    private final String lastPass;

    public CheckPasswordEvent(Object source, String lastPass) {
        super(source);
        this.lastPass = lastPass;
    }

    public String getLastPass() {
        return lastPass;
    }
}

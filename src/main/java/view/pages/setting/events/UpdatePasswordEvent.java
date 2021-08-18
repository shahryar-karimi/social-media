package view.pages.setting.events;

import event.Event;

public class UpdatePasswordEvent extends Event {
    private final String newPass;

    public UpdatePasswordEvent(Object source, String newPass) {
        super(source);
        this.newPass = newPass;
    }

    public String getNewPass() {
        return newPass;
    }
}

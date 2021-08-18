package view.pages.setting.events;

import event.Event;

public class SettingActivityEvent extends Event {
    private final String item;

    public SettingActivityEvent(Object source, String item) {
        super(source);
        this.item = item;
    }

    public String getItem() {
        return item;
    }
}

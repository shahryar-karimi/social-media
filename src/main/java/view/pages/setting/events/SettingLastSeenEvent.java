package view.pages.setting.events;

import event.Event;

public class SettingLastSeenEvent extends Event {
    private final String item;

    public SettingLastSeenEvent(Object source, String item) {
        super(source);
        this.item = item;
    }

    public String getItem() {
        return item;
    }
}

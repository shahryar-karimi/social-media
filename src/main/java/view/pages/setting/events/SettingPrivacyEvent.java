package view.pages.setting.events;

import event.Event;

public class SettingPrivacyEvent extends Event {
    private final String item;

    public SettingPrivacyEvent(Object source, String item) {
        super(source);
        this.item = item;
    }

    public String getItem() {
        return item;
    }
}

package view.pages.messages.chatRoom.events;

import event.Event;

public class SendMessageEvent extends Event {
    private final String text;

    public SendMessageEvent(Object source, String text) {
        super(source);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}

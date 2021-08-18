package view.pages.messages.messenger.events;

import event.Event;
import model.pages.messenger.ChatRoom;

import java.util.HashSet;

public class SendSeveralMessageEvent extends Event {
    private final String text;
    private final HashSet<ChatRoom> selectedList;

    public SendSeveralMessageEvent(Object source, String text, HashSet<ChatRoom> selectedList) {
        super(source);
        this.text = text;
        this.selectedList = selectedList;
    }

    public HashSet<ChatRoom> getSelectedList() {
        return selectedList;
    }

    public String getText() {
        return text;
    }
}

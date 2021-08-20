package view.pages.messages.chatRoom.events;

import event.Event;
import model.pages.messenger.Message;

import java.util.HashSet;

public class ForwardEvent extends Event {
    private final Message message;
    private final HashSet<String> userNames;
    public ForwardEvent(Object source, Message message, HashSet<String> userNames) {
        super(source);
        this.message = message;
        this.userNames = userNames;
    }

    public Message getMessage() {
        return message;
    }

    public HashSet<String> getUserNames() {
        return userNames;
    }
}

package view.pages.messages.messenger.events;

import event.Event;

public class OpenChatRoomEvent extends Event {
    private final String userName;

    public OpenChatRoomEvent(Object source, String userName) {
        super(source);
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}

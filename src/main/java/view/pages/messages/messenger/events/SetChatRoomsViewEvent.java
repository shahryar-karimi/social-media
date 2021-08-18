package view.pages.messages.messenger.events;

import event.Event;

import java.util.LinkedList;

public class SetChatRoomsViewEvent extends Event {
    private final LinkedList<String> chatRoomView;

    public SetChatRoomsViewEvent(Object source, LinkedList<String> chatRoomView) {
        super(source);
        this.chatRoomView = chatRoomView;
    }

    public LinkedList<String> getChatRoomView() {
        return chatRoomView;
    }
}

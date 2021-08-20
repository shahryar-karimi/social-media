package view.pages.messages.chatRoom.listener;

import event.Event;
import listener.FormListener;
import view.pages.messages.chatRoom.controller.ChatRoomController;
import view.pages.messages.chatRoom.events.ForwardEvent;
import view.pages.messages.chatRoom.events.SendMessageEvent;

public class ChatRoomListener extends FormListener {

    public ChatRoomListener(ChatRoomController controller) {
        this.controller = controller;
    }

    @Override
    public String eventOccurred(Event event) {
        if (event instanceof SendMessageEvent) {
            SendMessageEvent sendMessageEvent = (SendMessageEvent) event;
            return ((ChatRoomController) controller).sendMessage(sendMessageEvent.getText());
        } else if (event instanceof ForwardEvent) {
            ForwardEvent forwardEvent = (ForwardEvent) event;
            return ((ChatRoomController) controller).forward(forwardEvent.getUserNames(), forwardEvent.getMessage());
        }
        return null;
    }
}

package view.pages.messages.messenger.listener;

import event.Event;
import listener.FormListener;
import view.pages.messages.messenger.controller.MessengersController;
import view.pages.messages.messenger.events.*;

public class MessengersListener extends FormListener {

    public MessengersListener(MessengersController controller) {
        this.controller = controller;
    }

    @Override
    public String eventOccurred(Event event) {
        if (event instanceof SetChatRoomsViewEvent) {
            SetChatRoomsViewEvent setChatRoomsViewEvent = (SetChatRoomsViewEvent) event;
            return ((MessengersController) controller).setChatRoomsView(setChatRoomsViewEvent.getChatRoomView());
        } else if (event instanceof OpenChatRoomEvent) {
            OpenChatRoomEvent openChatRoomEvent = (OpenChatRoomEvent) event;
            return ((MessengersController) controller).openChatRoom(openChatRoomEvent.getUserName());
        } else if (event instanceof NewChatListEvent) {
            NewChatListEvent newChatListEvent = (NewChatListEvent) event;
            return ((MessengersController) controller).newChatList(newChatListEvent.getAccounts());
        } else if (event instanceof BuildNewChatRoomEvent) {
            BuildNewChatRoomEvent buildNewChatRoomEvent = (BuildNewChatRoomEvent) event;
            return ((MessengersController) controller).buildNewChatRoom(buildNewChatRoomEvent.getAccountList(),
                    buildNewChatRoomEvent.getUserName());
        } else if (event instanceof SendSeveralMessageEvent) {
            SendSeveralMessageEvent sendSeveralMessageEvent = (SendSeveralMessageEvent) event;
            return ((MessengersController) controller).sendSeveralMessage(sendSeveralMessageEvent.getText(),
                    sendSeveralMessageEvent.getSelectedList());
        }
        return null;
    }
}

package view.pages.messages.chatRoom.controller;

import model.pages.messenger.ChatRoom;
import model.pages.messenger.Message;
import view.controller.MainGraphicController;

public class ChatRoomController extends MainGraphicController {

    public ChatRoomController(ChatRoom page) {
        super(page);
    }

    public String sendMessage(String text) {
        Message message = ((ChatRoom) page).writeNewMessage(text);
        return String.valueOf(((ChatRoom) page).sendMessage(message));
    }
}

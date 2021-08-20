package view.pages.messages.chatRoom.controller;

import model.Account;
import model.pages.messenger.ChatRoom;
import model.pages.messenger.Message;
import view.controller.MainGraphicController;

import java.util.ArrayList;
import java.util.HashSet;

public class ChatRoomController extends MainGraphicController {

    public ChatRoomController(ChatRoom page) {
        super(page);
    }

    public String sendMessage(String text) {
        Message message = ((ChatRoom) page).writeNewMessage(text);
        return String.valueOf(((ChatRoom) page).sendMessage(message));
    }

    public String forward(HashSet<String> userNames, Message message) {
        ArrayList<Account> accounts = new ArrayList<>();
        for (String userName : userNames)
            accounts.add(page.getManager().searchByUserName(userName));
        return ((ChatRoom) page).forwardMessage(accounts, message.clone());
    }
}

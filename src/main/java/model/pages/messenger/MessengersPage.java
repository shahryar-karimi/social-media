package model.pages.messenger;

import logic.Manager;
import model.Account;
import model.pages.Page;

import java.util.ArrayList;
import java.util.LinkedList;

public class MessengersPage extends Page {

    private final LinkedList<ChatRoom> chatRooms;

    public MessengersPage(Account account, Manager manager) {
        super(account, manager);
        account.setMessagesPage(this);
        chatRooms = new LinkedList<>();
        chatRooms.add(new ChatRoom(manager, account, account.getUserName()));
        chatRooms.remove(0);
    }

    public ChatRoom buildNewChatRoom(Account listener) {
        if (listener != null && account.isValidToSendMessage(listener)) {
            ChatRoom myChatRoom = new ChatRoom(manager, account, listener.getUserName());
            new ChatRoom(manager, listener, account.getUserName());
            return myChatRoom;
        }
        return null;
    }

    public String sendMessage(Message message, ChatRoom chatRoom) {
        if (chatRoom == null) {
            chatRoom = buildNewChatRoom(account);
            if (chatRoom == null) {
                return "You can't send message to " + account;
            }
        }
        if (!chatRoom.sendMessage(message)) {
            return "Failed to send message to " + account;
        }
        return "Message sent to " + account;
    }

    public LinkedList<ChatRoom> getChatRooms() {
        return chatRooms;
    }

    public ChatRoom searchChatRoomsByUserName(String userName) {
        if (userName.equals("Saved messages"))
            return searchChatRoomByListener(account);
        for (ChatRoom chatRoom : chatRooms)
            if (chatRoom.getListenerUserName().equals(userName)) return chatRoom;
        return null;
    }

    public ChatRoom searchChatRoomByListener(Account listener) {
        for (ChatRoom chatRoom : chatRooms)
            if (chatRoom.getListenerUserName().equals(listener.getUserName())) return chatRoom;
        return null;
    }

    public void addChatRoom(ChatRoom chatRoom) {
        chatRooms.add(chatRoom);
    }
}

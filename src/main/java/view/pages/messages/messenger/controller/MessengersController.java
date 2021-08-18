package view.pages.messages.messenger.controller;

import model.Account;
import model.pages.messenger.ChatRoom;
import model.pages.messenger.Message;
import model.pages.messenger.MessengersPage;
import view.controller.MainGraphicController;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

public class MessengersController extends MainGraphicController {
    public MessengersController(MessengersPage page) {
        super(page);
    }

    public String setChatRoomsView(LinkedList<String> chatRoomsView) {
        chatRoomsView.clear();
        LinkedList<ChatRoom> chatRooms = ((MessengersPage) page).getChatRooms();
        Collections.sort(chatRooms);
        for (ChatRoom chatRoom : chatRooms)
            chatRoomsView.add(chatRoom.getString());
        return null;
    }

    public String openChatRoom(String userName) {
        ChatRoom newChatRoom1 = ((MessengersPage) page).searchChatRoomsByUserName(userName);
        page.getManager().goToChatRoom(newChatRoom1);
        return null;
    }

    public String newChatList(LinkedList<Account> accounts) {
        HashSet<Account> accountsAddition = new HashSet<>(page.getAccount().getFollowers());
        accountsAddition.addAll(page.getAccount().getFollowings());
        accounts.clear();
        accounts.addAll(accountsAddition);
        for (int i = 0; i < accounts.size(); i++) {
            for (ChatRoom chatRoom : ((MessengersPage) page).getChatRooms()) {
                if (accounts.get(i).getUserName().equals(chatRoom.getListener().getUserName())) {
                    accounts.remove(i);
                    i--;
                    break;
                }
            }
        }
        return null;
    }

    public String buildNewChatRoom(LinkedList<Account> accountList, String userName) {
        Account listener = MainGraphicController.search(accountList, userName);
        ChatRoom newChatRoom = ((MessengersPage) page).buildNewChatRoom(listener);
        if (newChatRoom != null) {
            page.getManager().goToChatRoom(newChatRoom);
            return null;
        } else {
            return "You can not send message to this account!";
        }
    }

    public String sendSeveralMessage(String text, HashSet<ChatRoom> selectedList) {
        if (text.isBlank()) return "Failed to send message\nText is blank";
        for (ChatRoom chatRoom : selectedList) {
            Message message = chatRoom.writeNewMessage(text);
            ((MessengersPage) page).sendMessage(message, chatRoom);
        }
        selectedList.clear();
        return null;
    }
}

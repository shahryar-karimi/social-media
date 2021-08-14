package logic.pages.messenger;

import logic.Account;
import logic.Manager;
import logic.pages.Page;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MessengersPage extends Page {

    private LinkedList<ChatRoom> chatRooms;

    public MessengersPage() {
    }

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

    public String writeMessage(String[] lines, Message message) {
        String result = "";
        if (lines[0].equals("send to all")) {
            for (Account following : account.getFollowings()) {
                result += sendMessage(message, searchChatRoomsByUserName(following.getUserName())) + "\n";
            }
        } else {
            for (String line : lines) {
                if (line.charAt(0) == 'a') {
                    line = line.substring(2);
                    Account account = getManager().searchByUserName(line);
                    if (account == null) {
                        result += "Account " + line + " not found" + "\n";
                    } else {
                        result += sendMessage(message, searchChatRoomsByUserName(account.getUserName())) + "\n";
                    }
                } else if (line.charAt(0) == 'l') {
                    line = line.substring(2);
                    ArrayList<Account> list = getAccount().getFriendsList().get(line);
                    result += sendMessageToList(message, line, list);
                } else {
                    result += "Wrong input for \"" + line + "\"\n";
                }
            }
        }
        return result;
    }

    private String sendMessageToList(Message message, String line, ArrayList<Account> list) {
        if (list == null) {
            return  "List \"" + line + "\" not found" + "\n";
        }
        String result = "";
        for (Account account : list) {
            result += sendMessage(message, searchChatRoomsByUserName(account.getUserName())) + "\n";
        }
        return result;
    }

    public LinkedList<ChatRoom> getChatRooms() {
        return chatRooms;
    }

    public void setChatRooms(LinkedList<ChatRoom> chatRooms) {
        this.chatRooms = chatRooms;
    }

    public String showPage() {
        String result = "";
        for (ChatRoom chatRoom : chatRooms) {
            if (chatRoom.getListenerUserName().equals(account.getUserName())) {
                result += "Saved messages\n";
                continue;
            }
            result += chatRoom.getListenerUserName() + " " + chatRoom.getUnreadMessages() + "\n";
        }
        return result;
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

    public static String showCLIPage() {
        return "1.saved messages\n" +
                "2.new chat\n" +
                "3.Enter user name\n" +
                "4.write message\n" +
                "5.back\n" +
                "6.quit\n" +
                "7.exit";
    }

    public void addChatRoom(ChatRoom chatRoom) {
        chatRooms.add(chatRoom);
    }
}

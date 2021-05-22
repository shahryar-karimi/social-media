package logic.pages.messenger;

import logic.Account;
import logic.Manager;
import logic.pages.Page;

import java.util.ArrayList;

public class MessagesPage extends Page {

    ArrayList<ChatRoom> chatRooms;

    public MessagesPage() {
    }

    public MessagesPage(Account account, Manager manager) {
        super(account, manager);
        account.setMessagesPage(this);
        chatRooms = new ArrayList<>();
        chatRooms.add(new ChatRoom(manager, account, account.getUserName()));
        chatRooms.remove(0);
    }

    public boolean buildNewChatRoom(Account listener) {
        if (listener != null && account.isValidToSendMessage(listener)) {
            new ChatRoom(manager, account, listener.getUserName());
            new ChatRoom(manager, listener, account.getUserName());
            return true;
        }
        return false;
    }

    public String writeMessage(String[] lines, Message message) {
        String result = "";
        if (lines[0].equals("send to all")) {
            for (Account following : account.getFollowings()) {
                result += sendingMessage(message, following) + "\n";
            }
        } else {
            for (String line : lines) {
                if (line.charAt(0) == 'a') {
                    line = line.substring(2);
                    Account account = getManager().searchByUserName(line);
                    if (account == null) {
                        result += "Account " + line + " not found" + "\n";
                        continue;
                    }
                    result += sendingMessage(message, account) + "\n";

                } else if (line.charAt(0) == 'l') {
                    line = line.substring(2);
                    ArrayList<Account> list = getAccount().getFriendsList().get(line);
                    if (list == null) {
                        result += "List \"" + line + "\" not found" + "\n";
                        continue;
                    }
                    for (Account account : list) {
                        result += sendingMessage(message, account) + "\n";
                    }
                } else {
                    result += "Wrong input for \"" + line + "\"\n";
                }
            }
        }
        return result;
    }

    private String sendingMessage(Message message, Account account) {
        String result = "";
        ChatRoom chatRoom = searchChatRoomByListener(account);
        if (chatRoom == null) {
            if (buildNewChatRoom(account)) {
                chatRoom = searchChatRoomByListener(account);
            } else {
                return "You can't send message to " + account;
            }
        }
        if (!chatRoom.sendMessage(message)) {
            return "Failed to send message to " + account;
        }
        return "Message sent to " + account;
    }

    public ArrayList<ChatRoom> getChatRooms() {
        return chatRooms;
    }

    public void setChatRooms(ArrayList<ChatRoom> chatRooms) {
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

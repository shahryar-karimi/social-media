package logic.pages.messenger;

import logic.Account;
import logic.Manager;
import logic.Tweet;
import logic.pages.Page;

import java.util.ArrayList;
import java.util.LinkedList;

public class ChatRoom extends Page implements Comparable<ChatRoom> {
    private String listenerUserName;
    private transient Account listener;
    private LinkedList<Message> messages;
    private Message selectedMessage;

    public ChatRoom() {
    }

    public ChatRoom(Manager manager, Account account, String listenerUserName) {
        super(account, manager);
        this.listenerUserName = listenerUserName;
        if (listenerUserName.equals(account.getUserName())) this.listener = account;
        else this.listener = manager.searchByUserName(listenerUserName);
        this.messages = new LinkedList<>();
        this.account.getMessengersPage().addChatRoom(this);
    }

    public void setListener(Account listener) {
        this.listener = listener;
        this.listenerUserName = listener.getUserName();
    }

    public void setMessages(LinkedList<Message> messages) {
        this.messages = messages;
    }

    public Account getListener() {
        if (listener == null) {
            if (listenerUserName.equals(account.getUserName())) this.listener = account;
            else this.listener = manager.searchByUserName(listenerUserName);
        }
        return listener;
    }

    public String getListenerUserName() {
        return listenerUserName;
    }

    public int getUnreadMessages() {
        int counter = 0;
        for (Message message : messages)
            if (message.getOwnerUserName().equals(listenerUserName) && !message.isSeen())
                counter++;
        return counter;
    }

    public Message getSelectedMessage() {
        return selectedMessage;
    }

    public LinkedList<Message> getMessages() {
        return messages;
    }

    public void setListenerUserName(String listenerUserName) {
        this.listenerUserName = listenerUserName;
    }

    public void setSelectedMessage(Message selectedMessage) {
        this.selectedMessage = selectedMessage;
    }

    public Message writeNewMessage(String messageText) {
        return new Message(messageText, account);
    }

    public boolean sendMessage(Message message) {
        if (account.isValidToSendMessage(listener)) {
            message.setTime();
            message.setIndex(messages.size());
            messages.add(message);
            if (listener.equals(account)) {
                message.setSeen(true);
                return true;
            }
            ChatRoom listenerChatRoom = listener.getMessengersPage().searchChatRoomByListener(account);
            listenerChatRoom.messages.add(message);
            return true;
        } else {
            return false;
        }
    }

    public boolean sendTweet(Tweet tweet) {
        Message message = new Message(tweet.toString(), account);
        return sendMessage(message);
    }

    public void selectMessage(int index) {
        if (index < messages.size() && index >= 0) {
            setSelectedMessage(messages.get(index));
            selectedMessage.setSelected(true);
        }
    }

    public void deselectMessage() {
        selectedMessage.setSelected(false);
        setSelectedMessage(null);
    }

    public String forwardMessage(ArrayList<Account> accounts, Message message) {
        message.setForward(true);
        String result = "";
        for (Account account : accounts) {
            ChatRoom anotherChatRoom = account.getMessengersPage().searchChatRoomByListener(account);
            if (anotherChatRoom == null) {
                anotherChatRoom = account.getMessengersPage().buildNewChatRoom(account);
                if (anotherChatRoom != null) {
                    if (anotherChatRoom.sendMessage(message))
                        result += "Message sent to " + account + " successfully\n";
                    else result += "Failed to send message to " + account + "\n";
                } else result += "Failed to create a chat room with " + account + "\n";
            } else if (anotherChatRoom.sendMessage(message))
                result += "message sent to " + account + "\n";
            else result += "Failed to create a chat room with " + account + "\n";
        }
        return result;
    }

    @Override
    public int compareTo(ChatRoom chatRoom) {
        if (messages.isEmpty() && chatRoom.messages.isEmpty()) {
            return 0;
        } else if (messages.isEmpty()) {
            return -1;
        } else if (chatRoom.messages.isEmpty()) {
            return 1;
        }
        return chatRoom.messages.getLast().compareTo(messages.getLast());
    }

    @Override
    public String toString() {
        return getListenerUserName();
    }

    public String getString() {
        return getListenerUserName() + " " + getListener().getLastSeen(getAccount()) + " : " + getUnreadMessages();
    }
}

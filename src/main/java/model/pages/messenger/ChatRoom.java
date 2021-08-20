package model.pages.messenger;

import logic.Manager;
import model.Account;
import model.Tweet;
import model.pages.Page;

import java.util.ArrayList;
import java.util.LinkedList;

public class ChatRoom extends Page implements Comparable<ChatRoom> {
    private String listenerUserName;
    private transient Account listener;
    private LinkedList<Message> messages;

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
            if (message.getSenderUserName().equals(listenerUserName) && !message.isSeen())
                counter++;
        return counter;
    }

    public LinkedList<Message> getMessages() {
        return messages;
    }

    public Message writeNewMessage(String messageText) {
        return new Message(messageText, account.getUserName());
    }

    public boolean sendMessage(Message message) {
        seenBothWay();
        if (account.isValidToSendMessage(listener)) {
            message.setTime();
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
        seenBothWay();
        Message message = writeNewMessage(tweet.toString());
        return sendMessage(message);
    }

    public void seenBothWay() {
        seen(this, true);
        MessengersPage otherMessenger = this.getListener().getMessengersPage();
        seen(otherMessenger.searchChatRoomsByUserName(this.getAccount().getUserName()), false);
    }

    private void seen(ChatRoom chatRoom, boolean isMine) {
        LinkedList<Message> messages = chatRoom.getMessages();
        if (isMine) {
            for (int i = messages.size() - 1; i >= 0; i--) {
                Message message = messages.get(i);
                if (message.getSenderUserName().equals(chatRoom.getListenerUserName())) {
                    message.setSeen(true);
                } else {
                    break;
                }
            }
        } else {
            for (int i = messages.size() - 1; i >= 0; i--) {
                Message message = messages.get(i);
                if (message.getSenderUserName().equals(chatRoom.getAccount().getUserName()))
                    message.setSeen(true);
                else break;
            }
        }
    }

    public String forwardMessage(ArrayList<Account> accounts, Message message) {
        message.setSenderUserName(account.getUserName());
        String result = "";
        for (Account account : accounts) {
            ChatRoom anotherChatRoom = account.getMessengersPage().searchChatRoomByListener(this.account);
            if (anotherChatRoom == null) {
                anotherChatRoom = account.getMessengersPage().buildNewChatRoom(this.account);
                if (anotherChatRoom != null) {
                    if (sendMessage(message))
                        result += "Message sent to " + account + " successfully\n";
                    else
                        result += "Failed to send message to " + account + "\n";
                } else
                    result += "Failed to create a chat room with " + account + "\n";
            } else if (sendMessage(message))
                result += "message sent to " + account + "\n";
            else
                result += "Failed to create a chat room with " + account + "\n";
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

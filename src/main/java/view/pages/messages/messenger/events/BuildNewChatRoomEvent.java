package view.pages.messages.messenger.events;

import event.Event;
import model.Account;

import java.util.LinkedList;

public class BuildNewChatRoomEvent extends Event {
    private final LinkedList<Account> accountList;
    private final String userName;

    public BuildNewChatRoomEvent(Object source, LinkedList<Account> accountList, String userName) {
        super(source);
        this.accountList = accountList;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public LinkedList<Account> getAccountList() {
        return accountList;
    }
}

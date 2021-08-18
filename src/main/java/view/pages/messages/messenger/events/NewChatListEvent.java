package view.pages.messages.messenger.events;

import event.Event;
import model.Account;

import java.util.LinkedList;

public class NewChatListEvent extends Event {
    private final LinkedList<Account> accounts;

    public NewChatListEvent(Object source, LinkedList<Account> accounts) {
        super(source);
        this.accounts = accounts;
    }

    public LinkedList<Account> getAccounts() {
        return accounts;
    }
}

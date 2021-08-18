package view.pages.accountsListSwing.event;

import event.Event;
import model.Account;

import java.util.List;

public class ClickEvent extends Event {
    private final String userName;
    private final List<Account> accounts;

    public ClickEvent(Object source, String userName, List<Account> accounts) {
        super(source);
        this.userName = userName;
        this.accounts = accounts;
    }

    public String getUserName() {
        return userName;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}

package model.pages;

import logic.Manager;
import model.Account;

public abstract class Page {
    protected transient Account account;
    protected transient Manager manager;

    public Page() {
    }

    public Page(Account account, Manager manager) {
        this.account = account;
        this.manager = manager;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Account getAccount() {
        return account;
    }
}

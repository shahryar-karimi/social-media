package model.pages;

import model.Account;
import logic.Manager;

public class LoginPage extends Page{

    public LoginPage() {
    }

    public LoginPage(Manager manager) {
        this.manager = manager;
    }

    public Account searchByUserName(String userName) {
        return manager.searchByUserName(userName);
    }

    public Account searchByEmail(String email) {
        return manager.searchByEmail(email);
    }

    public Account createAnAccount(String firstName, String lastName, String userName, String password, String emailAddress, String phoneNumber, String bio) {
        synchronized (Manager.locker) {
            Account newAccount = new Account(manager, firstName, lastName, userName, password, emailAddress, phoneNumber, bio);
            manager.getAccounts().add(newAccount);
            manager.save();
            return newAccount;
        }
    }
}

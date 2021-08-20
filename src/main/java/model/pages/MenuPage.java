package model.pages;

import logic.Manager;
import model.Account;

public class MenuPage extends Page {

    public MenuPage(Account account, Manager manager) {
        super(account, manager);
        account.setMenuPage(this);
    }
}

package view.controller;

import controller.MainController;
import model.Account;
import model.pages.Page;

import java.util.List;

public abstract class MainGraphicController extends MainController {

    public MainGraphicController(Page page) {
        super(page);
    }

    public static Account search(List<Account> accounts, String userName) {
        for (Account account : accounts)
            if (account.getUserName().equals(userName))
                return account;
        return null;
    }
}

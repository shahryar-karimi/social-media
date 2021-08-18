package view.pages.accountsListSwing.controller;

import model.Account;
import model.pages.Page;
import view.controller.MainGraphicController;

import java.util.List;

public class ClickController extends MainGraphicController {

    public ClickController(Page page) {
        super(page);
    }

    public String click(List<Account> accounts, String userName) {
        Account account = MainGraphicController.search(accounts, userName);
        if (account != null)
            page.getManager().goToInfoPage(account.getPersonalPage().getInfo(), page.getAccount());
        return null;
    }
}

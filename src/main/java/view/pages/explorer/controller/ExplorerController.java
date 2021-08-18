package view.pages.explorer.controller;

import model.Account;
import model.pages.ExplorerPage;
import model.pages.TimeLinePage;
import view.controller.MainGraphicController;

import java.util.List;

public class ExplorerController extends MainGraphicController {

    public ExplorerController(ExplorerPage explorerPage) {
        super(explorerPage);
    }

    public String setTweet(TimeLinePage timeLine) {
        timeLine.setTweets(((ExplorerPage) page).getRandomTweets());
        return null;
    }

    public String click(List<Account> accounts, String userName) {
        Account account = MainGraphicController.search(accounts, userName);
        if (account == null) return "account not found";
        page.getManager().goToInfoPage(account.getPersonalPage().getInfo(), page.getAccount());
        return null;
    }
}

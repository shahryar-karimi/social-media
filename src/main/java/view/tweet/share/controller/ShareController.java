package view.tweet.share.controller;

import view.controller.MainGraphicController;
import model.Account;
import model.Tweet;
import model.pages.Page;

import java.util.ArrayList;
import java.util.HashSet;

public class ShareController extends MainGraphicController {

    public ShareController(Page page) {
        super(page);
    }

    public String share(Tweet tweet, HashSet<String> selected) {
        Account account = page.getAccount();
        String msg = "";
        for (String line : selected) {
            if (line.charAt(0) == 'l') {
                String listName = line.substring(2);
                ArrayList<Account> list = account.getFriendsList().get(listName);
                for (Account otherAccount : list)
                    msg += tweet.forward(page.getAccount(), otherAccount);
            } else {
                String userName = line.substring(2);
                Account otherAccount = page.getManager().searchByUserName(userName);
                msg += tweet.forward(page.getAccount(), otherAccount);
            }
        }
        return msg;
    }
}

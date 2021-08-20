package view.tweet.tweetsPanel.controller;

import model.Account;
import model.Tweet;
import model.pages.Page;
import view.controller.MainGraphicController;
import view.pages.accountsListSwing.controller.ClickController;
import view.pages.accountsListSwing.listener.ClickListener;
import view.pages.accountsListSwing.view.AccountsListSwing;
import view.tweet.share.controller.ShareController;
import view.tweet.share.listener.ShareListener;
import view.tweet.share.view.ShareFrame;

import java.util.LinkedList;

public class TweetsPanelController extends MainGraphicController {

    public TweetsPanelController(Page page) {
        super(page);
    }

    public String commentBtn(Tweet tweet) {
        page.getManager().goToComments(tweet);
        return null;
    }

    public String likeBtn(Tweet tweet, Account visitor) {
        return String.valueOf(tweet.fave(visitor));
    }

    public String retweetBtn(Tweet tweet, Account visitor) {
        tweet.retweet(visitor);
        return null;
    }

    public String shareBtn(Tweet tweet) {
        new ShareFrame(new ShareListener(new ShareController(page)), tweet);
        return null;
    }

    public String nameLbl(Tweet tweet, Account visitor) {
        Account account;
        if (tweet.isRetweet()) account = tweet.getRetweeter();
        else account = tweet.getAccount();
        page.getManager().goToInfoPage(account.getPersonalPage().getInfo(), visitor);
        return null;
    }

    public String likeQty(Tweet tweet) {
        new AccountsListSwing(new LinkedList<>(tweet.getFavesSet()), new ClickListener(new ClickController(page)));
        return null;
    }
}

package logic;

import model.Account;
import model.logger.MyLogger;
import model.Tweet;
import model.pages.TimeLinePage;
import model.pages.messenger.ChatRoom;
import model.pages.messenger.MessengersPage;
import model.pages.personal.Info;
import view.controller.GraphicManager;

import java.util.ArrayList;
import java.util.LinkedList;

public class Manager {
    private LinkedList<Account> accounts;
    private static final GraphicManager graphicManager = new GraphicManager();
    public static final Object locker = new Object();

    public Manager() {
        accounts = new LinkedList<>();
    }

    public void setAccounts(LinkedList<Account> accounts) {
        this.accounts = accounts;
    }

    public void deleteAccount(Account account) {
        synchronized (locker) {
            LinkedList<Account> followings = account.getFollowings();
            LinkedList<Account> followers = account.getFollowers();
            LinkedList<Tweet> tweets = account.getMyTweets();
            LinkedList<Account> blackList = account.getBlackList();
            ArrayList<Account> mutedPeople = account.getMutedPeople();
            MessengersPage messenger = account.getMessengersPage();
            while (!followings.isEmpty())
                account.unFollow(followings.get(0), false);
            while (!followers.isEmpty())
                followers.get(0).unFollow(account, false);
            while (!tweets.isEmpty())
                tweets.pop();
            while (!blackList.isEmpty())
                blackList.pop();
            while (!mutedPeople.isEmpty())
                mutedPeople.remove(0);
            for (ChatRoom chatRoom : messenger.getChatRooms()) {
                Account listener = chatRoom.getListener();
                if (!listener.getUserName().equals(account.getUserName())) {
                    MessengersPage otherMessenger = listener.getMessengersPage();
                    ChatRoom otherChatRoom = otherMessenger.searchChatRoomsByUserName(chatRoom.getAccount().getUserName());
                    otherMessenger.getChatRooms().remove(otherChatRoom);
                }
            }
            accounts.remove(account);
        }
    }

    public LinkedList<Account> getAccounts() {
        return accounts;
    }

    public Account searchByUserName(String userName) {
        synchronized (locker) {
            if (userName == null) return null;
            for (Account account : accounts)
                if (account.getUserName().equals(userName)) return account;
            return null;
        }
    }

    public void save() {
        DBManger.save(this);
    }

    public void quit(Account account) {
        account.setOnline(false);
        MyLogger logger = MyLogger.getLogger();
        logger.debug(Manager.class.getName(), "quit", "Program ended");
        save();
        goToLoginPage();
    }

    public void home(Account account) {
        graphicManager.home();
        save();
        goToMenuPage(account);

    }

    public void exit(Account account) {
        account.setOnline(false);
        save();
        MyLogger logger = MyLogger.getLogger();
        logger.debug(Manager.class.getName(), "exit", "Program ended");
        System.exit(0);
    }

    public void goToLoginPage() {
        save();
        graphicManager.goToLoginPage(this);
    }

    public void goToMenuPage(Account account) {
        save();
        graphicManager.goToMenuPage(account);
    }

    public void gotoPersonalPage(Account account) {
        save();
        graphicManager.gotoPersonalPage(account);
    }

    public void goToTimeLinePage(Account account) {
        save();
        graphicManager.goToTimeLinePage(account);
    }

    public void goToExplorerPage(Account account) {
        save();
        graphicManager.goToExplorerPage(account);
    }

    public void goToSettingPage(Account account) {
        save();
        graphicManager.goToSettingPage(account);
    }

    public void goToMessagesPage(Account account) {
        save();
        graphicManager.goToMessagesPage(account);
    }

    public void goToInfoPage(Info info, Account visitor) {
        save();
        graphicManager.goToInfoPage(info, visitor);
    }

    public void goToChatRoom(ChatRoom chatRoom) {
        save();
        graphicManager.goToChatRoom(chatRoom);
    }

    public void goToComments(Tweet tweet) {
        save();
        TimeLinePage timeLinePage = new TimeLinePage(tweet.getAccount(), this, false);
        timeLinePage.setTweets(tweet.getComments());
        graphicManager.goToComment(timeLinePage, tweet);
    }

    public Account searchByEmail(String email) {
        synchronized (locker) {
            for (Account account : accounts)
                if (email.equals(account.getEmailAddress())) return account;
            return null;
        }
    }

    public GraphicManager getGraphicManager() {
        return graphicManager;
    }
}

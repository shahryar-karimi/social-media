package logic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import graphic.GraphicManager;
import logic.Logger.MyLogger;
import logic.pages.TimeLinePage;
import logic.pages.messenger.ChatRoom;
import logic.pages.messenger.MessengersPage;
import logic.pages.personal.Info;

public class Manager {
    private LinkedList<Account> accounts;
    private transient GraphicManager graphicManager = new GraphicManager();
    private static final Object locker = new Object();
//    private transient Loop loop;

    public Manager() {
        accounts = new LinkedList<>();
//        loop = new Loop(this);
    }

    public void setAccounts(LinkedList<Account> accounts) {
        this.accounts = accounts;
    }

    public Account createAnAccount(String firstName, String lastName, String userName, String password, String emailAddress, String phoneNumber, String bio) {
        synchronized (locker) {
            Account newAccount = new Account(this, firstName, lastName, userName, password, emailAddress, phoneNumber, bio);
            accounts.add(newAccount);
            save();
            return newAccount;
        }
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

    public Account search(List<Account> accounts, String userName) {
        synchronized (locker) {
            if (userName == null) return null;
            for (Account account : accounts)
                if (account.getUserName().equals(userName)) return account;
            return null;
        }
    }

    public Account searchByUserName(String userName) {
        return search(accounts, userName);
    }

    public boolean isCorrectPassword(Account account, String password) {
        return password.equals(account.getPassword());
    }

    public void save() {
        Singleton.save(this);
    }

    public void update() {
        synchronized (locker) {
            Manager manager = Singleton.load();
            first:
            for (Account newAccount : manager.getAccounts()) {
                for (Account oldAccount : accounts) {
                    if (oldAccount.equals(newAccount)) {
                        oldAccount.update(newAccount);
                        continue first;
                    }
                }
                accounts.add(newAccount);
            }
            first:
            for (Account oldAccount : accounts) {
                for (Account newAccount : manager.getAccounts()) {
                    if (oldAccount.equals(newAccount)) {
                        continue first;
                    }
                }
                deleteAccount(oldAccount);
            }
        }
    }

    public void quit(Account account) {
        account.setOnline(false);
        MyLogger logger = MyLogger.getLogger();
        logger.debug(Manager.class.getName(), "quit", "Program ended");
        save();
        goToLoginPage();
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
//        loop.start();
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

    public void goToChatroom(ChatRoom chatRoom) {
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

    public void setGraphicManager(GraphicManager graphicManager) {
        this.graphicManager = graphicManager;
    }
}

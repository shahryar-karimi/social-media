package logic.pages.personal;

import logic.Account;
import logic.Manager;
import logic.Tweet;
import logic.pages.Page;

import java.util.ArrayList;
import java.util.LinkedList;

public class PersonalPage extends Page {

    private Notification notification;
    private Info info;

    public PersonalPage() {
    }

    public PersonalPage(Account account, Manager manager) {
        super(account, manager);
        account.setPersonalPage(this);
        notification = new Notification(account, manager);
        info = new Info(account, manager);
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public static String showPage() {
        return "Personal:\n" +
                "1.new tweet\n" +
                "2.my tweets\n" +
                "3.edit profile\n" +
                "4.my followings\n" +
                "5.my followers\n" +
                "6.my black list\n" +
                "7.info\n" +
                "8.notifications\n" +
                "9.create list\n" +
                "10.back\n" +
                "11.quit\n" +
                "12.exit";
    }

    public LinkedList<Account> myBlackList() {
        return account.getBlackList();
    }

    public LinkedList<Account> myFollowings() {
        return account.getFollowings();
    }

    public LinkedList<Account> myFollowers() {
        return account.getFollowers();
    }

    public Tweet writeNewTweet(String tweetText) {
        return new Tweet(account, tweetText);
    }

    public void sendingATweet(Tweet tweet, boolean canSetTime) {
        if (canSetTime) tweet.setTime();
        account.addTweet(tweet);
        for (Account follower : account.getFollowers()) {
            if (!follower.isMute(account))
                follower.getTimeLinePage().addTweet(tweet);
        }
        account.getTimeLinePage().addTweet(tweet);
    }


    public String showMyTweets() {
        String result = "--------------------------------------------------\n";
        for (Tweet myTweet : account.getMyTweets()) {
            result += myTweet + "\n--------------------------------------------------\n";
        }
        return result.trim();
    }

    public void editFirstName(String newFirstName) {
        account.setFirstName(newFirstName);
    }

    public void editLastName(String newLastName) {
        account.setLastName(newLastName);
    }

    public void editPassword(String newPassword) {
        account.setPassword(newPassword);
    }

    public void editEmail(String newEmailAddress) {
        account.setEmailAddress(newEmailAddress);
    }

    public void editPhoneNumber(String newPhoneNumber) {
        account.setPhoneNumber(newPhoneNumber);
    }

    public void editBio(String newBio) {
        account.setBio(newBio);
    }

    public void editBirthdayDate(String newBirthdayDate) {
        account.setBirthdayDate(newBirthdayDate);
    }

    public String putListToFriendsList(String listsName, ArrayList<Account> accounts) {
        account.getFriendsList().put(listsName, accounts);
        String result = "list created successfully for:\n";
        for (Account account1 : accounts) {
            result += account1 + "\n";
        }
        return result;
    }

    public String removeList(String listName) {
        ArrayList<Account> accounts = account.getFriendsList().getOrDefault(listName, null);
        if (accounts == null) {
            return "List not found";
        } else {
            account.getFriendsList().remove(listName, accounts);
            return listName + " deleted from lists";
        }
    }
}

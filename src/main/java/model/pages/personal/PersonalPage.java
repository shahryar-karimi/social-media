package model.pages.personal;

import logic.Manager;
import model.Account;
import model.Tweet;
import model.pages.Page;

import java.util.ArrayList;
import java.util.LinkedList;

public class PersonalPage extends Page {

    private Notification notification;
    private Info info;

    public PersonalPage(Account account, Manager manager) {
        super(account, manager);
        account.setPersonalPage(this);
        notification = new Notification(account, manager);
        info = new Info(account, manager);
    }

    public Notification getNotification() {
        return notification;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
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
        manager.save();
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
        manager.save();
    }

    public void editLastName(String newLastName) {
        account.setLastName(newLastName);
        manager.save();
    }

    public void editPassword(String newPassword) {
        account.setPassword(newPassword);
        manager.save();
    }

    public void editEmail(String newEmailAddress) {
        account.setEmailAddress(newEmailAddress);
        manager.save();
    }

    public void editPhoneNumber(String newPhoneNumber) {
        account.setPhoneNumber(newPhoneNumber);
        manager.save();
    }

    public void editBio(String newBio) {
        account.setBio(newBio);
        manager.save();
    }

    public void editBirthdayDate(String newBirthdayDate) {
        account.setBirthDate(newBirthdayDate);
        manager.save();
    }

    public String putListToFriendsList(String listsName, ArrayList<Account> accounts) {
        account.getFriendsList().put(listsName, accounts);
        String result = "list created successfully for:\n";
        for (Account account1 : accounts) {
            result += account1 + "\n";
        }
        return result;
    }
}

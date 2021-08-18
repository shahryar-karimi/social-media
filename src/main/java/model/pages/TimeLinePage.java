package model.pages;

import model.Account;
import logic.Manager;
import model.Tweet;

import java.util.LinkedList;

public class TimeLinePage extends Page {

    private transient LinkedList<Tweet> tweets;
    private int indexOfTweet;
    private transient Tweet currentTweet;

    public TimeLinePage() {
    }

    public TimeLinePage(Account account, Manager manager, boolean canAddToAccount) {
        super(account, manager);
        this.tweets = new LinkedList<>();
        this.indexOfTweet = 0;
        if (canAddToAccount) account.setTimeLinePage(this);
    }

    public LinkedList<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(LinkedList<Tweet> tweets) {
        this.tweets = tweets;
        if (!tweets.isEmpty()) setIndexOfTweet(tweets.size() - 1);
        else this.indexOfTweet = -1;
    }

    public static String showPage() {
        return "Time line:\n" +
                "1.next tweet\n" +
                "2.previous tweet\n" +
                "3.fave\n" +
                "4.save tweet\n" +
                "5.retweet\n" +
                "6.forward\n" +
                "7.block user\n" +
                "8.mute user\n" +
                "9.report\n" +
                "10.user page\n" +
                "11.send comment\n" +
                "12.show comments\n" +
                "13.faves list\n" +
                "14.back\n" +
                "15.quit\n" +
                "16.exit";
    }

    public static Tweet showTweetByIndex(LinkedList<Tweet> tweets, int index) {
        if (index < tweets.size() && index >= 0)
            return tweets.get(index);
        return null;
    }

    public int getIndexOfTweet() {
        return indexOfTweet;
    }

    public Tweet getCurrentTweet() {
        return currentTweet;
    }

    public void setCurrentTweet(Tweet currentTweet) {
        this.currentTweet = currentTweet;
    }

    public void setIndexOfTweet(int indexOfTweet) {
        this.indexOfTweet = indexOfTweet;
        if (indexOfTweet >= 0 && indexOfTweet < tweets.size()) setCurrentTweet(tweets.get(indexOfTweet));
        else setCurrentTweet(null);
    }

    public String goNextTweet(LinkedList<Tweet> tweets, int indexOfTweet) {
        String message = "";
        if (tweets.size() == 0) {
            message = "There is nothing here!";
            return message;
        } else {
            indexOfTweet++;
            Tweet nextTweet = showTweetByIndex(tweets, indexOfTweet);
            if (nextTweet == null) {
                indexOfTweet--;
                message = "There is no more for you\nThis is the last one\n";
            }
            this.currentTweet = showTweetByIndex(tweets, indexOfTweet);
            this.indexOfTweet = indexOfTweet;
        }
        return message;
    }

    public String goPreviousTweet(LinkedList<Tweet> tweets, int indexOfTweet) {
        String message = "";
        if (tweets.size() == 0) {
            message = "There is nothing here!";
            return message;
        } else {
            indexOfTweet--;
            Tweet previousTweet = showTweetByIndex(tweets, indexOfTweet);
            if (previousTweet == null) {
                indexOfTweet++;
                message = "There is no more for you\nThis is the first one:\n";
            }
            currentTweet = showTweetByIndex(tweets, indexOfTweet);
            this.indexOfTweet = indexOfTweet;
        }
        return message;
    }

    public String fave() {
        if (currentTweet != null && !currentTweet.faveSetContains(account)) {
            currentTweet.addFave(account);
            return "Current tweet liked successfully";
        } else if (currentTweet == null) {
            return "Failed to fave current tweet\nCurrent tweet not found";
        } else {
            currentTweet.removeFave(account);
            return "Current tweet disliked successfully";
        }
    }

    public void addTweet(Tweet tweet) {
        tweets.add(tweet);
        setIndexOfTweet(tweets.size() - 1);
    }

    public void removeTweetByIndex(int index) {
        tweets.remove(index);
        setIndexOfTweet(tweets.size() - 1);
    }

    public void blockUser() {
        this.account.block(currentTweet.getAccount());
        for (int i = 0; i < tweets.size(); i++) {
            if (tweets.get(i).getAccount().equals(account)) {
                tweets.remove(i);
                i--;
            }
        }
        setIndexOfTweet(tweets.size() - 1);
    }

    public void muteUser() {
        this.account.mute(currentTweet.getAccount());
    }

    public String report() {
        return account.report(currentTweet.getAccount());
    }

    public void sendComment(String comment) {
        Tweet newComment = account.getPersonalPage().writeNewTweet(comment);
        newComment.setTime();
        currentTweet.addComment(newComment);
    }

    public String forward(LinkedList<Account> accounts) {
        String result = "";
        for (Account account : accounts) {
            result += currentTweet.forward(this.account, account);
        }
        return result;
    }

    public String showFavesList() {
        String result = "";
        for (Account account1 : currentTweet.getFavesSet()) {
            result += account1 + "\n";
        }
        return result;
    }
}

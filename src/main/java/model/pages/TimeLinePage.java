package model.pages;

import logic.Manager;
import model.Account;
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

    public void addTweet(Tweet tweet) {
        tweets.add(tweet);
        setIndexOfTweet(tweets.size() - 1);
    }

    public void removeTweetByIndex(int index) {
        tweets.remove(index);
        setIndexOfTweet(tweets.size() - 1);
    }
}

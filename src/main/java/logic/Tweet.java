package logic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;

public class Tweet implements Comparable{
    private String tweetText;
    private transient Account account;
    private String time;
    private ArrayList<Account> favesSet;
    private LinkedList<Tweet> comments;
    private int retweet;
    private boolean isRetweet;
    private Account retweeter;

    public Tweet() {
    }

    public Tweet(Account account) {
        this.account = account;
        this.isRetweet = false;
        this.favesSet = new ArrayList<>();
        this.comments = new LinkedList<>();
        this.retweet = 0;
    }

    public Tweet(Account account, String tweetText) {
        this(account);
        this.tweetText = tweetText;
    }

    public Tweet(Account account, String tweetText, String time) {
        this(account, tweetText);
        this.time = time;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void addComment(Tweet newComment) {
        comments.add(newComment);
    }

    public void addFave(Account account) {
        favesSet.add(account);
    }

    public String getTweetText() {
        return tweetText;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ArrayList<Account> getFavesSet() {
        return favesSet;
    }

    public boolean faveSetContains(Object o) {
        for (Account account1 : favesSet)
            if (account1.equals(o)) return true;
        return false;
    }

    public void setComments(LinkedList<Tweet> comments) {
        this.comments = comments;
    }

    public void setFavesSet(ArrayList<Account> favesSet) {
        this.favesSet = favesSet;
    }

    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }

    public String getTime() {
        return time;
    }

    public Account getAccount() {
        return account;
    }

    public LinkedList<Tweet> getComments() {
        return comments;
    }

    public void setTime() {
        this.time = LocalDateTime.now().toString().replace('T', ' ').substring(0, 19);
    }

    public int getRetweet() {
        return retweet;
    }

    public void setRetweet(int retweet) {
        this.retweet = retweet;
    }

    public void setRetweet(boolean retweet) {
        isRetweet = retweet;
    }

    public boolean isRetweet() {
        return isRetweet;
    }

    @Override
    public String toString() {
        String result = "    " + getAccount().toString() + " :\n" +
                getTweetText() + "\n" +
                "    Details:\n" +
                "    Time : " + getTime() + "\n" +
                "    Likes : " + getFavesSet().size() + "\n" +
                "    Retweet : " + getRetweet() + "\n" +
                "    comments : " + getComments().size();
        if (isRetweet) result = "Retweet : \n" + result;
        else result = "Tweet : \n" + result;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tweet)) return false;
        Tweet tweet = (Tweet) o;
        return getTweetText().equals(tweet.getTweetText()) && (getAccount().equals(tweet.getAccount()) || getAccount() == null && tweet.getAccount() == null) && getTime().equals(tweet.getTime()) && getFavesSet().equals(tweet.getFavesSet()) && getComments().equals(tweet.getComments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTweetText(), getAccount(), getTime(), getFavesSet(), getComments());
    }

    @Override
    public Tweet clone() {
        Tweet newTweet = new Tweet(getAccount(), getTweetText(), getTime());
        newTweet.setComments(getComments());
        newTweet.setFavesSet(getFavesSet());
        return newTweet;
    }

    @Override
    public int compareTo(Object o) {
        Tweet anotherTweet = (Tweet) o;
        return time.compareTo(anotherTweet.getTime());
    }

    public Account getRetweeter() {
        return retweeter;
    }

    public void setRetweeter(Account retweeter) {
        this.retweeter = retweeter;
    }
}

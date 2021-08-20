package model;

import logic.TweetManager;
import model.pages.messenger.ChatRoom;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class Tweet implements Comparable<Tweet> {
    private String tweetText;
    private transient Account account;
    private String ownerUserName;
    private String time;
    private transient ArrayList<Account> favesSet;
    private ArrayList<String> faveSetUserName;
    private LinkedList<Tweet> comments;
    private int retweetQty;
    private transient Tweet superTweet;
    private transient Account retweeter;
    private transient LinkedList<Tweet> subTweets;
    private int tweetId;
    private int superTweetId = -1;
    private String superTweetOwnerUserName;

    public Tweet() {
    }

    public Tweet(Account account) {
        this.account = account;
        this.ownerUserName = account.getUserName();
        this.favesSet = new ArrayList<>();
        this.faveSetUserName = new ArrayList<>();
        this.comments = new LinkedList<>();
        this.retweetQty = 0;
        this.subTweets = new LinkedList<>();
        TweetManager.addTweet(this);
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
        this.ownerUserName = account.getUserName();
    }

    public void addComment(Tweet newComment) {
        comments.add(newComment);
    }

    public void addFave(Account account) {
        favesSet.add(account);
        if (isRetweet()) {
            getSuperTweet().faveSetUserName.add(account.getUserName());
        } else {
            faveSetUserName.add(account.getUserName());
        }
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

    public int getRetweetQty() {
        return retweetQty;
    }

    public void setRetweetQty(int retweetQty) {
        if (isRetweet()) {
            getSuperTweet().setRetweetQty(retweetQty);
        } else {
            this.retweetQty = retweetQty;
            for (Tweet subTweet : subTweets)
                subTweet.retweetQty = retweetQty;
        }
    }

    public boolean isRetweet() {
        return superTweetId != -1;
    }

    @Override
    public String toString() {
        String result = "    " + getAccount().toString() + " :\n" +
                getTweetText() + "\n" +
                "    Details:\n" +
                "    Time : " + getTime() + "\n" +
                "    Likes : " + getFavesSet().size() + "\n" +
                "    Retweet : " + getRetweetQty() + "\n" +
                "    comments : " + getComments().size();
        if (isRetweet()) result = "Retweet : \n" + result;
        else result = "Tweet : \n" + result;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tweet)) return false;
        Tweet tweet = (Tweet) o;
        return getTweetId() == tweet.getTweetId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTweetId());
    }

    @Override
    public Tweet clone() {
        Tweet newTweet = new Tweet(getAccount(), getTweetText(), getTime());
        newTweet.setComments(getComments());
        newTweet.setFavesSet(getFavesSet());
        return newTweet;
    }

    public String getOwnerUserName() {
        return ownerUserName;
    }

    public ArrayList<String> getFaveSetUserName() {
        return faveSetUserName;
    }

    @Override
    public int compareTo(Tweet anotherTweet) {
        return time.compareTo(anotherTweet.getTime());
    }

    public void removeFave(Account account) {
        favesSet.remove(account);
        if (isRetweet()) {
            getSuperTweet().faveSetUserName.remove(account.getUserName());
        } else {
            faveSetUserName.remove(account.getUserName());
        }
    }

    public void retweet(Account retweeter) {
        Tweet retweetTweet = this.clone();
        retweetTweet.setTime();
        retweetTweet.setSuperTweet(this);
        retweetTweet.setRetweeter(retweeter);
        if (isRetweet()) {
            getSuperTweet().subTweets.add(retweetTweet);
        } else {
            subTweets.add(retweetTweet);
        }
        retweetTweet.setRetweetQty(this.getRetweetQty() + 1);
        retweeter.getPersonalPage().sendingATweet(retweetTweet, false);
    }

    public boolean fave(Account account) {
        if (faveSetContains(account)) {
            removeFave(account);
            return false;
        } else {
            addFave(account);
            return true;
        }
    }

    public void setSuperTweet(Tweet superTweet) {
        if (superTweet.isRetweet()) {
            this.superTweet = superTweet.getSuperTweet();
            this.superTweetId = superTweet.getSuperTweet().tweetId;
            this.superTweetOwnerUserName = superTweet.getSuperTweet().getOwnerUserName();
        } else {
            this.superTweet = superTweet;
            this.superTweetId = superTweet.tweetId;
            this.superTweetOwnerUserName = superTweet.getOwnerUserName();
        }
    }

    public Tweet getSuperTweet() {
        if (superTweet.isRetweet()) return superTweet.getSuperTweet();
        else return superTweet;
    }

    public Account getRetweeter() {
        return retweeter;
    }

    public void setRetweeter(Account retweeter) {
        this.retweeter = retweeter;
    }

    public LinkedList<Tweet> getSubTweets() {
        return subTweets;
    }

    public void setSubTweets(LinkedList<Tweet> subTweets) {
        this.subTweets = subTweets;
    }

    public int getTweetId() {
        return tweetId;
    }

    public int getSuperTweetId() {
        return superTweetId;
    }

    public void setTweetId(int tweetId) {
        this.tweetId = tweetId;
    }

    public String forward(Account owner, Account otherAccount) {
        ChatRoom chatRoom = owner.getMessengersPage().searchChatRoomByListener(otherAccount);
        if (chatRoom == null) {
            chatRoom = owner.getMessengersPage().buildNewChatRoom(otherAccount);
            if (chatRoom != null) {
                if (chatRoom.sendTweet(this))
                    return "Message sent to '" + otherAccount + "' successfully\n";
                else return "Failed to send message to '" + otherAccount + "'\n";
            } else return "Failed to create a chat room with '" + otherAccount + "'\n";
        } else if (chatRoom.sendTweet(this))
            return "message sent to '" + otherAccount + "'\n";
        else return "Failed to create a chat room with '" + otherAccount + "'\n";
    }
}

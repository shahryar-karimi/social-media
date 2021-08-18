package view.tweet.tweetsPanel.events;

import event.Event;
import model.Account;
import model.Tweet;

public class TweetsPanelEvent extends Event {
    private Account visitor;
    private final Tweet tweet;
    private final String work;

    public TweetsPanelEvent(Object source, Tweet tweet, String work, Account visitor) {
        this(source, tweet, work);
        this.visitor = visitor;
    }

    public TweetsPanelEvent(Object source, Tweet tweet, String work) {
        super(source);
        this.tweet = tweet;
        this.work = work;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public String getWork() {
        return work;
    }

    public Account getVisitor() {
        return visitor;
    }
}

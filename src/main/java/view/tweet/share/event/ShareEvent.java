package view.tweet.share.event;

import event.Event;
import model.Tweet;

import java.util.HashSet;

public class ShareEvent extends Event {
    private final HashSet<String> selected;
    private final Tweet tweet;

    public ShareEvent(Object source, HashSet<String> selected, Tweet tweet) {
        super(source);
        this.selected = selected;
        this.tweet = tweet;
    }

    public HashSet<String> getSelected() {
        return selected;
    }

    public Tweet getTweet() {
        return tweet;
    }
}

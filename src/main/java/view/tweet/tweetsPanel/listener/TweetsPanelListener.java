package view.tweet.tweetsPanel.listener;

import event.Event;
import view.tweet.tweetsPanel.controller.TweetsPanelController;
import view.tweet.tweetsPanel.events.TweetsPanelEvent;
import listener.FormListener;

public class TweetsPanelListener extends FormListener {
    public TweetsPanelListener(TweetsPanelController controller) {
        this.controller = controller;
    }

    @Override
    public String eventOccurred(Event event) {
        TweetsPanelEvent tweetsPanelEvent = (TweetsPanelEvent) event;
        return switch (tweetsPanelEvent.getWork()) {
            case "comment btn" -> ((TweetsPanelController) controller).commentBtn(tweetsPanelEvent.getTweet());
            case "like btn" -> ((TweetsPanelController) controller).likeBtn(tweetsPanelEvent.getTweet(), tweetsPanelEvent.getVisitor());
            case "retweet btn" -> ((TweetsPanelController) controller).retweetBtn(tweetsPanelEvent.getTweet(), tweetsPanelEvent.getVisitor());
            case "share btn" -> ((TweetsPanelController) controller).shareBtn(tweetsPanelEvent.getTweet());
            case "name Lbl" -> ((TweetsPanelController) controller).nameLbl(tweetsPanelEvent.getTweet(), tweetsPanelEvent.getVisitor());
            case "like Lbl" -> ((TweetsPanelController) controller).likeQty(tweetsPanelEvent.getTweet());
            default -> null;
        };
    }
}

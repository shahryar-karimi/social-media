package view.pages.personalPage.event;

import event.Event;

public class SendBtnEvent extends Event {
    private final String tweetText;

    public SendBtnEvent(Object source, String tweetText) {
        super(source);
        this.tweetText = tweetText;
    }

    public String getTweetText() {
        return tweetText;
    }
}

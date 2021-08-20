package view.pages.explorer.event;

import event.Event;
import model.pages.TimeLinePage;

public class SetRandomTweetEvent extends Event {
    private final TimeLinePage timeLine;

    public SetRandomTweetEvent(Object source, TimeLinePage timeLine) {
        super(source);
        this.timeLine = timeLine;
    }

    public TimeLinePage getTimeLine() {
        return timeLine;
    }
}

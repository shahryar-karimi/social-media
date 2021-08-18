package view.tweet.share.listener;

import event.Event;
import view.tweet.share.controller.ShareController;
import view.tweet.share.event.ShareEvent;
import listener.FormListener;

public class ShareListener extends FormListener {
    public ShareListener(ShareController controller) {
        this.controller = controller;
    }

    @Override
    public String eventOccurred(Event event) {
        ShareEvent shareEvent = (ShareEvent) event;
        return ((ShareController) controller).share(shareEvent.getTweet(), shareEvent.getSelected());
    }
}

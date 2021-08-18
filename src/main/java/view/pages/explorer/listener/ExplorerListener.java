package view.pages.explorer.listener;

import event.Event;
import listener.FormListener;
import view.pages.accountsListSwing.event.ClickEvent;
import view.pages.explorer.controller.ExplorerController;
import view.pages.explorer.event.SetRandomTweetEvent;

public class ExplorerListener extends FormListener {
    public ExplorerListener(ExplorerController controller) {
        this.controller = controller;
    }

    @Override
    public String eventOccurred(Event event) {
        if (event instanceof SetRandomTweetEvent) {
            SetRandomTweetEvent setRandomTweetEvent = (SetRandomTweetEvent) event;
            return ((ExplorerController) controller).setTweet(setRandomTweetEvent.getTimeLine());
        } else if (event instanceof ClickEvent) {
            ClickEvent clickEvent = (ClickEvent) event;
            return ((ExplorerController) controller).click(clickEvent.getAccounts(), clickEvent.getUserName());
        }
        return null;
    }
}

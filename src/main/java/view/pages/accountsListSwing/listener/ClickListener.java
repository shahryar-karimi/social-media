package view.pages.accountsListSwing.listener;

import event.Event;
import listener.FormListener;
import view.pages.accountsListSwing.controller.ClickController;
import view.pages.accountsListSwing.event.ClickEvent;

public class ClickListener extends FormListener {
    public ClickListener(ClickController controller) {
        this.controller = controller;
    }

    @Override
    public String eventOccurred(Event event) {
        ClickEvent clickEvent = (ClickEvent) event;
        return ((ClickController) controller).click(clickEvent.getAccounts(), clickEvent.getUserName());
    }
}

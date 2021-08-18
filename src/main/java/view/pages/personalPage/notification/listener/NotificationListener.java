package view.pages.personalPage.notification.listener;

import event.Event;
import listener.FormListener;
import view.pages.personalPage.notification.controller.NotificationController;
import view.pages.personalPage.notification.event.RequestEvent;

public class NotificationListener extends FormListener {
    public NotificationListener(NotificationController controller) {
        this.controller = controller;
    }

    @Override
    public String eventOccurred(Event event) {
        RequestEvent requestEvent = (RequestEvent) event;
        switch (requestEvent.getWork()) {
            case "accept" -> ((NotificationController) controller).accept(requestEvent.getAccount());
            case "decline" -> ((NotificationController) controller).decline(requestEvent.isMute(), requestEvent.getAccount());
        }
        return null;
    }
}

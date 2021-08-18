package view.pages.personalPage.info.listener;

import event.Event;
import listener.FormListener;
import view.pages.personalPage.info.controller.InfoController;
import view.pages.personalPage.info.events.InfoEvent;

public class InfoListener extends FormListener {
    public InfoListener(InfoController controller) {
        this.controller = controller;
    }

    @Override
    public String eventOccurred(Event event) {
        InfoEvent infoEvent = (InfoEvent) event;
        return switch (infoEvent.getWork()) {
            case "change follow" -> ((InfoController) controller).changeFollow(infoEvent.getOwner(), infoEvent.getVisitor());
            case "change list" -> ((InfoController) controller).changeList(infoEvent.getOwner(), infoEvent.getVisitor());
            case "block" -> ((InfoController) controller).block(infoEvent.getOwner(), infoEvent.getVisitor());
            case "send message" -> ((InfoController) controller).sendMessage(infoEvent.getOwner(), infoEvent.getVisitor());
            case "report" -> ((InfoController) controller).report(infoEvent.getOwner(), infoEvent.getVisitor());
            case "mute" -> ((InfoController) controller).mute(infoEvent.getOwner(), infoEvent.getVisitor());
            default -> null;
        };
    }
}

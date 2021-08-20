package view.myPanels.footerPanel.listener;

import event.Event;
import listener.FormListener;
import view.myPanels.footerPanel.controller.FooterPanelController;
import view.myPanels.footerPanel.event.FooterPanelEvent;

public class FooterPanelListener extends FormListener {
    public FooterPanelListener(FooterPanelController controller) {
        this.controller = controller;
    }

    @Override
    public String eventOccurred(Event event) {
        FooterPanelEvent footerPanelEvent = (FooterPanelEvent) event;
        return switch (footerPanelEvent.getWork()) {
            case "home" -> ((FooterPanelController) controller).home();
            case "exit" -> ((FooterPanelController) controller).exit();
            case "back" -> ((FooterPanelController) controller).back();
            default -> null;
        };
    }
}
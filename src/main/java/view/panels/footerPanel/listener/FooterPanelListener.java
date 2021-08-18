package view.panels.footerPanel.listener;

import event.Event;
import view.panels.footerPanel.controller.FooterPanelController;
import view.panels.footerPanel.event.FooterPanelEvent;
import listener.FormListener;

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

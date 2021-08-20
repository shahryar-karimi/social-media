package view.myPanels.footerPanel.event;

import event.Event;

public class FooterPanelEvent extends Event {
    private final String work;

    public FooterPanelEvent(Object source, String work) {
        super(source);
        this.work = work;
    }

    public String getWork() {
        return work;
    }
}

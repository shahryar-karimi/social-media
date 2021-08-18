package view.pages.personalPage.info.events;

import event.Event;
import model.Account;

public class InfoEvent extends Event {
    private final Account owner;
    private final Account visitor;
    private final String work;

    public InfoEvent(Object source, Account owner, Account visitor, String work) {
        super(source);
        this.owner = owner;
        this.visitor = visitor;
        this.work = work;
    }

    public String getWork() {
        return work;
    }

    public Account getOwner() {
        return owner;
    }

    public Account getVisitor() {
        return visitor;
    }
}

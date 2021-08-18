package view.pages.personalPage.notification.event;

import event.Event;
import model.Account;

public class RequestEvent extends Event {
    private final Account account;
    private final String work;
    private boolean isMute;

    public RequestEvent(Object source, Account account, String work) {
        super(source);
        this.account = account;
        this.work = work;
    }

    public RequestEvent(Object source, Account account, boolean isMute, String work) {
        super(source);
        this.account = account;
        this.isMute = isMute;
        this.work = work;
    }

    public Account getAccount() {
        return account;
    }

    public boolean isMute() {
        return isMute;
    }

    public String getWork() {
        return work;
    }
}

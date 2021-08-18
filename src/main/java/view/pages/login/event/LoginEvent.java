package view.pages.login.event;

import event.Event;

public class LoginEvent extends Event {
    private final String userName;
    private final String password;

    public LoginEvent(Object source, String userName, String password) {
        super(source);
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}

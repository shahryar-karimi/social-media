package logic.pages;

import logic.Manager;

public class LoginPage {

    private Manager manager;

    public LoginPage() {
    }

    public LoginPage(Manager manager) {
        this.manager = manager;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}

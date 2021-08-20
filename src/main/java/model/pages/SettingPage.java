package model.pages;

import logic.Manager;
import model.Account;

public class SettingPage extends Page {

    public SettingPage(Account account, Manager manager) {
        super(account, manager);
        account.setSettingPage(this);
    }

    public void deleteAccount() {
        manager.deleteAccount(account);
    }

    public void setPagePrivacy(boolean isPagePublic) {
        account.setPagePublic(isPagePublic);
    }

    public void setLastSeenPrivacy(String lastSeenSituation) {
        account.setLastSeenSituation(lastSeenSituation);
    }

    public void setActivity(boolean activity) {
        account.setActive(activity);
    }
}

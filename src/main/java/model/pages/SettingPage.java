package model.pages;

import model.Account;
import logic.Manager;

public class SettingPage extends Page {

    public SettingPage() {
    }

    public SettingPage(Account account, Manager manager) {
        super(account, manager);
        account.setSettingPage(this);
    }

    public static String showPage() {
        return "1.setting privacy\n" +
                "2.delete account\n" +
                "3.log out\n" +
                "4.back\n" +
                "5.exit";
    }

    public void deleteAccount() {
        manager.deleteAccount(account);
    }

    public void setPagePrivacy(boolean isPagePublic) {
        account.setPagePublic(isPagePublic);
    }

    public String showPrivacyMenu() {
        return "1.pages privacy\n" +
                "2.last seen privacy\n" +
                "3.pages activity\n" +
                "4.edit password\n" +
                "5.back\n" +
                "6.exit";
    }

    public void setLastSeenPrivacy(String lastSeenSituation) {
        account.setLastSeenSituation(lastSeenSituation);
    }

    public void setActivity(boolean activity) {
        account.setActive(activity);
    }
}

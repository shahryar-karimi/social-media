package logic.pages;

import logic.Account;
import logic.Manager;

public class MenuPage extends Page {

    public MenuPage() {
    }

    public MenuPage(Account account, Manager manager) {
        super(account, manager);
        account.setMenuPage(this);
    }

    public static String showPage() {
        return "Menu:\n" +
                "1.personal\n" +
                "2.time line\n" +
                "3.explorer\n" +
                "4.messages\n" +
                "5.setting\n" +
                "6.quit\n" +
                "7.exit";
    }
}

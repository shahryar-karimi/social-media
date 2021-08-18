package model.pages.personal;

import model.Account;
import logic.Manager;
import model.pages.Page;

public class Info extends Page {

    public Info() {
    }

    public Info(Account account, Manager manager) {
        super(account, manager);
        account.getPersonalPage().setInfo(this);
    }

    public static String menuForActions() {
        return "Info:\n" +
                "1.follow button\n" +
                "2.block button\n" +
                "3.report\n" +
                "4.send message\n" +
                "5.followings\n" +
                "6.followers\n" +
                "7.add to list\n" +
                "8.remove from list\n" +
                "9.quit\n" +
                "10.back\n" +
                "11.exit";
    }

    public static String menuForActionOwner() {
        return "Info:\n" +
                "1.send message\n" +
                "2.followings\n" +
                "3.followers\n" +
                "4.quit\n" +
                "5.back\n" +
                "6.exit";
    }

    public String toString(Account visitor) {
        return "first name : " + account.getFirstName() + "\n" +
                "last name : " + account.getLastName() + "\n" +
                "user name : " + account.getUserName() + "\n" +
                "id : " + account.getId() + "\n" +
                "bio : " + account.getBio() + "\n" +
                "last seen : " + account.getLastSeen(visitor) + "\n" +
                ((account.equals(visitor)) ? "" : ((account.isFollow(visitor)) ? "Follows you" : "Doesn't follow you"));
    }
}

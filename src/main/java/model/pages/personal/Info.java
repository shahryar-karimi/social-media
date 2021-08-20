package model.pages.personal;

import logic.Manager;
import model.Account;
import model.pages.Page;

public class Info extends Page {

    public Info(Account account, Manager manager) {
        super(account, manager);
        account.getPersonalPage().setInfo(this);
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

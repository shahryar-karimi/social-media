package view.pages.personalPage.creatingList.controller;

import logic.Manager;
import model.Account;
import model.pages.personal.PersonalPage;
import view.controller.MainGraphicController;

import java.util.ArrayList;
import java.util.HashSet;

public class CreateListController extends MainGraphicController {

    public CreateListController(PersonalPage page) {
        super(page);
    }

    public String create(String name, HashSet<String> selectedSet) {
        ArrayList<Account> accounts = new ArrayList<>();
        Manager manager = page.getManager();
        for (String selectedValue : selectedSet)
            accounts.add(manager.searchByUserName(selectedValue));
        return ((PersonalPage) page).putListToFriendsList(name, accounts);
    }
}

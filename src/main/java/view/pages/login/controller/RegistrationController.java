package view.pages.login.controller;

import model.Account;
import model.pages.LoginPage;
import view.controller.MainGraphicController;
import view.pages.login.view.LoginSwing;

public class RegistrationController extends MainGraphicController {

    public RegistrationController(LoginPage loginPage) {
        super(loginPage);
    }

    public String login(String userName, String password) {
        Account account = ((LoginPage) page).searchByUserName(userName);
        if (account == null) {
            return "Account not found\nTry another user name or sign up";
        } else {
            if (password.equals(account.getPassword())) {
                enterTo(account);
                return userName + " logged in";
            } else {
                return "Incorrect password";
            }
        }
    }

    public String signUp(String firstName, String lastName, String userName, String password,
                         String email, String phoneNumber, String bio) {
        if (((LoginPage) page).searchByUserName(userName) != null) {
            return "User already exist!\nTry another user name";
        } else if (((LoginPage) page).searchByEmail(email) != null) {
            return "User with this email already exist!\nTry another email address";
        } else {
            Account account = ((LoginPage) page).createAnAccount(firstName, lastName, userName, password, email, phoneNumber, bio);
            if (account != null) {
                enterTo(account);
                return "Page " + userName + " created successfully!";
            } else {
                return "Failed to create this account";
            }
        }
    }

    public void enterTo(Account account) {
        myLogger.info(LoginSwing.class.getName(), "enterTo",
                "an account with user name \"" + account.toString() + "\" logged in");
        account.setOnline(true);
        page.getManager().goToMenuPage(account);
    }
}

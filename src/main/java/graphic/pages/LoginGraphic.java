package graphic.pages;

import graphic.GraphicPages;
import logic.Account;
import logic.Singleton;
import logic.pages.LoginPage;

import javax.swing.*;

public class LoginGraphic extends GraphicPages {

    private LoginPage loginPage;

    public LoginGraphic(LoginPage loginPage) {
        super();
        this.loginPage = loginPage;
    }

    @Override
    public void run() {
        while (true) {
            int answer = JOptionPane.showConfirmDialog(null, "Already have an account?", "Login", JOptionPane.YES_NO_CANCEL_OPTION);
            switch (answer) {
                case 0 -> {
                    processToEnterAnAccount();
                    return;
                }
                case 1 -> processCreatingAnAccount();
                case 2 -> {
                    Singleton.save(loginPage.getManager());
                    return;
                }
                default -> {
                    Singleton.save(loginPage.getManager());
                    System.exit(0);
                }
            }
        }
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    private void processToEnterAnAccount() {
        String userName = getUserNameThatExist();
        Account account = loginPage.getManager().searchByUserName(userName);
        String password = getWord("password");
        while (!loginPage.getManager().isCorrectPassword(account, password)) {
            JOptionPane.showMessageDialog(null, "Incorrect password!", "Match password", JOptionPane.ERROR_MESSAGE);
            password = getWord("password");
        }
        myLogger.info(LoginGraphic.class.getName(), "processToEnterAnAccount",
                "an account with user name \"" + account.toString() + "\" logged in");
        JOptionPane.showMessageDialog(null, "You are logged in", "Successful process", JOptionPane.INFORMATION_MESSAGE);
        account.setOnline(true);
        loginPage.getManager().goToMenuPage(account);
    }

    private void processCreatingAnAccount() {
        String firstName = getWord("first name");
        String lastName = getWord("last name");
        String userName = getUserNameThatDoesntExist();
        String password = getWord("password");
        String email = getEmailAddress();
        String phoneNumber = JOptionPane.showInputDialog(null, "Enter your phone number:", "Creating account", JOptionPane.PLAIN_MESSAGE);
        String bio = JOptionPane.showInputDialog(null, "Enter your bio:", "Creating account", JOptionPane.PLAIN_MESSAGE);
        Account account = loginPage.getManager().createAnAccount(firstName, lastName, userName, password, email, phoneNumber, bio);
        if (account != null) {
            myLogger.info(LoginGraphic.class.getName(), "processCreatingAnAccount",
                    "an account with user name \"" + account.toString() + "\" has created");
            JOptionPane.showMessageDialog(null, "Your account created successfully", "Successful process", JOptionPane.INFORMATION_MESSAGE);
        } else {
            myLogger.error(LoginGraphic.class.getName(), "processCreatingAnAccount",
                    "Failed to creating an account");
            JOptionPane.showMessageDialog(null, "Your account failed to create!!", "Failed process", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getEmailAddress() {
        String email = getWord("email address");
        while (loginPage.getManager().searchByEmail(email) != null) {
            JOptionPane.showMessageDialog(null, "This email has been token try another one!", "get user name", JOptionPane.WARNING_MESSAGE);
            email = getWord("email address");
        }
        return email;
    }

    private String getWord(String word) {
        String result = JOptionPane.showInputDialog(null, "Enter your " + word + ":", "Creating account", JOptionPane.PLAIN_MESSAGE);
        while (result == null) {
            result = JOptionPane.showInputDialog(null, "You should enter your " + word + ":", "Creating account", JOptionPane.PLAIN_MESSAGE);
        }
        return result;
    }

    private String getUserNameThatDoesntExist() {
        String userName = getWord("user name");
        while (loginPage.getManager().searchByUserName(userName) != null) {
            JOptionPane.showMessageDialog(null, "User already exist!\ntry another one", "get user name", JOptionPane.WARNING_MESSAGE);
            userName = getWord("user name");
        }
        return userName;
    }

    private String getUserNameThatExist() {
        String userName = getWord("user name");
        while (loginPage.getManager().searchByUserName(userName) == null) {
            JOptionPane.showMessageDialog(null, "This user name not found please try another one", "take user name", JOptionPane.ERROR_MESSAGE);
            userName = getWord("user name");
        }
        return userName;
    }
}

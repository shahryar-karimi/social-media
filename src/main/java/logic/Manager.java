package logic;

import java.util.LinkedList;

import cLI.*;
import cLI.messengerCLI.MessagesCLI;
import cLI.personalCLI.InfoCLI;
import graphic.pages.LoginGraphic;
import graphic.pages.MenuGraphic;
import graphic.pages.personalPage.PersonalPageSwing;
import logic.pages.LoginPage;

public class Manager {
    private LinkedList<Account> accounts;

    public Manager() {
        this.accounts = new LinkedList<>();
    }

    public void setAccounts(LinkedList<Account> accounts) {
        this.accounts = accounts;
    }

    public Account createAnAccount(String firstName, String lastName, String userName, String password, String emailAddress, String phoneNumber, String bio) {
        Account newAccount = new Account(this, firstName, lastName, userName, password, emailAddress, phoneNumber, bio);
        accounts.add(newAccount);
        return newAccount;
    }

    public LinkedList<Account> getAccounts() {
        return accounts;
    }

    public Account searchByUserName(String userName) {
        for (Account account : accounts)
            if (account.getUserName().equals(userName)) return account;
        return null;
    }

    public boolean isCorrectPassword(Account account, String password) {
        return password.equals(account.getPassword());
    }

    public void save() {
        Singleton.save(this);
    }

    public void quit(Account account) {
        account.setOnline(false);
        save();
        goToLoginPage();
        System.exit(0);
    }

    public void exit(Account account) {
        account.setOnline(false);
        save();
        System.exit(0);
    }

    public void goToLoginPage() {
        LoginGraphic loginGraphic = new LoginGraphic(new LoginPage(this));
        loginGraphic.run();
    }

    public void goToMenuPage(Account account) {
        MenuGraphic menuGraphic = new MenuGraphic(account.getMenuPage());
        menuGraphic.run();
    }

    public void gotoPersonalPage(Account account) {
        PersonalPageSwing personalPageSwing = new PersonalPageSwing(account.getPersonalPage());
        personalPageSwing.run();
    }

    public void goToTimeLinePage(Account account) {
        TimeLineCLI timeLineCLI = new TimeLineCLI(account.getTimeLinePage());
        timeLineCLI.run();
    }

    public void goToExplorerPage(Account account) {
        ExplorerCLI explorerCLI = new ExplorerCLI(account.getExplorerPage());
        explorerCLI.run();
    }

    public void goToSettingPage(Account account) {
        SettingCLI settingCLI = new SettingCLI(account.getSettingPage());
        settingCLI.run();
    }

    public void goToMessagesPage(Account account) {
        MessagesCLI messagesCLI = new MessagesCLI(account.getMessagesPage());
        messagesCLI.run();
    }

    public void goToInfoPage(Account infosAccount, Account visitor) {
        if (!infosAccount.hasBlocked(visitor) && infosAccount.isActive()) {
            InfoCLI infoCLI = new InfoCLI(infosAccount.getPersonalPage().getInfo(), visitor);
            infoCLI.run();
        } else {
            System.err.println("Page not found\nYou are blocked or page is deActive");
        }
    }

    public Account searchByEmail(String email) {
        for (Account account : accounts)
            if (email.equals(account.getEmailAddress())) return account;
        return null;
    }
}

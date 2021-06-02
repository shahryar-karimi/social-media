package logic;

import java.util.LinkedList;

import cLI.*;
import cLI.messengerCLI.MessagesCLI;
import cLI.personalCLI.InfoCLI;
import graphic.pages.MenuSwing;
import graphic.pages.login.LoginSwing;
import graphic.pages.personalPage.PersonalPageSwing;
import logic.Logger.MyLogger;
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
        save();
        return newAccount;
    }

    public LinkedList<Account> getAccounts() {
        return accounts;
    }

    public Account searchByUserName(String userName) {
        if (userName == null) return null;
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
        goToLoginPage();
        System.exit(0);
    }

    public void exit(Account account) {
        account.setOnline(false);
        save();
        MyLogger logger = MyLogger.getLogger();
        logger.debug(Manager.class.getName(), "exit", "Program ended");
        System.exit(0);
    }

    public void goToLoginPage() {
        save();
        new LoginSwing(new LoginPage(this));
    }

    public void goToMenuPage(Account account) {
        save();
        new MenuSwing(account.getMenuPage());
    }

    public void gotoPersonalPage(Account account) {
        save();
        new PersonalPageSwing(account.getPersonalPage());
    }

    public void goToTimeLinePage(Account account) {
        save();
        TimeLineCLI timeLineCLI = new TimeLineCLI(account.getTimeLinePage());
        timeLineCLI.run();
    }

    public void goToExplorerPage(Account account) {
        save();
        ExplorerCLI explorerCLI = new ExplorerCLI(account.getExplorerPage());
        explorerCLI.run();
    }

    public void goToSettingPage(Account account) {
        save();
        SettingCLI settingCLI = new SettingCLI(account.getSettingPage());
        settingCLI.run();
    }

    public void goToMessagesPage(Account account) {
        save();
        MessagesCLI messagesCLI = new MessagesCLI(account.getMessagesPage());
        messagesCLI.run();
    }

    public void goToInfoPage(Account infosAccount, Account visitor) {
        save();
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

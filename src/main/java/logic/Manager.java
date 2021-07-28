package logic;

import java.util.LinkedList;

import cLI.personalCLI.InfoCLI;
import graphic.GraphicManager;
import logic.Logger.MyLogger;
import logic.pages.personal.Info;

public class Manager {
    private LinkedList<Account> accounts;
    private transient GraphicManager graphicManager;

    public Manager() {
        accounts = new LinkedList<>();
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
        for (Account account : this.accounts)
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
        MyLogger logger = MyLogger.getLogger();
        logger.debug(Manager.class.getName(), "quit", "Program ended");
        save();
        goToLoginPage();
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
        graphicManager = new GraphicManager();
        graphicManager.goToLoginPage(this);
    }

    public void goToMenuPage(Account account) {
        save();
        graphicManager.goToMenuPage(account);
    }

    public void gotoPersonalPage(Account account) {
        save();
        graphicManager.gotoPersonalPage(account);
    }

    public void goToTimeLinePage(Account account) {
        save();
        graphicManager.goToTimeLinePage(account);
    }

    public void goToExplorerPage(Account account) {
        save();
        graphicManager.goToExplorerPage(account);
    }

    public void goToSettingPage(Account account) {
        save();
        graphicManager.goToSettingPage(account);
    }

    public void goToMessagesPage(Account account) {
        save();
        graphicManager.goToMessagesPage(account);
    }

    public void goToInfoPage(Info info, Account visitor) {
        save();
//        InfoCLI infoCLI = new InfoCLI(info, visitor);
//        infoCLI.run();
        graphicManager.goToInfoPage(info, visitor);
    }

    public Account searchByEmail(String email) {
        for (Account account : accounts)
            if (email.equals(account.getEmailAddress())) return account;
        return null;
    }

    public GraphicManager getGraphicManager() {
        return graphicManager;
    }

    public void setGraphicManager(GraphicManager graphicManager) {
        this.graphicManager = graphicManager;
    }
}

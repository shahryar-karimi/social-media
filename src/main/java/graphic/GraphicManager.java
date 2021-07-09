package graphic;

import cLI.ExplorerCLI;
import cLI.SettingCLI;
import cLI.messengerCLI.MessagesCLI;
import cLI.personalCLI.InfoCLI;
import graphic.pages.menuPage.MenuSwing;
import graphic.pages.Swing;
import graphic.pages.TimeLineSwing;
import graphic.pages.login.LoginSwing;
import graphic.pages.personalPage.PersonalPageSwing;
import logic.Account;
import logic.Manager;
import logic.pages.LoginPage;

import java.util.Stack;

public class GraphicManager {

    private Stack<Swing> swings;

    public GraphicManager() {
        swings = new Stack<>();
    }

    public GraphicManager(Stack<Swing> swings) {
        this.swings = swings;
    }

    public void addSwing(Swing swing) {
        swings.push(swing);
    }

    public void back() {
        if (!swings.isEmpty()) {
            swings.pop().dispose();
        }
        if (!swings.isEmpty()) {
            swings.peek().run();
        }
    }

    public void goToLoginPage(Manager manager) {
        new LoginSwing(new LoginPage(manager));
    }

    public void goToMenuPage(Account account) {
        new MenuSwing(account.getMenuPage());
    }

    public void gotoPersonalPage(Account account) {
        new PersonalPageSwing(account.getPersonalPage());
    }

    public void goToTimeLinePage(Account account) {
        new TimeLineSwing(account.getTimeLinePage(), false);
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


    public Stack<Swing> getSwings() {
        return swings;
    }

    public void setSwings(Stack<Swing> swings) {
        this.swings = swings;
    }
}

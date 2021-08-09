package graphic;

import cLI.SettingCLI;
import cLI.messengerCLI.ChatRoomCLI;
import cLI.messengerCLI.MessengersCLI;
import graphic.pages.explorer.ExplorerSwing;
import graphic.pages.menuPage.MenuSwing;
import graphic.pages.Swing;
import graphic.pages.messages.messenger.MessengerSwing;
import graphic.pages.personalPage.InfoPageSwing;
import graphic.pages.login.LoginSwing;
import graphic.pages.personalPage.PersonalPageSwing;
import graphic.pages.timeline.TimeLineSwing;
import logic.Account;
import logic.Manager;
import logic.pages.LoginPage;
import logic.pages.messenger.ChatRoom;
import logic.pages.personal.Info;

import javax.swing.*;
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
            Swing swing = swings.peek();
            swing.updateGraphic();
            swing.run();
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
        new TimeLineSwing(account.getTimeLinePage());
    }

    public void goToExplorerPage(Account account) {
        new ExplorerSwing(account.getExplorerPage());
//        ExplorerCLI explorerCLI = new ExplorerCLI(account.getExplorerPage());
//        explorerCLI.run();
    }

    public void goToSettingPage(Account account) {
        SettingCLI settingCLI = new SettingCLI(account.getSettingPage());
        settingCLI.run();
    }

    public void goToMessagesPage(Account account) {
        new MessengerSwing(account.getMessengersPage());
//        MessengersCLI messengersCLI = new MessengersCLI(account.getMessengersPage());
//        messengersCLI.run();
    }

    public void goToInfoPage(Info info, Account visitor) {
        if (!info.getAccount().hasBlocked(visitor) && info.getAccount().isActive()) {
            new InfoPageSwing(info, visitor);
        } else {
            JOptionPane.showMessageDialog(null, "Page not found\nYou are blocked or page is deActive", "Change frame", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void goToChatRoom(ChatRoom chatRoom) {
        ChatRoomCLI chatRoomCLI = new ChatRoomCLI(chatRoom);
        chatRoomCLI.run();
    }

    public Stack<Swing> getSwings() {
        return swings;
    }

    public void setSwings(Stack<Swing> swings) {
        this.swings = swings;
    }
}

package graphic;

import cLI.SettingCLI;
import graphic.pages.explorer.ExplorerSwing;
import graphic.pages.menuPage.MenuSwing;
import graphic.pages.Swing;
import graphic.pages.messages.chatRoom.ChatRoomSwing;
import graphic.pages.messages.messenger.MessengerSwing;
import graphic.pages.personalPage.InfoPageSwing;
import graphic.pages.login.LoginSwing;
import graphic.pages.personalPage.PersonalPageSwing;
import graphic.pages.setting.SettingSwing;
import graphic.pages.timeline.TimeLineSwing;
import graphic.pages.timeline.comment.CommentsSwing;
import logic.Account;
import logic.Manager;
import logic.Tweet;
import logic.pages.LoginPage;
import logic.pages.TimeLinePage;
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
    }

    public void goToSettingPage(Account account) {
//        SettingCLI settingCLI = new SettingCLI(account.getSettingPage());
//        settingCLI.run();
        new SettingSwing(account.getSettingPage());
    }

    public void goToMessagesPage(Account account) {
        new MessengerSwing(account.getMessengersPage());
    }

    public void goToInfoPage(Info info, Account visitor) {
        if (!info.getAccount().hasBlocked(visitor) && info.getAccount().isActive()) {
            new InfoPageSwing(info, visitor);
        } else {
            JOptionPane.showMessageDialog(null, "Page not found\nYou are blocked or page is deActive", "Change frame", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void goToComment(TimeLinePage timeLinePage, Tweet tweet) {
        new CommentsSwing(timeLinePage, tweet);
    }

    public void goToChatRoom(ChatRoom chatRoom) {
        new ChatRoomSwing(chatRoom);
    }

    public Stack<Swing> getSwings() {
        return swings;
    }

    public void setSwings(Stack<Swing> swings) {
        this.swings = swings;
    }
}

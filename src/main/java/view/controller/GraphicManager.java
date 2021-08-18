package view.controller;

import view.pages.explorer.controller.ExplorerController;
import view.pages.explorer.listener.ExplorerListener;
import view.pages.explorer.view.ExplorerSwing;
import view.pages.login.listener.RegistrationListener;
import view.pages.menuPage.controller.MenuController;
import view.pages.menuPage.listener.MenuListener;
import view.pages.menuPage.view.MenuSwing;
import view.pages.Swing;
import view.pages.messages.chatRoom.controller.ChatRoomController;
import view.pages.messages.chatRoom.listener.ChatRoomListener;
import view.pages.messages.chatRoom.view.ChatRoomSwing;
import view.pages.messages.messenger.controller.MessengersController;
import view.pages.messages.messenger.listener.MessengersListener;
import view.pages.messages.messenger.view.MessengerSwing;
import view.pages.personalPage.controller.ProfileController;
import view.pages.personalPage.info.controller.InfoController;
import view.pages.personalPage.info.listener.InfoListener;
import view.pages.personalPage.info.view.InfoPageSwing;
import view.pages.login.view.LoginSwing;
import view.pages.personalPage.PersonalPageSwing;
import view.pages.personalPage.listener.ProfileListener;
import view.pages.setting.view.SettingSwing;
import view.pages.setting.controller.SettingController;
import view.pages.setting.listener.SettingListener;
import view.pages.timeline.controller.TimeLineController;
import view.pages.timeline.listener.TimeLineListener;
import view.pages.timeline.view.TimeLineSwing;
import view.pages.timeline.view.comment.CommentsSwing;
import model.Account;
import logic.Manager;
import model.Tweet;
import model.pages.LoginPage;
import model.pages.TimeLinePage;
import model.pages.messenger.ChatRoom;
import model.pages.personal.Info;

import javax.swing.JOptionPane;
import java.util.Stack;

public class GraphicManager {

    private final Stack<Swing> swings;

    public GraphicManager() {
        swings = new Stack<>();
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

    public void home() {
        swings.pop().dispose();
        swings.empty();
    }

    public void goToLoginPage(Manager manager) {
        new LoginSwing(new RegistrationListener(new LoginPage(manager)));
    }

    public void goToMenuPage(Account account) {
        new MenuSwing(new MenuListener(new MenuController(account.getMenuPage())));
    }

    public void gotoPersonalPage(Account account) {
        new PersonalPageSwing(new ProfileListener(new ProfileController(account.getPersonalPage())));
    }

    public void goToTimeLinePage(Account account) {
        new TimeLineSwing(new TimeLineListener(new TimeLineController(account.getTimeLinePage())));
    }

    public void goToExplorerPage(Account account) {
        new ExplorerSwing(new ExplorerListener(new ExplorerController(account.getExplorerPage())));
    }

    public void goToSettingPage(Account account) {
        new SettingSwing(new SettingListener(new SettingController(account.getSettingPage())));
    }

    public void goToMessagesPage(Account account) {
        new MessengerSwing(new MessengersListener(new MessengersController(account.getMessengersPage())));
    }

    public void goToInfoPage(Info info, Account visitor) {
        if (!info.getAccount().hasBlocked(visitor) && info.getAccount().isActive()) {
            new InfoPageSwing(new InfoListener(new InfoController(info)), visitor);
        } else {
            JOptionPane.showMessageDialog(null, "Page not found\nYou are blocked or page is deActive", "Change frame", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void goToComment(TimeLinePage timeLinePage, Tweet tweet) {
        new CommentsSwing(tweet, new TimeLineListener(new TimeLineController(timeLinePage)));
    }

    public void goToChatRoom(ChatRoom chatRoom) {
        new ChatRoomSwing(new ChatRoomListener(new ChatRoomController(chatRoom)));
    }

    public Stack<Swing> getSwings() {
        return swings;
    }
}

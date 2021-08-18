package view.pages.personalPage.notification.controller;

import model.Account;
import model.pages.personal.Notification;
import view.controller.MainGraphicController;

public class NotificationController extends MainGraphicController {

    public NotificationController(Notification page) {
        super(page);
    }

    public void accept(Account account) {
        ((Notification) page).acceptRequest(account);
    }

    public void decline(boolean isMute, Account account) {
        ((Notification) page).declineRequest(isMute, account);
    }
}

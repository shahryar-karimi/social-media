package view.pages.personalPage.listener;

import event.Event;
import listener.FormListener;
import view.pages.personalPage.controller.ProfileController;
import view.pages.personalPage.event.SendBtnEvent;

public class ProfileListener extends FormListener {
    public ProfileListener(ProfileController controller) {
        this.controller = controller;
    }

    @Override
    public String eventOccurred(Event event) {
        SendBtnEvent sendBtnEvent = (SendBtnEvent) event;
        return ((ProfileController) controller).sendBtn(sendBtnEvent.getTweetText());
    }
}

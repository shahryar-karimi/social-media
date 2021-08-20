package view.pages.login.listener;

import event.Event;
import listener.FormListener;
import view.pages.login.controller.RegistrationController;
import view.pages.login.event.LoginEvent;
import view.pages.login.event.SignUpEvent;

public class RegistrationListener extends FormListener {
    public RegistrationListener(RegistrationController controller) {
        this.controller = controller;
    }

    @Override
    public String eventOccurred(Event event) {
        if (event instanceof LoginEvent) {
            LoginEvent loginEvent = (LoginEvent) event;
            return ((RegistrationController) controller).login(loginEvent.getUserName(), loginEvent.getPassword());
        } else if (event instanceof SignUpEvent) {
            SignUpEvent signUpEvent = (SignUpEvent) event;
            return ((RegistrationController) controller).signUp(signUpEvent.getFirstName(), signUpEvent.getLastName(), signUpEvent.getUserName(), signUpEvent.getPassword(),
                    signUpEvent.getEmail(), signUpEvent.getPhoneNumber(), signUpEvent.getBio());
        }
        return null;
    }
}

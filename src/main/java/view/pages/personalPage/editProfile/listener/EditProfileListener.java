package view.pages.personalPage.editProfile.listener;

import event.Event;
import listener.FormListener;
import view.pages.personalPage.editProfile.controller.EditProfileController;
import view.pages.personalPage.editProfile.event.EditProfileEvent;

public class EditProfileListener extends FormListener {
    public EditProfileListener(EditProfileController controller) {
        this.controller = controller;
    }

    @Override
    public String eventOccurred(Event event) {
        EditProfileEvent editProfileEvent = (EditProfileEvent) event;
        return switch (editProfileEvent.getWork()) {
            case "first name" -> ((EditProfileController) controller).editFirstName(editProfileEvent.getNewVision());
            case "last name" -> ((EditProfileController) controller).editLastName(editProfileEvent.getNewVision());
            case "password" -> ((EditProfileController) controller).editPassword(editProfileEvent.getNewVision());
            case "birthdate" -> ((EditProfileController) controller).editBirthDate(editProfileEvent.getNewVision());
            case "phone number" -> ((EditProfileController) controller).editPhoneNumber(editProfileEvent.getNewVision());
            case "email" -> ((EditProfileController) controller).editEmail(editProfileEvent.getNewVision());
            case "bio" -> ((EditProfileController) controller).editBio(editProfileEvent.getNewVision());
            default -> "";
        };
    }
}

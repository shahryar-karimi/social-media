package view.pages.personalPage.editProfile.controller;

import model.pages.personal.PersonalPage;
import view.controller.MainGraphicController;

public class EditProfileController extends MainGraphicController {

    public EditProfileController(PersonalPage page) {
        super(page);
    }

    public String writeMsg(String name, String last, String change) {
        return "\"" + name + "\" changes from \"" + last +
                "\" to \"" + change + "\"\n";
    }

    public String editFirstName(String newFirstName) {
        String oldFirstName = page.getAccount().getFirstName();
        ((PersonalPage) page).editFirstName(newFirstName);
        return writeMsg("first name", oldFirstName, newFirstName);
    }

    public String editLastName(String newLastName) {
        String oldLastName = page.getAccount().getLastName();
        ((PersonalPage) page).editLastName(newLastName);
        return writeMsg("last name", oldLastName, newLastName);
    }

    public String editPassword(String newPassword) {
        String oldPassword = page.getAccount().getPassword();
        ((PersonalPage) page).editPassword(newPassword);
        return writeMsg("password", oldPassword, newPassword);
    }

    public String editBirthDate(String newBirthDate) {
        String oldBirthDate = page.getAccount().getBirthDate();
        ((PersonalPage) page).editBirthdayDate(newBirthDate);
        return writeMsg("birthdate", oldBirthDate, newBirthDate);
    }

    public String editPhoneNumber(String newPhoneNumber) {
        String oldPhoneNumber = page.getAccount().getPhoneNumber();
        ((PersonalPage) page).editPhoneNumber(newPhoneNumber);
        return writeMsg("phone number", oldPhoneNumber, newPhoneNumber);
    }

    public String editEmail(String newEmail) {
        String oldEmail = page.getAccount().getEmailAddress();
        ((PersonalPage) page).editEmail(newEmail);
        return writeMsg("email", oldEmail, newEmail);
    }

    public String editBio(String newBio) {
        String oldBio = page.getAccount().getBio();
        ((PersonalPage) page).editBio(newBio);
        return writeMsg("bio", oldBio, newBio);
    }
}

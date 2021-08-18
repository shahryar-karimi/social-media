package view.pages.personalPage.editProfile.event;

import event.Event;

public class EditProfileEvent extends Event {
    private final String work;
    private final String newVision;

    public EditProfileEvent(Object source, String work, String newVision) {
        super(source);
        this.work = work;
        this.newVision = newVision;
    }

    public String getWork() {
        return work;
    }

    public String getNewVision() {
        return newVision;
    }
}

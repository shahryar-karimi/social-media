package view.pages.login.event;

import event.Event;

public class SignUpEvent extends Event {
    private final String firstName;
    private final String lastName;
    private final String userName;
    private final String password;
    private final String email;
    private final String phoneNumber;
    private final String bio;

    public SignUpEvent(Object source, String firstName, String lastName, String userName, String password,
                       String email, String phoneNumber, String bio) {
        super(source);
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bio = bio;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBio() {
        return bio;
    }
}

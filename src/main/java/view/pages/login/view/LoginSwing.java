package view.pages.login.view;

import view.pages.Swing;
import view.pages.login.event.LoginEvent;
import view.pages.login.event.SignUpEvent;
import view.pages.login.listener.RegistrationListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginSwing extends Swing {

    private LoginPanel loginPanel;
    private SignUpPanel signUpPanel;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public LoginSwing(RegistrationListener listener) {
        super();
        this.listener = listener;
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        run();
    }

    @Override
    public void run() {
        myLogger.debug(LoginSwing.class.getName(), "run", "login page ran");
        showGraphic();
    }

    @Override
    public void showGraphic() {
        this.setSize(300, 360);
        this.setTitle("Registration page");
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 300, 360);

        loginPanel = new LoginPanel();
        loginPanel.getLoginButton().addActionListener(this);
        loginPanel.getSignUp().addActionListener(this);

        signUpPanel = new SignUpPanel();
        signUpPanel.getLogin().addActionListener(this);
        signUpPanel.getSignUP().addActionListener(this);

        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        mainPanel.add(loginPanel, "login");
        mainPanel.add(signUpPanel, "sign up");

        cardLayout.show(mainPanel, "login");

        this.add(mainPanel);
        this.setVisible(true);
    }

    @Override
    public void updateGraphic() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginPanel.getLoginButton()) {
            login();
        } else if (e.getSource() == loginPanel.getSignUp()) {
            this.setTitle("Sign up");
            cardLayout.show(mainPanel, "sign up");
        } else if (e.getSource() == signUpPanel.getSignUP()) {
            signUp();
        } else if (e.getSource() == signUpPanel.getLogin()) {
            this.setTitle("LoginEvent");
            cardLayout.show(mainPanel, "login");
        }
    }

    public boolean isValidString(String[] s) {
        for (String s1 : s) {
            if (s1 == null || s1.isBlank()) return false;
        }
        return true;
    }

    private void login() {
        String userName = loginPanel.getUserNameT().getText();
        String password = String.valueOf(loginPanel.getPasswordT().getPassword());
        if (isValidString(new String[]{userName, password})) {
            LoginEvent loginEvent = new LoginEvent(this, userName, password);
            String msg = listener.eventOccurred(loginEvent);
            if (msg != null) {
                JOptionPane.showMessageDialog(null, msg);
                if (msg.equals(userName + " logged in")) {
                    dispose();
                }
            }
        } else {
            showMessageDialogError("Please fill blank spaces.", "LoginEvent");
        }
    }

    private void signUp() {
        String firstName = signUpPanel.getFirstNameT().getText();
        String lastName = signUpPanel.getLastNameT().getText();
        String userName = signUpPanel.getUserNameT().getText();
        String password = signUpPanel.getPasswordT().getText();
        String email = signUpPanel.getEmailT().getText();
        String phoneNumber = signUpPanel.getPhoneNumberT().getText();
        String bio = signUpPanel.getBioT().getText();
        if (isValidString(new String[]{firstName, lastName, userName, password, email})) {
            SignUpEvent signUpEvent = new SignUpEvent(this, firstName, lastName, userName, password, email, phoneNumber, bio);
            String msg = listener.eventOccurred(signUpEvent);
            if (msg != null) {
                JOptionPane.showMessageDialog(null, msg);
                if (msg.equals("Page " + userName + " created successfully!")) {
                    dispose();
                }
            }
        } else {
            showMessageDialogError("Please fill all of " +
                    "\"first name\", " +
                    "\"last name\", " +
                    "\"user name\", " +
                    "\"password\", " +
                    "\"email\"", "Sign up");
        }
    }

    private void showMessageDialogError(String msg, String title) {
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.ERROR_MESSAGE);
        emptyFields(title.equals("LoginEvent"));
    }

    private void emptyFields(boolean isLogin) {
        if (isLogin) {
            loginPanel.getUserNameT().setText("");
            loginPanel.getPasswordT().setText("");
        } else {
            signUpPanel.getFirstNameT().setText("");
            signUpPanel.getLastNameT().setText("");
            signUpPanel.getUserNameT().setText("");
            signUpPanel.getPasswordT().setText("");
            signUpPanel.getEmailT().setText("");
            signUpPanel.getPhoneNumberT().setText("");
            signUpPanel.getBioT().setText("");
        }
    }
}

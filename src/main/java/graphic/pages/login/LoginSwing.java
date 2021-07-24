package graphic.pages.login;

import graphic.pages.Swing;
import logic.Account;
import logic.Manager;
import logic.pages.LoginPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginSwing extends Swing {

    private final LoginPage loginPage;
    private LoginPanel loginPanel;
    private SignUpPanel signUpPanel;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public LoginSwing(LoginPage loginPage) {
        super();
        this.loginPage = loginPage;
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
        this.setTitle("Login");
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
//        this.pack();
        this.setVisible(true);
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
            this.setTitle("Login");
            cardLayout.show(mainPanel, "login");
        }
    }

    public void enterTo(Account account, String page) {
        myLogger.info(LoginSwing.class.getName(), "enterTo",
                "an account with user name \"" + account.toString() + "\" logged in");
        JOptionPane.showMessageDialog(null, account.toString() + " logged in", page, JOptionPane.INFORMATION_MESSAGE);
        account.setOnline(true);
        this.dispose();
        loginPage.getManager().goToMenuPage(account);
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
            Account account = loginPage.getManager().searchByUserName(userName);
            if (account == null) {
                showMessageDialogErrorLogin("Account not found\nTry another user name or sign up");
            } else {
                if (loginPage.getManager().isCorrectPassword(account, password)) {
                    enterTo(account, "Login");
                } else {
                    showMessageDialogErrorLogin("Incorrect password");
                }
            }
        } else {
            showMessageDialogErrorLogin("Please fill blank spaces.");
        }
    }

    private void showMessageDialogErrorLogin(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Login", JOptionPane.ERROR_MESSAGE);
        emptyFields(true);
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
            if (loginPage.getManager().searchByUserName(userName) != null) {
                showMessageDialogErrorSignUp("User already exist!\nTry another user name");
                signUpPanel.getUserNameT().setText("");
                signUpPanel.getPasswordT().setText("");
            } else if (loginPage.getManager().searchByEmail(email) != null) {
                showMessageDialogErrorSignUp("User with this email already exist!\nTry another email address");
                signUpPanel.getEmailT().setText("");
            } else {
                Account account = loginPage.getManager().createAnAccount(firstName, lastName, userName, password, email, phoneNumber, bio);
                if (account != null) {
                    JOptionPane.showMessageDialog(null, "Page " + account.toString() + " created successfully!", "Sign up", JOptionPane.INFORMATION_MESSAGE);
                    enterTo(account, "Sign up");
                } else {
                    showMessageDialogErrorSignUp("Failed to create this account");
                    emptyFields(false);
                }
            }
        } else {
            showMessageDialogErrorSignUp("Please fill all of " +
                    "\"first name\", " +
                    "\"last name\", " +
                    "\"user name\", " +
                    "\"password\", " +
                    "\"email\"");
        }
    }

    private void showMessageDialogErrorSignUp(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Sign up", JOptionPane.ERROR_MESSAGE);
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

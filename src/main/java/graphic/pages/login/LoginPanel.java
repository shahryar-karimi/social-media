package graphic.pages.login;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private JTextField userNameT;
    private JPasswordField passwordT;
    private JButton loginButton;
    private JButton signUp;

    public LoginPanel() {
        this.setLayout(null);
        this.setBounds(0, 0, 300, 360);

        JLabel userName = new JLabel("User name : ");
        userName.setBounds(10, 20, 80, 25);
        add(userName);

        JLabel password = new JLabel("Password : ");
        password.setBounds(10, 50, 80, 25);
        add(password);

        userNameT = new JTextField();
        userNameT.setBounds(100, 20, 165, 25);
        add(userNameT);

        passwordT = new JPasswordField();
        passwordT.setBounds(100, 50, 165, 25);
        add(passwordT);

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 80, 80, 25);
        add(loginButton);

        signUp = new JButton("SignUp");
        signUp.setBounds(190, 80, 80, 25);
        add(signUp);
    }

    public JTextField getUserNameT() {
        return userNameT;
    }

    public JPasswordField getPasswordT() {
        return passwordT;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getSignUp() {
        return signUp;
    }
}

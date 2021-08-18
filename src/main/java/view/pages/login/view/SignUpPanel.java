package view.pages.login.view;

import javax.swing.*;

public class SignUpPanel extends JPanel {
    private final JTextField firstNameT;
    private final JTextField lastNameT;
    private final JTextField userNameT;
    private final JTextField passwordT;
    private final JTextField emailT;
    private final JTextField phoneNumberT;
    private final JTextArea bioT;
    private final JButton signUP;
    private final JButton login;

    public SignUpPanel() {
        this.setLayout(null);
        this.setBounds(0, 0, 300, 360);

        JLabel firstNameL = new JLabel("First name : ");
        firstNameL.setBounds(10, 20, 105, 25);
        add(firstNameL);

        firstNameT = new JTextField();
        firstNameT.setBounds(120, 20, 165, 25);
        add(firstNameT);

        JLabel lastNameL = new JLabel("Last name : ");
        lastNameL.setBounds(10, 50, 105, 25);
        add(lastNameL);

        lastNameT = new JTextField();
        lastNameT.setBounds(120, 50, 165, 25);
        add(lastNameT);

        JLabel userNameL = new JLabel("User name : ");
        userNameL.setBounds(10, 80, 105, 25);
        add(userNameL);

        userNameT = new JTextField();
        userNameT.setBounds(120, 80, 165, 25);
        add(userNameT);

        JLabel passwordL = new JLabel("Password : ");
        passwordL.setBounds(10, 110, 105, 25);
        add(passwordL);

        passwordT = new JTextField();
        passwordT.setBounds(120, 110, 165, 25);
        add(passwordT);

        JLabel emailL = new JLabel("Email : ");
        emailL.setBounds(10, 140, 105, 25);
        add(emailL);

        emailT = new JTextField();
        emailT.setBounds(120, 140, 165, 25);
        add(emailT);

        JLabel phoneNumberL = new JLabel("Phone number : ");
        phoneNumberL.setBounds(10, 170, 105, 25);
        add(phoneNumberL);

        phoneNumberT = new JTextField();
        phoneNumberT.setBounds(120, 170, 165, 25);
        add(phoneNumberT);

        JLabel bioL = new JLabel("Bio : ");
        bioL.setBounds(10, 200, 60, 25);
        add(bioL);

        bioT = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(80, 200, 200, 100);
        scrollPane.setViewportView(bioT);
        add(scrollPane);

        signUP = new JButton("Sign up");
        signUP.setBounds(50, 305, 95, 25);
        add(signUP);

        login = new JButton("Login");
        login.setBounds(155, 305, 95, 25);
        add(login);
    }

    public JTextField getFirstNameT() {
        return firstNameT;
    }

    public JTextField getLastNameT() {
        return lastNameT;
    }

    public JTextField getUserNameT() {
        return userNameT;
    }

    public JTextField getPasswordT() {
        return passwordT;
    }

    public JTextField getEmailT() {
        return emailT;
    }

    public JButton getSignUP() {
        return signUP;
    }

    public JButton getLogin() {
        return login;
    }

    public JTextField getPhoneNumberT() {
        return phoneNumberT;
    }

    public JTextArea getBioT() {
        return bioT;
    }
}

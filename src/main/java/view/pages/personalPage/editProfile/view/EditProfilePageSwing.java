package view.pages.personalPage.editProfile.view;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import utility.AppProperties;
import utility.DateLabelFormatter;
import view.pages.Swing;
import view.pages.personalPage.PersonalPageSwing;
import view.pages.personalPage.editProfile.event.EditProfileEvent;
import view.pages.personalPage.editProfile.listener.EditProfileListener;
import view.myPanels.footerPanel.controller.FooterPanelController;
import view.myPanels.footerPanel.listener.FooterPanelListener;
import view.myPanels.footerPanel.view.FooterPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Properties;

public class EditProfilePageSwing extends Swing {
    private final JTextField firstNameT = new JTextField();
    private final JTextField lastNameT = new JTextField();
    private final JTextField passwordT = new JTextField();
    private final JTextField emailT = new JTextField();
    private final JTextField phoneNumberT = new JTextField();
    private final JTextArea bioT = new JTextArea();
    private JButton update;
    private JDatePickerImpl datePicker;

    public EditProfilePageSwing(EditProfileListener listener) {
        super();
        this.listener = listener;
        footerPanel = new FooterPanel(new FooterPanelListener(new FooterPanelController(getListener().getController().getPage())));
        addSwing(this);
        run();
    }

    @Override
    public void run() {
        this.setVisible(true);
        myLogger.debug(PersonalPageSwing.class.getName(), "run",
                "Edit Profile run for account \"" + getListener().getController().getPage().getAccount().toString() + "\"");
        showGraphic();
    }

    @Override
    public void showGraphic() {

        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());


        update = new JButton();
        JLabel firstName = new JLabel();
        JLabel lastName = new JLabel();
        JLabel password = new JLabel();
        JLabel birthdayDate = new JLabel();
        JLabel phoneNumber = new JLabel();
        JLabel email = new JLabel();
        JLabel bio = new JLabel();
        JScrollPane jScrollPane1 = new JScrollPane();
        final JPanel jPanel1 = new JPanel();


        firstName.setText(AppProperties.getInstance().getProperty("First-name"));
        lastName.setText(AppProperties.getInstance().getProperty("Last-name"));
        password.setText(AppProperties.getInstance().getProperty("Password"));
        birthdayDate.setText(AppProperties.getInstance().getProperty("Birth-date"));
        phoneNumber.setText(AppProperties.getInstance().getProperty("Phone-number"));
        email.setText(AppProperties.getInstance().getProperty("Email-address"));
        bio.setText(AppProperties.getInstance().getProperty("Bio"));

        update.setText(AppProperties.getInstance().getProperty("Update"));
        update.addActionListener(this);

        this.bioT.setColumns(20);
        this.bioT.setRows(5);
        jScrollPane1.setViewportView(this.bioT);

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(lastName)
                                        .addComponent(password)
                                        .addComponent(birthdayDate)
                                        .addComponent(phoneNumber)
                                        .addComponent(firstName)
                                        .addComponent(email)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(bio)))
                                .addGap(53, 53, 53)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(update, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                                .addComponent(emailT)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(phoneNumberT, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                                                        .addComponent(passwordT, GroupLayout.Alignment.LEADING)
                                                        .addComponent(firstNameT, GroupLayout.Alignment.LEADING)
                                                        .addComponent(lastNameT, GroupLayout.Alignment.LEADING)
                                                        .addComponent(datePicker, GroupLayout.Alignment.LEADING))))
                                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(firstName)
                                        .addComponent(firstNameT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(13, 13, 13)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lastNameT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lastName))
                                .addGap(13, 13, 13)

                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(passwordT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(password))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(datePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(birthdayDate))
                                .addGap(13, 13, 13)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(phoneNumberT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(phoneNumber))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(email)
                                        .addComponent(emailT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(bio)
                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                                .addComponent(update, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47))
        );

        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(footerPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(88, Short.MAX_VALUE)
                                .addComponent(footerPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

    }

    @Override
    public void updateGraphic() {
        firstNameT.setText("");
        lastNameT.setText("");
        passwordT.setText("");
        emailT.setText("");
        phoneNumberT.setText("");
        bioT.setText("");
    }

    public boolean isValidString(String s) {
        return s != null && !s.isBlank();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == update) {
            String newFirstName = firstNameT.getText();
            String newLastName = lastNameT.getText();
            String newPassword = passwordT.getText();
            String newBirthDate = datePicker.getJFormattedTextField().getText();
            String newPhoneNumber = phoneNumberT.getText();
            String newEmail = emailT.getText();
            String newBio = bioT.getText();
            String msg = "";
            if (isValidString(newFirstName)) {
                EditProfileEvent event = new EditProfileEvent(this, "first name", newFirstName);
                msg += listener.eventOccurred(event);
            }
            if (isValidString(newLastName)) {
                EditProfileEvent event = new EditProfileEvent(this, "last name", newLastName);
                msg += listener.eventOccurred(event);
            }
            if (isValidString(newPassword)) {
                EditProfileEvent event = new EditProfileEvent(this, "password", newPassword);
                msg += listener.eventOccurred(event);
            }
            if (isValidString(newBirthDate)) {
                EditProfileEvent event = new EditProfileEvent(this, "birthdate", newBirthDate);
                msg += listener.eventOccurred(event);
            }
            if (isValidString(newPhoneNumber)) {
                EditProfileEvent event = new EditProfileEvent(this, "phone number", newPhoneNumber);
                msg += listener.eventOccurred(event);
            }
            if (isValidString(newEmail)) {
                EditProfileEvent event = new EditProfileEvent(this, "email", newEmail);
                msg += listener.eventOccurred(event);
            }
            if (isValidString(newBio)) {
                EditProfileEvent event = new EditProfileEvent(this, "bio", newBio);
                msg += listener.eventOccurred(event);
            }
            JOptionPane.showMessageDialog(null, msg.trim(), "profile edited", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

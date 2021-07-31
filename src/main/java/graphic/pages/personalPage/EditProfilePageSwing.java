package graphic.pages.personalPage;

import graphic.FooterPanel;
import graphic.pages.Swing;
import logic.Account;
import logic.pages.personal.PersonalPage;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import utility.AppProperties;
import utility.DateLabelFormatter;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Properties;

public class EditProfilePageSwing extends Swing {
    private JButton update;
    private final JTextField firstNameT = new JTextField();
    private final JTextField lastNameT = new JTextField();
    private final JTextField passwordT = new JTextField();
    private final JTextField emailT = new JTextField();
    private final JTextField phoneNumberT = new JTextField();
    private final JTextArea bioT = new JTextArea();
    private JDatePickerImpl datePicker;

    public EditProfilePageSwing(PersonalPage personalPage) {
        super();
        this.page = personalPage;
        footerPanel = new FooterPanel(getManager(), page.getManager(), page.getAccount());
        addSwing(this);
        run();
    }

    @Override
    public void run() {
        this.setVisible(true);
        myLogger.debug(PersonalPageSwing.class.getName(), "run",
                "Edit Profile run for account \"" + page.getAccount().toString() + "\"");
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

    public String writeMsg(String name, String last, String change) {
        return "\"" + name + "\" changes from \"" + last +
                "\" to \"" + change + "\"\n";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == update) {
            Account account = page.getAccount();
            String newFirstName = firstNameT.getText();
            String newLastName = lastNameT.getText();
            String newPassword = passwordT.getText();
            String newBirthdayDate = datePicker.getJFormattedTextField().getText();
            String newPhoneNumber = phoneNumberT.getText();
            String newEmail = emailT.getText();
            String newBio = bioT.getText();
            String msg = "";
            if (isValidString(newFirstName)) {
                msg += writeMsg("first name", account.getFirstName(), newFirstName);
                ((PersonalPage) page).editFirstName(newFirstName);
            }
            if (isValidString(newLastName)) {
                msg += writeMsg("last name", account.getLastName(), newLastName);
                ((PersonalPage) page).editLastName(newLastName);
            }
            if (isValidString(newPassword)) {
                msg += writeMsg("password", account.getPassword(), newPassword);
                ((PersonalPage) page).editPassword(newPassword);
            }
            if (isValidString(newBirthdayDate)) {
                msg += writeMsg("birthday date", account.getBirthdayDate(), newBirthdayDate);
                ((PersonalPage) page).editBirthdayDate(newBirthdayDate);
            }
            if (isValidString(newPhoneNumber)) {
                msg += writeMsg("phone number", account.getPhoneNumber(), newPhoneNumber);
                ((PersonalPage) page).editPhoneNumber(newPhoneNumber);
            }
            if (isValidString(newEmail)) {
                msg += writeMsg("email", account.getEmailAddress(), newEmail);
                ((PersonalPage) page).editEmail(newEmail);
            }
            if (isValidString(newBio)) {
                msg += writeMsg("bio", account.getBio(), newBio);
                ((PersonalPage) page).editBio(newBio);
            }
            msg = msg.trim();
            JOptionPane.showMessageDialog(null, msg, "profile edited", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

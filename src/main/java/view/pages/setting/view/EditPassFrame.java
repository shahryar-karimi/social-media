package view.pages.setting.view;

import view.pages.setting.events.CheckPasswordEvent;
import view.pages.setting.events.UpdatePasswordEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EditPassFrame extends JFrame {
    private final SettingSwing settingSwing;
    private JTextField newPassField;
    private JButton confirm;
    private JButton update;
    private JPasswordField lastPassField;


    public EditPassFrame(SettingSwing settingSwing) {
        this.settingSwing = settingSwing;
        showGraphic();
    }

    private void showGraphic() {
        newPassField = new javax.swing.JTextField();
        JLabel lastPassLbl = new javax.swing.JLabel();
        JLabel newPassLbl = new javax.swing.JLabel();
        JLabel header = new javax.swing.JLabel();
        update = new javax.swing.JButton();
        lastPassField = new javax.swing.JPasswordField();
        confirm = new javax.swing.JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        lastPassLbl.setText("Enter your last pass:");

        newPassLbl.setText("Enter your new pass:");

        update.setText("update");
        update.setEnabled(false);
        update.addActionListener(this::updatePass);

        header.setFont(new java.awt.Font("Lucida Grande", Font.BOLD, 24)); // NOI18N
        header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        header.setText("Edit Password");

        confirm.setText("confirm");
        confirm.addActionListener(this::confirmPass);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                        .addComponent(newPassLbl)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(newPassField, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(update))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                        .addComponent(lastPassLbl)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(lastPassField, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(confirm))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lastPassLbl)
                                        .addComponent(lastPassField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(confirm))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(newPassField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(newPassLbl)
                                        .addComponent(update))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setVisible(true);
        JOptionPane.showMessageDialog(null, "You should enter your last password to confirm", "Editing Password", JOptionPane.WARNING_MESSAGE);
    }

    private void updatePass(ActionEvent e) {
        String newPass = newPassField.getText();
        String lastPass = settingSwing.getListener().getController().getPage().getAccount().getPassword();
        UpdatePasswordEvent event = new UpdatePasswordEvent(this, newPass);
        String msg = settingSwing.getListener().eventOccurred(event);
        dispose();
        JOptionPane.showMessageDialog(null, msg, "Editing Password", JOptionPane.PLAIN_MESSAGE);
        settingSwing.getMyLogger().info(EditPassFrame.class.getName(), "processEditPassword",
                "an account with user name \"" + settingSwing.getListener().getController().getPage().getAccount().getUserName() + "\"" +
                        " edited his/her password from \"" + lastPass + "\"" + " to \"" + newPass + "\"");
    }

    private void confirmPass(ActionEvent e) {
        String lastPass = String.valueOf(lastPassField.getPassword());
        CheckPasswordEvent event = new CheckPasswordEvent(this, lastPass);
        String msg = settingSwing.getListener().eventOccurred(event);
        if (msg.equals("Wrong last pass")) {
            JOptionPane.showMessageDialog(null, msg, "Editing Password", JOptionPane.ERROR_MESSAGE);
            lastPassField.setText("");
        } else {
            JOptionPane.showMessageDialog(null, msg, "Editing Password", JOptionPane.PLAIN_MESSAGE);
            newPassField.setEnabled(true);
            newPassField.setEditable(true);
            update.setEnabled(true);
            confirm.setEnabled(false);
            lastPassField.setEnabled(false);
            lastPassField.setEditable(false);
        }
    }
}

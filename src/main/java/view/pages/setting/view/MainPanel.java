package view.pages.setting.view;

import model.pages.SettingPage;
import view.pages.setting.events.DeleteAccountEvent;
import view.pages.setting.events.SettingActivityEvent;
import view.pages.setting.events.SettingLastSeenEvent;
import view.pages.setting.events.SettingPrivacyEvent;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;

public class MainPanel extends JPanel {
    private final SettingSwing settingSwing;
    private JComboBox<String> activityCb;
    private JLabel activityLbl;
    private JButton deleteAccountBtn;
    private JButton editPasswordBtn;
    private JLabel header;
    private JComboBox<String> lastSeenCb;
    private JLabel lastSeenLbl;
    private JComboBox<String> privacyCb;
    private JLabel privacyLbl;

    public MainPanel(SettingSwing settingSwing) {
        this.settingSwing = settingSwing;
        initComponents();
        showGraphic();
    }

    private void initComponents() {
        header = new JLabel();
        privacyCb = new JComboBox<>();
        lastSeenLbl = new JLabel();
        lastSeenCb = new JComboBox<>();
        privacyLbl = new JLabel();
        editPasswordBtn = new JButton();
        deleteAccountBtn = new JButton();
        activityLbl = new JLabel();
        activityCb = new JComboBox<>();

        header.setFont(new Font("Lucida Grande", Font.BOLD, 24));
        header.setText("Setting");
        header.setHorizontalAlignment(JLabel.CENTER);

        privacyCb.setModel(new DefaultComboBoxModel<>(new String[]{"public", "private"}));
        if (settingSwing.getPage().getAccount().isPagePublic()) privacyCb.setSelectedItem("public");
        else privacyCb.setSelectedItem("private");
        privacyCb.addActionListener(this::privacyActionPerformed);

        lastSeenLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
        lastSeenLbl.setText("Last seen : ");

        lastSeenCb.setModel(new DefaultComboBoxModel<>(new String[]{"every one", "followings", "no one"}));
        if (settingSwing.getPage().getAccount().getLastSeenSituation().equals("every one"))
            lastSeenCb.setSelectedItem("every one");
        else if (settingSwing.getPage().getAccount().getLastSeenSituation().equals("followings"))
            lastSeenCb.setSelectedItem("followings");
        else
            lastSeenCb.setSelectedItem("no one");
        lastSeenCb.addActionListener(this::lastSeenActionPerformed);

        privacyLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
        privacyLbl.setText("privacy : ");

        activityLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
        activityLbl.setText("Activity : ");

        activityCb.setModel(new DefaultComboBoxModel<>(new String[]{"Activate", "Deactivate"}));
        if (settingSwing.getPage().getAccount().isActive())
            activityCb.setSelectedItem("Activate");
        else
            activityCb.setSelectedItem("Deactivate");
        activityCb.addActionListener(this::activityActionPerformed);

        editPasswordBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
        editPasswordBtn.setText("Edit password");
        editPasswordBtn.addActionListener(this::editPasswordActionPerformed);

        deleteAccountBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
        deleteAccountBtn.setText("Delete Account");
        deleteAccountBtn.addActionListener(this::deleteAccountActionPerformed);
    }

    private void activityActionPerformed(ActionEvent e) {
        SettingActivityEvent event = new SettingActivityEvent(this, (String) activityCb.getSelectedItem());
        String msg = settingSwing.getListener().eventOccurred(event);
        JOptionPane.showMessageDialog(null, msg);
    }

    private void lastSeenActionPerformed(ActionEvent e) {
        SettingLastSeenEvent event = new SettingLastSeenEvent(this, (String) lastSeenCb.getSelectedItem());
        String msg = settingSwing.getListener().eventOccurred(event);
        JOptionPane.showMessageDialog(null, msg);
    }

    private void privacyActionPerformed(ActionEvent e) {
        SettingPrivacyEvent event = new SettingPrivacyEvent(this, (String) privacyCb.getSelectedItem());
        String msg = settingSwing.getListener().eventOccurred(event);
        JOptionPane.showMessageDialog(null, msg);
    }

    private void editPasswordActionPerformed(ActionEvent e) {
        new EditPassFrame(settingSwing);
    }

    private void deleteAccountActionPerformed(ActionEvent e) {
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete your account?",
                "Delete Account", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (result == 0) {
            DeleteAccountEvent event = new DeleteAccountEvent(this);
            settingSwing.getListener().eventOccurred(event);
            settingSwing.getManager().getSwings().empty();
            settingSwing.dispose();
            settingSwing.getPage().getManager().goToLoginPage();
        }
    }

    private void showGraphic() {
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(header, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(deleteAccountBtn, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(lastSeenLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(activityLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(privacyLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(activityCb, 0, 450, Short.MAX_VALUE)
                                                        .addComponent(lastSeenCb, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(privacyCb, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addComponent(editPasswordBtn, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(header, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(privacyCb, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(privacyLbl, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lastSeenLbl, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                        .addComponent(lastSeenCb))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(activityLbl, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                        .addComponent(activityCb))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editPasswordBtn, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(deleteAccountBtn, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
    }
}

package graphic.pages.setting;

import logic.pages.SettingPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainPanel extends JPanel {
    private SettingSwing settingSwing;
    private javax.swing.JComboBox<String> activityCb;
    private javax.swing.JLabel activityLbl;
    private javax.swing.JButton deleteAccountBtn;
    private javax.swing.JButton editPasswordBtn;
    private javax.swing.JLabel header;
    private javax.swing.JComboBox<String> lastSeenCb;
    private javax.swing.JLabel lastSeenLbl;
    private javax.swing.JComboBox<String> privacyCb;
    private javax.swing.JLabel privacyLbl;

    public MainPanel(SettingSwing settingSwing) {
        this.settingSwing = settingSwing;
        initComponents();
        showGraphic();
    }

    private void initComponents() {
        header = new javax.swing.JLabel();
        privacyCb = new javax.swing.JComboBox<>();
        lastSeenLbl = new javax.swing.JLabel();
        lastSeenCb = new javax.swing.JComboBox<>();
        privacyLbl = new javax.swing.JLabel();
        editPasswordBtn = new javax.swing.JButton();
        deleteAccountBtn = new javax.swing.JButton();
        activityLbl = new javax.swing.JLabel();
        activityCb = new javax.swing.JComboBox<>();

        header.setFont(new java.awt.Font("Lucida Grande", Font.BOLD, 24));
        header.setText("Setting");
        header.setHorizontalAlignment(JLabel.CENTER);

        privacyCb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"public", "private"}));
        if (settingSwing.getPage().getAccount().isPagePublic()) privacyCb.setSelectedItem("public");
        else privacyCb.setSelectedItem("private");
        privacyCb.addActionListener(this::privacyActionPerformed);

        lastSeenLbl.setFont(new java.awt.Font("Lucida Grande", Font.PLAIN, 24));
        lastSeenLbl.setText("Last seen : ");

        lastSeenCb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"every one", "followings", "no one"}));
        if (settingSwing.getPage().getAccount().getLastSeenSituation().equals("every one"))
            lastSeenCb.setSelectedItem("every one");
        else if (settingSwing.getPage().getAccount().getLastSeenSituation().equals("followings"))
            lastSeenCb.setSelectedItem("followings");
        else
            lastSeenCb.setSelectedItem("no one");
        lastSeenCb.addActionListener(this::lastSeenActionPerformed);

        privacyLbl.setFont(new java.awt.Font("Lucida Grande", Font.PLAIN, 24));
        privacyLbl.setText("privacy : ");

        activityLbl.setFont(new java.awt.Font("Lucida Grande", Font.PLAIN, 24));
        activityLbl.setText("Activity : ");

        activityCb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Activate", "Deactivate"}));
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
        if (activityCb.getSelectedItem().equals("Activate")) {
            ((SettingPage) settingSwing.getPage()).setActivity(true);
            JOptionPane.showMessageDialog(null, "Your page activated");
        } else {
            ((SettingPage) settingSwing.getPage()).setActivity(false);
            JOptionPane.showMessageDialog(null, "Your page deactivated");
        }
    }

    private void lastSeenActionPerformed(ActionEvent e) {
        ((SettingPage) settingSwing.getPage()).setLastSeenPrivacy((String) lastSeenCb.getSelectedItem());
        JOptionPane.showMessageDialog(null, "Your last seen privacy set " + lastSeenCb.getSelectedItem());
    }

    private void privacyActionPerformed(ActionEvent e) {
        ((SettingPage) settingSwing.getPage()).setPagePrivacy(privacyCb.getSelectedItem().equals("public"));
        JOptionPane.showMessageDialog(null, "Your page privacy set " + privacyCb.getSelectedItem());
    }

    private void editPasswordActionPerformed(ActionEvent e) {
        new EditPassFrame(this);
    }

    private void deleteAccountActionPerformed(ActionEvent e) {
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete your account?",
                "Delete Account", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (result == 0) {
            ((SettingPage) settingSwing.getPage()).deleteAccount();
            settingSwing.getManager().getSwings().empty();
            settingSwing.dispose();
            settingSwing.getPage().getManager().goToLoginPage();
        }
    }

    private void showGraphic() {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(deleteAccountBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(lastSeenLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(activityLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(privacyLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(activityCb, 0, 450, Short.MAX_VALUE)
                                                        .addComponent(lastSeenCb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(privacyCb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addComponent(editPasswordBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(privacyCb, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(privacyLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lastSeenLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                        .addComponent(lastSeenCb))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(activityLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                        .addComponent(activityCb))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editPasswordBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(deleteAccountBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
    }

    public SettingSwing getSettingSwing() {
        return settingSwing;
    }
}

package graphic.pages.personalPage;

import graphic.SelectingPanel;
import logic.Account;
import logic.Manager;
import logic.pages.personal.PersonalPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class CreatingListFrame extends JFrame {
    private PersonalPageSwing personalPageSwing;
    private javax.swing.JButton createBtn;
    private javax.swing.JLabel header;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLbl;
    private SelectingPanel selectingPanel;

    public CreatingListFrame(PersonalPageSwing personalPageSwing) {
        this.personalPageSwing = personalPageSwing;
        showGraphic();
    }

    private void showGraphic() {
        LinkedList<String> followings = personalPageSwing.getPage().getAccount().getFollowingsUserName();
        createBtn = new javax.swing.JButton();
        nameField = new javax.swing.JTextField();
        selectingPanel = new SelectingPanel(followings, personalPageSwing);
        nameLbl = new javax.swing.JLabel();
        header = new javax.swing.JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        createBtn.setText("create");
        createBtn.addActionListener(this::createList);
        createBtn.setEnabled(false);

        selectingPanel.getSelectingSP().getMyJList().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                createBtn.setEnabled(!selectingPanel.getSelectedAccountsSet().isEmpty() && !nameField.getText().isBlank());
            }
        });

        nameLbl.setText("Name : ");

        nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                createBtn.setEnabled(!selectingPanel.getSelectedAccountsSet().isEmpty() && !nameField.getText().isBlank());
            }
        });

        header.setFont(new java.awt.Font("Lucida Grande", Font.BOLD, 18));
        header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        header.setText("Creating List");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(header)
                                                .addGap(190, 190, 190))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(nameLbl)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(nameField))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(selectingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(createBtn)))
                                                .addGap(0, 6, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(createBtn)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(header)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(nameLbl))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(selectingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        this.pack();
        this.setVisible(true);
    }

    private void createList(ActionEvent e) {
        ArrayList<Account> accounts = new ArrayList<>();
        Manager manager = personalPageSwing.getPage().getManager();
        for (String selectedValue : selectingPanel.getSelectedAccountsSet()) {
            accounts.add(manager.searchByUserName(selectedValue));
        }
        String msg = ((PersonalPage) personalPageSwing.getPage()).putListToFriendsList(nameField.getText(), accounts);
        JOptionPane.showMessageDialog(null, msg, "Creating list", JOptionPane.PLAIN_MESSAGE);
        dispose();
    }
}

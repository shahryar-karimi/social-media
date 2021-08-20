package view.pages.personalPage.info.view;

import model.Account;
import view.myPanels.SelectingPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ChangeListFrame extends JFrame {
    private final Account owner;
    private final Account visitor;
    private SelectingPanel selectingPanel;

    public ChangeListFrame(Account owner, Account visitor) {
        this.owner = owner;
        this.visitor = visitor;
        showGraphic();
    }

    private void showGraphic() {
        selectingPanel = new SelectingPanel(new ArrayList<>(visitor.getFriendsList().keySet()));
        JButton add = new JButton();
        JButton remove = new JButton();
        JLabel header = new JLabel();

        selectingPanel.getSelectedLbl().setText("Selected list : ");
        selectingPanel.getSelectingLbl().setText("Select list you want to add : ");

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        add.setText("Add");
        add.addActionListener(this::addBtnActionPerformed);

        remove.setText("Remove");
        remove.addActionListener(this::removeBtnActionPerformed);

        header.setText(String.format("Add/Remove '%s' to/from:", owner.getUserName()));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(remove)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(add))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(selectingPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(header))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(header)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(selectingPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(add)
                                        .addComponent(remove))
                                .addContainerGap())
        );

        this.pack();
        this.setVisible(true);
    }

    private void removeBtnActionPerformed(ActionEvent e) {
        HashMap<String, ArrayList<Account>> friendsList = visitor.getFriendsList();
        HashMap<String, ArrayList<String>> friendsListUserName = visitor.getFriendsListsUserName();
        HashSet<String> selectedList = selectingPanel.getSelectedAccountsSet();
        for (String listName : selectedList) {
            friendsList.get(listName).remove(owner);
            friendsListUserName.get(listName).remove(owner.getUserName());
        }
        for (String listName : selectedList) {
            if (friendsList.get(listName).isEmpty()){
                friendsList.remove(listName);
                friendsListUserName.remove(listName);
            }
        }
        dispose();
    }

    private void addBtnActionPerformed(ActionEvent e) {
        HashMap<String, ArrayList<Account>> friendsList = visitor.getFriendsList();
        HashMap<String, ArrayList<String>> friendsListUserName = visitor.getFriendsListsUserName();
        for (String listName : selectingPanel.getSelectedAccountsSet()) {
            friendsList.get(listName).add(owner);
            friendsListUserName.get(listName).add(owner.getUserName());
        }
        dispose();
    }
}

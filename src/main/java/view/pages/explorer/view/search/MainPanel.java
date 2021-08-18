package view.pages.explorer.view.search;

import model.Account;
import view.panels.MyScrollPane;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Locale;

public class MainPanel extends JPanel {
    private LinkedList<Account> accounts;
    private MyScrollPane<Account> myScrollPane;

    private JTextField searchField;
    private JButton cancel;

    public MainPanel(LinkedList<Account> accounts) {
        this.accounts = accounts;
        showGraphic();
    }

    private void searchFilter(String searchTerm) {
        if (searchTerm.isBlank()) {
            myScrollPane.setDefaultListModel(new DefaultListModel<>());
        } else {
            DefaultListModel<String> filteredItems = new DefaultListModel<>();
            accounts.forEach((account) -> {
                String userName = account.getUserName().toLowerCase(Locale.ROOT);
                if (userName.startsWith(searchTerm.toLowerCase(Locale.ROOT))) {
                    filteredItems.addElement(userName);
                }
            });
            myScrollPane.setDefaultListModel(filteredItems);
        }
        myScrollPane.setMyJList();
    }

    private void showGraphic() {
        myScrollPane = new MyScrollPane<>(accounts, true) {
            @Override
            public void listClicked(MouseEvent e) {
            }
        };
        searchField = new JTextField();
        cancel = new JButton();

        setPreferredSize(new java.awt.Dimension(548, 380));

        searchField.setText("");

        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                searchFilter(searchField.getText());
            }
        });

        cancel.setText("cancel");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(searchField, GroupLayout.Alignment.TRAILING)
                                        .addComponent(myScrollPane, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(cancel)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(searchField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(myScrollPane, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cancel)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    public void updateGraphic() {
        searchFilter(searchField.getText());
    }

    public void refresh() {
        searchField.setText("");
        updateGraphic();
    }

    public JButton getCancel() {
        return cancel;
    }

    public MyScrollPane<Account> getMyScrollPane() {
        return myScrollPane;
    }

    public LinkedList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(LinkedList<Account> accounts) {
        this.accounts = accounts;
    }
}

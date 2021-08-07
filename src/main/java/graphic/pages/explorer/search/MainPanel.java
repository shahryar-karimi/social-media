package graphic.pages.explorer.search;

import logic.Account;
import logic.pages.ExplorerPage;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Locale;

public class MainPanel extends JPanel {
    private JList<String> myJList;
    private LinkedList<Account> accounts;
    private DefaultListModel<String> defaultListModel;
    private final ExplorerPage explorerPage;

    private JTextField searchField;
    private JScrollPane jScrollPane1;
    private JButton cancel;

    public MainPanel(ExplorerPage explorerPage, LinkedList<Account> accounts) {
        this.explorerPage = explorerPage;
        this.accounts = accounts;
        this.myJList = new JList<>();
        this.defaultListModel = new DefaultListModel<>();
//        bindData();
        showGraphic();
    }

//    private void bindData() {
//        for (Account account : accounts) {
//            defaultListModel.addElement(account.toString());
//        }
//        myJList.setModel(defaultListModel);
//        myJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//    }

    private void searchFilter(String searchTerm) {
        if (searchTerm.isBlank()) {
            defaultListModel = new DefaultListModel<>();
        } else {
            DefaultListModel<String> filteredItems = new DefaultListModel<>();
            accounts.forEach((account) -> {
                String userName = account.getUserName().toLowerCase(Locale.ROOT);
                if (userName.startsWith(searchTerm.toLowerCase(Locale.ROOT))) {
                    filteredItems.addElement(userName);
                }
            });
            defaultListModel = filteredItems;
        }
        myJList.setModel(defaultListModel);
        myJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void showGraphic() {
        searchField = new JTextField();
        jScrollPane1 = new JScrollPane();
        cancel = new JButton();

        setPreferredSize(new java.awt.Dimension(548, 380));

        searchField.setText("");

        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                searchFilter(searchField.getText());
            }
        });

        jScrollPane1.setViewportView(myJList);

        cancel.setText("cancel");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(searchField, GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
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
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE)
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

    public JList<String> getMyJList() {
        return myJList;
    }

    public LinkedList<Account> getAccounts() {
        return accounts;
    }
}

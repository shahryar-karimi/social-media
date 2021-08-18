package view.pages.accountsListSwing.view;

import model.Account;
import utility.AppProperties;
import view.pages.Swing;
import view.pages.accountsListSwing.event.ClickEvent;
import view.pages.accountsListSwing.listener.ClickListener;
import view.panels.MyScrollPane;
import view.panels.footerPanel.controller.FooterPanelController;
import view.panels.footerPanel.listener.FooterPanelListener;
import view.panels.footerPanel.view.FooterPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Locale;

public class AccountsListSwing extends Swing {
    private final List<Account> accounts;
    private JTextField searchTxt;
    private MyScrollPane<Account> myScrollPane;

    public AccountsListSwing(List<Account> accounts, ClickListener listener) {
        super();
        this.listener = listener;
        this.accounts = accounts;
        footerPanel = new FooterPanel(new FooterPanelListener(new FooterPanelController(getPage())));
        addSwing(this);
        run();
    }

    private void searchFilter(String searchTerm) {
        DefaultListModel<String> filteredItems = new DefaultListModel<>();
        accounts.forEach((account) -> {
            String star = account.toString();
            String starName = star.toLowerCase(Locale.ROOT);
            if (starName.startsWith(searchTerm.toLowerCase())) {
                filteredItems.addElement(star);
            }
        });
        myScrollPane.setDefaultListModel(filteredItems);
        myScrollPane.setMyJList();
    }

    @Override
    public void run() {
        this.setVisible(true);
        showGraphic();
    }

    @Override
    public void showGraphic() {
        myScrollPane = new MyScrollPane<>(accounts, true) {
            @Override
            public void listClicked(MouseEvent e) {
                myJListMouseClicked();
            }
        };

        Panel panel1 = new Panel();
        JLabel searchLabel = new JLabel();
        searchTxt = new JTextField();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        searchLabel.setText(AppProperties.getInstance().getProperty("Search"));

        searchTxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                searchTxtKeyReleased();
            }
        });

        GroupLayout panel1Layout = new GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(myScrollPane)
                                        .addGroup(panel1Layout.createSequentialGroup()
                                                .addComponent(searchLabel, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                                                .addGap(30, 30, 30)
                                                .addComponent(searchTxt, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(111, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(searchLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(searchTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addComponent(myScrollPane, GroupLayout.PREFERRED_SIZE, 465, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(73, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(footerPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(143, Short.MAX_VALUE)
                                .addComponent(footerPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        this.pack();
    }

    @Override
    public void updateGraphic() {
        searchTxtKeyReleased();
    }

    private void myJListMouseClicked() {
        getManager().getSwings().pop().dispose();
        ClickEvent event = new ClickEvent(this, myScrollPane.getMyJList().getSelectedValue(), accounts);
        listener.eventOccurred(event);
    }

    private void searchTxtKeyReleased() {
        searchFilter(searchTxt.getText());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

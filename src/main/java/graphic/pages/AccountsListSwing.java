package graphic.pages;

import graphic.FooterPanel;
import graphic.pages.personalPage.InfoPageSwing;
import logic.Account;
import logic.pages.Page;
import utility.AppProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;

public class AccountsListSwing extends Swing {

    private JList<String> myJList;
    private JTextField searchTxt;
    private DefaultListModel<String> defaultListModel = new DefaultListModel<>();
    private LinkedList<Account> accounts;

    public AccountsListSwing(Page page, LinkedList<Account> accounts) {
        super();
        this.page = page;
        this.accounts = accounts;
        footerPanel = new FooterPanel(getManager(), page.getManager(), page.getAccount());
        addSwing(this);
        run();
        this.bindData();
    }

    private ArrayList<String> getStars(LinkedList<Account> accounts) {
        ArrayList<String> stars = new ArrayList<>();
        for (Account account : accounts) {
            if (account.isActive()) {
                stars.add(account.toString());
            }
        }
        return stars;
    }

    private void bindData() {
        for (String star : getStars(accounts)) {
            defaultListModel.addElement(star);
        }
        myJList.setModel(defaultListModel);
        myJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void searchFilter(String searchTerm) {
        DefaultListModel<String> filteredItems = new DefaultListModel<>();
        ArrayList<String> stars = getStars(accounts);

        stars.forEach((star) -> {
            String starName = stars.toString().toLowerCase(Locale.ROOT);
            if (starName.contains(searchTerm.toLowerCase())) {
                filteredItems.addElement(star);
            }
        });

        defaultListModel = filteredItems;
        myJList.setModel(defaultListModel);
    }

    @Override
    public void run() {
        this.setVisible(true);
        showGraphic();
    }

    @Override
    public void showGraphic() {
        JMenuItem jMenuItem1 = new JMenuItem();
        Panel panel1 = new Panel();
        JLabel searchLabel = new JLabel();
        searchTxt = new JTextField();
        JScrollPane jScrollPane1 = new JScrollPane();
        myJList = new JList<>();

        jMenuItem1.setText("jMenuItem1");

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        searchLabel.setText(AppProperties.getInstance().getProperty("Search"));

        searchTxt.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                searchTxtKeyReleased(evt);
            }
        });

        myJList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                myJListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(myJList);

        GroupLayout panel1Layout = new GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane1)
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
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 465, GroupLayout.PREFERRED_SIZE)
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

    private void myJListMouseClicked(MouseEvent evt) {
        this.dispose();
        Account account = page.getManager().searchByUserName(myJList.getSelectedValue());
        new InfoPageSwing(myJList.getSelectedValuesList(), account.getPersonalPage().getInfo());
        // JOptionPane.showMessageDialog(rootPane, myJList.getSelectedValuesList(), "selected stars", JOptionPane.INFORMATION_MESSAGE);
    }

    private void searchTxtKeyReleased(KeyEvent evt) {
        searchFilter(searchTxt.getText());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

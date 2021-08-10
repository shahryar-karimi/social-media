package graphic.pages.explorer;

import graphic.FooterPanel;
import graphic.pages.Swing;
import graphic.pages.explorer.search.MainPanel;
import logic.Account;
import logic.pages.ExplorerPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ExplorerSwing extends Swing {
    private ExplorerPanel explorerPanel;
    private ExplorerPage explorerPage;

    public ExplorerSwing(ExplorerPage explorerPage) {
        super();
        this.explorerPage = explorerPage;
        this.page = explorerPage;
        footerPanel = new FooterPanel(getManager(), page.getManager(), page.getAccount());
        addSwing(this);
        run();
    }

    @Override
    public void run() {
        myLogger.debug(ExplorerSwing.class.getName(), "run",
                "explorer run for account \"" + page.getAccount().toString() + "\"");
        showGraphic();
    }

    @Override
    public void showGraphic() {
        explorerPanel = new ExplorerPanel(this);
        this.setLayout(new BorderLayout());
        initPanels();
        this.setVisible(true);
    }

    private void initPanels() {
        MainPanel mainPanel = explorerPanel.getSearchPanel().getMainPanel();
        JList<String> myJList = mainPanel.getMyScrollPane().getMyJList();
        myJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Account account = null;
                String userName = myJList.getSelectedValue();
                for (Account account1 : mainPanel.getAccounts())
                    if (account1.getUserName().equals(userName))
                        account = account1;
                if (account == null) {
                    JOptionPane.showMessageDialog(null, "account not found", "Search", JOptionPane.WARNING_MESSAGE);
                } else {
                    explorerPage.getManager().goToInfoPage(account.getPersonalPage().getInfo(), explorerPage.getAccount());
                    mainPanel.refresh();
                }
                dispose();
            }
        });
        this.add(explorerPanel, BorderLayout.CENTER);
        this.add(footerPanel, BorderLayout.SOUTH);
    }

    @Override
    public void updateGraphic() {
        explorerPanel.updateGraphic();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

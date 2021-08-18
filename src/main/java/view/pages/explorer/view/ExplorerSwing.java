package view.pages.explorer.view;

import view.pages.Swing;
import view.pages.accountsListSwing.event.ClickEvent;
import view.pages.explorer.listener.ExplorerListener;
import view.pages.explorer.view.search.MainPanel;
import view.panels.footerPanel.controller.FooterPanelController;
import view.panels.footerPanel.listener.FooterPanelListener;
import view.panels.footerPanel.view.FooterPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ExplorerSwing extends Swing {
    private final ExplorerPanel explorerPanel;

    public ExplorerSwing(ExplorerListener listener) {
        super();
        this.listener = listener;
        footerPanel = new FooterPanel(new FooterPanelListener(new FooterPanelController(getPage())));
        explorerPanel = new ExplorerPanel(this);
        this.setLayout(new BorderLayout());
        initPanels();
        addSwing(this);
        run();
    }

    @Override
    public void run() {
        myLogger.debug(ExplorerSwing.class.getName(), "run",
                "explorer run for account \"" + getPage().getAccount().toString() + "\"");
        showGraphic();
    }

    @Override
    public void showGraphic() {
        this.setVisible(true);
    }

    private void initPanels() {
        MainPanel mainPanel = explorerPanel.getSearchPanel().getMainPanel();
        JList<String> myJList = mainPanel.getMyScrollPane().getMyJList();
        myJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ClickEvent event = new ClickEvent(this, myJList.getSelectedValue(), mainPanel.getAccounts());
                String msg = listener.eventOccurred(event);
                if (msg != null) {
                    JOptionPane.showMessageDialog(null, msg);
                }
                mainPanel.refresh();
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

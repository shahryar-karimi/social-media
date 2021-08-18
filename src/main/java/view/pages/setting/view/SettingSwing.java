package view.pages.setting.view;

import view.pages.Swing;
import view.pages.setting.listener.SettingListener;
import view.panels.footerPanel.controller.FooterPanelController;
import view.panels.footerPanel.listener.FooterPanelListener;
import view.panels.footerPanel.view.FooterPanel;

import java.awt.*;
import java.awt.event.ActionEvent;

public class SettingSwing extends Swing {
    private final MainPanel mainPanel;

    public SettingSwing(SettingListener listener) {
        super();
        this.listener = listener;
        mainPanel = new MainPanel(this);
        footerPanel = new FooterPanel(new FooterPanelListener(new FooterPanelController(getPage())));
        addSwing(this);
        run();
    }

    @Override
    public void run() {
        myLogger.debug(SettingSwing.class.getName(), "run",
                "setting run for account \"" + getPage().getAccount().toString() + "\"");
        showGraphic();
    }

    @Override
    public void showGraphic() {
        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(footerPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    @Override
    public void updateGraphic() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

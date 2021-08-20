package view.pages.messages.messenger.view;

import view.pages.Swing;
import view.pages.messages.messenger.listener.MessengersListener;
import view.myPanels.footerPanel.controller.FooterPanelController;
import view.myPanels.footerPanel.listener.FooterPanelListener;
import view.myPanels.footerPanel.view.FooterPanel;

import java.awt.*;
import java.awt.event.ActionEvent;

public class MessengerSwing extends Swing {
    private final MainPanel mainPanel;

    public MessengerSwing(MessengersListener listener) {
        super();
        this.listener = listener;
        footerPanel = new FooterPanel(new FooterPanelListener(new FooterPanelController(getListener().getController().getPage())));
        mainPanel = new MainPanel(this);
        addSwing(this);
        run();
    }

    @Override
    public void run() {
        myLogger.debug(MessengerSwing.class.getName(), "run",
                "messages page run for account \"" + getListener().getController().getPage().getAccount().toString() + "\"");
        showGraphic();
    }

    @Override
    public void showGraphic() {
        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(footerPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void updateGraphic() {
        mainPanel.updateGraphic();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

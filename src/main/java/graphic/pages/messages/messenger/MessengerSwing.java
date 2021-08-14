package graphic.pages.messages.messenger;

import graphic.FooterPanel;
import graphic.pages.Swing;
import logic.pages.messenger.MessengersPage;

import java.awt.*;
import java.awt.event.ActionEvent;

public class MessengerSwing extends Swing {
    private MainPanel mainPanel;

    public MessengerSwing(MessengersPage messengersPage) {
        super();
        this.page = messengersPage;
        footerPanel = new FooterPanel(getManager(), page.getManager(), page.getAccount());
        addSwing(this);
        run();
    }

    @Override
    public void run() {
        myLogger.debug(MessengerSwing.class.getName(), "run",
                "messages page run for account \"" + page.getAccount().toString() + "\"");
        showGraphic();
    }

    @Override
    public void showGraphic() {
        mainPanel = new MainPanel(this);
        this.setLayout(new BorderLayout());
        addAction();
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(footerPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    private void addAction() {

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

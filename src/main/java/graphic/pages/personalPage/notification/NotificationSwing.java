package graphic.pages.personalPage.notification;

import graphic.FooterPanel;
import graphic.pages.Swing;
import graphic.pages.personalPage.PersonalPageSwing;
import graphic.pages.personalPage.notification.request.RequestsPanel;
import graphic.pages.personalPage.notification.systemMessage.SystemMessagePanel;
import logic.pages.personal.Notification;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class NotificationSwing extends Swing {
    private final RequestsPanel requestsPanel;
    private final SystemMessagePanel systemMessagePanel;

    public NotificationSwing(Notification notification) {
        super();
        this.page = notification;
        this.requestsPanel = new RequestsPanel(notification);
        this.systemMessagePanel = new SystemMessagePanel(notification);
        footerPanel = new FooterPanel(getManager(), page.getManager(), page.getAccount());
        addSwing(this);
        run();
    }

    @Override
    public void run() {
        this.setVisible(true);
        myLogger.debug(PersonalPageSwing.class.getName(), "run",
                "Notifications window run for account \"" + this.page.getAccount().toString() + "\"");
        showGraphic();
    }

    @Override
    public void showGraphic() {
        //TODO
        JPanel centerPanel = new JPanel();
        centerPanel.setPreferredSize(new Dimension(600, 700));
        centerPanel.setLayout(null);
        centerPanel.add(requestsPanel);
        centerPanel.add(systemMessagePanel);
        this.setLayout(new BorderLayout());
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(footerPanel, BorderLayout.SOUTH);
    }

    @Override
    public void updateGraphic() {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

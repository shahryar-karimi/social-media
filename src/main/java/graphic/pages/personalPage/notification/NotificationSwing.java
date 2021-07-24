package graphic.pages.personalPage.notification;

import graphic.FooterPanel;
import graphic.pages.Swing;
import graphic.pages.personalPage.PersonalPageSwing;
import graphic.pages.personalPage.notification.request.RequestsPanel;
import graphic.pages.personalPage.notification.systemMessage.SystemMessagePanel;
import logic.pages.personal.Notification;

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
        this.add(requestsPanel);
        this.add(systemMessagePanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

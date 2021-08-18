package view.pages.personalPage.notification.view;

import model.Account;
import model.pages.personal.Notification;
import view.pages.Swing;
import view.pages.personalPage.PersonalPageSwing;
import view.pages.personalPage.notification.event.RequestEvent;
import view.pages.personalPage.notification.listener.NotificationListener;
import view.pages.personalPage.notification.view.request.RequestsPanel;
import view.pages.personalPage.notification.view.request.SingleRequest;
import view.pages.personalPage.notification.view.systemMessage.SystemMessagePanel;
import view.panels.footerPanel.controller.FooterPanelController;
import view.panels.footerPanel.listener.FooterPanelListener;
import view.panels.footerPanel.view.FooterPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class NotificationSwing extends Swing {
    private final RequestsPanel requestsPanel;
    private final SystemMessagePanel systemMessagePanel;

    public NotificationSwing(NotificationListener listener) {
        super();
        this.listener = listener;
        this.requestsPanel = new RequestsPanel(((Notification) listener.getController().getPage()).getRequests());
        this.systemMessagePanel = new SystemMessagePanel((Notification) listener.getController().getPage());
        footerPanel = new FooterPanel(new FooterPanelListener(new FooterPanelController(getPage())));
        for (SingleRequest request : requestsPanel.getRequests()) {
            request.getAccept().addActionListener(this);
            request.getDecline().addActionListener(this);
            request.getMuteDecline().addActionListener(this);
        }
        addSwing(this);
        run();
    }

    @Override
    public void run() {
        this.setVisible(true);
        myLogger.debug(PersonalPageSwing.class.getName(), "run",
                "Notifications window run for account \"" + this.listener.getController().getPage().getAccount().toString() + "\"");
        showGraphic();
    }

    @Override
    public void showGraphic() {
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
        requestsPanel.setScrollPanel();
        systemMessagePanel.setText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (SingleRequest request : requestsPanel.getRequests()) {
            Account account = listener.getController().getPage().getManager().searchByUserName(request.getUserName());
            if (e.getSource() == request.getAccept()) {
                RequestEvent event = new RequestEvent(this, account, "accept");
                listener.eventOccurred(event);
                requestsPanel.getRequests().remove(request);
                updateGraphic();
                return;
            } else if (e.getSource() == request.getDecline()) {
                RequestEvent event = new RequestEvent(this, account, false, "decline");
                listener.eventOccurred(event);
                requestsPanel.getRequests().remove(request);
                updateGraphic();
                return;
            } else if (e.getSource() == request.getMuteDecline()) {
                RequestEvent event = new RequestEvent(this, account, true, "decline");
                listener.eventOccurred(event);
                requestsPanel.getRequests().remove(request);
                updateGraphic();
                return;
            }
        }
    }
}

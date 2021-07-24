package graphic.pages.personalPage.notification.request;

import logic.Manager;
import logic.pages.personal.Notification;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RequestsPanel extends JPanel implements ActionListener {
    private final JScrollPane scrollPane;
    private final JPanel scrollPanel;
    private final ArrayList<SingleRequest> requests;
    private final Notification notification;

    public RequestsPanel(Notification notification) {
        this.notification = notification;
        scrollPanel = new JPanel();
        requests = new ArrayList<>();
        scrollPane = new JScrollPane();
        setRequests();
        setScrollPanel();
        this.setLayout(null);
        this.setBounds(5, 5, 290, 790);
        this.add(scrollPane);
    }

    private void setScrollPanel() {
        scrollPane.setBounds(0, 0, 290, 790);
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));
        scrollPanel.setSize(290, 790);
        for (SingleRequest request : requests)
            scrollPanel.add(request);
        scrollPane.setViewportView(scrollPanel);
    }

    private void setRequests() {
        ArrayList<String> userNamesRequests = notification.getRequests();
        for (String userName : userNamesRequests) {
            SingleRequest request = new SingleRequest(notification, userName);
            request.getAccept().addActionListener(this);
            request.getDecline().addActionListener(this);
            request.getMuteDecline().addActionListener(this);
            requests.add(request);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (SingleRequest request : requests) {
            if (e.getSource() == request.getAccept()) {
                notification.acceptRequest(notification.getManager().searchByUserName(request.getUserName()));
                request.getAccept().setEnabled(false);
                request.getDecline().setEnabled(false);
                request.getMuteDecline().setEnabled(false);
                requests.remove(request);
                return;
            } else if (e.getSource() == request.getDecline()) {
                notification.declineRequest(true, notification.getManager().searchByUserName(request.getUserName()));
                request.getAccept().setEnabled(false);
                request.getDecline().setEnabled(false);
                request.getMuteDecline().setEnabled(false);
                requests.remove(request);
                return;
            } else if (e.getSource() == request.getMuteDecline()) {
                notification.declineRequest(false, notification.getManager().searchByUserName(request.getUserName()));
                request.getAccept().setEnabled(false);
                request.getDecline().setEnabled(false);
                request.getMuteDecline().setEnabled(false);
                requests.remove(request);
                return;
            }
        }
    }
}

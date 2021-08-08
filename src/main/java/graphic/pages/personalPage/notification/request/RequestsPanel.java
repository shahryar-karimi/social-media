package graphic.pages.personalPage.notification.request;

import logic.pages.personal.Notification;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RequestsPanel extends JPanel implements ActionListener {
    private static final int REQUEST_WIDTH = 300;
    private static final int REQUEST_HEIGHT = 50;
    private final JScrollPane scrollPane;
    private final JPanel scrollPanel;
    private final ArrayList<SingleRequest> requests;
    private final Notification notification;

    public RequestsPanel(Notification notification) {
        this.notification = notification;
        this.scrollPanel = new JPanel();
        this.requests = new ArrayList<>();
        this.scrollPane = new JScrollPane(scrollPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setRequests();
        setScrollPanel();
        this.setLayout(null);
        this.setBounds(0, 0, 300, 700);
        JLabel label = new JLabel("Requests");
        label.setFont(new java.awt.Font("Lucida Grande", Font.BOLD, 18));
        label.setBounds(0, 0, 300, 70);
        this.add(scrollPane);
        this.add(label);
    }

    private void setScrollPanel() {
        scrollPane.setBounds(0, 70, 300, 630);
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.PAGE_AXIS));
//        scrollPanel.setSize(300, 630);
        for (SingleRequest request : requests)
            scrollPanel.add(request);
    }

    private void setRequests() {
        ArrayList<String> userNamesRequests = notification.getRequests();
        for (String userName : userNamesRequests) {
            SingleRequest request = new SingleRequest(notification, userName);
            request.getAccept().addActionListener(this);
            request.getDecline().addActionListener(this);
            request.getMuteDecline().addActionListener(this);
            request.setPreferredSize(new Dimension(REQUEST_WIDTH, REQUEST_HEIGHT));
            requests.add(request);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (SingleRequest request : requests) {
            if (e.getSource() == request.getAccept() ||
                    e.getSource() == request.getDecline() ||
                    e.getSource() == request.getMuteDecline()) {
                request.getAccept().setEnabled(false);
                request.getDecline().setEnabled(false);
                request.getMuteDecline().setEnabled(false);
                requests.remove(request);
                return;
            }
        }
    }
}

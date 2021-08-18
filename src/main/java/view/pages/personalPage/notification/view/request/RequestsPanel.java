package view.pages.personalPage.notification.view.request;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RequestsPanel extends JPanel {
    private final JScrollPane scrollPane;
    private final ArrayList<SingleRequest> requests;
    private JPanel scrollPanel;

    public RequestsPanel(ArrayList<String> userNamesRequests) {
        this.requests = new ArrayList<>();
        this.scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        for (String userName : userNamesRequests) {
            SingleRequest request = new SingleRequest(userName);
            requests.add(request);
        }
        setScrollPanel();
        this.setLayout(null);
        this.setBounds(0, 0, 300, 700);
        JLabel label = new JLabel("Requests");
        label.setFont(new java.awt.Font("Lucida Grande", Font.BOLD, 18));
        label.setBounds(0, 0, 300, 70);
        this.add(scrollPane);
        this.add(label);
    }

    public void setScrollPanel() {
        scrollPane.setBounds(0, 70, 300, 630);
        scrollPanel = new JPanel();
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.PAGE_AXIS));
        scrollPanel.setSize(SingleRequest.REQUEST_WIDTH, SingleRequest.REQUEST_HEIGHT * requests.size());
        for (SingleRequest request : requests)
            scrollPanel.add(request);
        scrollPane.setViewportView(scrollPanel);
    }

    public ArrayList<SingleRequest> getRequests() {
        return requests;
    }
}

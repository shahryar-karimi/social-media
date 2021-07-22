package graphic.pages.personalPage.notification.request;

import logic.pages.personal.Notification;

import javax.swing.*;
import java.awt.*;

public class SingleRequest extends JPanel {
    private final String userName;
    private final JButton accept = new JButton("Accept");
    private final JButton decline = new JButton("Decline");
    private final JButton muteDecline = new JButton("Mute Decline");
    private final Notification notification;

    public SingleRequest(Notification notification, String userName) {
        this.notification = notification;
        this.userName = userName;
        this.setLayout(null);
        this.setSize(300, 50);
        JLabel label = new JLabel(userName);
        setDetailsBounds(label);
        color();
        addDetails(label);
    }

    private void addDetails(JLabel label) {
        this.add(label);
        this.add(accept);
        this.add(decline);
        this.add(muteDecline);
    }

    private void color() {
        accept.setBackground(Color.GREEN);
        decline.setBackground(Color.RED);
        muteDecline.setBackground(Color.LIGHT_GRAY);
        accept.setForeground(Color.WHITE);
        decline.setForeground(Color.WHITE);
        muteDecline.setForeground(Color.WHITE);
    }

    private void setDetailsBounds(JLabel label) {
        label.setBounds(10, 10, 100, 30);
        accept.setBounds(120, 10, 50, 30);
        decline.setBounds(180, 10, 50, 30);
        muteDecline.setBounds(240, 10, 50, 30);
    }

    public JButton getAccept() {
        return accept;
    }

    public JButton getDecline() {
        return decline;
    }

    public JButton getMuteDecline() {
        return muteDecline;
    }

    public String getUserName() {
        return userName;
    }
}

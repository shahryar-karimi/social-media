package graphic.pages.personalPage.notification.request;

import logic.Account;
import logic.pages.personal.Notification;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SingleRequest extends JPanel {
    private final String userName;
    private final JButton accept = new JButton("Accept");
    private final JButton decline = new JButton("Decline");
    private final JButton muteDecline = new JButton("Mute Decline");
    private final Notification notification;
    private final Account account;

    public SingleRequest(Notification notification, String userName) {
        this.notification = notification;
        this.userName = userName;
        this.account = notification.getManager().searchByUserName(userName);
        this.setLayout(null);
        JLabel label = new JLabel(userName);
        setDetailsBounds(label);
        color();
        addDetails(label);
        this.setBorder(new LineBorder(Color.BLACK, 1));
    }

    private void addDetails(JLabel label) {
        this.add(label);
        accept.addActionListener(e -> notification.acceptRequest(account));
        decline.addActionListener(e -> notification.declineRequest(true, account));
        muteDecline.addActionListener(e -> notification.declineRequest(false, account));
        this.add(accept);
        this.add(decline);
        this.add(muteDecline);
    }

    private void color() {
        accept.setText("Accept");
        decline.setText("Decline");
        muteDecline.setText("Mute decline");
        accept.setFont(new Font("lucia", Font.PLAIN, 10));
        decline.setFont(new Font("lucia", Font.PLAIN, 10));
        muteDecline.setFont(new Font("lucia", Font.PLAIN, 10));
    }

    private void setDetailsBounds(JLabel label) {
        label.setBounds(10, 5, 280, 10);
        accept.setBounds(10, 15, 90, 30);
        decline.setBounds(100, 15, 90, 30);
        muteDecline.setBounds(190, 15, 90, 30);
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

package view.pages.personalPage.notification.view.request;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SingleRequest extends JPanel {
    public static final int REQUEST_WIDTH = 300;
    public static final int REQUEST_HEIGHT = 50;
    private final String userName;
    private final JButton accept = new JButton("Accept");
    private final JButton decline = new JButton("Decline");
    private final JButton muteDecline = new JButton("Mute Decline");

    public SingleRequest(String userName) {
        this.userName = userName;
        this.setLayout(null);
        JLabel label = new JLabel(userName);
        setDetailsBounds(label);
        color();
        addDetails(label);
        this.setBorder(new LineBorder(Color.BLACK, 1));
        this.setSize(new Dimension(300, 50));
        this.setPreferredSize(new Dimension(REQUEST_WIDTH, REQUEST_HEIGHT));
    }

    private void addDetails(JLabel label) {
        this.add(label);
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

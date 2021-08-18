package view.pages.personalPage.notification.view.systemMessage;

import model.pages.personal.Notification;

import javax.swing.*;
import java.awt.*;

public class SystemMessagePanel extends JPanel {
    private final Notification notification;
    private final JScrollPane scrollPane;
    private final JTextArea textArea;

    public SystemMessagePanel(Notification notification) {
        this.notification = notification;
        this.setLayout(null);
        this.setBounds(300, 0, 300, 700);
        this.scrollPane = new JScrollPane();
        textArea = new JTextArea();
        textArea.setEditable(false);
        JLabel label = new JLabel("System Messages");
        label.setFont(new java.awt.Font("Lucida Grande", Font.BOLD, 18));
        label.setBounds(0, 0, 300, 70);
        setText();
        scrollPane.setViewportView(textArea);
        scrollPane.setBounds(0, 70, 300, 630);
        this.add(scrollPane);
        this.add(label);
    }

    public void setText() {
        String messages = "";
        for (String systemMessage : notification.getSystemMessages()) {
            messages += systemMessage + "\n--------------------------------------------------\n";
        }
        textArea.setText(messages);
    }
}

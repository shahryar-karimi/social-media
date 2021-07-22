package graphic.pages.personalPage.notification.systemMessage;

import logic.pages.personal.Notification;

import javax.swing.*;

public class SystemMessagePanel extends JPanel {
    private final Notification notification;
    private final JScrollPane scrollPane;
    private JTextArea textArea;

    public SystemMessagePanel(Notification notification) {
        this.notification = notification;
        this.setLayout(null);
        this.setBounds(305, 5, 290, 790);
        this.scrollPane = new JScrollPane();
        setText();
        scrollPane.setViewportView(textArea);
        this.add(scrollPane);
    }

    private void setText() {
        String messages = "";
        for (String systemMessage : notification.getSystemMessages()) {
            messages += systemMessage + "\n--------------------------------------------------\n";
        }
        textArea = new JTextArea(messages);
        textArea.setEditable(false);
    }
}

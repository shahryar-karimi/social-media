package graphic.pages.messages;

import logic.pages.messenger.Message;
import utility.ImageLoader;

import javax.swing.*;
import java.awt.*;

public class MessagePanel extends JPanel {
    private Message message;

    public MessagePanel(Message message) {
        this.message = message;
        showGraphic();
    }

    public MessagePanel(Message message, boolean isMine) {
        this(message);
        setColor(isMine);
    }

    private void showGraphic() {
        JLabel time = new JLabel();
        JLabel checkMark = new JLabel();
        JScrollPane jScrollPane1 = new JScrollPane();
        JTextArea text = new JTextArea();

        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        time.setText(message.getTime());

        if (message.isSeen()) {
            checkMark.setIcon(ImageLoader.getOthers().get("seen"));
        } else {
            checkMark.setIcon(ImageLoader.getOthers().get("unSeen"));
        }

        text.setColumns(20);
        text.setRows(1);
        text.setText(message.getText());
        text.setEditable(false);
        jScrollPane1.setViewportView(text);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(time, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(checkMark)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(checkMark, GroupLayout.Alignment.TRAILING)
                                        .addComponent(time, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
    }

    public Message getMessage() {
        return message;
    }

    public void setColor(boolean isMine) {
        if (isMine) super.setBackground(new Color(125, 0, 125));
        else super.setBackground(new Color(0, 125, 0));
    }
}

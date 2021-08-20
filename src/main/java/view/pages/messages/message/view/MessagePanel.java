package view.pages.messages.message.view;

import model.pages.messenger.Message;
import utility.ImageLoader;

import javax.swing.*;
import java.awt.*;

public class MessagePanel extends JPanel {
    public static final int HEIGHT = 110;
    private final Message message;
    private JButton forwardBtn;
    private JLabel ownerLbl;

    public MessagePanel(Message message) {
        this.message = message;
        forwardBtn = new JButton("forward");
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
        ownerLbl = new JLabel(message.getOwnerUserName());

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(ownerLbl)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 193, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(checkMark))
                                                        .addComponent(forwardBtn, javax.swing.GroupLayout.Alignment.TRAILING))))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(ownerLbl)
                                        .addComponent(forwardBtn))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(checkMark, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(time, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
    }

    public Message getMessage() {
        return message;
    }

    public JButton getForwardBtn() {
        return forwardBtn;
    }

    public JLabel getOwnerLbl() {
        return ownerLbl;
    }

    public void setColor(boolean isMine) {
        if (isMine) super.setBackground(new Color(125, 0, 125));
        else super.setBackground(new Color(0, 125, 0));
    }
}

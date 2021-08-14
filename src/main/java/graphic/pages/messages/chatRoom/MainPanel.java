package graphic.pages.messages.chatRoom;

import logic.pages.messenger.ChatRoom;
import logic.pages.messenger.Message;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private final ChatRoom chatRoom;
    private final ChatRoomSwing chatRoomSwing;

    private NorthPanel northPanel;
    private CenterPanel centerPanel;
    private SouthPanel southPanel;

    public MainPanel(ChatRoomSwing chatRoomSwing) {
        this.chatRoomSwing = chatRoomSwing;
        this.chatRoom = (ChatRoom) chatRoomSwing.getPage();
        showGraphic();
    }

    public void showGraphic() {
        northPanel = new NorthPanel(chatRoom.getAccount(), chatRoom.getListener());
        centerPanel = new CenterPanel(chatRoomSwing);
        JScrollPane scrollPane = new JScrollPane(centerPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        southPanel = new SouthPanel();

        addAction();

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(scrollPane)
                                        .addComponent(southPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(northPanel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(northPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(southPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
    }

    public void addAction() {
        southPanel.getSendBtn().addActionListener(e -> {
            String text = southPanel.getTextArea().getText();
            Message message = chatRoom.writeNewMessage(text);
            chatRoom.sendMessage(message);
            southPanel.refresh();
            centerPanel.sendMessage();
        });
    }

    public void updateGraphic() {
        northPanel.updateGraphic();
        southPanel.updateGraphic();
    }
}

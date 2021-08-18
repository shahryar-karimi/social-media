package view.pages.messages.chatRoom.view;

import model.Account;
import model.pages.messenger.ChatRoom;
import view.pages.messages.chatRoom.events.SendMessageEvent;

import javax.swing.*;

public class MainPanel extends JPanel {
    private final ChatRoomSwing chatRoomSwing;

    private NorthPanel northPanel;
    private CenterPanel centerPanel;
    private SouthPanel southPanel;

    public MainPanel(ChatRoomSwing chatRoomSwing) {
        this.chatRoomSwing = chatRoomSwing;
        showGraphic();
    }

    public void showGraphic() {
        northPanel = new NorthPanel(chatRoomSwing.getPage().getAccount(),
                ((ChatRoom) chatRoomSwing.getPage()).getListener());
        centerPanel = new CenterPanel(chatRoomSwing);
        JScrollPane scrollPane = new JScrollPane(centerPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        Account listener = ((ChatRoom) chatRoomSwing.getPage()).getListener();
        Account account = chatRoomSwing.getPage().getAccount();
        boolean isBlocked = listener.hasBlocked(account);
        boolean isFollow = listener.isFollow(account) || account.isFollow(listener);
        boolean isMe = listener.getUserName().equals(account.getUserName());
        southPanel = new SouthPanel(isMe || (!isBlocked && isFollow));

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
            SendMessageEvent event = new SendMessageEvent(this, text);
            chatRoomSwing.getListener().eventOccurred(event);
            southPanel.refresh();
            centerPanel.sendMessage();
        });
    }

    public void updateGraphic() {
        northPanel.updateGraphic();
        southPanel.updateGraphic();
    }
}

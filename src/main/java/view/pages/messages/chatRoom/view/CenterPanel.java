package view.pages.messages.chatRoom.view;

import model.pages.messenger.ChatRoom;
import model.pages.messenger.Message;
import view.pages.messages.chatRoom.controller.ChatRoomController;
import view.pages.messages.chatRoom.listener.ChatRoomListener;
import view.pages.messages.message.view.MessagePanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class CenterPanel extends JPanel {
    private final ChatRoomSwing chatRoomSwing;

    public CenterPanel(ChatRoomSwing chatRoomSwing) {
        this.chatRoomSwing = chatRoomSwing;
        showGraphic();
    }

    public void showGraphic() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(125, 125, 125));
        int height = ((ChatRoom) chatRoomSwing.getListener().getController().getPage()).getMessages().size() * MessagePanel.HEIGHT;
        this.setPreferredSize(new Dimension(520, height));
        for (Message message : ((ChatRoom) chatRoomSwing.getListener().getController().getPage()).getMessages())
            addMessage(message);
    }

    public void updateGraphic() {
        int height = ((ChatRoom) chatRoomSwing.getListener().getController().getPage()).getMessages().size() * 100;
        this.setPreferredSize(new Dimension(588, height));
    }

    public void addMessage(Message message) {
        MessagePanel messagePanel = new MessagePanel(message, !message.getSenderUserName()
                .equals(((ChatRoom) chatRoomSwing.getListener().getController().getPage()).getListenerUserName()));
        messagePanel.getForwardBtn().addActionListener(e -> {
            HashSet<String> accounts = new HashSet<>(chatRoomSwing.getListener().getController().getPage().getAccount().getFollowingsUserName());
            accounts.addAll(chatRoomSwing.getListener().getController().getPage().getAccount().getFollowersUserName());
            new ForwardFrame(message, new ArrayList<>(accounts), chatRoomSwing);
        });
        if (message.isForward()) {
            messagePanel.getOwnerLbl().setText("Forward from " + message.getOwnerUserName());
        }
        this.add(messagePanel);
    }

    public void refresh() {
        chatRoomSwing.getManager().getSwings().pop().dispose();
        new ChatRoomSwing(new ChatRoomListener(new ChatRoomController((ChatRoom) chatRoomSwing.getListener().getController().getPage())));
    }
}

package view.pages.messages.chatRoom.view;

import model.pages.messenger.ChatRoom;
import model.pages.messenger.Message;
import view.pages.messages.MessagePanel;
import view.pages.messages.chatRoom.controller.ChatRoomController;
import view.pages.messages.chatRoom.listener.ChatRoomListener;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel {
    private final ChatRoomSwing chatRoomSwing;

    public CenterPanel(ChatRoomSwing chatRoomSwing) {
        this.chatRoomSwing = chatRoomSwing;
        showGraphic();
    }

    public void showGraphic() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(125, 125, 125));
        int height = ((ChatRoom) chatRoomSwing.getPage()).getMessages().size() * 100;
        this.setPreferredSize(new Dimension(520, height));
        for (Message message : ((ChatRoom) chatRoomSwing.getPage()).getMessages())
            addMessage(message);
    }

    public void updateGraphic() {
        int height = ((ChatRoom) chatRoomSwing.getPage()).getMessages().size() * 100;
        this.setPreferredSize(new Dimension(588, height));
    }

    public void addMessage(Message message) {
        if (message.getOwnerUserName().equals(((ChatRoom) chatRoomSwing.getPage()).getListenerUserName()))
            this.add(new MessagePanel(message, false));
        else
            this.add(new MessagePanel(message, true));
    }

    public void sendMessage() {
        chatRoomSwing.getManager().getSwings().pop().dispose();
        new ChatRoomSwing(new ChatRoomListener(new ChatRoomController((ChatRoom) chatRoomSwing.getPage())));
    }
}

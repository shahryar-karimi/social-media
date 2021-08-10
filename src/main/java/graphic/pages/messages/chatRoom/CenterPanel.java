package graphic.pages.messages.chatRoom;

import graphic.pages.messages.MessagePanel;
import logic.pages.messenger.ChatRoom;
import logic.pages.messenger.Message;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel {
    private ChatRoom chatRoom;
    private ChatRoomSwing chatRoomSwing;

    public CenterPanel(ChatRoomSwing chatRoomSwing) {
        this.chatRoomSwing = chatRoomSwing;
        this.chatRoom = (ChatRoom) chatRoomSwing.getPage();
        showGraphic();
    }

    public void showGraphic() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(0, 0, 125));
        int height = chatRoom.getMessages().size() * 100;
        this.setPreferredSize(new Dimension(520, height));
        for (Message message : chatRoom.getMessages())
            addMessage(message);
    }

    public void updateGraphic() {
        int height = chatRoom.getMessages().size() * 100;
        this.setPreferredSize(new Dimension(588, height));
    }

    public void addMessage(Message message) {
        if (message.getOwnerUserName().equals(chatRoom.getListenerUserName()))
            this.add(new MessagePanel(message, false));
        else
            this.add(new MessagePanel(message, true));
    }

    public void sendMessage() {
        chatRoomSwing.getManager().getSwings().pop().dispose();
        new ChatRoomSwing(chatRoom);
    }
}

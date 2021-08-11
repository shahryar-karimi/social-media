package graphic.pages.messages.chatRoom;

import graphic.FooterPanel;
import graphic.pages.Swing;
import logic.pages.messenger.ChatRoom;
import logic.pages.messenger.Message;
import logic.pages.messenger.MessengersPage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

public class ChatRoomSwing extends Swing {
    private MainPanel mainPanel;

    public ChatRoomSwing(ChatRoom chatRoom) {
        super();
        seenBothWay(chatRoom);
        this.page = chatRoom;
        mainPanel = new MainPanel(this);
        footerPanel = new FooterPanel(getManager(), page.getManager(), page.getAccount());
        addSwing(this);
        run();
    }

    private void seen(ChatRoom chatRoom, boolean isMine) {
        LinkedList<Message> messages = chatRoom.getMessages();
        if (isMine) {
            for (int i = messages.size() - 1; i >= 0; i--) {
                Message message = messages.get(i);
                if (message.getOwnerUserName().equals(chatRoom.getListenerUserName())) {
                    message.setSeen(true);
                } else {
                    break;
                }
            }
        } else {
            for (int i = messages.size() - 1; i >= 0; i--) {
                Message message = messages.get(i);
                if (message.getOwnerUserName().equals(chatRoom.getAccount().getUserName())) {
                    message.setSeen(true);
                } else {
                    break;
                }
            }
        }
    }

    private void seenBothWay(ChatRoom chatRoom) {
        seen(chatRoom, true);
        MessengersPage otherMessenger = chatRoom.getListener().getMessengersPage();
        seen(otherMessenger.searchChatRoomsByUserName(chatRoom.getAccount().getUserName()), false);
    }

    @Override
    public void run() {
        myLogger.debug(ChatRoomSwing.class.getName(), "run",
                "chat room page run for account \"" + page.getAccount().toString() + "\"");
        showGraphic();
    }

    @Override
    public void showGraphic() {
        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(footerPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    @Override
    public void updateGraphic() {
        mainPanel.updateGraphic();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

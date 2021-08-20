package view.pages.messages.chatRoom.view;

import model.pages.messenger.ChatRoom;
import view.pages.Swing;
import view.pages.messages.chatRoom.listener.ChatRoomListener;
import view.myPanels.footerPanel.controller.FooterPanelController;
import view.myPanels.footerPanel.listener.FooterPanelListener;
import view.myPanels.footerPanel.view.FooterPanel;

import java.awt.*;
import java.awt.event.ActionEvent;

public class ChatRoomSwing extends Swing {
    private final MainPanel mainPanel;

    public ChatRoomSwing(ChatRoomListener listener) {
        super();
        this.listener = listener;
        ((ChatRoom) getListener().getController().getPage()).seenBothWay();
        mainPanel = new MainPanel(this);
        footerPanel = new FooterPanel(new FooterPanelListener(new FooterPanelController(getListener().getController().getPage())));
        addSwing(this);
        run();
    }

    @Override
    public void run() {
        myLogger.debug(ChatRoomSwing.class.getName(), "run",
                "chat room page run for account \"" + getListener().getController().getPage().getAccount().toString() + "\"");
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

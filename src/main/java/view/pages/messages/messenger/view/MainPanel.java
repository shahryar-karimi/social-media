package view.pages.messages.messenger.view;

import model.Account;
import view.pages.messages.messenger.events.NewChatListEvent;
import view.pages.messages.messenger.events.OpenChatRoomEvent;
import view.pages.messages.messenger.events.SetChatRoomsViewEvent;
import view.panels.MyScrollPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class MainPanel extends JPanel implements ActionListener {
    private final MessengerSwing messengerSwing;
    private MyScrollPane<String> scrollPane;
    private JButton newChat;
    private JButton newMessage;

    private final LinkedList<String> chatRoomsView = new LinkedList<>();

    public MainPanel(MessengerSwing messengerSwing) {
        this.messengerSwing = messengerSwing;
        initialize();
        showGraphic();
    }

    private void initialize() {
        SetChatRoomsViewEvent event = new SetChatRoomsViewEvent(this, chatRoomsView);
        messengerSwing.getListener().eventOccurred(event);
        scrollPane = new MyScrollPane<>(chatRoomsView, true) {
            @Override
            public void listClicked(MouseEvent e) {
                listMouseClicked();
            }
        };

        scrollPane.getMyJList().setFont(new Font("Lucida Grande", Font.BOLD, 18));

        newChat = new JButton("New Chat");
        newChat.addActionListener(this);
        newMessage = new JButton("New Message");
        newMessage.addActionListener(this);
    }

    public void showGraphic() {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPane)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(newChat)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(newMessage)
                                .addGap(0, 366, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(newChat)
                                        .addComponent(newMessage))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE))
        );
        updateGraphic();
    }

    private void listMouseClicked() {
        String userName = scrollPane.getMyJList().getSelectedValue().split("\\s")[0];
        OpenChatRoomEvent event = new OpenChatRoomEvent(this, userName);
        messengerSwing.getListener().eventOccurred(event);
        messengerSwing.dispose();
    }

    public LinkedList<Account> getAccountList() {
        LinkedList<Account> accountList = new LinkedList<>();
        NewChatListEvent event = new NewChatListEvent(this, accountList);
        messengerSwing.getListener().eventOccurred(event);
        return accountList;
    }

    public void updateGraphic() {
        LinkedList<Account> accounts = getAccountList();
        newChat.setEnabled(!accounts.isEmpty());
        SetChatRoomsViewEvent event = new SetChatRoomsViewEvent(this, chatRoomsView);
        messengerSwing.getListener().eventOccurred(event);
        scrollPane.setList(chatRoomsView);
        scrollPane.updateGraphic();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        LinkedList<Account> accounts = getAccountList();
        if (e.getSource() == newChat) {
            if (accounts.isEmpty()) {
                JOptionPane.showMessageDialog(null, "There is no account to show", "New chat", JOptionPane.WARNING_MESSAGE);
            } else {
                new NewChatFrame(messengerSwing, accounts);
            }
        } else if (e.getSource() == newMessage) {
            new NewMessageFrame(messengerSwing);
        }
    }
}

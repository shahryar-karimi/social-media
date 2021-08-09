package graphic.pages.messages.messenger;

import graphic.MyScrollPane;
import logic.Account;
import logic.pages.messenger.ChatRoom;
import logic.pages.messenger.MessengersPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.LinkedList;

public class MainPanel extends JPanel implements ActionListener {
    private MyScrollPane<ChatRoom> scrollPane;
    private final MessengerSwing messengerSwing;
    //todo add action listener for buttons
    private JButton newChat;
    private JButton newMessage;

    public MainPanel(MessengerSwing messengerSwing) {
        this.messengerSwing = messengerSwing;
        initialize();
        showGraphic();
    }

    private void initialize() {
        scrollPane = new MyScrollPane<>(((MessengersPage) messengerSwing.getPage()).getChatRooms(), true) {
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
        String userName = scrollPane.getMyJList().getSelectedValue();
        ChatRoom newChatRoom1 = ((MessengersPage) messengerSwing.getPage()).searchChatRoomsByUserName(userName);
        messengerSwing.dispose();
        messengerSwing.getPage().getManager().goToChatroom(newChatRoom1);
    }

    public LinkedList<Account> getAccountList(MessengersPage messengersPage) {
        final LinkedList<Account> accountList;
        HashSet<Account> accounts = new HashSet<>(messengersPage.getAccount().getFollowers());
        accounts.addAll(messengersPage.getAccount().getFollowings());
        accountList = new LinkedList<>(accounts);
        for (int i = 0; i < accountList.size(); i++) {
            for (ChatRoom chatRoom : messengersPage.getChatRooms()) {
                if (accountList.get(i).getUserName().equals(chatRoom.getListener().getUserName())) {
                    accountList.remove(i);
                    i--;
                    break;
                }
            }
        }
        return accountList;
    }

    public void updateGraphic() {
        LinkedList<Account> accounts = getAccountList((MessengersPage) messengerSwing.getPage());
        newChat.setEnabled(!accounts.isEmpty());
        scrollPane.updateGraphic();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        LinkedList<Account> accounts = getAccountList((MessengersPage) messengerSwing.getPage());
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

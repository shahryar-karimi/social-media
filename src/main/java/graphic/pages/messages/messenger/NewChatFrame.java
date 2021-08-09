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
import java.util.LinkedList;

public class NewChatFrame extends JFrame {
    private final MessengerSwing messengerSwing;
    private final LinkedList<Account> accountList;
    private JButton cancel;

    public NewChatFrame(MessengerSwing messengerSwing, LinkedList<Account> accountList) {
        this.messengerSwing = messengerSwing;
        this.accountList = accountList;
        cancel = new JButton("cancel");
        showGraphic();
    }

    public void showGraphic() {
        JLabel label = new JLabel("Choose who you want to chat:");
        MyScrollPane<Account> myScrollPane = new MyScrollPane<>(accountList, true) {
            @Override
            public void listClicked(MouseEvent e) {
                Account listener = messengerSwing.getPage().getManager().search(accountList, getMyJList().getSelectedValue());
                ChatRoom newChatRoom1 = ((MessengersPage) messengerSwing.getPage()).buildNewChatRoom(listener);
                if (newChatRoom1 != null) {
                    messengerSwing.dispose();
                    messengerSwing.getPage().getManager().goToChatroom(newChatRoom1);
                } else {
                    JOptionPane.showMessageDialog(null, "You can not send message to this account!",
                            "New chat", JOptionPane.ERROR_MESSAGE);
                }
                dispose();
            }
        };

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        label.setFont(new Font("Lucida Grande", Font.BOLD, 18));

        cancel.addActionListener(e -> dispose());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(label)
                                        .addComponent(myScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cancel))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(label)
                                .addGap(18, 18, 18)
                                .addComponent(myScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cancel)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        this.setVisible(true);
    }
}

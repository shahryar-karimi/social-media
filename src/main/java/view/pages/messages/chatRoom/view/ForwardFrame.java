package view.pages.messages.chatRoom.view;

import model.pages.messenger.Message;
import view.myPanels.SelectingPanel;
import view.pages.messages.chatRoom.events.ForwardEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ForwardFrame extends JFrame {
    private final SelectingPanel selectingPeople;
    private final Message message;
    private final JButton forwardBtn;
    private final ChatRoomSwing chatRoomSwing;

    public ForwardFrame(Message message, ArrayList<String> accounts, ChatRoomSwing chatRoomSwing) {
        this.chatRoomSwing = chatRoomSwing;
        this.message = message;
        selectingPeople = new SelectingPanel(accounts);
        forwardBtn = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        forwardBtn.setText("forward");
        forwardBtn.addActionListener(this::forwardTo);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(selectingPeople, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 326, Short.MAX_VALUE)
                                                .addComponent(forwardBtn)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(selectingPeople, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(forwardBtn)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setVisible(true);
    }

    private void forwardTo(ActionEvent e) {
        ForwardEvent event = new ForwardEvent(this, message, selectingPeople.getSelectedAccountsSet());
        String msg = chatRoomSwing.getListener().eventOccurred(event);
        if (msg != null) JOptionPane.showMessageDialog(null, msg);
        dispose();
    }
}

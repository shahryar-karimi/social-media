package view.pages.messages.messenger.view;

import model.pages.messenger.ChatRoom;
import model.pages.messenger.Message;
import model.pages.messenger.MessengersPage;
import view.pages.messages.messenger.events.SendSeveralMessageEvent;
import view.panels.MyScrollPane;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashSet;

public class NewMessageFrame extends JFrame {
    private JButton cancel;
    private JPanel choosePanel;
    private JScrollPane jScrollPane1;
    private MyScrollPane<ChatRoom> myScrollPane;
    private JLabel selectedLabel;
    private JButton sendBtn;
    private JTextArea textArea;
    private JPanel writingPanel;
    private JTextArea selectedArea;

    private final MessengerSwing messengerSwing;
    private final HashSet<ChatRoom> selectedList;

    public NewMessageFrame(MessengerSwing messengerSwing) {
        this.messengerSwing = messengerSwing;
        this.selectedList = new HashSet<>();
        showGraphic();
    }

    private void showGraphic() {
        writingPanel = new JPanel();
        jScrollPane1 = new JScrollPane();
        textArea = new JTextArea();
        sendBtn = new JButton();
        choosePanel = new JPanel();
        MessengersPage messengersPage = ((MessengersPage) messengerSwing.getPage());
        myScrollPane = new MyScrollPane<>(messengersPage.getChatRooms(), false) {
            @Override
            public void listClicked(MouseEvent e) {
                String userName = getMyJList().getSelectedValue();
                ChatRoom chatRoom = messengersPage.searchChatRoomsByUserName(userName);
                if (chatRoom != null) {
                    selectedList.add(chatRoom);
                } else {
                    JOptionPane.showMessageDialog(null, "ChatRoom \"" + userName + "\" not found",
                            "Selecting ChatRoom", JOptionPane.ERROR_MESSAGE);
                }
                NewMessageFrame.this.updateGraphic();
            }
        };
        selectedArea = new JTextArea();
        cancel = new JButton();
        selectedLabel = new JLabel("Selected ChatRooms:");
        JScrollPane selectedChatRoomScrollPanel = new JScrollPane();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        textArea.setColumns(20);
        textArea.setRows(5);
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                updateGraphic();
            }
        });
        jScrollPane1.setViewportView(textArea);

        sendBtn.setText("send");

        javax.swing.GroupLayout writingPanelLayout = new javax.swing.GroupLayout(writingPanel);
        writingPanel.setLayout(writingPanelLayout);
        writingPanelLayout.setHorizontalGroup(
                writingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(writingPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(writingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, writingPanelLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(sendBtn)))
                                .addContainerGap())
        );
        writingPanelLayout.setVerticalGroup(
                writingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(writingPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sendBtn)
                                .addContainerGap())
        );

        selectedArea.setColumns(20);
        selectedArea.setRows(5);
        selectedArea.setEditable(false);
        selectedChatRoomScrollPanel.setViewportView(selectedArea);

        cancel.setText("cancel");

        addAction();

        javax.swing.GroupLayout choosePanelLayout = new javax.swing.GroupLayout(choosePanel);
        choosePanel.setLayout(choosePanelLayout);
        choosePanelLayout.setHorizontalGroup(
                choosePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(choosePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(choosePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(selectedChatRoomScrollPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(myScrollPane)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, choosePanelLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(cancel))
                                        .addGroup(choosePanelLayout.createSequentialGroup()
                                                .addComponent(selectedLabel)
                                                .addGap(0, 78, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        choosePanelLayout.setVerticalGroup(
                choosePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(choosePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(myScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(selectedLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(selectedChatRoomScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cancel)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(writingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(choosePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(writingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(choosePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        updateGraphic();
        this.pack();
        this.setVisible(true);
    }

    public void addAction() {
        cancel.addActionListener(e -> dispose());
        sendBtn.addActionListener(e -> {
            String text = textArea.getText().trim();
            textArea.setText("");
            SendSeveralMessageEvent event = new SendSeveralMessageEvent(this, text, selectedList);
            String msg = messengerSwing.getListener().eventOccurred(event);
            if (msg != null) JOptionPane.showMessageDialog(null, msg);
        });
    }

    public void updateGraphic() {
        sendBtn.setEnabled(!textArea.getText().isBlank() && !selectedList.isEmpty());
        String result = "";
        for (ChatRoom chatRoom : selectedList)
            result += chatRoom.getListenerUserName() + "\n";
        result = result.trim();
        selectedArea.setText(result);
    }
}

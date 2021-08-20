package view.tweet.share.view;

import view.myPanels.SelectingPanel;
import view.tweet.share.event.ShareEvent;
import view.tweet.share.listener.ShareListener;
import model.Account;
import model.Tweet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

public class ShareFrame extends JFrame {
    private final ShareListener listener;
    private final Tweet tweet;
    private SelectingPanel selectingPanel;

    public ShareFrame(ShareListener listener, Tweet tweet) {
        this.tweet = tweet;
        this.listener = listener;
        showGraphic();
    }

    private void showGraphic() {
        Account account = listener.getController().getPage().getAccount();
        LinkedList<String> myList = new LinkedList<>();
        for (String listName : account.getFriendsList().keySet()) {
            myList.add("l " + listName);
        }
        for (String followingUserName : account.getFollowingsUserName()) {
            myList.add("a " + followingUserName);
        }
        for (String followingUserName : account.getFollowersUserName()) {
            myList.add("a " + followingUserName);
        }
        selectingPanel = new SelectingPanel(myList);
        JButton shareBtn = new JButton();
        JLabel header = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        shareBtn.setText("share");
        shareBtn.addActionListener(this::shareBtnActionPerformed);

        header.setFont(new java.awt.Font("Lucida Grande", Font.BOLD, 24)); // NOI18N
        header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        header.setText("Share to");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(shareBtn))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(164, 164, 164)
                                                                .addComponent(header))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(selectingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(header)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(selectingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(shareBtn)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        this.pack();
        this.setVisible(true);
    }

    private void shareBtnActionPerformed(ActionEvent e) {
        ShareEvent event = new ShareEvent(this, selectingPanel.getSelectedAccountsSet(), tweet);
        String msg = listener.eventOccurred(event);
        if (msg != null) JOptionPane.showMessageDialog(null, msg);
        dispose();
    }
}

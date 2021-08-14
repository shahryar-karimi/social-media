package graphic.tweet;

import graphic.SelectingPanel;
import logic.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.LinkedList;

public class ShareFrame extends JFrame {
    private TweetsPanel tweetsPanel;
    private JLabel header;
    private SelectingPanel selectingPanel;
    private JButton shareBtn;

    public ShareFrame(TweetsPanel tweetsPanel) {
        this.tweetsPanel = tweetsPanel;
        showGraphic();
    }

    private void showGraphic() {
        Account account = tweetsPanel.getSwing().getPage().getAccount();
        LinkedList<String> myList = new LinkedList<>();
        for (String listName : account.getFriendsList().keySet()) {
            myList.add("l " + listName);
        }
        for (String followingUserName : account.getFollowingsUserName()) {
            myList.add("a " + followingUserName);
        }
        selectingPanel = new SelectingPanel(myList, tweetsPanel.getSwing());
        shareBtn = new javax.swing.JButton();
        header = new javax.swing.JLabel();

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
        Account account = tweetsPanel.getSwing().getPage().getAccount();
        for (String line : selectingPanel.getSelectedAccountsSet()) {
            if (line.charAt(0) == 'l') {
                String listName = line.substring(2);
                ArrayList<Account> list = account.getFriendsList().get(listName);
                for (Account otherAccount : list) {
                    tweetsPanel.getTweet().forward(tweetsPanel.getSwing().getPage().getAccount(), otherAccount);
                }
            } else {
                String userName = line.substring(2);
                Account otherAccount = tweetsPanel.getSwing().getPage().getManager().searchByUserName(userName);
                tweetsPanel.getTweet().forward(tweetsPanel.getSwing().getPage().getAccount(), otherAccount);
            }
        }
        dispose();
    }
}

package graphic.pages;

import graphic.FooterPanel;
import graphic.TweetsSwing;
import graphic.pages.personalPage.CommentSwing;
import graphic.pages.personalPage.PersonalPageSwing;
import logic.Account;
import logic.Tweet;
import logic.pages.TimeLinePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class TimeLineSwing extends Swing {

    private JTextArea newTweetTxtArea;
    private JButton sendBtn;
    private TweetsSwing tweetPanel;
    private final boolean isComment;

    public TimeLineSwing(TimeLinePage timeLinePage, boolean isComment) {
        super();
        this.page = timeLinePage;
        this.isComment = isComment;
        footerPanel = new FooterPanel(getManager(), page.getManager(), page.getAccount());
        addSwing(this);
        run();
    }

    @Override
    public void run() {
        myLogger.debug(PersonalPageSwing.class.getName(), "run",
                "Time line run for account \"" + page.getAccount().toString() + "\"");
        showGraphic();
    }

    private void initTweet() {
        tweetPanel = new TweetsSwing(((TimeLinePage) page).getCurrentTweet());
        tweetPanel.getNameLbl().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                Account account = page.getManager().searchByUserName(tweetPanel.getNameLbl().getText());
                page.getManager().goToInfoPage(account, page.getAccount());
            }

            public void mouseEntered(MouseEvent evt) {
                TweetsSwing.mouseEntered(tweetPanel.getNameLbl());
            }
        });
        tweetPanel.getLikeBtn().addActionListener(e -> {
            ((TimeLinePage) page).fave();
            tweetPanel.getLikeQtyLbl().setText("" + tweetPanel.getTweet().getFavesSet().size());
            tweetPanel.getLikeBtn().setIcon(new ImageIcon("src/main/resources/pictures/red-like.png")); // like
        });
        tweetPanel.getCommentBtn().addActionListener(e -> {
            dispose();
            TimeLinePage anotherTimeLinePage = new TimeLinePage(page.getAccount(), page.getManager(), false);
            anotherTimeLinePage.setTweets(((TimeLinePage) page).getCurrentTweet().getComments());
            anotherTimeLinePage.setIndexOfTweet(((TimeLinePage) page).getCurrentTweet().getComments().size() - 1);
            new TimeLineSwing(anotherTimeLinePage, true);
        });
        tweetPanel.getRetweetBtn().addActionListener(e -> ((TimeLinePage) page).retweet());
        tweetPanel.getShareBtn().addActionListener(e -> {
            //Todo forward a message (build a list from accounts and choose from them)
        });
        tweetPanel.getLikeQtyLbl().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                dispose();
                new AccountsListSwing(page, new LinkedList<>(tweetPanel.getTweet().getFavesSet()));
            }

            public void mouseEntered(MouseEvent evt) {
                TweetsSwing.mouseEntered(tweetPanel.getLikeQtyLbl());
            }
        });
        tweetPanel.getCommentBtn().addActionListener(e -> {
            //Todo forward a message
            dispose();
            new CommentSwing(page);
        });
    }

    @Override
    public void showGraphic() {
        JLabel timeLineLbl = new JLabel();
        JLabel newTweetLbl = new JLabel();
        JPanel jPanel1 = new JPanel();
        newTweetTxtArea = new JTextArea();
        sendBtn = new JButton();

        JButton nextBtn = new JButton();
        JButton previousBtn = new JButton();

        JScrollPane jScrollPane2 = new JScrollPane();

        initTweet();

        newTweetLbl.setText("New tweet: ");

        timeLineLbl.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        if (isComment) timeLineLbl.setText("Comments");
        else timeLineLbl.setText("Time line");

        newTweetTxtArea.setColumns(20);
        newTweetTxtArea.setRows(5);
        jScrollPane2.setViewportView(newTweetTxtArea);

        sendBtn.setText("send");
        sendBtn.setMaximumSize(new java.awt.Dimension(580, 580));
        sendBtn.setPreferredSize(new java.awt.Dimension(34, 34));
        sendBtn.addActionListener(this);

        nextBtn.setText(">>");
        nextBtn.setToolTipText("next tweet");
        nextBtn.setMaximumSize(new Dimension(580, 580));
        nextBtn.setPreferredSize(new Dimension(34, 34));
        nextBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //nextBtnActionPerformed(evt);
            }
        });

        previousBtn.setText("<<");
        previousBtn.setToolTipText("previous tweet");
        previousBtn.setMaximumSize(new Dimension(580, 580));
        previousBtn.setPreferredSize(new Dimension(34, 34));
        previousBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //previousBtnActionPerformed(evt);
            }
        });

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(timeLineLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(229, 229, 229))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(previousBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tweetPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(newTweetLbl)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(sendBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addComponent(nextBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(newTweetLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sendBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(timeLineLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(previousBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(245, 245, 245))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(tweetPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(nextBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(247, 247, 247))))
        );


        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(footerPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(88, Short.MAX_VALUE)
                                .addComponent(footerPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );


        this.setVisible(true);
        this.pack();
    }

    public void sendingATweet(Tweet tweet) {
        tweet.setTime();
        Account account = page.getAccount();
        if (!isComment) {
            account.addTweet(tweet);
            for (Account follower : account.getFollowers()) {
                if (!follower.isMute(account))
                    follower.getTimeLinePage().addTweet(tweet);
            }
        } else {
            ((TimeLinePage) page).sendComment(tweet.getTweetText());
        }
        page.getManager().save();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendBtn) {
            String newTweetText = newTweetTxtArea.getText();
            if (newTweetText != null && !newTweetText.isBlank()) {
                if (isComment) {

                } else {
                    sendingATweet(new Tweet(page.getAccount(), newTweetText));
                }
            }
        }
    }
}

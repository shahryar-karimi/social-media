package graphic.pages;

import graphic.FooterPanel;
import graphic.TweetsSwing;
import graphic.pages.personalPage.PersonalPageSwing;
import logic.Account;
import logic.Tweet;
import logic.pages.TimeLinePage;
import logic.pages.personal.PersonalPage;

import javax.swing.*;
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

            public void mouseExited(MouseEvent evt) {
                TweetsSwing.mouseEntered(tweetPanel.getNameLbl());
            }
        });
        tweetPanel.getLikeBtn().addActionListener(e -> {
            ((TimeLinePage) page).fave();
            tweetPanel.getLikeQtyLbl().setText("" + tweetPanel.getTweet().getFavesSet().size());
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

            public void mouseExited(MouseEvent evt) {
                TweetsSwing.mouseEntered(tweetPanel.getLikeQtyLbl());
            }
        });


    }

    @Override
    public void showGraphic() {
        JLabel timeLineLbl = new JLabel();
        JLabel newTweetLbl = new JLabel();
        JPanel jPanel1 = new JPanel();
        newTweetTxtArea = new JTextArea();
        sendBtn = new JButton();
        initTweet();
        JScrollPane jScrollPane2 = new JScrollPane();


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

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(timeLineLbl, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                                .addGap(229, 229, 229))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(tweetPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        )
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(81, 81, 81)
                                                .addComponent(newTweetLbl))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(77, 77, 77)
                                                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(sendBtn, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(timeLineLbl)
                                .addGap(42, 42, 42)
                                .addComponent(newTweetLbl)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(sendBtn, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
                                .addGap(76, 76, 76)
                                .addComponent(tweetPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(60, Short.MAX_VALUE))
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

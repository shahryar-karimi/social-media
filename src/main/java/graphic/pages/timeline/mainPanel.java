//package graphic.pages.timeline;
//
//import graphic.FooterPanel;
//import graphic.TweetsSwing;
//import graphic.pages.AccountsListSwing;
//import graphic.pages.personalPage.PersonalPageSwing;
//import logic.Account;
//import logic.Tweet;
//import logic.pages.TimeLinePage;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.util.LinkedList;
//
//public class mainPanel extends JPanel implements ActionListener {
//
//    private final TimeLinePage page;
//    private JTextArea newTweetTxtArea;
//    private JButton sendBtn;
//    private JButton nextBtn;
//    private JButton previousBtn;
//    // TODO add refresh button to panel
//    private JButton refresh;
//    private TweetsSwing tweetPanel;
//    private final boolean isComment;
//    private JLabel timeLineLbl = new JLabel();
//    private JLabel newTweetLbl = new JLabel();
//
//    public mainPanel(TimeLinePage timeLinePage, boolean isComment) {
//        super();
//        this.page = timeLinePage;
//        this.isComment = isComment;
//        showGraphic();
//    }
//
//    private void initTweet() {
//        tweetPanel = new TweetsSwing(page.getAccount(), page.getCurrentTweet());
//        if (tweetPanel.getTweet().isRetweet()) {
//            tweetPanel.getNameLbl().setText(page.getAccount().getUserName() + " Retweeted from: " + tweetPanel.getTweet().getAccount());
//        }
//        tweetPanel.getNameLbl().addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent evt) {
//                Account account = page.getManager().searchByUserName(tweetPanel.getNameLbl().getText());
//                page.getManager().goToInfoPage(account.getPersonalPage().getInfo(), page.getAccount());
//            }
//
//            public void mouseEntered(MouseEvent evt) {
//                TweetsSwing.mouseEntered(tweetPanel.getNameLbl());
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                TweetsSwing.mouseExited(tweetPanel.getNameLbl());
//            }
//        });
//        tweetPanel.getLikeBtn().addActionListener(e -> {
//            page.fave();
//            tweetPanel.updatePage();
//            if (page.getCurrentTweet().faveSetContains(page.getAccount())) {
//                tweetPanel.getLikeBtn().setIcon(new ImageIcon("src/main/resources/pictures/red-like.png")); // like
//            } else {
//                tweetPanel.getLikeBtn().setIcon(new ImageIcon("src/main/resources/pictures/green-like.png")); // like
//            }
//        });
////        tweetPanel.getCommentBtn().addActionListener(e -> {
////            dispose();
////            TimeLinePage anotherTimeLinePage = new TimeLinePage(page.getAccount(), page.getManager(), false);
////            anotherTimeLinePage.setTweets(page.getCurrentTweet().getComments());
////            anotherTimeLinePage.setIndexOfTweet(page.getCurrentTweet().getComments().size() - 1);
////            new TimeLineSwing(anotherTimeLinePage, true);
////        });
//        tweetPanel.getRetweetBtn().addActionListener(e -> {
//            page.retweet();
//            updatePage();
//        });
//        tweetPanel.getShareBtn().addActionListener(e -> {
//            //Todo forward a message (build a list from accounts and choose from them)
//        });
//        tweetPanel.getLikeQtyLbl().addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent evt) {
//                updatePage();
//                dispose();
//                new AccountsListSwing(page, new LinkedList<>(tweetPanel.getTweet().getFavesSet()));
//            }
//
//            public void mouseEntered(MouseEvent evt) {
//                TweetsSwing.mouseEntered(tweetPanel.getLikeQtyLbl());
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                TweetsSwing.mouseExited(tweetPanel.getLikeQtyLbl());
//            }
//        });
//        tweetPanel.getCommentBtn().addActionListener(e -> {
//            //Todo forward a message
//            updatePage();
//            dispose();
//            new CommentSwing(page, page.getCurrentTweet());
//        });
//    }
//
//    public void showGraphic() {
//        JPanel jPanel1 = new JPanel();
//        newTweetTxtArea = new JTextArea();
//        sendBtn = new JButton();
//        nextBtn = new JButton();
//        previousBtn = new JButton();
//        refresh = new JButton();
//
//        refresh.setIcon(new ImageIcon("src/main/resources/pictures/refresh.png")); // refresh
//        refresh.setToolTipText("refresh");
//        refresh.setMaximumSize(new Dimension(580, 580));
//        refresh.setPreferredSize(new Dimension(34, 34));
//        refresh.addActionListener(this);
//
//        JScrollPane jScrollPane2 = new JScrollPane();
//
//        initTweet();
//
//        newTweetLbl.setText("New tweet: ");
//
//        timeLineLbl.setFont(new java.awt.Font("Lucida Grande", Font.BOLD, 14)); // NOI18N
//        if (isComment) timeLineLbl.setText("Comments");
//        else timeLineLbl.setText("Time line");
//
//        newTweetTxtArea.setColumns(20);
//        newTweetTxtArea.setRows(5);
//        jScrollPane2.setViewportView(newTweetTxtArea);
//
//        sendBtn.setText("send");
//        sendBtn.setMaximumSize(new java.awt.Dimension(580, 580));
//        sendBtn.setPreferredSize(new java.awt.Dimension(34, 34));
//        sendBtn.addActionListener(this);
//
//        nextBtn.setText(">>");
//        nextBtn.setToolTipText("next tweet");
//        nextBtn.setMaximumSize(new Dimension(580, 580));
//        nextBtn.setPreferredSize(new Dimension(34, 34));
//        nextBtn.addActionListener(this);
//
//        previousBtn.setText("<<");
//        previousBtn.setToolTipText("previous tweet");
//        previousBtn.setMaximumSize(new Dimension(580, 580));
//        previousBtn.setPreferredSize(new Dimension(34, 34));
//        previousBtn.addActionListener(this);
//
//        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
//        jPanel1.setLayout(jPanel1Layout);
//        jPanel1Layout.setHorizontalGroup(
//                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
//                                .addGap(0, 0, Short.MAX_VALUE)
//                                .addComponent(timeLineLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                .addGap(229, 229, 229))
//                        .addGroup(jPanel1Layout.createSequentialGroup()
//                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                                        .addGroup(jPanel1Layout.createSequentialGroup()
//                                                .addGap(39, 39, 39)
//                                                .addComponent(previousBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                                .addGap(18, 18, 18)
//                                                .addComponent(tweetPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
//                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
//                                                .addContainerGap()
//                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                                                        .addComponent(newTweetLbl)
//                                                        .addGroup(jPanel1Layout.createSequentialGroup()
//                                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                                                .addGap(18, 18, 18)
//                                                                .addComponent(sendBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))))
//                                .addGap(18, 18, 18)
//                                .addComponent(nextBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//        );
//        jPanel1Layout.setVerticalGroup(
//                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGroup(jPanel1Layout.createSequentialGroup()
//                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                                        .addGroup(jPanel1Layout.createSequentialGroup()
//                                                .addGap(59, 59, 59)
//                                                .addComponent(newTweetLbl)
//                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
//                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
//                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                                        .addComponent(sendBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
//                                        .addComponent(timeLineLbl))
//                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
//                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
//                                                .addComponent(previousBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                                .addGap(245, 245, 245))
//                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
//                                                .addComponent(tweetPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                                .addGap(125, 125, 125))
//                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
//                                                .addComponent(nextBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                                .addGap(247, 247, 247))))
//        );
//
//
//        GroupLayout layout = new GroupLayout(this.getContentPane());
//        this.getContentPane().setLayout(layout);
//        layout.setHorizontalGroup(
//                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                        .addGroup(layout.createSequentialGroup()
//                                .addGap(22, 22, 22)
//                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
//                                        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
//                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//        );
//        layout.setVerticalGroup(
//                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                        .addGroup(layout.createSequentialGroup()
//                                .addContainerGap()
//                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//                                .addContainerGap(88, Short.MAX_VALUE)
//        ));
//
//        updatePage();
//        this.setVisible(true);
//    }
//
//    public void sendingATweet(Tweet tweet) {
//        tweet.setTime();
//        Account account = page.getAccount();
//        if (!isComment) {
//            account.addTweet(tweet);
//            for (Account follower : account.getFollowers()) {
//                if (!follower.isMute(account))
//                    follower.getTimeLinePage().addTweet(tweet);
//            }
//        } else {
//            ((TimeLinePage) page).sendComment(tweet.getTweetText());
//            newTweetTxtArea.setText("");
//            JOptionPane.showMessageDialog(this, "your tweet sent!", "Tweet", JOptionPane.WARNING_MESSAGE);
//        }
//        updatePage();
//        page.getManager().save();
//    }
//
//    public void updatePage() {
//        previousBtn.setEnabled(true);
//        nextBtn.setEnabled(true);
//        if (((TimeLinePage) page).getIndexOfTweet() == 0) {
//            previousBtn.setEnabled(false);
//        } else if (((TimeLinePage) page).getIndexOfTweet() == ((TimeLinePage) page).getTweets().size() - 1) {
//            nextBtn.setEnabled(false);
//        }
//        newTweetTxtArea.setText("");
//        tweetPanel.changeTweet(((TimeLinePage) page).getCurrentTweet());
//    }
//
//    public void nextBtnAction() {
//        String result = ((TimeLinePage) page).goNextTweet(((TimeLinePage) page).getTweets(), ((TimeLinePage) page).getIndexOfTweet());
//        if (!result.isBlank())
//            JOptionPane.showMessageDialog(null, result, "Next tweet", JOptionPane.ERROR_MESSAGE);
//        updatePage();
//    }
//
//    public void previousBtnAction() {
//        String result = ((TimeLinePage) page).goPreviousTweet(((TimeLinePage) page).getTweets(), ((TimeLinePage) page).getIndexOfTweet());
//        if (!result.isBlank())
//            JOptionPane.showMessageDialog(null, result, "Previous tweet", JOptionPane.ERROR_MESSAGE);
//        updatePage();
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == sendBtn) {
//            String newTweetText = newTweetTxtArea.getText();
//            if (newTweetText != null && !newTweetText.isBlank()) {
//                sendingATweet(new Tweet(page.getAccount(), newTweetText));
//            }
//        } else if (e.getSource() == nextBtn) {
//            nextBtnAction();
//        } else if (e.getSource() == previousBtn) {
//            previousBtnAction();
//        } else if (e.getSource() == refresh) {
//            updatePage();
//        }
//    }
//
//    public JTextArea getNewTweetTxtArea() {
//        return newTweetTxtArea;
//    }
//
//    public void setNewTweetTxtArea(JTextArea newTweetTxtArea) {
//        this.newTweetTxtArea = newTweetTxtArea;
//    }
//
//    public JButton getSendBtn() {
//        return sendBtn;
//    }
//
//    public void setSendBtn(JButton sendBtn) {
//        this.sendBtn = sendBtn;
//    }
//
//    public JButton getNextBtn() {
//        return nextBtn;
//    }
//
//    public void setNextBtn(JButton nextBtn) {
//        this.nextBtn = nextBtn;
//    }
//
//    public JButton getPreviousBtn() {
//        return previousBtn;
//    }
//
//    public void setPreviousBtn(JButton previousBtn) {
//        this.previousBtn = previousBtn;
//    }
//
//    public JButton getRefresh() {
//        return refresh;
//    }
//
//    public void setRefresh(JButton refresh) {
//        this.refresh = refresh;
//    }
//
//    public TweetsSwing getTweetPanel() {
//        return tweetPanel;
//    }
//
//    public void setTweetPanel(TweetsSwing tweetPanel) {
//        this.tweetPanel = tweetPanel;
//    }
//
//    public boolean isComment() {
//        return isComment;
//    }
//
//    public JLabel getTimeLineLbl() {
//        return timeLineLbl;
//    }
//
//    public void setTimeLineLbl(JLabel timeLineLbl) {
//        this.timeLineLbl = timeLineLbl;
//    }
//
//    public JLabel getNewTweetLbl() {
//        return newTweetLbl;
//    }
//
//    public void setNewTweetLbl(JLabel newTweetLbl) {
//        this.newTweetLbl = newTweetLbl;
//    }
//}

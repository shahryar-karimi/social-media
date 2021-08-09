package graphic.pages.timeline;

import graphic.pages.AccountsListSwing;
import graphic.tweet.NewTweetPanel;
import graphic.tweet.TweetsPanel;
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

public class MainPanel extends JPanel implements ActionListener {
    private TimeLinePage timeLine;
    private TweetsPanel tweetPanel;
    private NewTweetPanel newTweetPanel;
    private JButton nextBtn;
    private JButton previousBtn;
    private JButton refresh;
    private JLabel header;


    public MainPanel(TimeLinePage timeLine) {
        this.timeLine = timeLine;
        showGraphic();
    }

    private void addAction() {
        nextBtn.addActionListener(this);
        previousBtn.addActionListener(this);
        refresh.addActionListener(this);
    }

    private void initPanels() {
        initTweetsPanel();
        initNewTweetPanel();
    }

    private void initTweetsPanel() {
        tweetPanel = new TweetsPanel(timeLine.getAccount(), timeLine.getCurrentTweet());
        tweetPanel.setPreferredSize(new Dimension(428, 283));
        if (tweetPanel.getTweet() == null) return;
        if (tweetPanel.getTweet().isRetweet()) {
            tweetPanel.getNameLbl().setText(timeLine.getAccount().getUserName() + " Retweeted from: " + tweetPanel.getTweet().getAccount());
        }
        tweetPanel.getNameLbl().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                Account account = timeLine.getManager().searchByUserName(tweetPanel.getNameLbl().getText());
                timeLine.getManager().goToInfoPage(account.getPersonalPage().getInfo(), timeLine.getAccount());
            }

            public void mouseEntered(MouseEvent evt) {
                TweetsPanel.mouseEntered(tweetPanel.getNameLbl());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                TweetsPanel.mouseExited(tweetPanel.getNameLbl());
            }
        });
        tweetPanel.getLikeBtn().addActionListener(e -> {
            timeLine.fave();
            tweetPanel.updatePage();
            if (timeLine.getCurrentTweet().faveSetContains(timeLine.getAccount())) {
                tweetPanel.getLikeBtn().setIcon(new ImageIcon("src/main/resources/pictures/red-like.png")); // like
            } else {
                tweetPanel.getLikeBtn().setIcon(new ImageIcon("src/main/resources/pictures/green-like.png")); // like
            }
        });
        tweetPanel.getRetweetBtn().addActionListener(e -> {
            timeLine.retweet();
            updateGraphic();
        });
        tweetPanel.getShareBtn().addActionListener(e -> {
            //Todo forward a message (build a list from accounts and choose from them)
        });
        tweetPanel.getLikeQtyLbl().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                updateGraphic();
                new AccountsListSwing(timeLine, new LinkedList<>(tweetPanel.getTweet().getFavesSet()));
            }

            public void mouseEntered(MouseEvent evt) {
                TweetsPanel.mouseEntered(tweetPanel.getLikeQtyLbl());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                TweetsPanel.mouseExited(tweetPanel.getLikeQtyLbl());
            }
        });
        tweetPanel.getCommentBtn().addActionListener(e -> {
            //Todo forward a message
            updateGraphic();
            new CommentSwing(this.timeLine, timeLine.getCurrentTweet());
        });
    }

    private void initNewTweetPanel() {
        newTweetPanel = new NewTweetPanel();
        newTweetPanel.setPreferredSize(new java.awt.Dimension(340, 161));
        newTweetPanel.getSend().addActionListener(e -> {
            String newTweetText = newTweetPanel.getTweetsText().getText();
            if (newTweetText != null && !newTweetText.isBlank()) {
                sendingATweet(new Tweet(timeLine.getAccount(), newTweetText));
            }
        });
    }

    public void sendingATweet(Tweet tweet) {
        tweet.setTime();
        Account account = timeLine.getAccount();
        account.addTweet(tweet);
        for (Account follower : account.getFollowers()) {
            if (!follower.isMute(account))
                follower.getTimeLinePage().addTweet(tweet);
        }
        updateGraphic();
        timeLine.getManager().save();
    }

    public void updateGraphic() {
        nextBtn.setEnabled(true);
        previousBtn.setEnabled(true);
        if (timeLine.getIndexOfTweet() == 0) {
            previousBtn.setEnabled(false);
        } else if (timeLine.getIndexOfTweet() == (timeLine.getTweets().size() - 1)) {
            nextBtn.setEnabled(false);
        }
        newTweetPanel.updateGraphic();
        if (tweetPanel.getTweet() == null) return;
        tweetPanel.changeTweet(timeLine.getCurrentTweet());
    }

    public void showGraphic() {
        initPanels();
        header = new javax.swing.JLabel();
        nextBtn = new javax.swing.JButton();
        previousBtn = new javax.swing.JButton();
        refresh = new JButton();

        setPreferredSize(new java.awt.Dimension(600, 700));

        newTweetPanel.showGraphic();

        header.setFont(new java.awt.Font("Lucida Grande", Font.BOLD, 18)); // NOI18N
        header.setText("Timeline");

        nextBtn.setText(">>");

        previousBtn.setText("<<");

        refresh.setIcon(new javax.swing.ImageIcon("src/main/resources/pictures/refresh.png")); // NOI18N

        addAction();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(35, 35, 35)
                                                .addComponent(previousBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(tweetPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(nextBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(33, 33, 33)
                                                                .addComponent(newTweetPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 29, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(255, 255, 255)
                                                .addComponent(header)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(nextBtn)
                                                                .addGap(319, 319, 319))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(previousBtn)
                                                                .addGap(322, 322, 322))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(37, 37, 37)
                                                .addComponent(newTweetPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(37, 37, 37)
                                                .addComponent(tweetPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(189, Short.MAX_VALUE))))
        );
    }

    public TweetsPanel getTweetPanel() {
        return tweetPanel;
    }

    public NewTweetPanel getNewTweetPanel() {
        return newTweetPanel;
    }

    public JButton getNextBtn() {
        return nextBtn;
    }

    public JButton getPreviousBtn() {
        return previousBtn;
    }

    public JButton getRefresh() {
        return refresh;
    }

    public void setTimeLine(TimeLinePage timeLine) {
        this.timeLine = timeLine;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextBtn) {
            String result = timeLine.goNextTweet(timeLine.getTweets(), timeLine.getIndexOfTweet());
            if (!result.isBlank())
                JOptionPane.showMessageDialog(null, result, "Next tweet", JOptionPane.ERROR_MESSAGE);
            updateGraphic();
        } else if (e.getSource() == previousBtn) {
            String result = timeLine.goPreviousTweet(timeLine.getTweets(), timeLine.getIndexOfTweet());
            if (!result.isBlank())
                JOptionPane.showMessageDialog(null, result, "Previous tweet", JOptionPane.ERROR_MESSAGE);
            updateGraphic();
        } else if (e.getSource() == refresh) {
            updateGraphic();
        }
    }
}
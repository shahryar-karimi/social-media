package view.pages.timeline.view;

import model.Account;
import model.Tweet;
import model.pages.TimeLinePage;
import view.pages.Swing;
import view.tweet.newTweetPanel.view.NewTweetPanel;
import view.tweet.tweetsPanel.controller.TweetsPanelController;
import view.tweet.tweetsPanel.listener.TweetsPanelListener;
import view.tweet.tweetsPanel.view.TweetsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel implements ActionListener {
    private TweetsPanel tweetPanel;
    private NewTweetPanel newTweetPanel;
    private JButton nextBtn;
    private JButton previousBtn;
    private JButton refresh;
    private JLabel header;

    private TimeLinePage timeLine;
    private final Swing swing;
    private Tweet topTweet;

    public MainPanel(Swing swing) {
        this.swing = swing;
        this.timeLine = (TimeLinePage) swing.getPage();
        showGraphic();
    }

    public MainPanel(Swing swing, Tweet topTweet) {
        this(swing);
        this.topTweet = topTweet;
    }

    private void addAction() {
        nextBtn.addActionListener(this);
        previousBtn.addActionListener(this);
        refresh.addActionListener(this);
    }

    private void initPanels() {
        tweetPanel = new TweetsPanel(new TweetsPanelListener(new TweetsPanelController(timeLine)), timeLine.getAccount(), timeLine.getCurrentTweet(), swing);
        initNewTweetPanel();
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
        if (topTweet == null) {
            account.addTweet(tweet);
            for (Account follower : account.getFollowers()) {
                if (!follower.isMute(account))
                    follower.getTimeLinePage().addTweet(tweet);
            }
        } else {
            topTweet.addComment(tweet);
        }
        updateGraphic();
        timeLine.getManager().save();
    }

    public void updateGraphic() {
        if (timeLine.getTweets().size() < 2) {
            nextBtn.setEnabled(false);
            previousBtn.setEnabled(false);
        } else {
            nextBtn.setEnabled(true);
            previousBtn.setEnabled(true);
            if (timeLine.getIndexOfTweet() == 0) {
                previousBtn.setEnabled(false);
            } else if (timeLine.getIndexOfTweet() == (timeLine.getTweets().size() - 1)) {
                nextBtn.setEnabled(false);
            }
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

        setPreferredSize(new Dimension(542, 492));

        newTweetPanel.showGraphic();

        header.setFont(new java.awt.Font("Lucida Grande", Font.BOLD, 18));
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
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(header)
                                                                .addGap(204, 204, 204))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(newTweetPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(66, 66, 66)))
                                                .addComponent(refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(previousBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tweetPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(nextBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(291, 291, 291)
                                                .addComponent(nextBtn))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(newTweetPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(tweetPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(291, 291, 291)
                                                                .addComponent(previousBtn)))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    public JLabel getHeader() {
        return header;
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
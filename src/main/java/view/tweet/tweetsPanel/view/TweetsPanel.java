package view.tweet.tweetsPanel.view;

import view.pages.Swing;
import view.tweet.tweetsPanel.events.TweetsPanelEvent;
import view.tweet.tweetsPanel.listener.TweetsPanelListener;
import model.Account;
import model.Tweet;
import utility.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TweetsPanel extends JPanel {
    private final TweetsPanelListener listener;

    private final Swing swing;
    private Tweet tweet;
    private Account visitor;
    private final JLabel nameLbl = new JLabel();
    private final JLabel dateLbl = new JLabel();
    private final JLabel likeQtyLbl = new JLabel();
    private final JLabel commentQtyLbl = new JLabel();
    private final JLabel retweetQtyLbl = new JLabel();

    private final JButton likeBtn = new JButton();
    private final JButton commentBtn = new JButton();
    private final JButton shareBtn = new JButton();
    private final JButton retweetBtn = new JButton();

    private final JScrollPane jScrollPane1 = new JScrollPane();
    private final JTextArea tweetTxtArea = new JTextArea();

    public TweetsPanel(TweetsPanelListener listener, Account visitor, Tweet tweet, Swing swing) {
        this.listener = listener;
        this.visitor = visitor;
        this.tweet = tweet;
        this.swing = swing;
        setPreferredSize(new Dimension(428, 283));
        setDetails();
        if (tweet == null) getNullPanel();
        else getTweetPanel();
    }

    public static void mouseEntered(JLabel label) {
        label.setFont(new Font("Lucida Grande", Font.BOLD, 13));
    }

    public static void mouseExited(JLabel label) {
        label.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
    }

    private void getNullPanel() {
        JLabel Alarm = new JLabel();

        Alarm.setFont(new java.awt.Font("Lucida Grande", Font.PLAIN, 36)); // NOI18N
        Alarm.setText("  there is no tweet here");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(Alarm, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addComponent(Alarm, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(109, Short.MAX_VALUE))
        );
    }

    public void getTweetPanel() {
        tweetTxtArea.setText(tweet.getTweetText());
        tweetTxtArea.setEditable(false);
        tweetTxtArea.setColumns(20);
        tweetTxtArea.setRows(5);
        jScrollPane1.setViewportView(tweetTxtArea);

        nameLbl.setText(tweet.getAccount().getUserName());
        if (tweet.isRetweet()) {
            nameLbl.setText(tweet.getRetweeter().getUserName() + " Retweeted from: " + tweet.getAccount().getUserName());
        }
        nameLbl.setCursor(new Cursor(Cursor.HAND_CURSOR));

        dateLbl.setText(tweet.getTime());

        likeBtn.setIcon(ImageLoader.getTweetsIcons().get("green-like"));
        likeBtn.setToolTipText("like");
        likeBtn.setMaximumSize(new Dimension(580, 580));
        likeBtn.setPreferredSize(new Dimension(34, 34));

        commentBtn.setIcon(ImageLoader.getTweetsIcons().get("comments"));
        commentBtn.setToolTipText("comment");
        commentBtn.setMaximumSize(new Dimension(580, 580));
        commentBtn.setPreferredSize(new Dimension(34, 34));

        retweetBtn.setIcon(ImageLoader.getTweetsIcons().get("retweet"));
        retweetBtn.setToolTipText("retweet");
        retweetBtn.setMaximumSize(new Dimension(580, 580));
        retweetBtn.setPreferredSize(new Dimension(34, 34));
        retweetBtn.setEnabled(tweet.getAccount().isPagePublic());

        shareBtn.setIcon(ImageLoader.getTweetsIcons().get("paper-plane"));
        shareBtn.setToolTipText("share");
        shareBtn.setMaximumSize(new Dimension(580, 580));
        shareBtn.setPreferredSize(new Dimension(34, 34));

        likeQtyLbl.setHorizontalAlignment(SwingConstants.CENTER);
        likeQtyLbl.setText(tweet.getFavesSet().size() + "");
        likeQtyLbl.setCursor(new Cursor(Cursor.HAND_CURSOR));

        commentQtyLbl.setText("" + tweet.getComments().size());

        retweetQtyLbl.setHorizontalAlignment(SwingConstants.CENTER);
        retweetQtyLbl.setText("" + tweet.getRetweetQty());

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 416, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(nameLbl)
                                                .addGap(156, 156, 156)
                                                .addComponent(dateLbl))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(commentBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(commentQtyLbl)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(likeBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(likeQtyLbl)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(retweetBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(retweetQtyLbl)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(shareBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(nameLbl, GroupLayout.Alignment.TRAILING)
                                        .addComponent(dateLbl))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addComponent(commentBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(likeBtn, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(likeQtyLbl)
                                                        .addComponent(retweetBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(retweetQtyLbl)
                                                        .addComponent(shareBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(commentQtyLbl))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        updatePage();
    }

    public void updatePage() {
        if (tweet != null) {
            likeQtyLbl.setText(tweet.getFavesSet().size() + "");
            if (tweet.faveSetContains(visitor)) {
                likeBtn.setIcon(ImageLoader.getTweetsIcons().get("red-like"));
            } else {
                likeBtn.setIcon(ImageLoader.getTweetsIcons().get("green-like"));
            }
            retweetQtyLbl.setText(tweet.getRetweetQty() + "");
            commentQtyLbl.setText(tweet.getComments().size() + "");
            nameLbl.setText(tweet.getAccount().getUserName());
            if (tweet.isRetweet()) {
                nameLbl.setText(tweet.getRetweeter().getUserName() + " Retweeted from: " + tweet.getAccount().getUserName());
            }
            dateLbl.setText(tweet.getTime());
        }
    }

    public void changeTweet(Tweet tweet) {
        this.tweet = tweet;
        if (tweet == null) getNullPanel();
        else getTweetPanel();
        updatePage();
    }

    public Tweet getTweet() {
        return tweet;
    }

    public JButton getLikeBtn() {
        return likeBtn;
    }

    public void setDetails() {
        addLabelsMouseListener();
        addBtnsActionListener();
    }

    protected void addBtnsActionListener() {
        commentBtn.addActionListener(this::commentBtnActionPerformed);
        likeBtn.addActionListener(this::likeBtnActionPerformed);
        retweetBtn.addActionListener(this::retweetBtnActionPerformed);
        shareBtn.addActionListener(this::shareBtnActionPerformed);
    }

    private String sendEvent(String work, Account account) {
        TweetsPanelEvent event = new TweetsPanelEvent(this, tweet, work, account);
        return listener.eventOccurred(event);
    }

    private void commentBtnActionPerformed(ActionEvent e) {
        swing.updateGraphic();
        swing.dispose();
        sendEvent("comment btn", null);
    }

    private void likeBtnActionPerformed(ActionEvent e) {
        boolean flag = "true".equals(sendEvent("like btn", visitor));
        updatePage();
        if (flag) {
            getLikeBtn().setIcon(ImageLoader.getTweetsIcons().get("red-like"));
        } else {
            getLikeBtn().setIcon(ImageLoader.getTweetsIcons().get("green-like"));
        }
    }

    private void retweetBtnActionPerformed(ActionEvent e) {
        sendEvent("retweet btn", visitor);
        swing.updateGraphic();
    }

    private void shareBtnActionPerformed(ActionEvent e) {
        sendEvent("share btn", null);
    }

    private void addLabelsMouseListener() {
        nameLbl.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                nameLblMouseClicked();
            }

            @Override
            public void mouseEntered(MouseEvent evt) {
                TweetsPanel.mouseEntered(nameLbl);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                TweetsPanel.mouseExited(nameLbl);
            }
        });
        likeQtyLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                likeQtyLblMouseClicked();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                TweetsPanel.mouseEntered(likeQtyLbl);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                TweetsPanel.mouseExited(likeQtyLbl);
            }
        });
    }

    public void nameLblMouseClicked() {
        swing.updateGraphic();
        sendEvent("name Lbl", visitor);
        swing.dispose();
    }

    private void likeQtyLblMouseClicked() {
        swing.updateGraphic();
        swing.dispose();
        sendEvent("like Lbl", null);
    }
}

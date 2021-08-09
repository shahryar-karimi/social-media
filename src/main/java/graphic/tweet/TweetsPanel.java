package graphic.tweet;

import logic.Account;
import logic.Tweet;
import utility.ImageLoader;

import javax.swing.*;
import java.awt.*;

public class TweetsPanel extends JPanel {

    private Tweet tweet;
    private Account visitor;
    private JLabel nameLbl = new JLabel();
    private JLabel dateLbl = new JLabel();
    private JLabel likeQtyLbl = new JLabel();
    private JLabel commentQtyLbl = new JLabel();
    private JLabel retweetQtyLbl = new JLabel();

    private JButton likeBtn = new JButton();
    private JButton commentBtn = new JButton();
    private JButton shareBtn = new JButton();
    private JButton retweetBtn = new JButton();

    private JScrollPane jScrollPane1 = new JScrollPane();
    private JTextArea tweetTxtArea = new JTextArea();

    public TweetsPanel(Account visitor, Tweet tweet) {
        this.tweet = tweet;
        this.visitor = visitor;
        if (tweet == null) getNullPanel();
        else getTweetPanel();
    }

    private void getNullPanel() {
        JLabel Alarm = new JLabel();

        Alarm.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
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

        shareBtn.setIcon(ImageLoader.getTweetsIcons().get("paper-plane"));
        shareBtn.setToolTipText("share");
        shareBtn.setMaximumSize(new Dimension(580, 580));
        shareBtn.setPreferredSize(new Dimension(34, 34));

        likeQtyLbl.setHorizontalAlignment(SwingConstants.CENTER);
        likeQtyLbl.setText(tweet.getFavesSet().size() + "");
        likeQtyLbl.setCursor(new Cursor(Cursor.HAND_CURSOR));

        commentQtyLbl.setText("" + tweet.getComments().size());

        retweetQtyLbl.setHorizontalAlignment(SwingConstants.CENTER);
        retweetQtyLbl.setText("" + tweet.getRetweet());

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
            retweetQtyLbl.setText(tweet.getRetweet() + "");
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
        getTweetPanel();
        updatePage();
    }

    public static void mouseEntered(JLabel label) {
        label.setFont(new Font("Lucida Grande", Font.BOLD, 13));
    }

    public static void mouseExited(JLabel label) {
        label.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
    }

    public Tweet getTweet() {
        return tweet;
    }

    public JLabel getNameLbl() {
        return nameLbl;
    }

    public JLabel getDateLbl() {
        return dateLbl;
    }

    public JLabel getLikeQtyLbl() {
        return likeQtyLbl;
    }

    public JLabel getCommentQtyLbl() {
        return commentQtyLbl;
    }

    public JLabel getRetweetQtyLbl() {
        return retweetQtyLbl;
    }

    public JButton getLikeBtn() {
        return likeBtn;
    }

    public JButton getCommentBtn() {
        return commentBtn;
    }

    public JButton getShareBtn() {
        return shareBtn;
    }

    public JButton getRetweetBtn() {
        return retweetBtn;
    }

    public JScrollPane getJScrollPane1() {
        return jScrollPane1;
    }

    public JTextArea getTweetTxtArea() {
        return tweetTxtArea;
    }

    public Account getVisitor() {
        return visitor;
    }

    public void setVisitor(Account visitor) {
        this.visitor = visitor;
    }
}

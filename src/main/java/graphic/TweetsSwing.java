package graphic;

import logic.Account;
import logic.Tweet;

import javax.swing.*;
import java.awt.*;

public class TweetsSwing extends JPanel{

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

    public TweetsSwing(Account visitor, Tweet tweet) {
        this.tweet = tweet;
        this.visitor = visitor;
        getTweetPanel();
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

        likeBtn.setIcon(new ImageIcon("src/main/resources/pictures/green-like.png")); // like
        likeBtn.setToolTipText("like");
        likeBtn.setMaximumSize(new Dimension(580, 580));
        likeBtn.setPreferredSize(new Dimension(34, 34));

        commentBtn.setIcon(new ImageIcon("src/main/resources/pictures/comments.png")); // comment
        commentBtn.setToolTipText("comment");
        commentBtn.setMaximumSize(new Dimension(580, 580));
        commentBtn.setPreferredSize(new Dimension(34, 34));

        retweetBtn.setIcon(new ImageIcon("src/main/resources/pictures/retweet.png")); // retweet
        retweetBtn.setToolTipText("retweet");
        retweetBtn.setMaximumSize(new Dimension(580, 580));
        retweetBtn.setPreferredSize(new Dimension(34, 34));

        shareBtn.setIcon(new ImageIcon("src/main/resources/pictures/paper-plane.png")); // share
        shareBtn.setToolTipText("share");
        shareBtn.setMaximumSize(new Dimension(580, 580));
        shareBtn.setPreferredSize(new Dimension(34, 34));

        likeQtyLbl.setHorizontalAlignment(SwingConstants.CENTER);
        likeQtyLbl.setText(tweet.getFavesSet().size() + "");
        likeQtyLbl.setCursor(new Cursor(Cursor.HAND_CURSOR));

        commentQtyLbl.setText("" + tweet.getComments().size());

        retweetQtyLbl.setHorizontalAlignment(SwingConstants.CENTER);
        retweetQtyLbl.setText("" + tweet.getRetweet());

        javax.swing.GroupLayout tweetPanelLayout = new javax.swing.GroupLayout(this);
        this.setLayout(tweetPanelLayout);
        tweetPanelLayout.setHorizontalGroup(
                tweetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(tweetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                                .addGap(55, 55, 55)
                                                .addComponent(nameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(dateLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                                .addComponent(commentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(commentQtyLbl)
                                                .addGap(18, 18, 18)
                                                .addComponent(likeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(likeQtyLbl)
                                                .addGap(18, 18, 18)
                                                .addComponent(retweetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(retweetQtyLbl)
                                                .addGap(18, 18, 18)
                                                .addComponent(shareBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 15, Short.MAX_VALUE))))
        );
        tweetPanelLayout.setVerticalGroup(
                tweetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(tweetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(commentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                                .addGroup(tweetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(nameLbl)
                                                        .addComponent(dateLbl))
                                                .addGap(18, 18, 18)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(tweetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(tweetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(commentQtyLbl)
                                                                .addComponent(likeQtyLbl))
                                                        .addComponent(likeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(retweetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(retweetQtyLbl)
                                                        .addComponent(shareBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(12, Short.MAX_VALUE))
        );
        updatePage();
    }

    public void updatePage() {
        likeQtyLbl.setText(tweet.getFavesSet().size() + "");
        if (tweet.faveSetContains(visitor)) {
            likeBtn.setIcon(new ImageIcon("src/main/resources/pictures/red-like.png"));
        } else {
            likeBtn.setIcon(new ImageIcon("src/main/resources/pictures/green-like.png"));
        }
        retweetQtyLbl.setText(tweet.getRetweet() + "");
        commentQtyLbl.setText(tweet.getComments().size() + "");
        nameLbl.setText(tweet.getAccount().getUserName());
        if (tweet.isRetweet()) {
            nameLbl.setText(tweet.getRetweeter().getUserName() + " Retweeted from: " + tweet.getAccount().getUserName());
        }
        dateLbl.setText(tweet.getTime());
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

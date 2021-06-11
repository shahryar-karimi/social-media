package graphic;

import logic.Tweet;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class TweetsSwing extends JPanel{

    private final Tweet tweet;
    private JLabel nameLbl = new JLabel();
    private JLabel dateLbl = new JLabel();
    private JLabel likeQtyLbl = new JLabel();
    private JLabel commentQtyLbl = new JLabel();
    private JLabel retweetQtyLbl = new JLabel();

    private JButton likeBtn = new JButton();
    private JButton commentBtn = new JButton();
    private JButton shareBtn = new JButton();
    private JButton previousBtn = new JButton();
    private JButton retweetBtn = new JButton();

    private JScrollPane jScrollPane1 = new JScrollPane();
    private JTextArea tweetTxtArea = new JTextArea();

    public TweetsSwing(Tweet tweet) {
        this.tweet = tweet;
        getTweetPanel();
    }

    public void getTweetPanel() {

        tweetTxtArea.setEditable(false);
        tweetTxtArea.setColumns(20);
        tweetTxtArea.setRows(5);
        jScrollPane1.setViewportView(tweetTxtArea);

        nameLbl.setText("Name");
        nameLbl.setCursor(new Cursor(Cursor.HAND_CURSOR));

        dateLbl.setText("Date");

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
//        commentQtyLbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

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
                                                .addComponent(dateLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    }

    //        private void likeBtnActionPerformed(ActionEvent evt) {
//            // TODO add your handling code here:
//        }
//
//        private void commentBtnActionPerformed(ActionEvent evt) {
//            // TODO add your handling code here:
//        }
//
//        private void shareBtnActionPerformed(ActionEvent evt) {
//            // TODO add your handling code here:
//        }
//
//        private void retweetBtnActionPerformed(ActionEvent evt) {
//            // TODO add your handling code here:
//        }
//
//        private void nameLblMouseClicked(MouseEvent evt) {
//
//        }
//
//        private void nextBtn1ActionPerformed(ActionEvent evt) {
//            // TODO add your handling code here:
//        }
//
//        private void commentQtyLblMouseClicked(MouseEvent evt) {
//            // TODO add your handling code here:
//        }

//        private void likeQtyLblMouseClicked(MouseEvent evt) {
//            // TODO add your handling code here:
//        }

    public static void mouseEntered(JLabel label) {
        label.setFont(new Font("Lucida Grande", 1, 13));
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

    public JButton getPreviousBtn() {
        return previousBtn;
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
}

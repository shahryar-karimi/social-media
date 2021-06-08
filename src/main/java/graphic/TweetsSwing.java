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
    private JButton nextBtn = new JButton();

    private JPanel tweetPanel = new JPanel();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JTextArea tweetTxtArea = new JTextArea();

    public TweetsSwing(Tweet tweet) {
        this.tweet = tweet;
    }

    public JPanel getTweetPanel() {

        tweetTxtArea.setEditable(false);
        tweetTxtArea.setColumns(20);
        tweetTxtArea.setRows(5);
        jScrollPane1.setViewportView(tweetTxtArea);

        nameLbl.setText("Name");
        nameLbl.setCursor(new Cursor(Cursor.HAND_CURSOR));

        dateLbl.setText("Date");

        likeBtn.setIcon(new ImageIcon("src/main/resources/pictures/love.png")); // like
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

        likeQtyLbl.setHorizontalAlignment(SwingConstants.CENTER);
        likeQtyLbl.setText(tweet.getFavesSet().size() + "");
        likeQtyLbl.setCursor(new Cursor(Cursor.HAND_CURSOR));

        commentQtyLbl.setText("" + tweet.getComments().size());
//        commentQtyLbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        retweetQtyLbl.setHorizontalAlignment(SwingConstants.CENTER);
        retweetQtyLbl.setText("" + tweet.getRetweet());

        GroupLayout tweetPanelLayout = new GroupLayout(tweetPanel);
        tweetPanel.setLayout(tweetPanelLayout);
        tweetPanelLayout.setHorizontalGroup(
                tweetPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(previousBtn, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(tweetPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                                .addComponent(nameLbl, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(dateLbl, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                                .addComponent(commentBtn, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(commentQtyLbl)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(likeBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(likeQtyLbl)
                                                .addGap(28, 28, 28)
                                                .addComponent(retweetBtn, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(retweetQtyLbl)
                                                .addGap(18, 18, 18)
                                                .addComponent(shareBtn, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                                .addGap(98, 98, 98))
                                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 370, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(nextBtn, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(29, Short.MAX_VALUE))))
        );
        tweetPanelLayout.setVerticalGroup(
                tweetPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(tweetPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(nameLbl)
                                        .addComponent(dateLbl))
                                .addGroup(tweetPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                                .addGroup(tweetPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                                                .addGap(92, 92, 92)
                                                                .addComponent(previousBtn, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                                                .addGap(90, 90, 90)
                                                                .addComponent(nextBtn, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
                                                .addGap(78, 78, 78))
                                        .addGroup(GroupLayout.Alignment.TRAILING, tweetPanelLayout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(tweetPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(commentQtyLbl, GroupLayout.Alignment.TRAILING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, tweetPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(likeBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(commentBtn, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(retweetBtn, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(shareBtn, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(retweetQtyLbl, GroupLayout.Alignment.TRAILING)
                                        .addComponent(likeQtyLbl, GroupLayout.Alignment.TRAILING))
                                .addContainerGap(66, Short.MAX_VALUE))
        );

        return tweetPanel;
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
//        private void nextBtnActionPerformed(ActionEvent evt) {
//            // TODO add your handling code here:
//        }
//
//        private void previousBtnActionPerformed(ActionEvent evt) {
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

    public JButton getNextBtn() {
        return nextBtn;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public JTextArea getTweetTxtArea() {
        return tweetTxtArea;
    }
}

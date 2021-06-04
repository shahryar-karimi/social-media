package graphic.pages;

import graphic.pages.personalPage.InfoPageSwing;
import logic.Account;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class SwingUtility {

    public static JPanel getTweetPanel() {

        JLabel nameLbl = new JLabel();
        JLabel dateLbl = new JLabel();
        JLabel likeQtyLbl = new JLabel();
        JLabel commentQtyLbl = new JLabel();
        JLabel retweetQtyLbl = new JLabel();

        JButton likeBtn = new JButton();
        JButton commentBtn = new JButton();
        JButton shareBtn = new JButton();
        JButton previousBtn = new JButton();
        JButton retweetBtn = new JButton();
        JButton nextBtn = new JButton();

        JPanel tweetPanel = new JPanel();
        JScrollPane jScrollPane1 = new JScrollPane();
        JTextArea tweetTxtArea = new JTextArea();

        JScrollBar jScrollBar1 = new JScrollBar();

        tweetTxtArea.setEditable(false);
        tweetTxtArea.setColumns(20);
        tweetTxtArea.setRows(5);
        jScrollPane1.setViewportView(tweetTxtArea);

        nameLbl.setText("Name");
        nameLbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nameLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                //nameLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nameLblMouseEntered(evt, nameLbl);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nameLblMouseExited(evt, nameLbl);
            }
        });

        dateLbl.setText("Date");

        likeBtn.setIcon(new javax.swing.ImageIcon("src/main/resources/pictures/love.png")); // like
        likeBtn.setToolTipText("like");
        likeBtn.setMaximumSize(new java.awt.Dimension(580, 580));
        likeBtn.setPreferredSize(new java.awt.Dimension(34, 34));
        likeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //likeBtnActionPerformed(evt);
            }
        });

        commentBtn.setIcon(new javax.swing.ImageIcon("src/main/resources/pictures/comments.png")); // comment
        commentBtn.setToolTipText("comment");
        commentBtn.setMaximumSize(new java.awt.Dimension(580, 580));
        commentBtn.setPreferredSize(new java.awt.Dimension(34, 34));
        commentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //commentBtnActionPerformed(evt);
            }
        });

        retweetBtn.setIcon(new javax.swing.ImageIcon("src/main/resources/pictures/retweet.png")); // retweet
        retweetBtn.setToolTipText("retweet");
        retweetBtn.setMaximumSize(new java.awt.Dimension(580, 580));
        retweetBtn.setPreferredSize(new java.awt.Dimension(34, 34));
        retweetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //retweetBtnActionPerformed(evt);
            }
        });

        shareBtn.setIcon(new javax.swing.ImageIcon("src/main/resources/pictures/paper-plane.png")); // share
        shareBtn.setToolTipText("share");
        shareBtn.setMaximumSize(new java.awt.Dimension(580, 580));
        shareBtn.setPreferredSize(new java.awt.Dimension(34, 34));
        shareBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //shareBtnActionPerformed(evt);
            }
        });

        nextBtn.setText(">>");
        nextBtn.setToolTipText("next tweet");
        nextBtn.setMaximumSize(new java.awt.Dimension(580, 580));
        nextBtn.setPreferredSize(new java.awt.Dimension(34, 34));
        nextBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //nextBtnActionPerformed(evt);
            }
        });

        previousBtn.setText("<<");
        previousBtn.setToolTipText("previous tweet");
        previousBtn.setMaximumSize(new java.awt.Dimension(580, 580));
        previousBtn.setPreferredSize(new java.awt.Dimension(34, 34));
        previousBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //previousBtnActionPerformed(evt);
            }
        });

        likeQtyLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        likeQtyLbl.setText("QtyLbl");
        likeQtyLbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        likeQtyLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //likeQtyLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                likeQtyLblMouseEntered(evt, likeQtyLbl);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                likeQtyLblMouseExited(evt, likeQtyLbl);
            }
        });

        commentQtyLbl.setText("QtyLbl");
        commentQtyLbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        commentQtyLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //commentQtyLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                commentQtyLblMouseEntered(evt, commentQtyLbl);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                commentQtyLblMouseExited(evt, commentQtyLbl);
            }
        });

        retweetQtyLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        retweetQtyLbl.setText("QtyLbl");

        javax.swing.GroupLayout tweetPanelLayout = new javax.swing.GroupLayout(tweetPanel);
        tweetPanel.setLayout(tweetPanelLayout);
        tweetPanelLayout.setHorizontalGroup(
                tweetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(previousBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(tweetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                                .addComponent(nameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(dateLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                                .addComponent(commentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(commentQtyLbl)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(likeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(likeQtyLbl)
                                                .addGap(28, 28, 28)
                                                .addComponent(retweetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(retweetQtyLbl)
                                                .addGap(18, 18, 18)
                                                .addComponent(shareBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(98, 98, 98))
                                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(nextBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(29, Short.MAX_VALUE))))
        );
        tweetPanelLayout.setVerticalGroup(
                tweetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(tweetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(nameLbl)
                                        .addComponent(dateLbl))
                                .addGroup(tweetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                                .addGroup(tweetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                                                .addGap(92, 92, 92)
                                                                .addComponent(previousBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                                                .addGap(90, 90, 90)
                                                                .addComponent(nextBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(78, 78, 78))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tweetPanelLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(tweetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(commentQtyLbl, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tweetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(likeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(commentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(retweetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(shareBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(retweetQtyLbl, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(likeQtyLbl, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addContainerGap(66, Short.MAX_VALUE))
        );

        return tweetPanel;
    }

    //        private void likeBtnActionPerformed(java.awt.event.ActionEvent evt) {
//            // TODO add your handling code here:
//        }
//
//        private void commentBtnActionPerformed(java.awt.event.ActionEvent evt) {
//            // TODO add your handling code here:
//        }
//
//        private void shareBtnActionPerformed(java.awt.event.ActionEvent evt) {
//            // TODO add your handling code here:
//        }
//
//        private void nextBtnActionPerformed(java.awt.event.ActionEvent evt) {
//            // TODO add your handling code here:
//        }
//
//        private void previousBtnActionPerformed(java.awt.event.ActionEvent evt) {
//            // TODO add your handling code here:
//        }
//
//        private void retweetBtnActionPerformed(java.awt.event.ActionEvent evt) {
//            // TODO add your handling code here:
//        }
//
//        private void nameLblMouseClicked(java.awt.event.MouseEvent evt) {
//                //this.dispose();
//                //Account account = page.getManager().searchByUserName(myJList.getSelectedValue());
//                //new InfoPageSwing(myJList.getSelectedValuesList(), account.getPersonalPage().getInfo());
//
//        }
//
//        private void nextBtn1ActionPerformed(java.awt.event.ActionEvent evt) {
//            // TODO add your handling code here:
//        }
//
//        private void commentQtyLblMouseClicked(java.awt.event.MouseEvent evt) {
//            // TODO add your handling code here:
//        }

//        private void likeQtyLblMouseClicked(java.awt.event.MouseEvent evt) {
//            // TODO add your handling code here:
//        }

    private static void nameLblMouseEntered(java.awt.event.MouseEvent evt, JLabel nameLbl) {
        nameLbl.setFont(new java.awt.Font("Lucida Grande", 1, 14));
    }

    private static void nameLblMouseExited(java.awt.event.MouseEvent evt, JLabel nameLbl) {
        nameLbl.setFont(new java.awt.Font("Lucida Grande", 0, 13));
    }

    private static void commentQtyLblMouseEntered(java.awt.event.MouseEvent evt, JLabel commentQtyLbl) {
        commentQtyLbl.setFont(new java.awt.Font("Lucida Grande", 1, 14));
    }

    private static void commentQtyLblMouseExited(java.awt.event.MouseEvent evt, JLabel commentQtyLbl) {
        commentQtyLbl.setFont(new java.awt.Font("Lucida Grande", 0, 13));
    }

    private static void likeQtyLblMouseEntered(java.awt.event.MouseEvent evt, JLabel likeQtyLbl) {
        likeQtyLbl.setFont(new java.awt.Font("Lucida Grande", 1, 14));
    }
    private static void likeQtyLblMouseExited(java.awt.event.MouseEvent evt, JLabel likeQtyLbl) {
        likeQtyLbl.setFont(new java.awt.Font("Lucida Grande", 0, 13));
    }
}

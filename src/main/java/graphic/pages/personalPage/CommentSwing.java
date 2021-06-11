package graphic.pages.personalPage;

import graphic.FooterPanel;
import graphic.pages.Swing;
import logic.pages.Page;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CommentSwing extends Swing {

    public CommentSwing(Page page) { //Tweet tweet
        super();
        //this.tweet = tweet;
        this.page = page;
        footerPanel = new FooterPanel(getManager(), page.getManager(), page.getAccount());
        addSwing(this);
        run();
    }

    @Override
    public void run() {
        myLogger.debug(PersonalPageSwing.class.getName(), "run",
                "Comments run for account \"" + page.getAccount().toString() + "\"");
        showGraphic();
    }

    @Override
    public void showGraphic() {
        JPanel jPanel1 = new javax.swing.JPanel();
        JPanel tweetPnl = new javax.swing.JPanel();
        JPanel commentsPnl = new javax.swing.JPanel();
        JPanel tweetPanel = new javax.swing.JPanel();


        JLabel commentsLbl = new javax.swing.JLabel();
        JLabel newCommentLbl = new javax.swing.JLabel();
        JLabel nameLbl = new javax.swing.JLabel();
        JLabel dateLbl = new javax.swing.JLabel();
        JLabel commentQtyLbl = new javax.swing.JLabel();
        JLabel likeQtyLbl = new javax.swing.JLabel();
        JLabel retweetQtyLbl = new javax.swing.JLabel();

        JButton sendBtn = new javax.swing.JButton();
        JButton nextBtn = new javax.swing.JButton();
        JButton previousBtn = new javax.swing.JButton();
        JButton commentBtn = new javax.swing.JButton();
        JButton retweetBtn = new javax.swing.JButton();
        JButton shareBtn = new javax.swing.JButton();
        JButton likeBtn = new javax.swing.JButton();


        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTextArea newCommentTxtArea = new javax.swing.JTextArea();

        JScrollPane jScrollPane3 = new javax.swing.JScrollPane();
        JTextArea tweetTxtArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tweetPnl.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout tweetPnlLayout = new javax.swing.GroupLayout(tweetPnl);
        tweetPnl.setLayout(tweetPnlLayout);
        tweetPnlLayout.setHorizontalGroup(
                tweetPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 569, Short.MAX_VALUE)
        );
        tweetPnlLayout.setVerticalGroup(
                tweetPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 226, Short.MAX_VALUE)
        );

        commentsLbl.setText("Comments");

        newCommentLbl.setText("New comment");

        newCommentTxtArea.setColumns(20);
        newCommentTxtArea.setRows(5);
        jScrollPane1.setViewportView(newCommentTxtArea);

        sendBtn.setText("send");
        sendBtn.setMaximumSize(new java.awt.Dimension(580, 580));
        sendBtn.setPreferredSize(new java.awt.Dimension(34, 34));
        sendBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //sendBtnActionPerformed(evt);
            }
        });

        nextBtn.setText(">>");
        nextBtn.setMaximumSize(new java.awt.Dimension(580, 580));
        nextBtn.setPreferredSize(new java.awt.Dimension(34, 34));
        nextBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //nextBtnActionPerformed(evt);
            }
        });

        previousBtn.setText("<<");
        previousBtn.setMaximumSize(new java.awt.Dimension(580, 580));
        previousBtn.setPreferredSize(new java.awt.Dimension(34, 34));
        previousBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //previousBtnActionPerformed(evt);
            }
        });

        tweetTxtArea.setEditable(false);
        tweetTxtArea.setColumns(20);
        tweetTxtArea.setRows(5);
        jScrollPane3.setViewportView(tweetTxtArea);

        nameLbl.setText("Name");
        nameLbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nameLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //nameLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                //nameLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                //nameLblMouseExited(evt);
            }
        });

        dateLbl.setText("Date");

        commentBtn.setIcon(new javax.swing.ImageIcon("src/main/resources/pictures/comments.png")); // comment
        commentBtn.setToolTipText("comment");
        commentBtn.setMaximumSize(new java.awt.Dimension(580, 580));
        commentBtn.setPreferredSize(new java.awt.Dimension(34, 34));
        commentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //commentBtnActionPerformed(evt);
            }
        });

        commentQtyLbl.setText("QtyLbl");
        commentQtyLbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        commentQtyLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //commentQtyLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                //commentQtyLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                //commentQtyLblMouseExited(evt);
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
                //likeQtyLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                //likeQtyLblMouseExited(evt);
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

        retweetQtyLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        retweetQtyLbl.setText("QtyLbl");

        shareBtn.setIcon(new javax.swing.ImageIcon("src/main/resources/pictures/paper-plane.png")); // share
        shareBtn.setToolTipText("share");
        shareBtn.setMaximumSize(new java.awt.Dimension(580, 580));
        shareBtn.setPreferredSize(new java.awt.Dimension(34, 34));
        shareBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //shareBtnActionPerformed(evt);
            }
        });

        likeBtn.setIcon(new javax.swing.ImageIcon("src/main/resources/pictures/green-like.png")); // like
        likeBtn.setToolTipText("like");
        likeBtn.setMaximumSize(new java.awt.Dimension(580, 580));
        likeBtn.setPreferredSize(new java.awt.Dimension(34, 34));
        likeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //likeBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tweetPanelLayout = new javax.swing.GroupLayout(tweetPanel);
        tweetPanel.setLayout(tweetPanelLayout);
        tweetPanelLayout.setHorizontalGroup(
                tweetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(tweetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                                .addGroup(tweetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                                                .addComponent(nameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(dateLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 15, Short.MAX_VALUE))))
        );
        tweetPanelLayout.setVerticalGroup(
                tweetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(tweetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(nameLbl)
                                        .addComponent(dateLbl))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(tweetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(tweetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(retweetBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(shareBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(likeBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(commentBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                                .addGap(16, 16, 16)
                                                .addComponent(commentQtyLbl))
                                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(likeQtyLbl))
                                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(retweetQtyLbl)))
                                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout commentsPnlLayout = new javax.swing.GroupLayout(commentsPnl);
        commentsPnl.setLayout(commentsPnlLayout);
        commentsPnlLayout.setHorizontalGroup(
                commentsPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(commentsPnlLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(previousBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(commentsPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(commentsPnlLayout.createSequentialGroup()
                                                .addGroup(commentsPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(newCommentLbl))
                                                .addGap(194, 194, 194))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, commentsPnlLayout.createSequentialGroup()
                                                .addComponent(tweetPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(nextBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(71, Short.MAX_VALUE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, commentsPnlLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(sendBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(96, 96, 96))
                        .addGroup(commentsPnlLayout.createSequentialGroup()
                                .addGap(229, 229, 229)
                                .addComponent(commentsLbl)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        commentsPnlLayout.setVerticalGroup(
                commentsPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(commentsPnlLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(commentsPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(sendBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(commentsPnlLayout.createSequentialGroup()
                                                .addComponent(commentsLbl)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(newCommentLbl)
                                                .addGap(18, 18, 18)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(commentsPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(commentsPnlLayout.createSequentialGroup()
                                                .addGap(42, 42, 42)
                                                .addComponent(tweetPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 93, Short.MAX_VALUE))
                                        .addGroup(commentsPnlLayout.createSequentialGroup()
                                                .addGap(159, 159, 159)
                                                .addComponent(nextBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, commentsPnlLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(previousBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(217, 217, 217))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(tweetPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(24, 24, 24)
                                                .addComponent(commentsPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(tweetPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(commentsPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(97, Short.MAX_VALUE))
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

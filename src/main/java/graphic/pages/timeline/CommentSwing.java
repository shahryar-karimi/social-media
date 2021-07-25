package graphic.pages.timeline;

import graphic.FooterPanel;
import graphic.pages.Swing;
import graphic.pages.personalPage.PersonalPageSwing;
import logic.Tweet;
import logic.pages.Page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CommentSwing extends Swing {
    private final Tweet tweet;

    JButton sendBtn = new JButton();
    JButton nextBtn = new JButton();
    JButton previousBtn = new JButton();
    JButton commentBtn = new JButton();
    JButton retweetBtn = new JButton();
    JButton shareBtn = new JButton();
    JButton likeBtn = new JButton();
    JButton[] buttons = {sendBtn, nextBtn, previousBtn, commentBtn, retweetBtn, shareBtn, likeBtn};

    JLabel commentsLbl = new JLabel();
    JLabel newCommentLbl = new JLabel();
    JLabel nameLbl = new JLabel();
    JLabel dateLbl = new JLabel();
    JLabel commentQtyLbl = new JLabel();
    JLabel likeQtyLbl = new JLabel();
    JLabel retweetQtyLbl = new JLabel();

    JScrollPane jScrollPane1 = new JScrollPane();
    JTextArea newCommentTxtArea = new JTextArea();

    JScrollPane jScrollPane3 = new JScrollPane();
    JTextArea tweetTxtArea = new JTextArea();

    public CommentSwing(Page page, Tweet tweet) { //Tweet tweet
        super();
        this.tweet = tweet;
        this.page = page;
        footerPanel = new FooterPanel(getManager(), page.getManager(), page.getAccount());
        for (JButton button : buttons)
            button.addActionListener(this);
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
        JPanel jPanel1 = new JPanel();
        JPanel tweetPnl = new JPanel();
        JPanel commentsPnl = new JPanel();
        JPanel tweetPanel = new JPanel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        tweetPnl.setBackground(new Color(204, 204, 204));

        GroupLayout tweetPnlLayout = new GroupLayout(tweetPnl);
        tweetPnl.setLayout(tweetPnlLayout);
        tweetPnlLayout.setHorizontalGroup(
                tweetPnlLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 569, Short.MAX_VALUE)
        );
        tweetPnlLayout.setVerticalGroup(
                tweetPnlLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
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

        commentBtn.setIcon(new ImageIcon("src/main/resources/pictures/comments.png")); // comment
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

        likeQtyLbl.setHorizontalAlignment(SwingConstants.CENTER);
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

        retweetBtn.setIcon(new ImageIcon("src/main/resources/pictures/retweet.png")); // retweet
        retweetBtn.setToolTipText("retweet");
        retweetBtn.setMaximumSize(new java.awt.Dimension(580, 580));
        retweetBtn.setPreferredSize(new java.awt.Dimension(34, 34));
        retweetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //retweetBtnActionPerformed(evt);
            }
        });

        retweetQtyLbl.setHorizontalAlignment(SwingConstants.CENTER);
        retweetQtyLbl.setText("QtyLbl");

        shareBtn.setIcon(new ImageIcon("src/main/resources/pictures/paper-plane.png")); // share
        shareBtn.setToolTipText("share");
        shareBtn.setMaximumSize(new java.awt.Dimension(580, 580));
        shareBtn.setPreferredSize(new java.awt.Dimension(34, 34));
        shareBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //shareBtnActionPerformed(evt);
            }
        });

        likeBtn.setIcon(new ImageIcon("src/main/resources/pictures/green-like.png")); // like
        likeBtn.setToolTipText("like");
        likeBtn.setMaximumSize(new java.awt.Dimension(580, 580));
        likeBtn.setPreferredSize(new java.awt.Dimension(34, 34));
        likeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //likeBtnActionPerformed(evt);
            }
        });

        GroupLayout tweetPanelLayout = new GroupLayout(tweetPanel);
        tweetPanel.setLayout(tweetPanelLayout);
        tweetPanelLayout.setHorizontalGroup(
                tweetPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(tweetPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                                .addComponent(commentBtn, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(commentQtyLbl)
                                                .addGap(18, 18, 18)
                                                .addComponent(likeBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(likeQtyLbl)
                                                .addGap(18, 18, 18)
                                                .addComponent(retweetBtn, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(retweetQtyLbl)
                                                .addGap(18, 18, 18)
                                                .addComponent(shareBtn, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                                .addGroup(tweetPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                                                .addComponent(nameLbl, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(dateLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 370, GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 15, Short.MAX_VALUE))))
        );
        tweetPanelLayout.setVerticalGroup(
                tweetPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(tweetPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(nameLbl)
                                        .addComponent(dateLbl))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                                .addGroup(tweetPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(tweetPanelLayout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(tweetPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(retweetBtn, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(shareBtn, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(likeBtn, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(commentBtn, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
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

        GroupLayout commentsPnlLayout = new GroupLayout(commentsPnl);
        commentsPnl.setLayout(commentsPnlLayout);
        commentsPnlLayout.setHorizontalGroup(
                commentsPnlLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(commentsPnlLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(previousBtn, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(commentsPnlLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(commentsPnlLayout.createSequentialGroup()
                                                .addGroup(commentsPnlLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(newCommentLbl))
                                                .addGap(194, 194, 194))
                                        .addGroup(GroupLayout.Alignment.LEADING, commentsPnlLayout.createSequentialGroup()
                                                .addComponent(tweetPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(nextBtn, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(71, Short.MAX_VALUE))))
                        .addGroup(GroupLayout.Alignment.TRAILING, commentsPnlLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(sendBtn, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
                                .addGap(96, 96, 96))
                        .addGroup(commentsPnlLayout.createSequentialGroup()
                                .addGap(229, 229, 229)
                                .addComponent(commentsLbl)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        commentsPnlLayout.setVerticalGroup(
                commentsPnlLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(commentsPnlLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(commentsPnlLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(sendBtn, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(commentsPnlLayout.createSequentialGroup()
                                                .addComponent(commentsLbl)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(newCommentLbl)
                                                .addGap(18, 18, 18)
                                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)))
                                .addGroup(commentsPnlLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(commentsPnlLayout.createSequentialGroup()
                                                .addGap(42, 42, 42)
                                                .addComponent(tweetPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 93, Short.MAX_VALUE))
                                        .addGroup(commentsPnlLayout.createSequentialGroup()
                                                .addGap(159, 159, 159)
                                                .addComponent(nextBtn, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, commentsPnlLayout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(previousBtn, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                                .addGap(217, 217, 217))))
        );

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(tweetPnl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(24, 24, 24)
                                                .addComponent(commentsPnl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(tweetPnl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(commentsPnl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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

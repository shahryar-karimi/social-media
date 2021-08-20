package view.pages.personalPage.info.view;

import model.Account;
import view.pages.Swing;
import view.pages.accountsListSwing.controller.ClickController;
import view.pages.accountsListSwing.listener.ClickListener;
import view.pages.accountsListSwing.view.AccountsListSwing;
import view.pages.personalPage.PersonalPageSwing;
import view.pages.personalPage.info.events.InfoEvent;
import view.pages.personalPage.info.listener.InfoListener;
import view.myPanels.footerPanel.controller.FooterPanelController;
import view.myPanels.footerPanel.listener.FooterPanelListener;
import view.myPanels.footerPanel.view.FooterPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class InfoPageSwing extends Swing {
    private final Account visitor;
    private final Account owner;
    private final JLabel followersQuantityLbl;
    private final JLabel followingsQuantityLbl;
    private final JLabel lastSeenLbl;
    private final JTextArea bioTxtArea;
    private final JTextArea tweetTxtArea;
    private final JButton followOrNotBtn;
    private final JButton addOrRemoveListBtn;
    private final JButton blockBtn;
    private final JButton sendMessageBtn;
    private final JButton reportBtn;
    private final JButton muteBtn;
    private final JButton followersBtn;
    private final JButton followingsBtn;
    private final JPanel jPanel1;
    private final JScrollPane jScrollPane1;
    private final JScrollPane jScrollPane3;

    public InfoPageSwing(InfoListener listener, Account visitor) {
        super();
        this.listener = listener;
        this.owner = getListener().getController().getPage().getAccount();
        this.visitor = visitor;
        footerPanel = new FooterPanel(new FooterPanelListener(new FooterPanelController(getListener().getController().getPage())));
        addSwing(this);
        this.followersQuantityLbl = new JLabel();
        this.followingsQuantityLbl = new JLabel();
        this.lastSeenLbl = new JLabel();
        this.bioTxtArea = new JTextArea();
        this.tweetTxtArea = new JTextArea();
        this.followOrNotBtn = new JButton();
        this.addOrRemoveListBtn = new JButton();
        this.blockBtn = new JButton();
        this.sendMessageBtn = new JButton();
        this.reportBtn = new JButton();
        this.muteBtn = new JButton();
        this.followersBtn = new JButton();
        this.followingsBtn = new JButton();
        JButton[] buttons = new JButton[]{followOrNotBtn, addOrRemoveListBtn, blockBtn, sendMessageBtn,
                reportBtn, muteBtn, followersBtn, followingsBtn};
        for (JButton button : buttons) button.addActionListener(this);
        jPanel1 = new JPanel();
        jScrollPane1 = new JScrollPane();
        jScrollPane3 = new JScrollPane();
        run();

    }

    @Override
    public void run() {
        this.setVisible(true);
        myLogger.debug(PersonalPageSwing.class.getName(), "run",
                "Personal page run for account \"" + this.owner.toString() + "\"");
        showGraphic();
    }

    @Override
    public void showGraphic() {
        JLabel nameLbl = new JLabel();
        JLabel userNameLbl = new JLabel();
        JLabel idLbl = new JLabel();
        JLabel bioLbl = new JLabel();

        if (visitor.getUserName().equals(owner.getUserName())) {
            followOrNotBtn.setEnabled(false);
            reportBtn.setEnabled(false);
            blockBtn.setEnabled(false);
            muteBtn.setEnabled(false);
        }

        if (visitor.isFollow(owner)) {
            followOrNotBtn.setText("unFollow");
        } else {
            followOrNotBtn.setText("Follow");
        }

        addOrRemoveListBtn.setText("Change to List");

        nameLbl.setText(owner.getFirstName() + " " + owner.getLastName());

        userNameLbl.setText(owner.getUserName());

        idLbl.setText(owner.getId() + "");

        lastSeenLbl.setText(owner.getLastSeen(visitor));

        bioTxtArea.setEditable(false);
        bioTxtArea.setColumns(20);
        bioTxtArea.setRows(5);
        bioTxtArea.setText(owner.getBio());
        jScrollPane1.setViewportView(bioTxtArea);

        bioLbl.setText("Bio : ");

        if (visitor.hasBlocked(owner)) {
            blockBtn.setText("unBlock");
        } else {
            blockBtn.setText("Block");
        }

        sendMessageBtn.setText("Send Message");

        if (owner.isPagePublic() || visitor.isFollow(owner) || owner.getUserName().equals(visitor.getUserName())) {
            tweetTxtArea.setText(owner.getPersonalPage().showMyTweets());
        } else {
            tweetTxtArea.setText("");
            tweetTxtArea.setEnabled(false);
        }
        tweetTxtArea.setEditable(false);
        tweetTxtArea.setColumns(20);
        tweetTxtArea.setRows(5);
        jScrollPane3.setViewportView(tweetTxtArea);

        reportBtn.setText("Report");

        followersQuantityLbl.setText(owner.getFollowers().size() + "");
        followersQuantityLbl.setHorizontalAlignment(SwingConstants.CENTER);
        followersQuantityLbl.setHorizontalTextPosition(SwingConstants.CENTER);

        followingsQuantityLbl.setText(owner.getFollowings().size() + "");
        followingsQuantityLbl.setHorizontalAlignment(SwingConstants.CENTER);
        followingsQuantityLbl.setHorizontalTextPosition(SwingConstants.CENTER);

        if (visitor.isMute(owner)) {
            muteBtn.setText("unMute");
        } else {
            muteBtn.setText("Mute");
        }


        followersBtn.setText("Followers");

        followingsBtn.setText("Followings");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 43, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(reportBtn, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(blockBtn, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(addOrRemoveListBtn, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(sendMessageBtn)
                                                        .addComponent(followOrNotBtn, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lastSeenLbl, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(idLbl, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(muteBtn, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(nameLbl, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(bioLbl)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(followersBtn, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
                                                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                                .addGap(6, 6, 6)
                                                                                                .addComponent(followersQuantityLbl, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)))
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                                .addGap(6, 6, 6)
                                                                                                .addComponent(followingsQuantityLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                        .addComponent(followingsBtn, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)))))))
                                        .addComponent(userNameLbl, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(userNameLbl)
                                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(followersBtn)
                                                .addComponent(followingsBtn)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(followingsQuantityLbl)
                                        .addComponent(followersQuantityLbl, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                                .addComponent(nameLbl)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(idLbl)
                                                        .addComponent(bioLbl))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lastSeenLbl)
                                                .addGap(47, 47, 47)))
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(followOrNotBtn)
                                                .addGap(18, 18, 18)
                                                .addComponent(sendMessageBtn)
                                                .addGap(18, 18, 18)
                                                .addComponent(addOrRemoveListBtn)
                                                .addGap(18, 18, 18)
                                                .addComponent(reportBtn)
                                                .addGap(18, 18, 18)
                                                .addComponent(blockBtn)
                                                .addGap(18, 18, 18)
                                                .addComponent(muteBtn))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(24, 24, 24)
                                                .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 437, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(40, Short.MAX_VALUE))
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
                                .addContainerGap(102, Short.MAX_VALUE)
                                .addComponent(footerPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == followOrNotBtn) {
            sendEvent("change follow");
        } else if (e.getSource() == addOrRemoveListBtn) {
            sendEvent("change list");
        } else if (e.getSource() == blockBtn) {
            sendEvent("block");
        } else if (e.getSource() == sendMessageBtn) {
            if (sendEvent("send message")) dispose();
        } else if (e.getSource() == reportBtn) {
            sendEvent("report");
        } else if (e.getSource() == muteBtn) {
            sendEvent("mute");
        } else if (e.getSource() == followersBtn) {
            this.dispose();
            new AccountsListSwing(owner.getFollowers(), new ClickListener(new ClickController(getListener().getController().getPage())));
        } else if (e.getSource() == followingsBtn) {
            this.dispose();
            new AccountsListSwing(owner.getFollowings(), new ClickListener(new ClickController(getListener().getController().getPage())));
        }
        updateGraphic();
    }

    private boolean sendEvent(String work) {
        InfoEvent event = new InfoEvent(this, owner, visitor, work);
        String msg = listener.eventOccurred(event);
        if (msg != null) {
            JOptionPane.showMessageDialog(null, msg);
            return false;
        }
        return true;
    }

    @Override
    public void updateGraphic() {
        followersQuantityLbl.setText(owner.getFollowers().size() + "");
        followingsQuantityLbl.setText(owner.getFollowings().size() + "");
        lastSeenLbl.setText(owner.getLastSeen(visitor));
        if (visitor.isFollow(owner)) {
            followOrNotBtn.setText("unFollow");
        } else {
            followOrNotBtn.setText("Follow");
        }
        if (visitor.hasBlocked(owner)) {
            blockBtn.setText("unBlock");
        } else {
            blockBtn.setText("Block");
        }
        if (visitor.isMute(owner)) {
            muteBtn.setText("unMute");
        } else {
            muteBtn.setText("Mute");
        }
    }
}

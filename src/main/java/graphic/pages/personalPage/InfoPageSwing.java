package graphic.pages.personalPage;

import graphic.FooterPanel;
import graphic.pages.Swing;
import logic.Account;
import logic.pages.personal.Info;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class InfoPageSwing extends Swing {

    private Account visitor;

    public InfoPageSwing(Info info, Account visitor) {
        super();
        this.page = info;
        this.visitor = visitor;
        footerPanel = new FooterPanel(getManager(), page.getManager(), page.getAccount());
        addSwing(this);
        run();

    }

    @Override
    public void run() {
        this.setVisible(true);
        myLogger.debug(PersonalPageSwing.class.getName(), "run",
                "Personal page run for account \"" + this.page.getAccount().toString() + "\"");
        showGraphic();
    }

    @Override
    public void showGraphic() {

        JPanel jPanel1 = new JPanel();

        JLabel nameLbl = new JLabel();
        JLabel userNameLbl = new JLabel();
        JLabel idLbl = new JLabel();
        JLabel lastSeenLbl = new JLabel();
        JLabel followersQuantityLbl = new JLabel();
        JLabel followingsQuantityLbl = new JLabel();
        JLabel bioLbl = new JLabel();

        JButton followOrNotBtn = new JButton();
        JButton addOrRemoveListBtn = new JButton();
        JButton blockBtn = new JButton();
        JButton sendMessageBtn = new JButton();
        JButton reportBtn1 = new JButton();
        JButton muteBtn = new JButton();
        JButton followersBtn = new JButton();
        JButton followingsBtn = new JButton();

        JScrollPane jScrollPane1 = new JScrollPane();
        JTextArea bioTxtArea = new JTextArea();


        JScrollPane jScrollPane3 = new JScrollPane();
        JTextArea tweetTxtArea = new JTextArea();

        followOrNotBtn.setText("Follow");

        addOrRemoveListBtn.setText("Add to List");

        nameLbl.setText(page.getAccount().getFirstName() + " " + page.getAccount().getLastName());

        userNameLbl.setText(page.getAccount().getUserName());

        idLbl.setText(page.getAccount().getId());

        lastSeenLbl.setText(page.getAccount().getLastSeen(visitor));

        bioTxtArea.setEditable(false);
        bioTxtArea.setColumns(20);
        bioTxtArea.setRows(5);
        jScrollPane1.setViewportView(bioTxtArea);

        bioLbl.setText("Bio : ");

        blockBtn.setText("Block");

        sendMessageBtn.setText("Send Message");

        tweetTxtArea.setEditable(false);
        tweetTxtArea.setColumns(20);
        tweetTxtArea.setRows(5);
        jScrollPane3.setViewportView(tweetTxtArea);

        reportBtn1.setText("Report");

        followersQuantityLbl.setText(page.getAccount().getFollowers().size() + "");
        followersQuantityLbl.setHorizontalAlignment(SwingConstants.CENTER);
        followersQuantityLbl.setHorizontalTextPosition(SwingConstants.CENTER);
        followersQuantityLbl.setText("QtyFollowers");

        followingsQuantityLbl.setText(page.getAccount().getFollowings().size() + "");
        followingsQuantityLbl.setHorizontalAlignment(SwingConstants.CENTER);
        followingsQuantityLbl.setHorizontalTextPosition(SwingConstants.CENTER);
        followingsQuantityLbl.setText("QtyFollowing");

        muteBtn.setText("Mute");

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
                                                        .addComponent(reportBtn1, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(blockBtn, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(addOrRemoveListBtn, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(sendMessageBtn)
                                                        .addComponent(followOrNotBtn, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lastSeenLbl, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(idLbl, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
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
                                                .addComponent(reportBtn1)
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

    }
}

package graphic.pages.personalPage;

import graphic.FooterPanel;
import graphic.pages.Swing;
import logic.pages.personal.Info;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class InfoPageSwing extends Swing {

//    private List<String> selectedValuesList;

    public InfoPageSwing(List<String> selectedValuesList, Info info) {
        super();
        this.page = info;
        footerPanel = new FooterPanel(getManager(), page.getManager(), page.getAccount());
//        this.selectedValuesList = selectedValuesList;
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
        JButton followOrNotBtn = new JButton();
        JButton addOrRemoveListBtn = new JButton();
        JLabel nameLbl = new JLabel();
        JLabel userNameLbl = new JLabel();
        JLabel idLbl = new JLabel();
        JLabel lastSeenLbl = new JLabel();
        JScrollPane jScrollPane1 = new JScrollPane();
        JTextArea bioTxtArea = new JTextArea();
        JLabel bioLbl = new JLabel();
        JButton blockBtn = new JButton();
        JButton sendMessageBtn = new JButton();
        JScrollPane jScrollPane3 = new JScrollPane();
        JTextArea tweetTxtArea = new JTextArea();
        JButton reportBtn1 = new JButton();
        JLabel followersLbl = new JLabel();
        JLabel followersQuantityLbl = new JLabel();
        JLabel followingsLbl = new JLabel();
        JLabel followingsQuantityLbl = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        followOrNotBtn.setText("Follow");

        addOrRemoveListBtn.setText("Add to List");

        nameLbl.setText("Name");

        userNameLbl.setText("UserName");

        idLbl.setText("id");

        lastSeenLbl.setText("LastSeen");

        bioTxtArea.setColumns(20);
        bioTxtArea.setRows(5);
        jScrollPane1.setViewportView(bioTxtArea);

        bioLbl.setText("Bio : ");

        blockBtn.setText("Block");

        sendMessageBtn.setText("Send Message");

        tweetTxtArea.setColumns(20);
        tweetTxtArea.setRows(5);
        jScrollPane3.setViewportView(tweetTxtArea);

        reportBtn1.setText("Report");

        followersLbl.setText("followers      ");

        followersQuantityLbl.setText("Qtyfollowers      ");

        followingsLbl.setText("Followings");

        followingsQuantityLbl.setText("QtyFollowing");

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
                                                        .addComponent(idLbl, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
                                                .addGap(51, 51, 51)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(bioLbl)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(followersLbl)
                                                                                        .addComponent(followersQuantityLbl))
                                                                                .addGap(43, 43, 43)
                                                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(followingsQuantityLbl)
                                                                                        .addComponent(followingsLbl)))))))
                                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                .addComponent(userNameLbl, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(nameLbl, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
                                                        .addGap(125, 125, 125))))
                                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(userNameLbl)
                                        .addComponent(followersLbl)
                                        .addComponent(followingsLbl))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(followersQuantityLbl)
                                        .addComponent(followingsQuantityLbl))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                                .addComponent(blockBtn))
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

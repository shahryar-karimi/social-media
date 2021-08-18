package view.pages.personalPage;


import model.pages.personal.PersonalPage;
import utility.AppProperties;
import view.pages.Swing;
import view.pages.accountsListSwing.controller.ClickController;
import view.pages.accountsListSwing.listener.ClickListener;
import view.pages.accountsListSwing.view.AccountsListSwing;
import view.pages.personalPage.creatingList.CreatingListFrame;
import view.pages.personalPage.creatingList.controller.CreateListController;
import view.pages.personalPage.creatingList.listener.CreateListListener;
import view.pages.personalPage.editProfile.controller.EditProfileController;
import view.pages.personalPage.editProfile.listener.EditProfileListener;
import view.pages.personalPage.editProfile.view.EditProfilePageSwing;
import view.pages.personalPage.event.SendBtnEvent;
import view.pages.personalPage.listener.ProfileListener;
import view.pages.personalPage.notification.controller.NotificationController;
import view.pages.personalPage.notification.listener.NotificationListener;
import view.pages.personalPage.notification.view.NotificationSwing;
import view.panels.footerPanel.controller.FooterPanelController;
import view.panels.footerPanel.listener.FooterPanelListener;
import view.panels.footerPanel.view.FooterPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PersonalPageSwing extends Swing {
    private final JButton[] buttons = new JButton[]{
            new JButton(AppProperties.getInstance().getProperty("edit-profile")),
            new JButton(AppProperties.getInstance().getProperty("my-followings")),
            new JButton(AppProperties.getInstance().getProperty("my-followers")),
            new JButton(AppProperties.getInstance().getProperty("my-black-list")),
            new JButton(AppProperties.getInstance().getProperty("notifications")),
            new JButton(AppProperties.getInstance().getProperty("create-list"))
    };
    private final JButton sendBtn = new JButton(AppProperties.getInstance().getProperty("send"));

    private final JPanel jPanel1 = new JPanel();
    private final JPanel jPanel2 = new JPanel();
    private final JPanel jPanel3 = new JPanel();
    private final JScrollPane jScrollPane1 = new JScrollPane();
    private final JScrollPane jScrollPane4 = new JScrollPane();
    private final JTextArea newTweetTxt = new JTextArea();
    private final JTextArea myTweetsView = new JTextArea();
    private final JLabel jLabel1 = new JLabel();

    public PersonalPageSwing(ProfileListener listener) {
        super();
        this.listener = listener;
        footerPanel = new FooterPanel(new FooterPanelListener(new FooterPanelController(getPage())));
        for (JButton button : buttons)
            button.addActionListener(this);
        addSwing(this);
        run();
    }

    @Override
    public void run() {
        myLogger.debug(PersonalPageSwing.class.getName(), "run",
                "Personal page run for account \"" + getPage().getAccount().toString() + "\"");
        showGraphic();
    }

    @Override
    public void showGraphic() {

        sendBtn.addActionListener(this);
        newTweetTxt.setColumns(20);
        newTweetTxt.setRows(5);
        jScrollPane1.setViewportView(newTweetTxt);


        myTweetsView.setColumns(20);
        myTweetsView.setRows(5);
        myTweetsView.setEditable(false);
        jScrollPane4.setViewportView(myTweetsView);
        myTweetsView.setText(((PersonalPage) getPage()).showMyTweets());


        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sendBtn)
                                .addContainerGap(133, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(sendBtn)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jScrollPane1)
        );

        jPanel3.setBackground(new Color(200, 200, 200));

        jLabel1.setText("My Tweets");

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jScrollPane4, GroupLayout.PREFERRED_SIZE, 397, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 73, Short.MAX_VALUE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane4, GroupLayout.PREFERRED_SIZE, 526, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(buttons[0], GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(buttons[1], GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(buttons[2], GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(buttons[3], GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(buttons[4], GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(buttons[5], GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, 100))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(buttons[0], GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(buttons[1], GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(buttons[2], GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jPanel2, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(buttons[3], GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(buttons[4], GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(buttons[5], GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(8, Short.MAX_VALUE))
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
    public void updateGraphic() {
        myTweetsView.setText(((PersonalPage) getPage()).showMyTweets());
        newTweetTxt.setText("");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttons[0]) {
            this.dispose();
            new EditProfilePageSwing(new EditProfileListener(new EditProfileController((PersonalPage) getPage())));
        } else if (e.getSource() == buttons[1]) {
            this.dispose();
            new AccountsListSwing(((PersonalPage) getPage()).myFollowings(), new ClickListener(new ClickController(getPage())));
        } else if (e.getSource() == buttons[2]) {
            this.dispose();
            new AccountsListSwing(((PersonalPage) getPage()).myFollowers(), new ClickListener(new ClickController(getPage())));
        } else if (e.getSource() == buttons[3]) {
            this.dispose();
            new AccountsListSwing(((PersonalPage) getPage()).myBlackList(), new ClickListener(new ClickController(getPage())));
        } else if (e.getSource() == buttons[4]) { // notification
            this.dispose();
            new NotificationSwing(new NotificationListener(new NotificationController(((PersonalPage) getPage()).getNotification())));
        } else if (e.getSource() == buttons[5]) { // create list
            new CreatingListFrame(new CreateListListener(new CreateListController((PersonalPage) getPage())));
        } else if (e.getSource() == sendBtn) {
            String newTweetTxt = this.newTweetTxt.getText();
            if (newTweetTxt != null && !newTweetTxt.isBlank()) {
                SendBtnEvent event = new SendBtnEvent(this, newTweetTxt);
                listener.eventOccurred(event);
            }
            myTweetsView.setText(((PersonalPage) getPage()).showMyTweets());
            this.newTweetTxt.setText("");
        }
    }
}

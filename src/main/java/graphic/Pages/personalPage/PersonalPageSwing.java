package graphic.Pages.personalPage;


import graphic.MyFrame;
import graphic.Pages.Swing;
import logic.Singleton;
import logic.Tweet;
import logic.pages.personal.PersonalPage;
import utility.AppProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PersonalPageSwing extends Swing {
    private final PersonalPage personalPage;

    Tweet newTweet = new Tweet();
    private final JButton[] buttons = new JButton[]{
            new JButton(AppProperties.getInstance().getProperty("edit-profile")),
            new JButton(AppProperties.getInstance().getProperty("my-followings")),
            new JButton(AppProperties.getInstance().getProperty("my-followers")),
            new JButton(AppProperties.getInstance().getProperty("my-black-list")),
            new JButton(AppProperties.getInstance().getProperty("notifications")),
            new JButton(AppProperties.getInstance().getProperty("create-list")),
            new JButton(AppProperties.getInstance().getProperty("back")),
            new JButton(AppProperties.getInstance().getProperty("Quit")),
            new JButton(AppProperties.getInstance().getProperty("Exit"))
    };
    private final JButton sendBtn = new JButton(AppProperties.getInstance().getProperty("send"));


    private final JPanel jPanel1 = new JPanel();
    private final JPanel jPanel2 = new JPanel();
    private final JPanel jPanel3 = new JPanel();
    private final JScrollPane jScrollPane1 = new JScrollPane();
    private final JScrollPane jScrollPane4 = new JScrollPane();
    private final JTextArea jTextArea1 = new JTextArea();
    private final JTextArea jTextArea4 = new JTextArea();
//    private final ScrollPane scrollPane1 = new ScrollPane();
//    private final Scrollbar scrollbar1 = new Scrollbar();
//    private final TextArea textArea1 = new TextArea();
//    private final TextArea textArea2 = new TextArea();
    private final JLabel jLabel1 = new JLabel();

    public PersonalPageSwing(PersonalPage personalPage) {
        super();
        this.personalPage = personalPage;
        for (JButton button : buttons)
            button.addActionListener(this);
    }


    @Override
    public void run() {
        frame = new MyFrame();
        myLogger.debug(PersonalPageSwing.class.getName(), "run",
                "Personal page run for account \"" + personalPage.getAccount().toString() + "\"");
//        System.out.println(PersonalPage.showPage());
        showGraphic();

    }

    @Override
    public void showGraphic() {

        if (frame != null) frame.dispose();

        sendBtn.addActionListener(this);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);


        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jTextArea4.setEditable(false);
        jScrollPane4.setViewportView(jTextArea4);
        jTextArea4.append(personalPage.showMyTweets());


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
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                                        .addComponent(buttons[5], GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(buttons[6], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(buttons[7], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(buttons[8], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                                                .addComponent(buttons[5], GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(buttons[6], GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(buttons[7], GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(buttons[8], GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(8, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 580, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 7, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );


        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttons[0]) {
            frame.dispose();
            EditProfilePageSwing editProfilePageSwing = new EditProfilePageSwing(personalPage);
            editProfilePageSwing.run();
        } else if (e.getSource() == buttons[1]) {
            System.out.println(personalPage.showMyTweets());
        } else if (e.getSource() == buttons[2]) {
        } else if (e.getSource() == buttons[3]) {
        } else if (e.getSource() == buttons[4]) {
        } else if (e.getSource() == buttons[5]) {
        } else if (e.getSource() == buttons[6]) {  //Back
            this.frame.setVisible(false);
        } else if (e.getSource() == buttons[7]) { //Quit
            personalPage.getAccount().setOnline(false);
            Singleton.save(personalPage.getManager());
            frame.dispose();
            personalPage.getManager().goToLoginPage();
        } else if (e.getSource() == buttons[8]) { //exit
            personalPage.getAccount().setOnline(false);
            Singleton.save(personalPage.getManager());
            System.exit(0);
        } else if (e.getSource() == sendBtn) {
            String newTweetTxt = jTextArea1.getText();
            if (newTweetTxt != null && !newTweetTxt.isBlank()) {
                newTweet = personalPage.writeNewTweet(newTweetTxt);
                personalPage.sendingATweet(newTweet, true);
            }
            jTextArea4.setText("");
            jTextArea4.append(personalPage.showMyTweets());
            jTextArea1.setText("");
        }
    }
}

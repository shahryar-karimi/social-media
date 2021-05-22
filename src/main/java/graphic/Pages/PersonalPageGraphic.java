package graphic.Pages;

import graphic.MyFrame;
import logic.Singleton;
import logic.pages.personal.PersonalPage;
import utility.AppProperties;

import   javax.swing.*;
import   javax.swing.border.EmptyBorder;
import   javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonalPageGraphic extends  Swing implements ActionListener {

        private final PersonalPage personalPage;
        private JScrollPane jScrollPane1;
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
        private final JLabel label = new JLabel(AppProperties.getInstance().getProperty("personal-page:"));

        public PersonalPageGraphic(PersonalPage personalPage) {
            super();
            this.personalPage = personalPage;
            for (JButton button : buttons)
                button.addActionListener(this);
        }


        @Override
        public void run() {
            myLogger.debug(PersonalPageSwing.class.getName(), "run",
                    "Personal page run for account \"" + personalPage.getAccount().toString() + "\"");
            System.out.println(PersonalPage.showPage());
            processShowMenuPageFrame();

        }

    @Override
    public void showGraphic() {

    }

    private void processShowMenuPageFrame() {
            label.setFocusable(false);
            label.setFont(new Font("MV Boli", Font.PLAIN, 25));
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setVerticalTextPosition(JLabel.CENTER);


            for (JButton button : buttons) {
                button.setFocusable(false);
                button.setFont(new Font("MV Boli", Font.PLAIN, 25));
            }

            if (frame != null) frame.dispose();

            JPanel menuPanel = new JPanel();
            menuPanel.setBackground(Color.BLUE);
            menuPanel.add(label);
            menuPanel.setLayout(new GridLayout(11, 1));

            for (JButton button : buttons) {
                menuPanel.add(button);
            }


            //frame.pack();

            JLabel myTweetLabel = new JLabel("New Tweets");
            myTweetLabel.setFont(new Font("MV Boli", Font.PLAIN, 18));
            myTweetLabel.setForeground(Color.GREEN);
            myTweetLabel.setBackground(Color.black);
            myTweetLabel.setOpaque(true);
            myTweetLabel.setVerticalAlignment(JLabel.TOP);

            JButton newTweetBtn = new JButton();


            JPanel myTweetPanel = new JPanel();
            myTweetPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
            myTweetPanel.setBounds(10, 10, 370, 200);
            myTweetPanel.setLayout(null);
            myTweetPanel.add(myTweetLabel);
            myTweetPanel.add(newTweetBtn);
            myTweetPanel.setBorder(LineBorder.createBlackLineBorder());

            JPanel contentPane = new JPanel();
            contentPane.setForeground(Color.GREEN);
            contentPane.setBackground(Color.BLACK);
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            contentPane.setBounds(10, 220, 370, 490);
            JScrollPane jScrollPane = new JScrollPane(contentPane,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            contentPane.setLayout(null);
            contentPane.setBorder(LineBorder.createBlackLineBorder());

            JTextArea textArea = new JTextArea();
            textArea.setLineWrap(true);
            textArea.setEditable(false);
            textArea.setBackground(Color.WHITE);
            textArea.setFont(new Font("MV Boli", Font.PLAIN, 13));
            textArea.setForeground(Color.GREEN);
            textArea.setBounds(10, 10, 350, 490);
            textArea.append(personalPage.showMyTweets());
            contentPane.add(textArea);
            JScrollPane jScrollText = new JScrollPane(contentPane,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            jScrollText.setPreferredSize(new Dimension(600, 600));

            JPanel mainPanel = new JPanel();
            mainPanel.setForeground(Color.GREEN);
            mainPanel.setBackground(Color.white);
            mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
            mainPanel.setLayout(null);
            mainPanel.add(contentPane);


            frame.setLayout(new BorderLayout());
            frame.add(menuPanel, BorderLayout.WEST);
            frame.add(mainPanel);
            frame.setVisible(true);

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttons[0]) {
                frame.dispose();
            } else if (e.getSource() == buttons[1]) {
                System.out.println(personalPage.showMyTweets());
            } else if (e.getSource() == buttons[2]) {
            } else if (e.getSource() == buttons[3]) {
            } else if (e.getSource() == buttons[4]) {
            } else if (e.getSource() == buttons[5]) {
            } else if (e.getSource() == buttons[6]) {
            } else if (e.getSource() == buttons[7]) {
                personalPage.getAccount().setOnline(false);
                Singleton.save(personalPage.getManager());
                frame.dispose();
                personalPage.getManager().goToLoginPage();
            } else if (e.getSource() == buttons[8]) {
                personalPage.getAccount().setOnline(false);
                Singleton.save(personalPage.getManager());
                System.exit(0);
            }
        }
    }
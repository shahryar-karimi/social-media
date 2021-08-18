package view.tweet.newTweetPanel.view;

import javax.swing.*;

public class NewTweetPanel extends JPanel {
    private final JScrollPane scrollPane;
    private final JTextArea tweetsText;
    private final JButton send;
    private final JLabel newTweet;

    public NewTweetPanel() {
        scrollPane = new JScrollPane();
        tweetsText = new JTextArea();
        newTweet = new JLabel();
        send = new JButton();
    }

    public void showGraphic() {
        tweetsText.setColumns(20);
        tweetsText.setRows(5);
        scrollPane.setViewportView(tweetsText);

        newTweet.setText("New Tweet:");

        send.setText("send");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(newTweet)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(9, 9, 9)
                                                .addComponent(send, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(send)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(newTweet)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    public void updateGraphic() {
        tweetsText.setText("");
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public JTextArea getTweetsText() {
        return tweetsText;
    }

    public JButton getSend() {
        return send;
    }

    public JLabel getNewTweet() {
        return newTweet;
    }
}

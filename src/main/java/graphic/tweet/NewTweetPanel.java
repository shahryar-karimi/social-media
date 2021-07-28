package graphic.tweet;

import javax.swing.*;

public class NewTweetPanel extends JPanel {
    private JScrollPane jScrollPane1;
    private JTextArea tweetsText;
    private JButton send;
    private JLabel newTweet;

    public NewTweetPanel() {
        jScrollPane1 = new javax.swing.JScrollPane();
        tweetsText = new javax.swing.JTextArea();
        newTweet = new javax.swing.JLabel();
        send = new javax.swing.JButton();
    }

    public void showGraphic() {
        tweetsText.setColumns(20);
        tweetsText.setRows(5);
        jScrollPane1.setViewportView(tweetsText);

        newTweet.setText("New Tweet:");

        send.setText("send");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(newTweet)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(9, 9, 9)
                                                .addComponent(send, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(send)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(newTweet)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    public void updateGraphic() {
        tweetsText.setText("");
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
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

package view.pages.timeline.view.comment.center;

import model.Tweet;

import javax.swing.*;

public class TopPanel extends JPanel {

    public TopPanel(Tweet tweet) {
        showGraphic(tweet);
    }

    private void showGraphic(Tweet tweet) {
        JLabel userName = new JLabel(tweet.getOwnerUserName());
        JLabel date = new JLabel(tweet.getTime());
        JTextArea textArea = new JTextArea();
        textArea.setText(tweet.getTweetText());
        textArea.setEditable(false);
        textArea.setColumns(20);
        textArea.setRows(5);
        JScrollPane scrollPane = new JScrollPane(textArea);

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(this);
        this.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
                topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(topPanelLayout.createSequentialGroup()
                                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(topPanelLayout.createSequentialGroup()
                                                .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(topPanelLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(scrollPane)))
                                .addContainerGap())
        );
        topPanelLayout.setVerticalGroup(
                topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(userName)
                                        .addComponent(date, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
    }
}

package view.pages.timeline.view.comment.center;

import model.pages.TimeLinePage;
import view.pages.timeline.view.MainPanel;
import view.pages.timeline.view.comment.CommentsSwing;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel {
    private final MainPanel mainPanel;
    private final TopPanel topPanel;
    private final CommentsSwing commentsSwing;

    public CenterPanel(CommentsSwing commentsSwing) {
        this.commentsSwing = commentsSwing;
        mainPanel = new MainPanel(commentsSwing, commentsSwing.getTopTweet());
        topPanel = new TopPanel(commentsSwing.getTopTweet());
        this.setPreferredSize(new Dimension(600, 700));
        showGraphic();
    }

    private void showGraphic() {
        mainPanel.getHeader().setText("Comments");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(152, 152, 152)
                                                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(29, 29, 29)
                                                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(72, Short.MAX_VALUE))
        );
    }

    public void updateGraphic() {
        mainPanel.setTimeLine((TimeLinePage) commentsSwing.getListener().getController().getPage());
        mainPanel.updateGraphic();
    }
}

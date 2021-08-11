package graphic.pages.timeline;

import logic.pages.TimeLinePage;

import javax.swing.*;
import java.awt.*;

public class TimeLinePanel extends JPanel {
    private MainPanel mainPanel;
    private TimeLineSwing timeLineSwing;

    public TimeLinePanel(TimeLineSwing timeLineSwing) {
        this.timeLineSwing = timeLineSwing;
        mainPanel = new MainPanel(timeLineSwing);
        showGraphic();
    }

    private void showGraphic() {
        this.setPreferredSize(new Dimension(600, 700));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(202, Short.MAX_VALUE))
        );
    }

    public void updateGraphic() {
        mainPanel.setTimeLine((TimeLinePage) timeLineSwing.getPage());
        mainPanel.updateGraphic();
    }
}

package view.pages.timeline.view;

import javax.swing.*;
import java.awt.*;

public class TimeLinePanel extends JPanel {
    private final MainPanel mainPanel;

    public TimeLinePanel(TimeLineSwing timeLineSwing) {
        mainPanel = new MainPanel(timeLineSwing);
        showGraphic();
    }

    private void showGraphic() {
        this.setPreferredSize(new Dimension(600, 700));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(202, Short.MAX_VALUE))
        );
    }

    public void updateGraphic() {
        mainPanel.updateGraphic();
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }
}

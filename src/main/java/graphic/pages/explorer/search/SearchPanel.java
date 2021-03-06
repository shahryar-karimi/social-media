package graphic.pages.explorer.search;

import graphic.pages.explorer.ExplorerSwing;
import logic.pages.ExplorerPage;

import javax.swing.*;
import java.awt.*;

public class SearchPanel extends JPanel{
    private MainPanel mainPanel;
    private JLabel label;
//    private ExplorerPage explorerPage;
    private ExplorerSwing explorerSwing;
    public SearchPanel(ExplorerSwing explorerSwing) {
        this.explorerSwing = explorerSwing;
        showGraphic();
    }

    public void showGraphic() {
        mainPanel = new MainPanel(explorerSwing.getPage().getManager().getAccounts());
        label = new javax.swing.JLabel();

        label.setFont(new java.awt.Font("Lucida Grande", Font.BOLD, 18)); // NOI18N
        label.setText("Search");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(272, 272, 272)
                                                .addComponent(label)))
                                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(label)
                                .addGap(61, 61, 61)
                                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(231, Short.MAX_VALUE))
        );
    }

    public void updateGraphic() {
        mainPanel.updateGraphic();
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }
}

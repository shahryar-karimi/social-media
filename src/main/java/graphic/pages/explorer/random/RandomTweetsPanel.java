package graphic.pages.explorer.random;

import logic.pages.ExplorerPage;
import utility.ImageLoader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RandomTweetsPanel extends JPanel {
    private MainPanel mainPanel;
    private ExplorerPage explorerPage;
    private JButton searchBtn;
    private JButton refresh;

    public RandomTweetsPanel(ExplorerPage explorerPage) {
        this.explorerPage = explorerPage;
        this.mainPanel = new MainPanel(explorerPage);
        showGraphic();
    }

    public void showGraphic() {
        mainPanel = new MainPanel(explorerPage);
        searchBtn = new JButton();
        refresh = new JButton();
        refresh.setIcon(ImageLoader.getOthers().get("refresh"));

        mainPanel.setPreferredSize(new java.awt.Dimension(536, 323));

        refresh.addActionListener(e -> {
            mainPanel.refresh();
        });

        searchBtn.setText("search");
        searchBtn.setFocusable(false);
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(32, 32, 32)
                                                .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 26, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(searchBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(refresh, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(searchBtn)
                                        .addComponent(refresh))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(347, Short.MAX_VALUE))
        );
    }

    public void updateGraphic() {
        mainPanel.updateGraphic();
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getSearchBtn() {
        return searchBtn;
    }
}

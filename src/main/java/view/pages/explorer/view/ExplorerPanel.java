package view.pages.explorer.view;

import view.pages.explorer.view.random.RandomTweetsPanel;
import view.pages.explorer.view.search.SearchPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExplorerPanel extends JPanel implements ActionListener {
    private final CardLayout cardLayout;
    private RandomTweetsPanel randomTweetsPanel;
    private SearchPanel searchPanel;

    public ExplorerPanel(ExplorerSwing explorerSwing) {
        this.setBounds(0, 0, 600, 800);
        this.cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        showGraphic(explorerSwing);
    }

    public void showGraphic(ExplorerSwing explorerSwing) {
        randomTweetsPanel = new RandomTweetsPanel(explorerSwing);
        searchPanel = new SearchPanel(explorerSwing);
        this.add(randomTweetsPanel, "random tweets");
        this.add(searchPanel, "search");
        addAction();
        cardLayout.show(this, "random tweets");
    }

    private void addAction() {
        searchPanel.getMainPanel().getCancel().addActionListener(this);
        randomTweetsPanel.getSearchBtn().addActionListener(this);
    }

    public void updateGraphic() {
        searchPanel.updateGraphic();
        randomTweetsPanel.updateGraphic();
    }

    public RandomTweetsPanel getRandomTweetsPanel() {
        return randomTweetsPanel;
    }

    public SearchPanel getSearchPanel() {
        return searchPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchPanel.getMainPanel().getCancel())
            cardLayout.show(this, "random tweets");
        else if (e.getSource() == randomTweetsPanel.getSearchBtn())
            cardLayout.show(this, "search");
    }
}

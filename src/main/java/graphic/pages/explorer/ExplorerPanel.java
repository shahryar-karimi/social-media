package graphic.pages.explorer;

import graphic.pages.explorer.random.RandomTweetsPanel;
import graphic.pages.explorer.search.SearchPanel;
import logic.pages.ExplorerPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExplorerPanel extends JPanel implements ActionListener {
    private CardLayout cardLayout;
    private RandomTweetsPanel randomTweetsPanel;
    private SearchPanel searchPanel;
    private ExplorerPage explorerPage;

    public ExplorerPanel(ExplorerPage explorerPage) {
        this.explorerPage = explorerPage;
        this.setBounds(0, 0, 600, 800);
        this.cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        showGraphic();
    }

    public void showGraphic() {
        randomTweetsPanel = new RandomTweetsPanel(explorerPage);
        searchPanel = new SearchPanel(explorerPage);
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
        if (e.getSource() == searchPanel.getMainPanel().getCancel()) {
            cardLayout.show(this, "random tweets");
        } else if (e.getSource() == randomTweetsPanel.getSearchBtn()) {
            cardLayout.show(this, "search");
        }
    }
}

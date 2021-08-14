package graphic.pages.explorer.random;

import graphic.pages.AccountsListSwing;
import graphic.pages.explorer.ExplorerSwing;
import graphic.pages.timeline.comment.CommentsSwing;
import graphic.tweet.TweetsPanel;
import logic.Account;
import logic.pages.ExplorerPage;
import logic.pages.TimeLinePage;
import utility.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class MainPanel extends JPanel implements ActionListener {
    private JButton nextBtn;
    private JButton previousBtn;
    private TweetsPanel tweetsPanel;
    private final ExplorerSwing explorerSwing;
    private final TimeLinePage timeLine;

    public MainPanel(ExplorerSwing explorerSwing) {
        this.explorerSwing = explorerSwing;
        timeLine = new TimeLinePage(explorerSwing.getPage().getAccount(), explorerSwing.getPage().getManager(), false);
        showGraphic();
    }

    private void initTweetsPanel() {
        tweetsPanel = new TweetsPanel(timeLine.getAccount(), timeLine.getCurrentTweet(), explorerSwing);
        tweetsPanel.setPreferredSize(new Dimension(428, 283));
    }

    public void showGraphic() {
        timeLine.setTweets(((ExplorerPage) explorerSwing.getPage()).getRandomTweets());
        initTweetsPanel();
        nextBtn = new JButton();
        previousBtn = new JButton();
        JLabel randomTweetLabel = new JLabel();

        nextBtn.setText(">>");
        nextBtn.addActionListener(this);

        previousBtn.setText("<<");
        previousBtn.addActionListener(this);

        randomTweetLabel.setFont(new Font("Lucida Grande", Font.BOLD, 18)); // NOI18N
        randomTweetLabel.setText("Random Tweets");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(previousBtn, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tweetsPanel, GroupLayout.PREFERRED_SIZE, 428, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nextBtn, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(197, 197, 197)
                                .addComponent(randomTweetLabel))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(randomTweetLabel)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tweetsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(130, 130, 130)
                                                .addComponent(nextBtn))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(130, 130, 130)
                                                .addComponent(previousBtn)))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        updateGraphic();
    }

    public void updateGraphic() {
        if (timeLine.getTweets().isEmpty()) {
            nextBtn.setEnabled(false);
            previousBtn.setEnabled(false);
        } else {
            nextBtn.setEnabled(true);
            previousBtn.setEnabled(true);
            if (timeLine.getIndexOfTweet() == 0) {
                previousBtn.setEnabled(false);
            } else if (timeLine.getIndexOfTweet() == (timeLine.getTweets().size() - 1)) {
                nextBtn.setEnabled(false);
            }
        }
        tweetsPanel.changeTweet(timeLine.getCurrentTweet());
    }

    public void refresh() {
        timeLine.setTweets(((ExplorerPage) explorerSwing.getPage()).getRandomTweets());
        updateGraphic();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextBtn) {
            String result = timeLine.goNextTweet(timeLine.getTweets(), timeLine.getIndexOfTweet());
            if (!result.isBlank())
                JOptionPane.showMessageDialog(null, result, "Next tweet", JOptionPane.ERROR_MESSAGE);
            updateGraphic();
        } else if (e.getSource() == previousBtn) {
            String result = timeLine.goPreviousTweet(timeLine.getTweets(), timeLine.getIndexOfTweet());
            if (!result.isBlank())
                JOptionPane.showMessageDialog(null, result, "Previous tweet", JOptionPane.ERROR_MESSAGE);
            updateGraphic();
        }
    }
}

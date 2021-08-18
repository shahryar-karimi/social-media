package view.pages.timeline.view.comment;

import model.Tweet;
import view.pages.Swing;
import view.pages.timeline.listener.TimeLineListener;
import view.pages.timeline.view.comment.center.CenterPanel;
import view.panels.footerPanel.controller.FooterPanelController;
import view.panels.footerPanel.listener.FooterPanelListener;
import view.panels.footerPanel.view.FooterPanel;

import java.awt.*;
import java.awt.event.ActionEvent;

public class CommentsSwing extends Swing {
    private final CenterPanel centerPanel;
    private final Tweet topTweet;

    public CommentsSwing(Tweet topTweet, TimeLineListener listener) {
        super();
        this.listener = listener;
        this.topTweet = topTweet;
        footerPanel = new FooterPanel(new FooterPanelListener(new FooterPanelController(getPage())));
        centerPanel = new CenterPanel(this);
        addSwing(this);
        run();
    }

    @Override
    public void run() {
        myLogger.debug(CommentsSwing.class.getName(), "run",
                "comments run for tweet \"" + topTweet + "\"");
        showGraphic();
    }

    @Override
    public void showGraphic() {
        updateGraphic();
        this.setLayout(new BorderLayout());
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(footerPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    @Override
    public void updateGraphic() {
        centerPanel.updateGraphic();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public Tweet getTopTweet() {
        return topTweet;
    }
}

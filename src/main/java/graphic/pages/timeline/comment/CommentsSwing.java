package graphic.pages.timeline.comment;

import graphic.FooterPanel;
import graphic.pages.Swing;
import graphic.pages.timeline.TimeLinePanel;
import graphic.pages.timeline.TimeLineSwing;
import graphic.pages.timeline.comment.center.CenterPanel;
import logic.Tweet;
import logic.pages.TimeLinePage;

import java.awt.*;
import java.awt.event.ActionEvent;

public class CommentsSwing extends Swing {
    private CenterPanel centerPanel;
    private Tweet topTweet;

    public CommentsSwing(TimeLinePage timeLinePage, Tweet topTweet) {
        super();
        this.topTweet = topTweet;
        this.page = timeLinePage;
        footerPanel = new FooterPanel(getManager(), page.getManager(), page.getAccount());
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

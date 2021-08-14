package graphic.pages.timeline;

import graphic.FooterPanel;
import graphic.pages.Swing;
import logic.pages.TimeLinePage;

import java.awt.*;
import java.awt.event.ActionEvent;

public class TimeLineSwing extends Swing {
    private TimeLinePanel timeLinePanel;

    public TimeLineSwing(TimeLinePage timeLinePage) {
        super();
        this.page = timeLinePage;
        timeLinePanel = new TimeLinePanel(this);
        footerPanel = new FooterPanel(getManager(), page.getManager(), page.getAccount());
        addSwing(this);
        run();
    }

    @Override
    public void run() {
        myLogger.debug(TimeLineSwing.class.getName(), "run",
                "Time line run for account \"" + page.getAccount().toString() + "\"");
        showGraphic();
    }

    @Override
    public void showGraphic() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(600, 800));
        this.add(timeLinePanel, BorderLayout.CENTER);
        this.add(footerPanel, BorderLayout.SOUTH);
        updateGraphic();
        this.setVisible(true);
    }

    public void updateGraphic() {
        timeLinePanel.updateGraphic();
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
}

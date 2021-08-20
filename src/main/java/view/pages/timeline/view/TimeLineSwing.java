package view.pages.timeline.view;

import model.pages.TimeLinePage;
import view.pages.Swing;
import view.pages.timeline.listener.TimeLineListener;
import view.myPanels.footerPanel.controller.FooterPanelController;
import view.myPanels.footerPanel.listener.FooterPanelListener;
import view.myPanels.footerPanel.view.FooterPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

public class TimeLineSwing extends Swing {
    private final TimeLinePanel timeLinePanel;

    public TimeLineSwing(TimeLineListener listener) {
        super();
        this.listener = listener;
        timeLinePanel = new TimeLinePanel(this);
        footerPanel = new FooterPanel(new FooterPanelListener(new FooterPanelController(getListener().getController().getPage())));
        addSwing(this);
        run();
    }

    @Override
    public void run() {
        myLogger.debug(TimeLineSwing.class.getName(), "run",
                "Time line run for account \"" + getListener().getController().getPage().getAccount().toString() + "\"");
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
        timeLinePanel.getMainPanel().setTimeLine((TimeLinePage) getListener().getController().getPage());
        timeLinePanel.updateGraphic();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}

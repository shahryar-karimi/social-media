package graphic.pages.timeline;

import graphic.FooterPanel;
import graphic.pages.Swing;
import graphic.pages.personalPage.PersonalPageSwing;
import logic.pages.TimeLinePage;

import java.awt.*;
import java.awt.event.ActionEvent;

public class TimeLineSwing2 extends Swing {
    private MainPanel mainPanel;

    public TimeLineSwing2(TimeLinePage timeLinePage) {
        super();
        this.page = timeLinePage;
        mainPanel = new MainPanel((TimeLinePage) page);
        footerPanel = new FooterPanel(getManager(), page.getManager(), page.getAccount());
        addSwing(this);
        run();
    }

    @Override
    public void run() {
        myLogger.debug(PersonalPageSwing.class.getName(), "run",
                "Time line run for account \"" + page.getAccount().toString() + "\"");
        showGraphic();
    }

    @Override
    public void showGraphic() {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(600, 800));
        mainPanel.setBounds(0, 0, 600, 700);
        footerPanel.setBounds(0, 700, 600, 100);
        this.add(mainPanel);
        this.add(footerPanel);
        updateGraphic();
        this.setVisible(true);
    }

    public void updateGraphic() {
        mainPanel.updatePage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
}

package graphic.pages.setting;

import graphic.FooterPanel;
import graphic.pages.Swing;
import logic.pages.SettingPage;

import java.awt.*;
import java.awt.event.ActionEvent;

public class SettingSwing extends Swing {
    private MainPanel mainPanel;

    public SettingSwing(SettingPage settingPage) {
        super();
        this.page = settingPage;
        mainPanel = new MainPanel(this);
        footerPanel = new FooterPanel(getManager(), page.getManager(), page.getAccount());
        addSwing(this);
        run();
    }

    @Override
    public void run() {
        myLogger.debug(SettingSwing.class.getName(), "run",
                "setting run for account \"" + page.getAccount().toString() + "\"");
        showGraphic();
    }

    @Override
    public void showGraphic() {
        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(footerPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    @Override
    public void updateGraphic() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

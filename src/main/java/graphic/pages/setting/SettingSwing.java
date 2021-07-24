package graphic.pages.setting;

import graphic.FooterPanel;
import graphic.pages.Swing;
import graphic.pages.personalPage.PersonalPageSwing;
import logic.pages.SettingPage;

import java.awt.event.ActionEvent;

public class SettingSwing extends Swing {

    public SettingSwing(SettingPage settingPage) {
        super();
        this.page = settingPage;
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
        //TODO
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

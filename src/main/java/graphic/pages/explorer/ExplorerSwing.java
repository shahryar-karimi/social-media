package graphic.pages.explorer;

import graphic.FooterPanel;
import graphic.pages.Swing;
import logic.pages.ExplorerPage;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ExplorerSwing extends Swing {

    public ExplorerSwing(ExplorerPage explorerPage) {
        super();
        this.page = explorerPage;
        footerPanel = new FooterPanel(getManager(), page.getManager(), page.getAccount());
        addSwing(this);
        run();
    }

    @Override
    public void run() {
        myLogger.debug(ExplorerSwing.class.getName(), "run",
                "explorer run for account \"" + page.getAccount().toString() + "\"");
        showGraphic();
    }

    @Override
    public void showGraphic() {

    }

    @Override
    public void updateGraphic() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

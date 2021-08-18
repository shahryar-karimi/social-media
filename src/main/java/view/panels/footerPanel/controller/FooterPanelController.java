package view.panels.footerPanel.controller;

import view.controller.MainGraphicController;
import model.pages.Page;

public class FooterPanelController extends MainGraphicController {
    public FooterPanelController(Page page) {
        super(page);
    }

    public String home() {
        page.getManager().home(page.getAccount());
        return null;
    }

    public String exit() {
        page.getManager().exit(page.getAccount());
        return null;
    }

    public String back() {
        if (page.getManager().getGraphicManager().getSwings().size() > 1)
            page.getManager().getGraphicManager().back();
        return null;
    }
}

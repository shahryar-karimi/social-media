package view.myPanels.footerPanel.controller;

import model.pages.Page;
import view.controller.MainGraphicController;

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

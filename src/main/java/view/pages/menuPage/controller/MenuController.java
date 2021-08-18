package view.pages.menuPage.controller;

import model.pages.MenuPage;
import view.controller.MainGraphicController;

public class MenuController extends MainGraphicController {

    public MenuController(MenuPage menuPage) {
        super(menuPage);
    }

    public void goToPersonalPage() {
        page.getManager().gotoPersonalPage(page.getAccount());
    }

    public void goToTimeLinePage() {
        page.getManager().goToTimeLinePage(page.getAccount());
    }

    public void goToExplorerPage() {
        page.getManager().goToExplorerPage(page.getAccount());
    }

    public void goToMessagesPage() {
        page.getManager().goToMessagesPage(page.getAccount());
    }

    public void goToSettingPage() {
        page.getManager().goToSettingPage(page.getAccount());
    }

    public void quit() {
        page.getManager().quit(page.getAccount());
    }

    public void exit() {
        page.getManager().exit(page.getAccount());
    }
}

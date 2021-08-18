package view.pages.menuPage.listener;

import event.Event;
import listener.FormListener;
import view.pages.menuPage.controller.MenuController;
import view.pages.menuPage.event.MenuEvent;

public class MenuListener extends FormListener {
    public MenuListener(MenuController controller) {
        this.controller = controller;
    }

    @Override
    public String eventOccurred(Event event) {
        MenuEvent menuEvent = (MenuEvent) event;
        switch (menuEvent.getWork()) {
            case "Personal" -> ((MenuController) controller).goToPersonalPage();
            case "Time line" -> ((MenuController) controller).goToTimeLinePage();
            case "Explorer" -> ((MenuController) controller).goToExplorerPage();
            case "Messages" -> ((MenuController) controller).goToMessagesPage();
            case "Setting" -> ((MenuController) controller).goToSettingPage();
            case "Quit" -> ((MenuController) controller).quit();
            case "Exit" -> ((MenuController) controller).exit();
        }
        return null;
    }
}

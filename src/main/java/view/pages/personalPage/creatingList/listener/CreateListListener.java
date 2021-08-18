package view.pages.personalPage.creatingList.listener;

import event.Event;
import listener.FormListener;
import view.pages.personalPage.creatingList.controller.CreateListController;
import view.pages.personalPage.creatingList.events.CreateListEvent;

public class CreateListListener extends FormListener {
    public CreateListListener(CreateListController controller) {
        this.controller = controller;
    }

    @Override
    public String eventOccurred(Event event) {
        CreateListEvent createListEvent = (CreateListEvent) event;
        return ((CreateListController) controller).create(createListEvent.getName(), createListEvent.getSelectedSet());
    }
}

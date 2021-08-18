package view.pages.personalPage.creatingList.events;

import event.Event;

import java.util.HashSet;

public class CreateListEvent extends Event {
    private final String name;
    private final HashSet<String> selectedSet;

    public CreateListEvent(Object source, String name, HashSet<String> selectedSet) {
        super(source);
        this.name = name;
        this.selectedSet = selectedSet;
    }

    public String getName() {
        return name;
    }

    public HashSet<String> getSelectedSet() {
        return selectedSet;
    }
}

package model.pages.personal;

import logic.Manager;
import model.Account;
import model.pages.Page;

import java.util.ArrayList;
import java.util.LinkedList;

public class Notification extends Page {
    private ArrayList<String> requests;
    private int indexOfCurrentRequest;
    private LinkedList<String> systemMessages;
    private int indexOfCurrentSystemMessage;

    public Notification(Account account, Manager manager) {
        super(account, manager);
        this.systemMessages = new LinkedList<>();
        this.requests = new ArrayList<>();
        this.indexOfCurrentRequest = -1;
        this.indexOfCurrentSystemMessage = -1;
    }

    public void addRequest(String request) {
        requests.add(request);
        setIndexOfCurrentRequest(requests.size() - 1);
    }

    public void addSystemMessage(String systemMessage) {
        systemMessages.add(systemMessage);
        setIndexOfCurrentSystemMessage(systemMessages.size() - 1);
    }

    public void removeRequest(int index) {
        if (index >= 0 && index < requests.size()) {
            requests.remove(index);
            if (indexOfCurrentRequest == requests.size()) indexOfCurrentRequest--;
        }
    }

    public void setIndexOfCurrentSystemMessage(int indexOfCurrentSystemMessage) {
        this.indexOfCurrentSystemMessage = indexOfCurrentSystemMessage;
    }

    public void setIndexOfCurrentRequest(int indexOfCurrentRequest) {
        this.indexOfCurrentRequest = indexOfCurrentRequest;
    }

    public ArrayList<String> getRequests() {
        return requests;
    }

    public LinkedList<String> getSystemMessages() {
        return systemMessages;
    }

    public void acceptRequest(Account requester) {
        String message = requester.follow(account);
        if (message.equals("You followed this page successfully!")) {
            removeRequest(indexOfCurrentRequest);
        }
    }

    public void declineRequest(boolean isMute, Account requester) {
        if (!isMute) {
            requester.getPersonalPage().getNotification().addSystemMessage("" +
                    "Your request from " + account + " has declined");
        }
        removeRequest(indexOfCurrentRequest);
    }
}

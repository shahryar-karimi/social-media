package model.pages.personal;

import model.Account;
import logic.Manager;
import model.pages.Page;

import java.util.ArrayList;
import java.util.LinkedList;

public class Notification extends Page {
    //each request = a user name for an account
    private ArrayList<String> requests;
    private int indexOfCurrentRequest;
    private LinkedList<String> systemMessages;
    private int indexOfCurrentSystemMessage;

    public Notification() {
    }

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

    public int getIndexOfCurrentRequest() {
        return indexOfCurrentRequest;
    }

    public int getIndexOfCurrentSystemMessage() {
        return indexOfCurrentSystemMessage;
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

    public void setRequests(ArrayList<String> requests) {
        this.requests = requests;
    }

    public LinkedList<String> getSystemMessages() {
        return systemMessages;
    }

    public void setSystemMessages(LinkedList<String> systemMessages) {
        this.systemMessages = systemMessages;
    }

    public String showPage() {
        return "1.requests\n" +
                "2.system messages\n" +
                "3.back\n" +
                "4.quit\n" +
                "5.exit";
    }

    public String showCurrentRequest() {
        return requests.get(indexOfCurrentRequest);
    }

    public String showCurrentSystemMessage() {
        return systemMessages.get(indexOfCurrentSystemMessage);
    }

    public void goNextRequest() {
        indexOfCurrentRequest++;
    }

    public void goNextSystemMessage() {
        indexOfCurrentSystemMessage++;
    }

    public void goPreviousRequest() {
        indexOfCurrentRequest--;
    }

    public void goPreviousSystemMessage() {
        indexOfCurrentSystemMessage--;
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

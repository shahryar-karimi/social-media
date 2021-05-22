package logic.pages.messenger;

import logic.Account;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    private String text;
    private String ownerUserName;
    private transient Account owner;
    private String time;
    private boolean isForward;
    private boolean isSeen;
    private int index;
    private boolean isSelected;

    public Message() {
    }

    public Message(String text, Account owner, boolean isForward) {
        this.text = text;
        this.owner = owner;
        this.isForward = isForward;
        this.isSeen = false;
        this.isSelected = false;
    }

    public Message(String text, Account owner) {
        this(text, owner, false);
    }

    public Message(Account owner) {
        this(null, owner);
    }

    public String getOwnerUserName() {
        return ownerUserName;
    }

    public void setOwnerUserName(String ownerUserName) {
        this.ownerUserName = ownerUserName;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        String result = "Message: \n    " +
                owner + ":\n\"" +
                text + "\"\n    " +
                "Details:\n    " +
                "time = " + time + "\n    " +
                "isSeen = " + isSeen + "\n    " +
                "id = " + index;
        if (isForward()) result = "This is forward m" + result.substring(1);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return getIndex() == message.getIndex() && getOwner().equals(message.getOwner());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOwner(), getIndex());
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTime() {
        this.time = LocalDateTime.now().toString().replace('T', ' ').substring(0, 19);
    }

    public void setForward(boolean forward) {
        isForward = forward;
    }

    public void setSeen(boolean seen) {
        this.isSeen = seen;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }

    public Account getOwner() {
        return owner;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public boolean isForward() {
        return isForward;
    }

    public int getIndex() {
        return index;
    }

    public boolean isSelected() {
        return isSelected;
    }
}

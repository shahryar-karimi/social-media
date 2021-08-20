package model.pages.messenger;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message implements Comparable<Message> {
    private String text;
    private String ownerUserName;
    private String senderUserName;
    private String time;
    private boolean isSeen;

    public Message() {
    }

    public Message(String text, String ownerUserName, String senderUserName) {
        this.text = text;
        this.ownerUserName = ownerUserName;
        this.senderUserName = senderUserName;
        this.isSeen = false;
    }

    public Message(String text, String ownerUserName) {
        this(text, ownerUserName, ownerUserName);
    }

    public String getOwnerUserName() {
        return ownerUserName;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        String result = "Message: \n    " +
                ownerUserName + ":\n\"" +
                text + "\"\n    " +
                "Details:\n    " +
                "time = " + time + "\n    " +
                "isSeen = " + isSeen;
        if (isForward()) result = "This is forward m" + result.substring(1);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return getText().equals(message.getText()) && getOwnerUserName().equals(message.getOwnerUserName()) && Objects.equals(getSenderUserName(), message.getSenderUserName()) && Objects.equals(getTime(), message.getTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getText(), getOwnerUserName(), getSenderUserName(), getTime());
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTime() {
        this.time = LocalDateTime.now().toString().replace('T', ' ').substring(0, 19);
    }

    public void setSeen(boolean seen) {
        this.isSeen = seen;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public boolean isForward() {
        return !senderUserName.equals(ownerUserName);
    }

    public String getSenderUserName() {
        return senderUserName;
    }

    @Override
    public int compareTo(Message another) {
        return time.compareTo(another.time);
    }

    public void setSenderUserName(String senderUserName) {
        this.senderUserName = senderUserName;
    }

    @Override
    public Message clone() {
        Message clone = new Message();
        clone.text = this.text;
        clone.ownerUserName = this.ownerUserName;
        clone.senderUserName = this.senderUserName;
        clone.time = this.time;
        clone.isSeen = false;
        return clone;
    }
}

package cLI.messengerCLI;

import cLI.ConsoleColors;
import logic.Account;
import cLI.CLI;
import logic.Manager;
import logic.Singleton;
import logic.pages.messenger.ChatRoom;
import logic.pages.messenger.Message;

import java.util.ArrayList;

public class ChatRoomCLI extends CLI {

    private final ChatRoom chatRoom;

    public ChatRoomCLI(ChatRoom chatRoom) {
        super();
        this.chatRoom = chatRoom;
    }

    private String chooseFromMenu() {
        System.out.println(ChatRoom.showCLIPage());
        System.out.println("Enter your command:");
        String result = scanner.nextLine();
        while (!result.equals("write message") && !result.equals("select message") &&
                !result.equals("next ten message") && !result.equals("previous ten message") &&
                !result.equals("show all messages") && !result.equals("back") &&
                !result.equals("exit")
        ) {
            System.err.println("Wrong input please try again!");
            System.out.println(ChatRoom.showCLIPage());
            result = scanner.nextLine();
        }
        return result;
    }

    @Override
    public void run() {
        String input;
        while (true) {
            System.out.println(chatRoom.showIndexTenPage());
            input = chooseFromMenu();
            if (input.equals("back")) {
                break;
            } else if (input.equals("exit")) {
                chatRoom.getAccount().setOnline(false);
                Singleton.save(chatRoom.getManager());
                System.exit(0);
            } else if (input.equals("select message")) {
                processSelectMessage();
            } else if (input.equals("write message")) {
                processSendingAMessage(processWriteNewMessage());
            } else if (input.equals("next ten message")) {
                processNextTenMessage();
            } else if (input.equals("previous ten message")) {
                processPreviousTenMessage();
            } else if (input.equals("show all messages")) {
                processShowAllMessage();
            }
        }
    }

    private void processPreviousTenMessage() {
        System.out.println(ConsoleColors.CYAN + chatRoom.previousTenMessage() + ConsoleColors.RESET);
    }

    private void processNextTenMessage() {
        System.out.println(ConsoleColors.CYAN + chatRoom.nextTenMessage() + ConsoleColors.RESET);
    }

    private void processShowAllMessage() {
        System.out.println(ConsoleColors.CYAN + chatRoom.showPage() + ConsoleColors.RESET);
    }

    private void processSelectMessage() {
        System.out.println("Enter messages id:");
        //todo check for put number
        int index = scanner.nextInt();
        chatRoom.selectMessage(index);
        if (chatRoom.getSelectedMessage() != null)
            doSelectedMessageMood(chatRoom.getSelectedMessage());
    }

    private void doSelectedMessageMood(Message message) {
        String input;
        input = chooseSelectedMoodMenu();
        if (input.equals("forward"))
            processForward(message);
        else if (input.equals("save message"))
            processSaveMessage(message);
        else if (input.equals("exit")) {
            chatRoom.deselectMessage();
            chatRoom.getAccount().setOnline(false);
            Singleton.save(chatRoom.getManager());
            System.exit(0);
        }
        chatRoom.deselectMessage();
    }

    private String chooseSelectedMoodMenu() {
        System.out.println("Enter what you want to do with this message:\n" +
                "1.forward\n" +
                "2.save message\n" +
                "3.back\n" +
                "4.exit"
        );
        String result = scanner.nextLine();
        while (!result.equals("forward") && !result.equals("save message") &&
                !result.equals("exit") && !result.equals("back")
        ) {
            System.out.println("Wrong input please try again");
            result = scanner.nextLine();
        }
        return result;
    }

    private void processSaveMessage(Message message) {
        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(chatRoom.getAccount());
        System.out.println(chatRoom.forwardMessage(accounts, message));
    }

    public Message processWriteNewMessage() {
        System.out.println(ConsoleColors.PURPLE + "Write your message and when finished say \"end\":" + ConsoleColors.RESET);
        String messageText = "";
        String line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.equals("back")) return null;
            if (line.equals("end")) break;
            messageText += line + "\n    ";
        }
        return chatRoom.writeNewMessage(messageText.trim());
    }

    private void processSendingAMessage(Message newMessage) {
        System.out.println(ConsoleColors.YELLOW + "Do you want to send it? (y/n)" + ConsoleColors.RESET);
        String input = scanner.nextLine();
        while (!input.equals("y") && !input.equals("n")) {
            System.err.println("Wrong input please just say \"y\" or \"n\":");
            input = scanner.nextLine();
        }
        if (input.equals("y")) {
            System.out.println(ConsoleColors.PURPLE +
                    ((chatRoom.sendMessage(newMessage)) ?
                            "message sent successfully!" : "Failed to send message") +
                    ConsoleColors.RESET);
        }
    }

    private void processForward(Message message) {
        System.out.println("Enter user names that you want to forward and when finished say end:");
        ArrayList<Account> accounts = new ArrayList<>();
        while (true) {
            String userName = scanner.nextLine();
            if (userName.equals("end")) break;
            Account account = chatRoom.getManager().searchByUserName(userName);
            if (account != null && chatRoom.getAccount().isValidToSendMessage(account)) {
                accounts.add(account);
            }
        }
        System.out.println(chatRoom.forwardMessage(accounts, message));
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }
}

package cLI.messengerCLI;

import logic.Account;
import cLI.*;
import logic.Singleton;
import logic.pages.messenger.ChatRoom;
import logic.pages.messenger.Message;
import logic.pages.messenger.MessengersPage;

import java.util.LinkedList;

public class MessengersCLI extends CLI {

    private final MessengersPage messengersPage;

    public MessengersCLI(MessengersPage messengersPage) {
        super();
        this.messengersPage = messengersPage;
    }

    private String chooseFromMenu() {
        System.out.println(MessengersPage.showCLIPage());
        System.out.println("Enter your command:");
        String result = scanner.nextLine();
        while (!result.equals("saved messages") && !result.equals("new chat") && !result.equals("write message") &&
                messengersPage.searchChatRoomsByUserName(result) == null && !result.equals("back") &&
                !result.equals("quit") && !result.equals("exit")
        ) {
            System.err.println("Wrong input please try again!");
            System.out.println(MessengersPage.showCLIPage());
            result = scanner.nextLine();
        }
        return result;
    }

    @Override
    public void run() {
        String input;
        while (true) {
            System.out.println(ConsoleColors.GREEN + messengersPage.showPage() + ConsoleColors.RESET);
            input = chooseFromMenu();
            if (input.equals("saved messages")) {
                messengersPage.getManager().goToChatroom(messengersPage.searchChatRoomByListener(messengersPage.getAccount()));
            } else if (input.equals("new chat")) {
                processBuildNewChatRoom();
            } else if (input.equals("write message")) {
                processWritingMessage();
            } else if (messengersPage.searchChatRoomsByUserName(input) != null) {
                messengersPage.getManager().goToChatroom(messengersPage.searchChatRoomsByUserName(input));
            } else if (input.equals("back")) {
                break;
            } else if (input.equals("quit")) {
                messengersPage.getAccount().setOnline(false);
                Singleton.save(messengersPage.getManager());
                messengersPage.getManager().goToLoginPage();
                System.exit(0);
            } else if (input.equals("exit")) {
                messengersPage.getAccount().setOnline(false);
                Singleton.save(messengersPage.getManager());
                System.exit(0);
            }
        }
    }

    private void processWritingMessage() {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setAccount(messengersPage.getAccount());
        ChatRoomCLI chatRoomCLI = new ChatRoomCLI(chatRoom);
        Message message = chatRoomCLI.processWriteNewMessage();
        if (message == null) return;
        System.out.println("Enter accounts user name or lists name or just say \"send to all\" to send message and when finished send end like this:\n" +
                "l \"list name\"\n" +
                "a \"user name\"\n" +
                "end");
        String result = "";
        while (true) {
            String s = scanner.nextLine();
            if (s.equals("send to all")) {
                result += s;
                break;
            }
            if (s.equals("end")) break;
            result += s + "\n";
        }
        String[] lines = result.split("\n");
        System.out.println(ConsoleColors.YELLOW + messengersPage.writeMessage(lines, message) + ConsoleColors.RESET);
    }

    private void processBuildNewChatRoom() {
        System.out.println("Enter user name that you want to chat:");
        String userName = scanner.nextLine();
        LinkedList<Account> fol = new LinkedList<>(messengersPage.getAccount().getFollowers());
        fol.addAll(messengersPage.getAccount().getFollowings());
        Account listener = messengersPage.getManager().search(fol, userName);
        if (listener == null) {
            System.err.println("Listener not found!");
            return;
        }
        ChatRoom newChatRoom1 = messengersPage.buildNewChatRoom(listener);
        if (newChatRoom1 != null) messengersPage.getManager().goToChatroom(newChatRoom1);
        else System.err.println("You can not send message to this account!");
    }
}

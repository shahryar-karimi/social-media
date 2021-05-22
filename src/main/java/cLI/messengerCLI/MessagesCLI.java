package cLI.messengerCLI;

import logic.Account;
import cLI.*;
import logic.Manager;
import logic.Singleton;
import logic.pages.messenger.ChatRoom;
import logic.pages.messenger.Message;
import logic.pages.messenger.MessagesPage;

import java.util.ArrayList;
import java.util.LinkedList;

public class MessagesCLI extends CLI {

    private final MessagesPage messagesPage;

    public MessagesCLI(MessagesPage messagesPage) {
        super();
        this.messagesPage = messagesPage;
    }

    private String chooseFromMenu() {
        System.out.println(MessagesPage.showCLIPage());
        System.out.println("Enter your command:");
        String result = scanner.nextLine();
        while (!result.equals("saved messages") && !result.equals("new chat") && !result.equals("write message") &&
                messagesPage.searchChatRoomsByUserName(result) == null && !result.equals("back") &&
                !result.equals("quit") && !result.equals("exit")
        ) {
            System.err.println("Wrong input please try again!");
            System.out.println(MessagesPage.showCLIPage());
            result = scanner.nextLine();
        }
        return result;
    }

    @Override
    public void run() {
        String input;
        while (true) {
            System.out.println(ConsoleColors.GREEN + messagesPage.showPage() + ConsoleColors.RESET);
            input = chooseFromMenu();
            if (input.equals("saved messages")) {
                processEnterToThisChatRoom(messagesPage.searchChatRoomByListener(messagesPage.getAccount()));
            } else if (input.equals("new chat")) {
                processBuildNewChatRoom();
            } else if (input.equals("write message")) {
                processWritingMessage();
            } else if (messagesPage.searchChatRoomsByUserName(input) != null) {
                processEnterToThisChatRoom(messagesPage.searchChatRoomsByUserName(input));
            } else if (input.equals("back")) {
                break;
            } else if (input.equals("quit")) {
                messagesPage.getAccount().setOnline(false);
                Singleton.save(messagesPage.getManager());
                messagesPage.getManager().goToLoginPage();
                System.exit(0);
            } else if (input.equals("exit")) {
                messagesPage.getAccount().setOnline(false);
                Singleton.save(messagesPage.getManager());
                System.exit(0);
            }
        }
    }

    private void processWritingMessage() {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setAccount(messagesPage.getAccount());
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
        String [] lines = result.split("\n");
        System.out.println(ConsoleColors.YELLOW + messagesPage.writeMessage(lines, message) + ConsoleColors.RESET);
    }

    private void processEnterToThisChatRoom(ChatRoom chatRoom) {
        ChatRoomCLI chatRoomCLI = new ChatRoomCLI(chatRoom);
        chatRoomCLI.run();
    }

    private void processBuildNewChatRoom() {
        System.out.println("Enter user name that you want to chat:");
        String userName = scanner.nextLine();
        Account listener = messagesPage.getManager().searchByUserName(userName);
        if (listener == null) {
            System.err.println("Listener not found!");
            return;
        }
        if (messagesPage.buildNewChatRoom(listener)) {
            ChatRoom newChatRoom1 = messagesPage.searchChatRoomByListener(listener);
            processEnterToThisChatRoom(newChatRoom1);
        } else System.err.println("You can not send message to this account!");
    }
}

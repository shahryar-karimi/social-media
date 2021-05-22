package cLI.personalCLI;

import cLI.CLI;
import cLI.ConsoleColors;
import cLI.messengerCLI.ChatRoomCLI;
import logic.Account;
import logic.Manager;
import logic.Singleton;
import logic.pages.personal.Info;
import logic.pages.messenger.ChatRoom;

import java.util.ArrayList;

public class InfoCLI extends CLI {
    private final Info info;
    private final Account visitor;
    private final boolean isOwnPage;

    public InfoCLI(Info info, Account visitor) {
        super();
        this.info = info;
        this.visitor = visitor;
        isOwnPage = visitor.equals(info.getAccount());
    }

    @Override
    public void run() {
        String input;
        while (true) {
            myLogger.debug(InfoCLI.class.getName(), "run",
                    "Info page of \"" + info.getAccount().toString() + "\" has opened for \"" +
                            visitor.toString() + "\"");
            System.out.println(ConsoleColors.PURPLE + info.toString(visitor) + ConsoleColors.RESET);
            input = chooseFromMenu();
            if (!isOwnPage) {
                if (input.equals("follow button")) {
                    processChangeFollowPosition(visitor);
                } else if (input.equals("block button")) {
                    processChangeBlockPosition(visitor);
                } else if (input.equals("report")) {
                    processReport(visitor);
                }
            }
            if (input.equals("send message")) {
                processSendMessage();
            } else if (input.equals("followings")) {
                processGoToFollowings();
            } else if (input.equals("followers")) {
                processGoToFollowers();
            } else if (input.equals("add to list")) {
                processAddingToList();
            } else if (input.equals("remove from list")) {
                processRemovingFromList();
            } else if (input.equals("quit")) {
                info.getAccount().setOnline(false);
                Singleton.save(info.getManager());
                info.getManager().goToLoginPage();
                System.exit(0);
            } else if (input.equals("back")) {
                break;
            } else if (input.equals("exit")) {
                info.getAccount().setOnline(false);
                Singleton.save(info.getManager());
                System.exit(0);
            }
        }
    }
    private String chooseFromMenu() {
        if (isOwnPage) System.out.println(Info.menuForActionOwner());
        else System.out.println(Info.menuForActions());
        System.out.println("Enter your command:");
        String result = scanner.nextLine();
        while ((isOwnPage || (!result.equals("follow button") && !result.equals("block button") &&
                !result.equals("report"))) && !result.equals("send message") &&
                !result.equals("add to list") && !result.equals("remove from list") &&
                !result.equals("followings") && !result.equals("followers") &&
                !result.equals("quit") && !result.equals("back") &&
                !result.equals("exit")
        ) {
            System.err.println("Wrong input please try again!");
            System.out.println(Info.menuForActions());
            result = scanner.nextLine();
        }
        return result;
    }

    private void processRemovingFromList() {
        System.out.println("Enter name of your list");
        String listName = scanner.nextLine();
        ArrayList<Account> accounts = visitor.getFriendsList().get(listName);
        if (accounts == null) {
            System.err.println("List not found");
        } else if (accounts.contains(info.getAccount())) {
            accounts.remove(info.getAccount());
        } else {
            System.err.println("Account not found");
        }
    }

    private void processAddingToList() {
        System.out.println("Enter name of your list");
        String listName = scanner.nextLine();
        ArrayList<Account> accounts = visitor.getFriendsList().get(listName);
        if (accounts == null) {
            System.err.println("List not found");
        } else if (!accounts.contains(info.getAccount())) {
            accounts.add(info.getAccount());
        } else {
            System.err.println("This account already added");
        }
    }


    private void processGoToFollowers() {
        if (info.getAccount().getFollowers().isEmpty()) {
            System.out.println(ConsoleColors.RED + "No followers here" + ConsoleColors.RESET);
            return;
        }
        System.out.println(ConsoleColors.BLUE_BRIGHT +
                info.getAccount().showFollowers() +
                ConsoleColors.RESET);
        String input;
        System.out.println(ConsoleColors.CYAN + "Menu:" + ConsoleColors.RESET);
        while (true) {
            System.out.println(ConsoleColors.CYAN +
                    "1.Enter followers user name\n" +
                    "2.Enter back" +
                    ConsoleColors.RESET
            );
            input = scanner.nextLine();
            if (input.equals("back")) {
                return;
            } else {
                Account follower = info.getAccount().searchFollowerByUserName(input);
                if (follower != null && follower.isActive()) {
                    info.getManager().goToInfoPage(follower, visitor);
                } else {
                    System.err.println("This user name not found!\nplease try another one:");
                }
            }
        }
    }

    private void processGoToFollowings() {
        if (info.getAccount().getFollowings().isEmpty()) {
            System.out.println(ConsoleColors.RED + "No followings here" + ConsoleColors.RESET);
            return;
        }
        System.out.println(ConsoleColors.BLUE_BRIGHT +
                info.getAccount().showFollowings() +
                ConsoleColors.RESET);
        String input;
        System.out.println(ConsoleColors.CYAN + "Menu:" + ConsoleColors.RESET);
        while (true) {
            System.out.println(ConsoleColors.CYAN +
                    "1.Enter followings user name\n" +
                    "2.Enter back" +
                    ConsoleColors.RESET
            );
            input = scanner.nextLine();
            if (input.equals("back")) {
                return;
            } else {
                Account following = info.getAccount().searchFollowingByUserName(input);
                if (following != null && following.isActive()) {
                    info.getManager().goToInfoPage(following, visitor);
                } else {
                    System.err.println("This user name not found!\nplease try another one:");
                }
            }
        }
    }

    private void processSendMessage() {
        ChatRoom chatRoom = visitor.getMessagesPage().searchChatRoomByListener(info.getAccount());
        if (chatRoom == null) {
            if (visitor.getMessagesPage().buildNewChatRoom(info.getAccount())) {
                chatRoom = visitor.getMessagesPage().searchChatRoomByListener(info.getAccount());
            } else {
                System.err.println("You can not send message to this account");
                return;
            }
        }
        ChatRoomCLI chatRoomCLI = new ChatRoomCLI(chatRoom);
        chatRoomCLI.run();
    }

    private void processReport(Account visitor) {
        System.out.println(visitor.report(info.getAccount()));
    }

    private void processChangeBlockPosition(Account visitor) {
        if (visitor.hasBlocked(info.getAccount())) {
            System.out.println(visitor.unBlock(info.getAccount()));
        } else {
            System.out.println(visitor.block(info.getAccount()));
        }
    }

    private void processChangeFollowPosition(Account visitor) {
        if (visitor.isFollow(info.getAccount()))
            System.out.println(visitor.unFollow(info.getAccount(), true));
        else if (!info.getAccount().hasBlocked(visitor)) {
            if (info.getAccount().isPagePublic()) {
                System.out.println(visitor.follow(info.getAccount()));
            } else {
                System.out.println(visitor.sendRequestTo(info.getAccount()));
            }
        } else {
            System.out.println("You are blocked");
        }
    }
}

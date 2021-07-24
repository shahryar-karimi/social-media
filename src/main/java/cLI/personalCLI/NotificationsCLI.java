package cLI.personalCLI;

import cLI.CLI;
import cLI.ConsoleColors;
import logic.Account;
import logic.Manager;
import logic.Singleton;
import logic.pages.personal.Notification;

public class NotificationsCLI extends CLI {

    private final Notification notification;

    public NotificationsCLI(Notification notification) {
        super();
        this.notification = notification;
    }


    @Override
    public void run() {
        String input;
        while (true) {
            System.out.println(notification.showPage());
            input = chooseFromMenu();
            if (input.equals("requests")) {
                processShowRequests();
            } else if (input.equals("system messages")) {
                processShowSystemMessages();
            } else if (input.equals("back")) {
                break;
            } else if (input.equals("quit")) {
                notification.getAccount().setOnline(false);
                Singleton.save(notification.getManager());
                notification.getManager().goToLoginPage();
                System.exit(0);
            } else if (input.equals("exit")) {
                notification.getAccount().setOnline(false);
                Singleton.save(notification.getManager());
                System.exit(0);
            }
        }
    }

    private void processShowSystemMessages() {
        if (notification.getSystemMessages().isEmpty()) {
            System.out.println(ConsoleColors.RED + "There is no system message here" + ConsoleColors.RESET);
            return;
        }
        String input;
        while (true) {
            if (notification.getIndexOfCurrentSystemMessage() == -1) {
                System.out.println(ConsoleColors.RED + "There is no system message here" + ConsoleColors.RESET);
                return;
            }
            System.out.println(notification.showCurrentSystemMessage());
            input = chooseFromMenuForSystemMessages();
            if (input.equals("next")) {
                processGoNextSystemMessage();
            } else if (input.equals("previous")) {
                processGoPreviousSystemMessage();
            } else if (input.equals("back")) {
                break;
            } else if (input.equals("quit")) {
                notification.getAccount().setOnline(false);
                Singleton.save(notification.getManager());
                notification.getManager().goToLoginPage();
                System.exit(0);
            } else if (input.equals("exit")) {
                notification.getAccount().setOnline(false);
                Singleton.save(notification.getManager());
                System.exit(0);
            }
        }
    }

    private void processGoPreviousSystemMessage() {
        if (notification.getIndexOfCurrentSystemMessage() > 0)
            notification.goPreviousSystemMessage();
        else
            System.err.println("There is no more system message here\nThis is the first system message:");
    }

    private void processGoNextSystemMessage() {
        if (notification.getIndexOfCurrentSystemMessage() < notification.getSystemMessages().size() - 1)
            notification.goNextSystemMessage();
        else
            System.err.println("There is no more system message here\nThis is the last system message:");
    }

    private String chooseFromMenuForSystemMessages() {
        System.out.println("" +
                "1.next\n" +
                "2.previous\n" +
                "3.back\n" +
                "4.quit\n" +
                "5.exit"
        );
        System.out.println("Enter your command:");
        String input = scanner.nextLine();
        while (!input.equals("next") && !input.equals("previous") &&
                !input.equals("back") && !input.equals("quit") && !input.equals("exit")
        ) {
            System.err.println("Wrong input please try again");
            System.out.println("" +
                    "1.next\n" +
                    "2.previous\n" +
                    "3.back\n" +
                    "4.quit\n" +
                    "5.exit"
            );
            input = scanner.nextLine();
        }
        return input;
    }

    private void processShowRequests() {
        if (notification.getRequests().isEmpty()) {
            System.out.println(ConsoleColors.RED + "There is no request here" + ConsoleColors.RESET);
            return;
        }
        String input;
        processGoNextRequest();
        while (true) {
            if (notification.getIndexOfCurrentRequest() == -1) {
                System.out.println(ConsoleColors.RED + "There is no request here" + ConsoleColors.RESET);
                return;
            }
            System.out.println(notification.showCurrentRequest() + " wants to follow you");
            input = chooseFromMenuForRequests();
            if (input.equals("next")) {
                processGoNextRequest();
            } else if (input.equals("previous")) {
                processGoPreviousRequest();
            } else if (input.equals("accept")) {
                processAcceptRequest();
            } else if (input.equals("decline with notification")) {
                processDecline(true);
            } else if (input.equals("decline without notification")) {
                processDecline(false);
            } else if (input.equals("back")) {
                break;
            } else if (input.equals("quit")) {
                notification.getAccount().setOnline(false);
                Singleton.save(notification.getManager());
                notification.getManager().goToLoginPage();
                System.exit(0);
            } else if (input.equals("exit")) {
                notification.getAccount().setOnline(false);
                Singleton.save(notification.getManager());
                System.exit(0);
            }
        }
    }

    private void processDecline(boolean distinctMode) {
        notification.declineRequest(distinctMode, processGetAccountOfCurrentRequest());
    }

    private void processAcceptRequest() {
        System.out.println(notification.acceptRequest(processGetAccountOfCurrentRequest()));
    }

    private Account processGetAccountOfCurrentRequest() {
        String currentRequest = notification.showCurrentRequest();
        return notification.getManager().searchByUserName(currentRequest);
    }

    private void processGoPreviousRequest() {
        if (notification.getIndexOfCurrentRequest() > 0)
            notification.goPreviousRequest();
        else
            System.err.println("There is no more request here\nThis is the first request:");
    }

    private void processGoNextRequest() {
        if (notification.getIndexOfCurrentRequest() < notification.getRequests().size() - 1)
            notification.goNextRequest();
        else
            System.err.println("There is no more request here\nThis is the last request:");
    }

    private String chooseFromMenuForRequests() {
        System.out.println("" +
                "1.next\n" +
                "2.previous\n" +
                "3.accept\n" +
                "4.decline with notification\n" +
                "5.decline without notification\n" +
                "6.back\n" +
                "7.quit\n" +
                "8.exit"
        );
        System.out.println("Enter your command:");
        String input = scanner.nextLine();
        while (!input.equals("next") && !input.equals("previous") &&
                !input.equals("accept") && !input.equals("decline with notification") &&
                !input.equals("decline without notification") && !input.equals("back") &&
                !input.equals("quit") && !input.equals("exit")
        ) {
            System.err.println("Wrong input please try again");
            System.out.println("" +
                    "1.next\n" +
                    "2.previous\n" +
                    "3.accept\n" +
                    "4.decline with notification\n" +
                    "5.decline without notification\n" +
                    "6.back\n" +
                    "7.quit\n" +
                    "8.exit"
            );
            input = scanner.nextLine();
        }
        return input;
    }

    private String chooseFromMenu() {
        String result = scanner.nextLine();
        while (!result.equals("requests") && !result.equals("system messages") &&
                !result.equals("back") && !result.equals("quit") &&
                !result.equals("exit")
        ) {
            System.err.println("Wrong input please try another one:");
            System.out.println(notification.showPage());
            result = scanner.nextLine();
        }
        return result;
    }
}

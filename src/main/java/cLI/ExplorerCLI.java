package cLI;

import logic.Account;
import logic.Singleton;
import logic.pages.ExplorerPage;
import logic.pages.TimeLinePage;
import logic.Tweet;

import java.util.LinkedList;

public class ExplorerCLI extends CLI {

    private final ExplorerPage explorerPage;

    public ExplorerCLI(ExplorerPage explorerPage) {
        super();
        this.explorerPage = explorerPage;
    }

    @Override
    public void run() {
        String input;
        while (true) {
            myLogger.debug(ExplorerCLI.class.getName(), "run",
                    "Explorer page ran for account \"" + explorerPage.getAccount().toString() + "\"");
            input = chooseFromMenu();
            if (input.equals("quit")) {
                explorerPage.getAccount().setOnline(false);
                Singleton.save(explorerPage.getManager());
                explorerPage.getManager().goToLoginPage();
                System.exit(0);
            } else if (input.equals("exit")) {
                explorerPage.getAccount().setOnline(false);
                Singleton.save(explorerPage.getManager());
                System.exit(0);
            } else if (input.equals("back")) {
                return;
            } else if (input.equals("search")) {
                processSearch();
            } else if (input.equals("random tweets")) {
                processShowRandomTweets();
            }
        }
    }

    private String chooseFromMenu() {
        System.out.println(ExplorerPage.showPage());
        System.out.println("Enter your command:");
        String result = scanner.nextLine();
        while (!result.equals("random tweets") && !result.equals("search") &&
                !result.equals("back") && !result.equals("exit") &&
                !result.equals("quit")
        ) {
            System.out.println(ExplorerPage.showPage());
            System.err.println("Wrong input please try again!");
            result = scanner.nextLine();
        }
        return result;
    }

    private void processShowRandomTweets() {
        LinkedList<Tweet> explorerTweets = explorerPage.getRandomTweets();
        if (explorerTweets == null) {
            System.out.println(ConsoleColors.RED + "There is nothing here" + ConsoleColors.RESET);
            return;
        }
        TimeLinePage timeLinePage = new TimeLinePage(explorerPage.getAccount(), explorerPage.getManager(), false);
        timeLinePage.setTweets(explorerTweets);
        TimeLineCLI timeLineCli = new TimeLineCLI(timeLinePage);
        timeLineCli.run();
    }

    private void processSearch() {
        System.out.println("Enter user name that you want to find:");
        String userName = scanner.nextLine();
        Account account = explorerPage.getManager().searchByUserName(userName);
        if (account == null || !account.isActive()) {
            System.err.println("Account not found!");
        } else {
            explorerPage.getManager().goToInfoPage(account.getPersonalPage().getInfo(), explorerPage.getAccount());
        }
    }
}

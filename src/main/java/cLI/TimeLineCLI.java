package cLI;

import logic.Account;
import logic.Manager;
import logic.Singleton;
import logic.pages.TimeLinePage;
import logic.Tweet;

import java.util.LinkedList;

public class TimeLineCLI extends CLI {

    private final TimeLinePage timeLinePage;

    public TimeLineCLI(TimeLinePage timeLinePage) {
        super();
        this.timeLinePage = timeLinePage;
    }

    @Override
    public void run() {
        myLogger.debug(TimeLineCLI.class.getName(), "run",
                "Time line page ran for account \"" + timeLinePage.getAccount().toString() + "\"");
        run(timeLinePage.getTweets(), timeLinePage.getIndexOfTweet());
    }

    public void run(LinkedList<Tweet> tweets, int indexOfTweet) {
        if (!tweets.isEmpty()) {
            showCurrentTweet();
        }
        while (true) {
            if (!tweets.isEmpty()) {
                System.out.println(TimeLinePage.showPage());
            } else {
                System.out.println(ConsoleColors.RED + "There is nothing here" + ConsoleColors.RESET);
                System.out.println("Time line:\n" +
                        "1.back\n" +
                        "2.quit\n" +
                        "3.exit");
            }
            String input = chooseFromMenu();
            if (!tweets.isEmpty()) {
                if (input.equals("next tweet")) {
                    indexOfTweet = processGoNextTweet(tweets, indexOfTweet);
                } else if (input.equals("previous tweet")) {
                    indexOfTweet = processGoPreviousTweet(tweets, indexOfTweet);
                } else if (input.equals("fave")) {
                    processFave();
                } else if (input.equals("faves list")) {
                    processShowFavesList();
                } else if (input.equals("save tweet")) {
                    processSendItToSavedMessages();
                } else if (input.equals("retweet")) {
                    processRetweet();
                } else if (input.equals("forward")) {
                    processForwardToAccounts();
                } else if (input.equals("block user")) {
                    processBlockUser();
                } else if (input.equals("mute user")) {
                    processMuteUser();
                } else if (input.equals("report")) {
                    processReport();
                } else if (input.equals("show user page")) {
                    processShowUserPage();
                } else if (input.equals("send comment")) {
                    processSendComment();
                } else if (input.equals("show comments")) {
                    processShowComments();
                }
            }
            if (input.equals("quit")) {
                timeLinePage.getAccount().setOnline(false);
                Singleton.save(timeLinePage.getManager());
                timeLinePage.getManager().goToLoginPage();
                System.exit(0);
            } else if (input.equals("exit")) {
                timeLinePage.getAccount().setOnline(false);
                Singleton.save(timeLinePage.getManager());
                System.exit(0);
            } else if (input.equals("back")) {
                break;
            }
        }
    }

    private String chooseFromMenu() {
        System.out.println("Choose from menu:");
        String result = scanner.nextLine();
        while (
                !result.equals("next tweet") && !result.equals("previous tweet") &&
                        !result.equals("fave") && !result.equals("save tweet") &&
                        !result.equals("retweet") && !result.equals("forward") &&
                        !result.equals("block user") && !result.equals("mute user") &&
                        !result.equals("report") && !result.equals("show user page") &&
                        !result.equals("send comment") && !result.equals("show comments") &&
                        !result.equals("faves list") && !result.equals("quit") &&
                        !result.equals("exit") && !result.equals("back")
        ) {
            System.err.println("Wrong input please try again");
            System.out.println(TimeLinePage.showPage());
            result = scanner.nextLine();
        }
        return result;
    }

    private void processShowFavesList() {
        System.out.println(timeLinePage.showFavesList());
    }

    private void processShowComments() {
        TimeLinePage anotherTimeLinePage = new TimeLinePage(timeLinePage.getAccount(), timeLinePage.getManager(), false);
        anotherTimeLinePage.setTweets(timeLinePage.getCurrentTweet().getComments());
        anotherTimeLinePage.setIndexOfTweet(timeLinePage.getCurrentTweet().getComments().size() - 1);
        TimeLineCLI anotherTimeLineCLI = new TimeLineCLI(anotherTimeLinePage);
        anotherTimeLineCLI.run();
    }

    private void processSendComment() {
        System.out.println("Write your comment and when finished say end:");
        String comment = "";
        String line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.equals("end")) break;
            comment += line + "\n";
        }
        timeLinePage.sendComment(comment.trim());
        System.out.println(ConsoleColors.CYAN + "Comment sent successfully" + ConsoleColors.RESET);
    }

    private void processShowUserPage() {
        Tweet currentTweet = timeLinePage.getCurrentTweet();
        Account tweetsAccount = currentTweet.getAccount();
        if (tweetsAccount.isActive()) {
            timeLinePage.getManager().goToInfoPage(tweetsAccount.getPersonalPage().getInfo(), timeLinePage.getAccount());
        }
    }

    private void processReport() {
        System.out.println(timeLinePage.report());
    }

    private void processMuteUser() {
        timeLinePage.muteUser();
    }

    private void processBlockUser() {
        timeLinePage.blockUser();
    }

    private void processRetweet() {
        timeLinePage.retweet();
        System.out.println("Current tweet retweeted");
        showCurrentTweet();
    }

    private void processFrowardToLists() {

    }

    private void processForwardToAccounts() {
        System.out.println("Enter user names that you want to forward this tweet and when finished say end:");
        LinkedList<Account> accounts = new LinkedList<>();
        while (true) {
            String userName = scanner.nextLine();
            if (userName.equals("end")) break;
            Account account = timeLinePage.getManager().searchByUserName(userName);
            if (account != null && timeLinePage.getAccount().isValidToSendMessage(account)) {
                accounts.add(account);
            }
        }
        System.out.println(timeLinePage.forward(accounts));
        showCurrentTweet();
    }

    private void processSendItToSavedMessages() {
        LinkedList<Account> accounts = new LinkedList<>();
        accounts.add(timeLinePage.getAccount());
        System.out.println(timeLinePage.forward(accounts));
        showCurrentTweet();
    }

    private void processFave() {
        System.out.println(timeLinePage.fave());
        showCurrentTweet();
    }

    private void showCurrentTweet() {
        showTweet(timeLinePage.getCurrentTweet().toString());
    }

    private void showTweet(String tweet) {
        System.out.println(ConsoleColors.RED + "=====================================================\n" +
                tweet +
                "\n=====================================================" +
                ConsoleColors.RESET);
    }

    private int processGoNextTweet(LinkedList<Tweet> tweets, int indexOfTweet) {
        String result = timeLinePage.goNextTweet(tweets, indexOfTweet);
        int lastIndex = result.lastIndexOf(' ');
        indexOfTweet = Integer.parseInt(result.substring(lastIndex + 1));
        result = result.substring(0, lastIndex);
        showTweet(result);
        return indexOfTweet;
    }

    private int processGoPreviousTweet(LinkedList<Tweet> tweets, int indexOfTweet) {
        String result = timeLinePage.goPreviousTweet(tweets, indexOfTweet);
        int lastIndex = result.lastIndexOf(' ');
        indexOfTweet = Integer.parseInt(result.substring(lastIndex + 1));
        result = result.substring(0, lastIndex);
        showTweet(result);
        return indexOfTweet;
    }
}

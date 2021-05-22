package cLI.personalCLI;

import cLI.CLI;
import cLI.ConsoleColors;
import logic.Account;
import logic.Singleton;
import logic.pages.personal.PersonalPage;
import logic.Tweet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonalPageCLI extends CLI {

    private final PersonalPage personalPage;

    public PersonalPageCLI(PersonalPage personalPage) {
        super();
        this.personalPage = personalPage;
    }

    @Override
    public void run() {
        while (true) {
            myLogger.debug(PersonalPageCLI.class.getName(), "run",
                    "Personal page ran for account \"" + personalPage.getAccount().toString() + "\"");
            System.out.println(PersonalPage.showPage());
            String input = chooseFromMenu();
            if (input.equals("new tweet")) {
                processWritingNewTweet();
            } else if (input.equals("my tweets")) {
                processShowingMyTweets();
            } else if (input.equals("edit profile")) {
                processEditProfile();
            } else if (input.equals("my followings")) {
                processMyFollowings();
            } else if (input.equals("my followers")) {
                processMyFollowers();
            } else if (input.equals("my black list")) {
                processMyBlackList();
            } else if (input.equals("info")) {
                processShowInfo();
            } else if (input.equals("notifications")) {
                processShowNotifications();
            } else if (input.equals("create list")) {
                processCreatingList();
            } else if (input.equals("remove list")) {
                processRemovingList();
            } else if (input.equals("quit")) {
                personalPage.getAccount().setOnline(false);
                Singleton.save(personalPage.getManager());

                personalPage.getManager().goToLoginPage();
                System.exit(0);
            } else if (input.equals("back")) {
                break;
            } else if (input.equals("exit")) {
                personalPage.getAccount().setOnline(false);
                Singleton.save(personalPage.getManager());
                System.exit(0);
            }
        }
    }

    private void processRemovingList() {
        System.out.println("Enter name of your list:");
        String listName = scanner.nextLine();
        System.out.println(ConsoleColors.CYAN + personalPage.removeList(listName) + ConsoleColors.RESET);
    }

    private String chooseFromMenu() {
        System.out.println("Choose from menu:");
        String result = scanner.nextLine();
        while (
                !result.equals("new tweet") && !result.equals("my tweets") &&
                        !result.equals("edit profile") && !result.equals("my followings") &&
                        !result.equals("my followers") && !result.equals("my black list") &&
                        !result.equals("info") && !result.equals("notifications") &&
                        !result.equals("quit") && !result.equals("exit") &&
                        !result.equals("back") && !result.equals("create list")
        ) {
            if (result.equals("")) {
                result = scanner.nextLine();
                continue;
            }
            System.err.println("Wrong input please try again");
            System.out.println(PersonalPage.showPage());
            result = scanner.nextLine();
        }
        return result;
    }

    private void processCreatingList() {
        System.out.println("Enter user names that you want to being in list and when finished say end:");
        ArrayList<Account> accounts = new ArrayList<>();
        while (true) {
            String userName = scanner.nextLine();
            if (userName.equals("end")) break;
            Account account = personalPage.getManager().searchByUserName(userName);
            if (account != null && personalPage.getAccount().isFollow(account)) {
                accounts.add(account);
            } else if (account == null) {
                System.err.println("Account not found");
            }
        }
        System.out.println("Enter a name for your list:");
        String listsName = scanner.nextLine();
        System.out.println(ConsoleColors.CYAN + personalPage.putListToFriendsList(listsName, accounts) + ConsoleColors.RESET);
    }

    private void processShowNotifications() {
        NotificationsCLI notificationsCLI = new NotificationsCLI(personalPage.getNotification());
        notificationsCLI.run();
    }

    private void processShowInfo() {
        personalPage.getManager().goToInfoPage(personalPage.getAccount(), personalPage.getAccount());
    }

    private void processMyBlackList() {
        if (personalPage.myBlackList().isEmpty()) {
            System.out.println("There is no blocked user here");
            return;
        }
        for (Account account : personalPage.myBlackList()) {
            if (account.isActive())
                System.out.println(account);
        }
        runOnList(personalPage.myBlackList());
    }

    private void processMyFollowings() {
        if (personalPage.myFollowings().isEmpty()) {
            System.out.println("There is no followings here");
            return;
        }
        for (Account account : personalPage.myFollowings()) {
            if (account.isActive())
                System.out.println(account);
        }
        runOnList(personalPage.myFollowings());
    }

    private void processMyFollowers() {
        if (personalPage.myFollowers().isEmpty()) {
            System.out.println("There is no followers here");
            return;
        }
        for (Account account : personalPage.myFollowers()) {
            if (account.isActive())
                System.out.println(account);
        }
        runOnList(personalPage.myFollowers());
    }

    public Account searchOnList(List<Account> list, String userName) {
        for (Account account1 : list)
            if (account1.getUserName().equals(userName) && account1.isActive()) return account1;
        return null;
    }

    public void runOnList(List<Account> list) {
        String input;
        while (true) {
            input = scanner.nextLine();
            Account account = searchOnList(list, input);
            if (input.equals("back")) {
                return;
            } else if (account != null && account.isActive()) {
                personalPage.getManager().goToInfoPage(searchOnList(list, input), personalPage.getAccount());
            } else {
                System.err.println("Wrong input\nplease input user name or \"back\"");
            }
        }
    }

    private void processEditProfile() {
        while (true) {
            System.out.println("What do you want to edit?");
            System.out.println("" +
                    "1.first name\n" +
                    "2.last name\n" +
                    "3.password\n" +
                    "4.email address\n" +
                    "5.phone number\n" +
                    "6.bio\n" +
                    "7.birthday date\n" +
                    "8.back\n" +
                    "9.quit\n" +
                    "10.exit"
            );
            String input = scanner.nextLine();
            while (
                    !input.equals("first name") && !input.equals("last name") &&
                            !input.equals("password") && !input.equals("email address") &&
                            !input.equals("phone number") && !input.equals("bio") &&
                            !input.equals("birthday date") && !input.equals("quit") &&
                            !input.equals("back") && !input.equals("exit")
            ) {
                input = scanner.nextLine();
            }
            if (input.equals("first name"))
                processEditFirstName();
            else if (input.equals("last name"))
                processEditLastName();
            else if (input.equals("password"))
                processEditPassword();
            else if (input.equals("email address"))
                processEditEmailAddress();
            else if (input.equals("phone number"))
                processEditPhoneNumber();
            else if (input.equals("bio"))
                processEditBio();
            else if (input.equals("birthday date"))
                processEditBirthdayDate();
            else if (input.equals("back"))
                break;
            else if (input.equals("quit")) {
                personalPage.getAccount().setOnline(false);
                Singleton.save(personalPage.getManager());
                personalPage.getManager().goToLoginPage();
                System.exit(0);
            } else {
                personalPage.getAccount().setOnline(false);
                Singleton.save(personalPage.getManager());
                System.exit(0);
            }
        }
    }

    private void processEditBirthdayDate() {
        System.out.println("Enter year of your birthday");
        int year = scanner.nextInt();
        System.out.println("Enter month of your birthday");
        int month = getValidMonth();
        System.out.println("Enter day of your birthday");
        int day = getValidDay(month);
        LocalDate bd = LocalDate.of(year, month, day);
        myLogger.info(PersonalPageCLI.class.getName(), "processEditBirthdayDate",
                "an account with user name \"" + personalPage.getAccount().toString() + "\"" +
                        " edited his/her birthday date from \"" + personalPage.getAccount().getBirthdayDate() + "\"" +
                        " to \"" + bd.toString() + "\"");
        personalPage.editBirthdayDate(bd.toString());
        System.out.println(ConsoleColors.CYAN_BACKGROUND + "Birth day date edited successfully!" + ConsoleColors.RESET);
    }

    private int getValidDay(int month) {
        int day = scanner.nextInt();
        int d = 31;
        if (month == 2) d = 28;
        else if (month == 4 || month == 6 || month == 9 || month == 11) d = 30;
        while (day < 1 || day > d) {
            System.err.printf("Wrong input for day\nsend a number between 1 to %d:\n", d);
            day = scanner.nextInt();
        }
        return day;
    }

    private int getValidMonth() {
        int month = scanner.nextInt();
        while (month < 1 || month > 12) {
            System.err.println("Wrong input for month\nsend a number between 1 to 12:");
            month = scanner.nextInt();
        }
        return month;
    }

    private void processEditBio() {
        System.out.println(ConsoleColors.GREEN + "Enter your new bio:" + ConsoleColors.RESET);
        String newBio = scanner.nextLine();
        myLogger.info(PersonalPageCLI.class.getName(), "processEditBio",
                "an account with user name \"" + personalPage.getAccount().toString() + "\"" +
                        " edited his/her bio from \"" + personalPage.getAccount().getBio() + "\"" +
                        " to \"" + newBio + "\"");
        personalPage.editBio(newBio);
        System.out.println(ConsoleColors.CYAN_BACKGROUND + "Bio edited successfully!" + ConsoleColors.RESET);
    }

    private void processEditPhoneNumber() {
        System.out.println(ConsoleColors.GREEN + "Enter your new phone number:" + ConsoleColors.RESET);
        String newPhoneNumber = scanner.next();
        myLogger.info(PersonalPageCLI.class.getName(), "processEditPhoneNumber",
                "an account with user name \"" + personalPage.getAccount().toString() + "\"" +
                        " edited his/her phone number from \"" + personalPage.getAccount().getPhoneNumber() + "\"" +
                        " to \"" + newPhoneNumber + "\"");
        personalPage.editPhoneNumber(newPhoneNumber);
        System.out.println(ConsoleColors.CYAN_BACKGROUND + "Phone number edited successfully!" + ConsoleColors.RESET);
    }

    private void processEditEmailAddress() {
        System.out.println(ConsoleColors.GREEN + "Enter your new email address:" + ConsoleColors.RESET);
        String newEmailAddress = scanner.next();
        myLogger.info(PersonalPageCLI.class.getName(), "processEditEmailAddress",
                "an account with user name \"" + personalPage.getAccount().toString() + "\"" +
                        " edited his/her email address from \"" + personalPage.getAccount().getEmailAddress() + "\"" +
                        " to \"" + newEmailAddress + "\"");
        personalPage.editEmail(newEmailAddress);
        System.out.println(ConsoleColors.CYAN_BACKGROUND + "Email address edited successfully!" + ConsoleColors.RESET);
    }

    public void processEditPassword() {
        System.out.println(ConsoleColors.CYAN + "Enter your last password:" + ConsoleColors.RESET);
        String lastPassword = scanner.next();
        if (personalPage.getManager().isCorrectPassword(personalPage.getAccount(), lastPassword)) {
            System.out.println(ConsoleColors.GREEN + "Enter your new password:" + ConsoleColors.RESET);
            lastPassword = scanner.next();
            myLogger.info(PersonalPageCLI.class.getName(), "processEditPassword",
                    "an account with user name \"" + personalPage.getAccount().toString() + "\"" +
                            " edited his/her password from \"" + personalPage.getAccount().getPassword() + "\"" +
                            " to \"" + lastPassword + "\"");
            personalPage.editPassword(lastPassword);
            System.out.println(ConsoleColors.CYAN_BACKGROUND + "Password edited successfully!" + ConsoleColors.RESET);
        } else {
            System.err.println("Incorrect last password");
        }
    }

    private void processEditLastName() {
        System.out.println(ConsoleColors.GREEN + "Enter your new last name:" + ConsoleColors.RESET);
        String newLastName = scanner.next();
        myLogger.info(PersonalPageCLI.class.getName(), "processEditLastName",
                "an account with user name \"" + personalPage.getAccount().toString() + "\"" +
                        " edited his/her last name from \"" + personalPage.getAccount().getLastName() + "\"" +
                        " to \"" + newLastName + "\"");
        personalPage.editLastName(newLastName);
        System.out.println(ConsoleColors.CYAN_BACKGROUND + "last name edited successfully!" + ConsoleColors.RESET);
    }

    private void processEditFirstName() {
        System.out.println(ConsoleColors.GREEN + "Enter your new first name:" + ConsoleColors.RESET);
        String newFirstName = scanner.next();
        myLogger.info(PersonalPageCLI.class.getName(), "processEditFirstName",
                "an account with user name \"" + personalPage.getAccount().toString() + "\"" +
                        " edited his/her first name from \"" + personalPage.getAccount().getFirstName() + "\"" +
                        " to \"" + newFirstName + "\"");
        personalPage.editFirstName(newFirstName);
        System.out.println(ConsoleColors.CYAN_BACKGROUND + "first name edited successfully!" + ConsoleColors.RESET);
    }

    private void processShowingMyTweets() {
        System.out.println(personalPage.showMyTweets());
    }

    private void processWritingNewTweet() {
        System.out.println("Write your tweet and when finished say end:");
        String tweetText = "";
        String line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.equals("end")) break;
            tweetText += line + "\n";
        }
        processSendingATweet(personalPage.writeNewTweet(tweetText.trim()));
    }

    private void processSendingATweet(Tweet tweet) {
        System.out.println("do you want to send it ? (y/n)");
        String input = scanner.next();
        if (input.equals("y")) {
            personalPage.sendingATweet(tweet, true);
            myLogger.info(PersonalPageCLI.class.getName(), "processSendingATweet",
                    "an account with user name \"" + personalPage.getAccount().toString() + "\"" +
                            " tweeted: \n{\n" + tweet.toString() + "\n}");
            System.out.println(personalPage.showMyTweets());
        } else if (input.equals("n")) {
            System.out.println("do you want to write again ? (y/n)");
            input = scanner.next();
            if (input.equals("y")) {
                processWritingNewTweet();
            }
        }
    }
}

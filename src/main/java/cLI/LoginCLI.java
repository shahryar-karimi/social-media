package cLI;

import logic.Account;
import logic.Singleton;
import logic.pages.LoginPage;

public class LoginCLI extends CLI {

    private LoginPage loginPage;

    public LoginCLI(LoginPage loginPage) {
        super();
        this.loginPage =  loginPage;
    }

    public void run() {
        while (true) {
            myLogger.debug(LoginCLI.class.getName(), "run", "Login page ran");
            System.out.println(ConsoleColors.CYAN + "Already have an account ? (y/n)" + ConsoleColors.RESET);
            String input = scanner.next();
            if (input.equals("y")) {
                processToEnterAnAccount();
            } else if (input.equals("n")) {
                processCreatingAnAccount();
            } else if (input.equals("exit")) {
                Singleton.save(loginPage.getManager());
                return;
            } else {
                System.err.println("Wrong input please just say y/n");
            }
        }
    }

    private void processToEnterAnAccount() {
        String userName = getUserNameThatExist();
        if (userName.equals("back")) return;

        Account account = loginPage.getManager().searchByUserName(userName);

        System.out.println("Enter your password:");
        String password = scanner.next();
        while (!password.equals("back") && !processIsCorrectPassword(account, password)) {
            System.err.println("Incorrect password!");
            password = scanner.next();
        }
        if (password.equals("back")) return;

        myLogger.info(LoginCLI.class.getName(), "processToEnterAnAccount",
                "an account with user name \"" + account.toString() + "\" logged in");
        System.out.println(ConsoleColors.GREEN + "You are log in" + ConsoleColors.RESET);
        account.setOnline(true);
        loginPage.getManager().goToMenuPage(account);
    }

    private void processCreatingAnAccount() {
        System.out.println("Enter your first name:");
        String firstName = scanner.next();
        if (firstName.equals("back")) return;

        System.out.println("Enter your last name:");
        String lastName = scanner.next();
        if (lastName.equals("back")) return;

        String userName = getUserNameThatDoesntExist();
        if (userName.equals("back")) return;

        System.out.println("Enter your password:");
        String password = scanner.next();
        if (password.equals("back")) return;

        String emailAddress = getEmailAddress();
        if (emailAddress.equals("back")) return;

        Account account = loginPage.getManager().createAnAccount(firstName, lastName, userName, password, emailAddress, null, null);

        if (account != null) {
            myLogger.info(LoginCLI.class.getName(), "processCreatingAnAccount",
                    "an account with user name \"" + account.toString() + "\" has created");
            System.out.println(ConsoleColors.GREEN + "Your account created successfully" + ConsoleColors.RESET);

        } else {
            myLogger.error(LoginCLI.class.getName(), "processCreatingAnAccount",
                    "Failed to creating an account");
            System.err.println("Your account failed to create!!");
        }
    }

    private String getEmailAddress() {
        System.out.println("Enter your email address:");
        String email = scanner.next();
        while (loginPage.getManager().searchByEmail(email) != null) {
            System.err.println("This email already has been token try another one");
            email = scanner.nextLine();
        }
        return email;
    }

    private String getUserNameThatExist() {
        System.out.println("Enter your user name:");
        String userName = scanner.next();
        while (!userName.equals("back") && loginPage.getManager().searchByUserName(userName) == null) {
            System.err.println("This user name not found please try another one:");
            userName = scanner.next();
        }
        return userName;
    }

    private String getUserNameThatDoesntExist() {
        System.out.println("Enter your user name and your user name should not be \"end\":");
        String userName = scanner.next();
        while (loginPage.getManager().searchByUserName(userName) != null || userName.equals("end")) {
            if (userName.equals("end")) {
                System.err.println("Your user name should not to be \"end\"");
            } else {
                System.err.println("User already exist! \ntry another user name:");
            }
            userName = scanner.nextLine();
        }
        return userName;
    }

    private boolean processIsCorrectPassword(Account account, String password) {
        return loginPage.getManager().isCorrectPassword(account, password);
    }
}

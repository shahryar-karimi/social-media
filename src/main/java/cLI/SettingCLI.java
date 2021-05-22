package cLI;

import cLI.personalCLI.PersonalPageCLI;
import logic.Singleton;
import logic.pages.SettingPage;

public class SettingCLI extends CLI {

    private final SettingPage settingPage;

    public SettingCLI(SettingPage settingPage) {
        super();
        this.settingPage = settingPage;
    }

    @Override
    public void run() {
        myLogger.debug(SettingCLI.class.getName(), "run",
                "Setting page ran for account \"" + settingPage.getAccount().toString() + "\"");
        String input;
        while (true) {
            input = chooseFromMenu();
            if (input.equals("setting privacy")) {
                processSettingPrivacy();
            } else if (input.equals("delete account")) {
                processDeleteAccount();
            } else if (input.equals("log out")) {
                settingPage.getAccount().setOnline(false);
                Singleton.save(settingPage.getManager());
                settingPage.getManager().goToLoginPage();
                System.exit(0);
            } else if (input.equals("back")) {
                break;
            } else if (input.equals("exit")) {
                settingPage.getAccount().setOnline(false);
                Singleton.save(settingPage.getManager());
                System.exit(0);
            }
        }
    }

    private void processSettingPrivacy() {
        String input;
        while (true) {
            input = chooseFromPrivacyMenu();
            if (input.equals("pages privacy")) {
                processPagePrivacy();
            } else if (input.equals("last seen privacy")) {
                processLastSeenPrivacy();
            } else if (input.equals("pages activity")) {
                processPageActivity();
            } else if (input.equals("edit password")) {
                processEditPassword();
            } else if (input.equals("back")) {
                break;
            } else if (input.equals("exit")) {
                settingPage.getAccount().setOnline(false);
                Singleton.save(settingPage.getManager());
                System.exit(0);
            }
        }
    }

    private void processEditPassword() {
        PersonalPageCLI personalPageCLI = new PersonalPageCLI(settingPage.getAccount().getPersonalPage());
        personalPageCLI.processEditPassword();
    }

    private void processPageActivity() {
        System.out.println("Do you want to set your page active or de active?");
        String activity = scanner.nextLine();
        while (!activity.equals("active") && !activity.equals("de active")) {
            System.err.println("Wrong input\nplease just say \"active\" or \"de active\"");
            activity = scanner.nextLine();
        }
        settingPage.setActivity(activity);
    }

    private void processLastSeenPrivacy() {
        System.out.println("Who can see your last seen?\n" +
                "1.every one\n" +
                "2.followings\n" +
                "3.no one\n" +
                "4.back\n" +
                "5.exit");
        String result = scanner.nextLine();
        while (!result.equals("every one") && !result.equals("followings") &&
                !result.equals("no one") && !result.equals("back") &&
                !result.equals("exit")
        ) {
            System.err.println("Wrong input please try again\n" +
                    "please just say" +
                    "\"every one\" or \"followings\" or \"no one\" or \"back\" or \"exit\"");
            result = scanner.nextLine();
        }
        if (result.equals("every one")) {
            settingPage.setLastSeenPrivacy("every one");
        } else if (result.equals("followings")) {
            settingPage.setLastSeenPrivacy("followings");
        } else if (result.equals("no one")) {
            settingPage.setLastSeenPrivacy("no one");
        } else if (result.equals("exit")) {
            settingPage.getAccount().setOnline(false);
            Singleton.save(settingPage.getManager());
            System.exit(0);
        }
    }

    private void processPagePrivacy() {
        System.out.println("Do you want to set your page \"public\" or \"private\"?");
        String result = scanner.nextLine();
        while (!result.equals("public") && !result.equals("private")) {
            System.err.println("Wrong input\nplease just say \"public\" or \"private\"");
            result = scanner.nextLine();
        }
        if (result.equals("public")) System.out.println(settingPage.setPagePrivacy(true));
        else System.out.println(settingPage.setPagePrivacy(false));
    }

    private String chooseFromPrivacyMenu() {
        System.out.println(settingPage.showPrivacyMenu());
        String result = scanner.nextLine();
        while (!result.equals("pages privacy") && !result.equals("last seen privacy") &&
                !result.equals("pages activity") && !result.equals("edit password") &&
                !result.equals("back") && !result.equals("exit")
        ) {
            System.err.println("Wrong input please try again");
            System.out.println(settingPage.showPrivacyMenu());
            result = scanner.nextLine();
        }
        return result;
    }

    private String chooseFromMenu() {
        System.out.println(SettingPage.showPage());
        String result = scanner.nextLine();
        while (!result.equals("setting privacy") && !result.equals("delete account") &&
                !result.equals("log out") && !result.equals("back") &&
                !result.equals("exit")
        ) {
            System.out.println("Wrong input please try again");
            System.out.println(SettingPage.showPage());
            result = scanner.nextLine();
        }
        return result;
    }

    private void processDeleteAccount() {
        settingPage.deleteAccount();
        settingPage.getManager().getAccounts().remove(settingPage.getAccount());
        Singleton.save(settingPage.getManager());
        settingPage.getManager().goToLoginPage();
        System.exit(0);
    }
}

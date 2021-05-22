package cLI;

import logic.Singleton;
import logic.pages.MenuPage;

public class MenuCLI extends CLI {

    private final MenuPage menuPage;

    public MenuCLI(MenuPage menuPage) {
        super();
        this.menuPage = menuPage;
    }

    @Override
    public void run() {
        while (true) {
        myLogger.debug(MenuCLI.class.getName(), "run",
                "Menu page ran for account \"" + menuPage.getAccount().toString() + "\"");
            System.out.println(MenuPage.showPage());
            String input = chooseFromMenu();
            if (input.equals("personal")) {
                menuPage.getManager().gotoPersonalPage(menuPage.getAccount());
            } else if (input.equals("time line")) {
                menuPage.getManager().goToTimeLinePage(menuPage.getAccount());
            } else if (input.equals("explorer")) {
                menuPage.getManager().goToExplorerPage(menuPage.getAccount());
            } else if (input.equals("messages")) {
                menuPage.getManager().goToMessagesPage(menuPage.getAccount());
            } else if (input.equals("setting")) {
                menuPage.getManager().goToSettingPage(menuPage.getAccount());
            } else if (input.equals("quit")) {
                menuPage.getAccount().setOnline(false);
                Singleton.save(menuPage.getManager());
                menuPage.getManager().goToLoginPage();
                System.exit(0);
            } else if (input.equals("exit")) {
                menuPage.getAccount().setOnline(false);
                Singleton.save(menuPage.getManager());
                System.exit(0);
            }
        }
    }

    private String chooseFromMenu() {
        System.out.println("Choose from menu:");
        String result = scanner.nextLine();
        while (!result.equals("personal") && !result.equals("time line") &&
                !result.equals("explorer") && !result.equals("messages") &&
                !result.equals("setting") && !result.equals("quit") &&
                !result.equals("exit")
        ) {
            System.err.println("Wrong input please try again");
            System.out.println(MenuPage.showPage());
            result = scanner.nextLine();
        }
        return result;
    }
}

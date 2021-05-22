package logic.pages;

import logic.Account;
import logic.Manager;
import logic.Tweet;

import java.util.LinkedList;
import java.util.Random;

public class ExplorerPage extends Page {

    private Random random;

    public ExplorerPage() {
    }

    public ExplorerPage(Account account, Manager manager) {
        super(account, manager);
        random = new Random();
        account.setExplorerPage(this);
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public static String showPage() {
        return  "Explorer:\n" +
                "1.search\n" +
                "2.random tweets\n" +
                "3.quit\n" +
                "4.exit\n" +
                "5.back";
    }

    public LinkedList<Tweet> randomTweets(Manager manager) {
        LinkedList<Tweet> explorerTweets = new LinkedList<>();
        LinkedList<Account> explorerAccount = new LinkedList<>();
        for (Account managerAccount : manager.getAccounts()) {
            if (managerAccount.isPagePublic() && managerAccount.isActive() &&
                    managerAccount.getMyTweets().size() > 1 && !managerAccount.hasBlocked(account)) {
                explorerAccount.add(managerAccount);
            }
        }
        if (explorerAccount.isEmpty()) return null;
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(explorerAccount.size());
            Account account = explorerAccount.get(index);
            Tweet newTweet = account.getMyTweets().get(random.nextInt(account.getMyTweets().size()));
            explorerTweets.add(newTweet);
        }
        return explorerTweets;
    }
}

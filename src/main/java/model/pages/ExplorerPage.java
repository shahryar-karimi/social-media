package model.pages;

import logic.Manager;
import model.Account;
import model.Tweet;

import java.util.LinkedList;
import java.util.Random;

public class ExplorerPage extends Page {

    public ExplorerPage(Account account, Manager manager) {
        super(account, manager);
        account.setExplorerPage(this);
    }

    public LinkedList<Tweet> getRandomTweets() {
        Random random = new Random();
        LinkedList<Tweet> explorerTweets = new LinkedList<>();
        LinkedList<Account> explorerAccounts = new LinkedList<>();
        for (Account managerAccount : manager.getAccounts()) {
            if (managerAccount.isPagePublic() && managerAccount.isActive() &&
                    managerAccount.getMyTweets().size() > 0 && !managerAccount.hasBlocked(account) && !account.isMute(managerAccount)) {
                explorerAccounts.add(managerAccount);
            }
        }
        if (explorerAccounts.isEmpty()) return explorerTweets;
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(explorerAccounts.size());
            Account account = explorerAccounts.get(index);
            Tweet newTweet = account.getMyTweets().get(random.nextInt(account.getMyTweets().size()));
            explorerTweets.add(newTweet);
        }
        return explorerTweets;
    }
}

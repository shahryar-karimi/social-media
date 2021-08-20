package logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Account;
import model.Tweet;
import model.pages.messenger.ChatRoom;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class DBManger {
    private static Gson gson;
    private static final Object locker = new Object();

    private DBManger() {
    }

    public static Gson getGson() {
        synchronized (locker) {
            if (gson == null) gson = new GsonBuilder().setPrettyPrinting().create();
            return gson;
        }
    }

    public static void save(Manager manager) {
        synchronized (locker) {
            try {
                saving(manager);
                FileWriter fileWriter = new FileWriter("SAVE.json");
                getGson().toJson(manager, fileWriter);
                fileWriter.flush();
                fileWriter.close();
            } catch (Exception e) {
                System.err.println("Didn't save program");
            }
        }
    }

    private static void saving(Manager manager) {
        for (Account account : manager.getAccounts())
            account.setAllLists();
    }

    public static Manager load() {
        synchronized (locker) {
            Manager manager = null;
            try {
                FileReader fileReader = new FileReader("SAVE.json");
                manager = getGson().fromJson(fileReader, Manager.class);
                if (manager != null) loadManager(manager);
            } catch (Exception e) {
                System.err.println("Didn't load");
            }
            return manager;
        }
    }

    private static void loadManager(Manager manager) {
        for (Account account : manager.getAccounts()) {
            loadFollower(manager, account);
            loadFollowing(manager, account);
            loadBlackList(manager, account);
            loadMutedPeople(manager, account);
            loadFriendsLists(manager, account);
            loadPersonalPage(manager, account);
            loadMenuPage(manager, account);
            loadMessagesPage(manager, account);
            loadTimeLinePage(manager, account);
            loadExplorerPage(manager, account);
            loadSettingPage(manager, account);
            loadInfo(manager, account);
            loadNotifications(manager, account);
            loadChatRooms(manager, account);
            loadTweets(manager, account);
            loadMessages(account);
        }
        for (Account account : manager.getAccounts()) {
            loadRetweetedTweets(account);
        }
    }

    private static void loadChatRooms(Manager manager, Account account) {
        for (ChatRoom chatRoom : account.getMessengersPage().getChatRooms()) {
            chatRoom.setManager(manager);
            chatRoom.setAccount(account);
            chatRoom.setListener(manager.searchByUserName(chatRoom.getListenerUserName()));
        }
    }

    private static void loadNotifications(Manager manager, Account account) {
        account.getPersonalPage().getNotification().setAccount(account);
        account.getPersonalPage().getNotification().setManager(manager);
    }

    private static void loadInfo(Manager manager, Account account) {
        account.getPersonalPage().getInfo().setAccount(account);
        account.getPersonalPage().getInfo().setManager(manager);
    }

    private static void loadSettingPage(Manager manager, Account account) {
        account.getSettingPage().setAccount(account);
        account.getSettingPage().setManager(manager);
    }

    private static void loadExplorerPage(Manager manager, Account account) {
        account.getExplorerPage().setAccount(account);
        account.getExplorerPage().setManager(manager);
    }

    private static void loadTimeLinePage(Manager manager, Account account) {
        account.getTimeLinePage().setAccount(account);
        account.getTimeLinePage().setManager(manager);
        LinkedList<Tweet> tweets = new LinkedList<>();
        for (Account following : account.getFollowings()) {
            tweets.addAll(following.getMyTweets());
        }
        tweets.addAll(account.getMyTweets());
        Collections.sort(tweets);
        account.getTimeLinePage().setTweets(tweets);
        account.getTimeLinePage().setIndexOfTweet(account.getTimeLinePage().getIndexOfTweet());
    }

    private static void loadMessagesPage(Manager manager, Account account) {
        account.getMessengersPage().setAccount(account);
        account.getMessengersPage().setManager(manager);
    }

    private static void loadMenuPage(Manager manager, Account account) {
        account.getMenuPage().setAccount(account);
        account.getMenuPage().setManager(manager);
    }

    private static void loadPersonalPage(Manager manager, Account account) {
        account.getPersonalPage().setAccount(account);
        account.getPersonalPage().setManager(manager);
    }

    private static void loadFriendsLists(Manager manager, Account account) {
        HashMap<String, ArrayList<Account>> friendsList = new HashMap<>();
        for (String listsName : account.getFriendsListsUserName().keySet()) {
            ArrayList<Account> accounts = new ArrayList<>();
            for (String userName : account.getFriendsListsUserName().get(listsName)) {
                accounts.add(manager.searchByUserName(userName));
            }
            friendsList.put(listsName, accounts);
        }
        account.setFriendsList(friendsList);
    }

    private static void loadMutedPeople(Manager manager, Account account) {
        ArrayList<Account> mutedPeople = new ArrayList<>();
        for (String userName : account.getMutedPeoplesUserName()) {
            mutedPeople.add(manager.searchByUserName(userName));
        }
        account.setMutedPeople(mutedPeople);
    }

    private static void loadBlackList(Manager manager, Account account) {
        LinkedList<Account> blackList = new LinkedList<>();
        for (String userName : account.getBlackListsUserName()) {
            blackList.add(manager.searchByUserName(userName));
        }
        account.setBlackList(blackList);
    }

    private static void loadFollowing(Manager manager, Account account) {
        LinkedList<Account> followings = new LinkedList<>();
        for (String userName : account.getFollowingsUserName()) {
            followings.add(manager.searchByUserName(userName));
        }
        account.setFollowings(followings);
    }

    private static void loadFollower(Manager manager, Account account) {
        LinkedList<Account> followers = new LinkedList<>();
        for (String userName : account.getFollowersUserName()) {
            followers.add(manager.searchByUserName(userName));
        }
        account.setFollowers(followers);
    }

    private static void loadMessages(Account account) {
        LinkedList<ChatRoom> chatRooms = account.getMessengersPage().getChatRooms();
        for (ChatRoom chatRoom : chatRooms) {
            Collections.sort(chatRoom.getMessages());
        }
        Collections.sort(chatRooms);
    }

    private static void loadTweets(Manager manager, Account account) {
        for (Tweet tweet : account.getMyTweets()) {
            tweet.setSubTweets(new LinkedList<>());
            setAccountForTweet(manager, tweet, manager.searchByUserName(tweet.getOwnerUserName()));
            setFavesSetForTweet(manager, tweet);
            TweetManager.addTweet(tweet);
        }
    }

    private static void setFavesSetForTweet(Manager manager, Tweet tweet) {
        if (!tweet.isRetweet()) {
            ArrayList<Account> favesSet = new ArrayList<>();
            for (String userName : tweet.getFaveSetUserName())
                favesSet.add(manager.searchByUserName(userName));
            tweet.setFavesSet(favesSet);
            for (Tweet comment : tweet.getComments()) {
                setFavesSetForTweet(manager, comment);
            }
        }
    }

    private static void setAccountForTweet(Manager manager, Tweet tweet, Account account) {
        tweet.setAccount(account);
        for (Tweet comment : tweet.getComments()) {
            if (comment.isRetweet()) {
                comment.setRetweeter(account);
            }
            setAccountForTweet(manager, comment, manager.searchByUserName(comment.getOwnerUserName()));
        }
    }

    private static void loadRetweetedTweets(Account account) {
        for (Tweet tweet : account.getMyTweets()) {
            if (tweet.isRetweet()) {
                tweet.setSuperTweet(Objects.requireNonNull(TweetManager.searchTweetById(tweet.getSuperTweetId())));
                tweet.setRetweeter(account);
                tweet.getSuperTweet().getSubTweets().add(tweet);
                tweet.setFavesSet(tweet.getSuperTweet().getFavesSet());
            }
        }
    }
}
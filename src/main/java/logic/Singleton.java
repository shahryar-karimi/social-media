package logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import logic.pages.messenger.ChatRoom;
import logic.pages.messenger.Message;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class Singleton {
    private static Gson gson;

    private Singleton() {
    }

    public static Gson getGson() {
        if (gson == null) gson = new GsonBuilder().setPrettyPrinting().create();
        return gson;
    }

    public static void save(Manager manager) {
        try {
            saving(manager);
            FileWriter fileWriter = new FileWriter("SAVE.json");
            getGson().toJson(manager, fileWriter);
            fileWriter.flush();
        } catch (Exception e) {
            System.err.println("Didn't save program");
        }
    }

    private static void saving(Manager manager) {
        for (Account account : manager.getAccounts()) {
            account.setAllLists();
            for (ChatRoom chatRoom : account.getMessagesPage().getChatRooms()) {
                for (Message message : chatRoom.getMessages()) {
                    if (message.getOwner().equals(account))
                        message.setOwnerUserName(account.getUserName());
                }
            }
        }
    }

    public static Manager loading() {
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

    private static void loadManager(Manager manager) {
        for (Account account : manager.getAccounts()) {
            loadFollower(manager, account);

            loadFollowing(manager, account);

            loadBlackList(manager, account);

            loadMutedPeople(manager, account);

            loadFriendsLists(manager, account);

            laodPersonalPage(manager, account);

            loadMenuPage(manager, account);

            loadMessagesPage(manager, account);

            loadTimeLinePage(manager, account);

            loadExplorerPage(manager, account);

            loadSettingPage(manager, account);

            loadInfo(manager, account);

            loadNotifications(manager, account);

            loadChatRooms(manager, account);

            loadTweets(account);

            loadMessages(manager, account);
        }
    }

    private static void loadChatRooms(Manager manager, Account account) {
        for (ChatRoom chatRoom : account.getMessagesPage().getChatRooms()) {
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
    }

    private static void loadMessagesPage(Manager manager, Account account) {
        account.getMessagesPage().setAccount(account);
        account.getMessagesPage().setManager(manager);
    }

    private static void loadMenuPage(Manager manager, Account account) {
        account.getMenuPage().setAccount(account);
        account.getMenuPage().setManager(manager);
    }

    private static void laodPersonalPage(Manager manager, Account account) {
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
        for (String userName : account.getMutedPeoplesUSerName()) {
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

    private static void loadMessages(Manager manager, Account account) {
        for (ChatRoom chatRoom : account.getMessagesPage().getChatRooms()) {
            for (Message message : chatRoom.getMessages()) {
                if (message.getOwnerUserName().equals(account.getUserName()))
                    message.setOwner(account);
                else
                    message.setOwner(manager.searchByUserName(chatRoom.getListenerUserName()));
            }
        }
    }

    private static void loadTweets(Account account) {
        for (Tweet tweet : account.getMyTweets()) {
            setAccountForTweet(tweet, account);
        }
    }

    private static void setAccountForTweet(Tweet tweet, Account account) {
        tweet.setAccount(account);
        for (Tweet comment : tweet.getComments()) {
            setAccountForTweet(comment, account);
        }
    }
}

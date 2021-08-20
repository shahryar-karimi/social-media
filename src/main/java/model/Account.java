package model;

import logic.Manager;
import model.pages.ExplorerPage;
import model.pages.MenuPage;
import model.pages.SettingPage;
import model.pages.TimeLinePage;
import model.pages.messenger.MessengersPage;
import model.pages.personal.PersonalPage;

import java.time.LocalDateTime;
import java.util.*;

public class Account {
    private String firstName;
    private String lastName;
    private String userName;
    private final int id;
    private String password;
    private String emailAddress;
    private String phoneNumber;
    private String bio;
    private boolean isOnline;
    private String birthDate;
    private String lastSeen;
    private boolean isPagePublic;
    private boolean isActive;
    private String lastSeenSituation;

    private LinkedList<String> followersUserName;
    private LinkedList<String> followingsUserName;
    private LinkedList<String> blackListsUserName;
    private ArrayList<String> mutedPeoplesUSerName;
    private HashMap<String, ArrayList<String>> friendsListsUserName;

    private transient LinkedList<Account> followers;
    private transient LinkedList<Account> followings;
    private transient LinkedList<Account> blackList;
    private transient ArrayList<Account> mutedPeople;
    private transient HashMap<String, ArrayList<Account>> friendsList;

    private TimeLinePage timeLinePage;
    private SettingPage settingPage;
    private PersonalPage personalPage;
    private MessengersPage messengersPage;
    private ExplorerPage explorerPage;
    private MenuPage menuPage;

    private final LinkedList<Tweet> myTweets;

    public Account(Manager manager, String firstName, String lastName, String userName, int id, String password, String emailAddress, String phoneNumber, String bio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.id = id;
        this.password = password;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.bio = bio;
        this.birthDate = "";
        this.lastSeen = "";
        this.isPagePublic = true;
        this.isOnline = false;
        this.isActive = true;
        this.lastSeenSituation = "every one";
        this.followersUserName = new LinkedList<>();
        this.followingsUserName = new LinkedList<>();
        this.blackListsUserName = new LinkedList<>();
        this.mutedPeoplesUSerName = new ArrayList<>();
        this.friendsListsUserName = new HashMap<>();

        this.followers = new LinkedList<>();
        this.followings = new LinkedList<>();
        this.blackList = new LinkedList<>();
        this.mutedPeople = new ArrayList<>();
        this.friendsList = new HashMap<>();
        this.timeLinePage = new TimeLinePage(this, manager, true);
        this.settingPage = new SettingPage(this, manager);
        this.personalPage = new PersonalPage(this, manager);
        this.messengersPage = new MessengersPage(this, manager);
        this.explorerPage = new ExplorerPage(this, manager);
        this.menuPage = new MenuPage(this, manager);
        this.myTweets = new LinkedList<>();
    }

    public LinkedList<String> getBlackListsUserName() {
        return blackListsUserName;
    }

    public ArrayList<String> getMutedPeoplesUserName() {
        return mutedPeoplesUSerName;
    }

    public HashMap<String, ArrayList<String>> getFriendsListsUserName() {
        return friendsListsUserName;
    }

    public LinkedList<String> getFollowersUserName() {
        return followersUserName;
    }

    public LinkedList<String> getFollowingsUserName() {
        return followingsUserName;
    }

    public TimeLinePage getTimeLinePage() {
        return timeLinePage;
    }

    public void setTimeLinePage(TimeLinePage timeLinePage) {
        this.timeLinePage = timeLinePage;
    }

    public SettingPage getSettingPage() {
        return settingPage;
    }

    public void setSettingPage(SettingPage settingPage) {
        this.settingPage = settingPage;
    }

    public PersonalPage getPersonalPage() {
        return personalPage;
    }

    public void setPersonalPage(PersonalPage personalPage) {
        this.personalPage = personalPage;
    }

    public MessengersPage getMessengersPage() {
        return messengersPage;
    }

    public void setMessagesPage(MessengersPage messengersPage) {
        this.messengersPage = messengersPage;
    }

    public ExplorerPage getExplorerPage() {
        return explorerPage;
    }

    public void setExplorerPage(ExplorerPage explorerPage) {
        this.explorerPage = explorerPage;
    }

    public HashMap<String, ArrayList<Account>> getFriendsList() {
        return friendsList;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isPagePublic() {
        return isPagePublic;
    }

    public MenuPage getMenuPage() {
        return menuPage;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getBio() {
        return bio;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public String getLastSeen(Account account) {
        if (userName.equals(account.getUserName())) return getLastSeen();
        if (getLastSeenSituation().equals("every one")) return getLastSeen();
        else if (getLastSeenSituation().equals("followings")) {
            if (isFollow(account)) return getLastSeen();
            else return "last seen recently";
        } else return "last seen recently";
    }

    public LinkedList<Tweet> getMyTweets() {
        return myTweets;
    }

    public LinkedList<Account> getFollowers() {
        return followers;
    }

    public LinkedList<Account> getFollowings() {
        return followings;
    }

    public LinkedList<Account> getBlackList() {
        return blackList;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public ArrayList<Account> getMutedPeople() {
        return mutedPeople;
    }

    public String getLastSeenSituation() {
        return lastSeenSituation;
    }

    public void setAllLists() {
        followingsUserName = new LinkedList<>();
        followersUserName = new LinkedList<>();
        blackListsUserName = new LinkedList<>();
        mutedPeoplesUSerName = new ArrayList<>();
        friendsListsUserName = new HashMap<>();
        for (Account following : followings) {
            followingsUserName.add(following.getUserName());
        }
        for (Account follower : followers) {
            followersUserName.add(follower.getUserName());
        }
        for (Account account : blackList) {
            blackListsUserName.add(account.getUserName());
        }
        for (Account mutedPerson : mutedPeople) {
            mutedPeoplesUSerName.add(mutedPerson.getUserName());
        }
        for (String listsName : friendsList.keySet()) {
            ArrayList<String> accountsUserName = new ArrayList<>();
            for (Account account : friendsList.get(listsName)) {
                accountsUserName.add(account.getUserName());
            }
            friendsListsUserName.put(listsName, accountsUserName);
        }
    }

    public String follow(Account account) {
        for (Account blockedAccount : account.blackList) {
            if (this.equals(blockedAccount)) return "You are blocked so you can not follow this page!";
        }
        for (Tweet accountsTweet : account.getMyTweets()) {
            getTimeLinePage().addTweet(accountsTweet);
        }
        Collections.sort(getTimeLinePage().getTweets());
        getTimeLinePage().setIndexOfTweet(getTimeLinePage().getTweets().size() - 1);
        followings.add(account);
        account.followers.add(this);
        account.getPersonalPage().getNotification().addSystemMessage(userName + " followed you");
        return "You followed this page successfully!";
    }

    public void addAnAccountToBlackList(Account account) {
        blackList.add(account);
    }

    public String unFollow(Account account, boolean canSendNotification) {
        for (int i = 0; i < getTimeLinePage().getTweets().size(); i++) {
            if (getTimeLinePage().getTweets().get(i).getAccount().equals(account)) {
                getTimeLinePage().removeTweetByIndex(i);
                i--;
            }
        }
        followings.remove(account);
        account.followers.remove(this);
        if (canSendNotification)
            account.getPersonalPage().getNotification().addSystemMessage(userName + " unfollowed you");
        return "You unfollowed this page";
    }

    public void addTweet(Tweet tweet) {
        myTweets.add(tweet);
        timeLinePage.addTweet(tweet);
    }

    public void setFriendsList(HashMap<String, ArrayList<Account>> friendsList) {
        this.friendsList = friendsList;
    }

    public void setLastSeenSituation(String lastSeenSituation) {
        this.lastSeenSituation = lastSeenSituation;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFollowers(LinkedList<Account> followers) {
        this.followers = followers;
    }

    public void setFollowings(LinkedList<Account> followings) {
        this.followings = followings;
    }

    public void setBlackList(LinkedList<Account> blackList) {
        this.blackList = blackList;
    }

    public void setMutedPeople(ArrayList<Account> mutedPeople) {
        this.mutedPeople = mutedPeople;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setPagePublic(boolean pagePublic) {
        isPagePublic = pagePublic;
    }

    public void setMenuPage(MenuPage menuPage) {
        this.menuPage = menuPage;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setOnline(boolean online) {
        isOnline = online;
        if (isOnline) lastSeen = "online";
        else lastSeen = LocalDateTime.now().toString().replace('T', ' ').substring(0, 19);
    }

    @Override
    public String toString() {
        return userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return getUserName().equals(account.getUserName()) && getId() == account.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getId());
    }

    public String unBlock(Account account) {
        for (Account blockedAccount : blackList) {
            if (account.equals(blockedAccount)) {
                blackList.remove(account);
                return "You unblocked " + account;
            }
        }
        return "Account not found";
    }

    public String block(Account account) {
        addAnAccountToBlackList(account);
        account.unFollow(this, false);
        this.unFollow(account, false);
        return "You blocked this page";
    }

    public void unMute(Account account) {
        for (Account mutedPerson : mutedPeople) {
            if (mutedPerson.equals(account)) {
                mutedPeople.remove(account);
                return;
            }
        }
    }

    public void mute(Account account) {
        mutedPeople.add(account);
    }

    public String report(Account account) {
        block(account);
        return "You report this page successfully";
    }

    public boolean isMute(Account anotherAccount) {
        for (Account mutedPerson : mutedPeople)
            if (mutedPerson.equals(anotherAccount)) return true;
        return false;
    }

    public boolean hasBlocked(Account account) {
        for (Account blockedUser : blackList)
            if (blockedUser.equals(account)) return true;
        return false;
    }

    public boolean isFollow(Account account) {
        return searchFollowingByUserName(account.getUserName()) != null;
    }

    public Account searchFollowingByUserName(String userName) {
        for (Account following : followings)
            if (following.getUserName().equals(userName) && following.isActive) return following;
        return null;
    }

    public boolean isValidToSendMessage(Account account) {
        if (account.equals(this)) return true;
        return (isFollow(account) || account.isFollow(this)) &&
                !account.hasBlocked(this);
    }

    public String sendRequestTo(Account account) {
        if (account.isActive) {
            account.getPersonalPage().getNotification().addRequest(getUserName());
            return "Your request sent";
        } else {
            return "Failed to send request";
        }
    }
}

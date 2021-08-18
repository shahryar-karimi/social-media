package logic;

import model.Tweet;

import java.util.HashSet;

public class TweetManager {
    private static int counter = 0;
    public static final HashSet<Tweet> tweets = new HashSet<>();

    public static Tweet searchTweetById(int tweetId) {
        for (Tweet tweet : tweets)
            if (tweet.getTweetId() == tweetId)
                return tweet;
        return null;
    }

    public static void addTweet(Tweet tweet) {
        tweets.add(tweet);
        tweet.setTweetId(counter++);
    }
}

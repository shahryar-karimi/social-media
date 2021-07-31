package utility;

import javax.swing.*;
import java.util.HashMap;

public class ImageLoader {
    private static final HashMap<String, ImageIcon> tweetsIcons = new HashMap<>();
    private static final HashMap<String, ImageIcon> others = new HashMap<>();

    private static void init() {
        initTweetsIcons();
        initOthers();
    }

    private static void initOthers() {
        others.put("refresh", new ImageIcon("src/main/resources/pictures/icons8-refresh.png"));
    }

    private static void initTweetsIcons() {
        tweetsIcons.put("comments", new ImageIcon("src/main/resources/pictures/comments.png"));
        tweetsIcons.put("green-like", new ImageIcon("src/main/resources/pictures/green-like.png"));
        tweetsIcons.put("paper-plane", new ImageIcon("src/main/resources/pictures/paper-plane.png"));
        tweetsIcons.put("red-like", new ImageIcon("src/main/resources/pictures/red-like.png"));
        tweetsIcons.put("retweet", new ImageIcon("src/main/resources/pictures/retweet.png"));
    }

    public static HashMap<String, ImageIcon> getTweetsIcons() {
        if (tweetsIcons.isEmpty()) initTweetsIcons();
        return tweetsIcons;
    }

    public static HashMap<String, ImageIcon> getOthers() {
        if (others.isEmpty()) initOthers();
        return others;
    }
}

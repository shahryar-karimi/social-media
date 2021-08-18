package view.pages.personalPage.controller;

import model.Tweet;
import model.pages.personal.PersonalPage;
import view.controller.MainGraphicController;

public class ProfileController extends MainGraphicController {
    public ProfileController(PersonalPage page) {
        super(page);
    }

    public String sendBtn(String tweetText) {
        Tweet newTweet = ((PersonalPage) page).writeNewTweet(tweetText);
        ((PersonalPage) page).sendingATweet(newTweet, true);
        return "Your tweet sent!";
    }
}

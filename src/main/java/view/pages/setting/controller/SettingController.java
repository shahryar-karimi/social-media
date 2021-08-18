package view.pages.setting.controller;

import model.pages.SettingPage;
import view.controller.MainGraphicController;

public class SettingController extends MainGraphicController {
    public SettingController(SettingPage page) {
        super(page);
    }

    public String settingPrivacy(String item) {
        ((SettingPage) page).setPagePrivacy(item.equals("public"));
        return "Your page privacy set " + item;
    }

    public String settingLastSeen(String item) {
        ((SettingPage) page).setLastSeenPrivacy(item);
        return "Your last seen privacy set " + item;
    }

    public String SettingActivity(String item) {
        if (item.equals("Activate")) {
            ((SettingPage) page).setActivity(true);
            return "Your page activated";
        } else {
            ((SettingPage) page).setActivity(false);
            return "Your page deactivated";
        }
    }

    public String deleteAccount() {
        ((SettingPage) page).deleteAccount();
        return "You deleted your account";
    }

    public String checkPassword(String lastPass) {
        if (lastPass.equals(page.getAccount().getPassword()))
            return "Last password confirmed\nEnter your new Pass";
        else
            return "Wrong last pass";
    }

    public String updatePassword(String newPass) {
        page.getAccount().setPassword(newPass);
        page.getManager().save();
        return "Password changed successfully";
    }
}

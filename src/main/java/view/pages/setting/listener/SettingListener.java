package view.pages.setting.listener;

import event.Event;
import listener.FormListener;
import view.pages.setting.controller.SettingController;
import view.pages.setting.events.*;

public class SettingListener extends FormListener {
    public SettingListener(SettingController controller) {
        this.controller = controller;
    }

    @Override
    public String eventOccurred(Event event) {
        if (event instanceof SettingPrivacyEvent){
            SettingPrivacyEvent settingPrivacyEvent = (SettingPrivacyEvent) event;
            return ((SettingController) controller).settingPrivacy(settingPrivacyEvent.getItem());
        } else if (event instanceof SettingLastSeenEvent) {
            SettingLastSeenEvent settingLastSeenEvent = (SettingLastSeenEvent) event;
            return ((SettingController) controller).settingLastSeen(settingLastSeenEvent.getItem());
        } else if (event instanceof DeleteAccountEvent) {
            return ((SettingController) controller).deleteAccount();
        } else if (event instanceof SettingActivityEvent) {
            SettingActivityEvent settingActivityEvent = (SettingActivityEvent) event;
            return ((SettingController) controller).SettingActivity(settingActivityEvent.getItem());
        } else if (event instanceof CheckPasswordEvent) {
            CheckPasswordEvent checkPasswordEvent = (CheckPasswordEvent) event;
            return ((SettingController) controller).checkPassword(checkPasswordEvent.getLastPass());
        } else if (event instanceof UpdatePasswordEvent) {
            UpdatePasswordEvent updatePasswordEvent = (UpdatePasswordEvent) event;
            return ((SettingController) controller).updatePassword(updatePasswordEvent.getNewPass());
        }
        return null;
    }
}

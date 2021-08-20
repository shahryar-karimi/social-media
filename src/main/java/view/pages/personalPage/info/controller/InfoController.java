package view.pages.personalPage.info.controller;

import model.Account;
import model.pages.messenger.ChatRoom;
import model.pages.personal.Info;
import view.controller.MainGraphicController;
import view.pages.personalPage.info.view.ChangeListFrame;

public class InfoController extends MainGraphicController {

    public InfoController(Info info) {
        super(info);
    }

    public String changeFollow(Account owner, Account visitor) {
        if (visitor.isFollow(owner)) {
            return visitor.unFollow(owner, true);
        } else if (!owner.hasBlocked(visitor)) {
            if (owner.isPagePublic()) {
                return visitor.follow(owner);
            } else {
                return visitor.sendRequestTo(owner);
            }
        } else {
            return "You are blocked";
        }
    }

    public String block(Account owner, Account visitor) {
        if (visitor.hasBlocked(owner)) return visitor.unBlock(owner);
        else return visitor.block(owner);
    }

    public String sendMessage(Account owner, Account visitor) {
        ChatRoom chatRoom = visitor.getMessengersPage().searchChatRoomByListener(owner);
        if (chatRoom == null) {
            chatRoom = visitor.getMessengersPage().buildNewChatRoom(owner);
            if (chatRoom == null) {
                return "You can not send message to this account";
            }
        }
        page.getManager().goToChatRoom(chatRoom);
        return null;
    }

    public String report(Account owner, Account visitor) {
        return visitor.report(owner);
    }

    public String mute(Account owner, Account visitor) {
        if (visitor.isMute(owner)) {
            visitor.unMute(owner);
            return "You unMuted \"" + owner + "\" successfully";
        } else {
            visitor.mute(owner);
            return "You muted \"" + owner + "\" successfully";
        }
    }

    public String changeList(Account owner, Account visitor) {
        new ChangeListFrame(owner, visitor);
        return null;
    }
}

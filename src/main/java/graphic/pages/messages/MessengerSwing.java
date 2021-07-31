package graphic.pages.messages;

import graphic.FooterPanel;
import graphic.pages.Swing;
import logic.pages.messenger.MessagesPage;

import java.awt.event.ActionEvent;

public class MessengerSwing extends Swing {

    public MessengerSwing(MessagesPage messagesPage) {
        super();
        this.page = messagesPage;
        footerPanel = new FooterPanel(getManager(), page.getManager(), page.getAccount());
        addSwing(this);
        run();
    }

    @Override
    public void run() {
        myLogger.debug(MessengerSwing.class.getName(), "run",
                "messages page run for account \"" + page.getAccount().toString() + "\"");
        showGraphic();
    }

    @Override
    public void showGraphic() {
        //TODO
    }

    @Override
    public void updateGraphic() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

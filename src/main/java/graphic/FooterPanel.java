package graphic;

import logic.Account;
import logic.Manager;
import utility.AppProperties;

import javax.swing.*;

public class FooterPanel extends JPanel {

    private GraphicManager graphicManager;
    private Manager manager;

    private final JButton[] buttons = new JButton[]{
            new JButton(AppProperties.getInstance().getProperty("back")),
            new JButton(AppProperties.getInstance().getProperty("home")),
            new JButton(AppProperties.getInstance().getProperty("Exit"))
    };

    public FooterPanel(GraphicManager graphicManager, Manager manager, Account account) {
        this.graphicManager = graphicManager;
        buttons[0].addActionListener(e -> {
            if (FooterPanel.this.graphicManager.getSwings().size() > 1) {
                FooterPanel.this.graphicManager.back();
            }
        });
        buttons[1].addActionListener(e -> {

        });
        buttons[2].addActionListener(e -> {
            manager.exit(account);
        });
        add(buttons[0]);
        add(buttons[1]);
        add(buttons[2]);

    }
}

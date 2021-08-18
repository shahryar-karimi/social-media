package view.panels.footerPanel.view;

import view.panels.footerPanel.event.FooterPanelEvent;
import view.panels.footerPanel.listener.FooterPanelListener;
import utility.AppProperties;

import javax.swing.*;

public class FooterPanel extends JPanel {
    private final FooterPanelListener listener;

    public FooterPanel(FooterPanelListener listener) {
        this.listener = listener;
        JButton[] buttons = new JButton[]{
                new JButton(AppProperties.getInstance().getProperty("back")),
                new JButton(AppProperties.getInstance().getProperty("home")),
                new JButton(AppProperties.getInstance().getProperty("Exit"))
        };
        buttons[0].addActionListener(e -> sendEvent("back"));
        buttons[1].addActionListener(e -> sendEvent("home"));
        buttons[2].addActionListener(e -> sendEvent("exit"));
        for (JButton button : buttons)
            add(button);
    }

    private String sendEvent(String work) {
        FooterPanelEvent event = new FooterPanelEvent(this, work);
        return listener.eventOccurred(event);
    }
}

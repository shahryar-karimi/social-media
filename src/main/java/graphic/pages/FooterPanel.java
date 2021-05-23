package graphic.pages;

import graphic.GraphicManager;
import logic.Singleton;
import logic.pages.personal.PersonalPage;
import utility.AppProperties;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FooterPanel extends JPanel {

    private GraphicManager graphicManager;

    private final JButton[] buttons = new JButton[]{
            new JButton(AppProperties.getInstance().getProperty("back")),
            new JButton(AppProperties.getInstance().getProperty("home")),
            new JButton(AppProperties.getInstance().getProperty("Exit"))
    };

    public FooterPanel(GraphicManager graphicManager) {
        this.graphicManager = graphicManager;
        buttons[0].addActionListener(e -> {
            FooterPanel.this.graphicManager.back();
        });
        buttons[1].addActionListener(e -> {

        });
        buttons[2].addActionListener(e -> {

        });
        add(buttons[0]);
        add(buttons[1]);
        add(buttons[2]);

    }
}

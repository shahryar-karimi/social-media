package graphic.Pages;

import logic.Singleton;
import logic.pages.personal.PersonalPage;
import utility.AppProperties;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FooterPanel extends JPanel {

    private final JButton[] buttons = new JButton[]{
            new JButton(AppProperties.getInstance().getProperty("back")),
            new JButton(AppProperties.getInstance().getProperty("home")),
            new JButton(AppProperties.getInstance().getProperty("Exit"))
    };
    private final PersonalPage personalPage;
    public FooterPanel(PersonalPage personalPage) {
        this.personalPage = personalPage;


        buttons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttons[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FooterPanel.this.personalPage.getAccount().setOnline(false);
                Singleton.save(FooterPanel.this.personalPage.getManager());
                System.exit(0);
            }
        });
        add(buttons[0]);
        add(buttons[1]);
        add(buttons[2]);

    }
}

package view.pages.menuPage.view;

import utility.AppProperties;
import view.MyFrame;
import view.pages.Swing;
import view.pages.menuPage.event.MenuEvent;
import view.pages.menuPage.listener.MenuListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuSwing extends Swing implements ActionListener {
    private final JButton[] buttons = new JButton[]{
            new JButton("Personal"),
            new JButton("Time line"),
            new JButton("Explorer"),
            new JButton("Messages"),
            new JButton("Setting"),
            new JButton(AppProperties.getInstance().getProperty("Quit")),
            new JButton(AppProperties.getInstance().getProperty("Exit"))
    };
    private final JLabel label = new JLabel("Menu:");
    private MyFrame mainFrame;

    public MenuSwing(MenuListener listener) {
        super();
        this.listener = listener;
        for (JButton button : buttons)
            button.addActionListener(this);
        addSwing(this);
        run();
    }

    @Override
    public void run() {
        myLogger.debug(MenuSwing.class.getName(), "run",
                "Menu page ran for account \"" + getPage().getAccount().getUserName() + "\"");
        showGraphic();
    }

    @Override
    public void showGraphic() {
        label.setFocusable(false);
        label.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.CENTER);

        for (JButton button : buttons) {
            button.setFocusable(false);
            button.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        }

        if (mainFrame != null) mainFrame.dispose();
        mainFrame = new MyFrame();
        mainFrame.setBackground(new Color(12, 70, 56));
        mainFrame.setLayout(new GridLayout(8, 1));
        mainFrame.add(label);
        for (JButton button : buttons) {
            mainFrame.add(button);
        }

        mainFrame.setVisible(true);
    }

    @Override
    public void updateGraphic() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttons[0]) {
            mainFrame.dispose();
            MenuEvent menuEvent = new MenuEvent(this, "Personal");
            listener.eventOccurred(menuEvent);
        } else if (e.getSource() == buttons[1]) {
            mainFrame.dispose();
            MenuEvent menuEvent = new MenuEvent(this, "Time line");
            listener.eventOccurred(menuEvent);
        } else if (e.getSource() == buttons[2]) {
            mainFrame.dispose();
            MenuEvent menuEvent = new MenuEvent(this, "Explorer");
            listener.eventOccurred(menuEvent);
        } else if (e.getSource() == buttons[3]) {
            mainFrame.dispose();
            MenuEvent menuEvent = new MenuEvent(this, "Messages");
            listener.eventOccurred(menuEvent);
        } else if (e.getSource() == buttons[4]) {
            mainFrame.dispose();
            MenuEvent menuEvent = new MenuEvent(this, "Setting");
            listener.eventOccurred(menuEvent);
        } else if (e.getSource() == buttons[5]) {
            mainFrame.dispose();
            MenuEvent menuEvent = new MenuEvent(this, "Quit");
            listener.eventOccurred(menuEvent);
        } else if (e.getSource() == buttons[6]) {
            mainFrame.dispose();
            MenuEvent menuEvent = new MenuEvent(this, "Exit");
            listener.eventOccurred(menuEvent);
        }
    }
}

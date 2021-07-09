package graphic.pages.menuPage;

import graphic.MyFrame;
import graphic.pages.Swing;
import logic.pages.MenuPage;
import utility.AppProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuSwing extends Swing implements ActionListener {

    private MyFrame mainFrame;
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

    public MenuSwing(MenuPage menuPage) {
        super();
        this.page = menuPage;
        for (JButton button : buttons)
            button.addActionListener(this);
        addSwing(this);
        run();
    }

    @Override
    public void run() {
        myLogger.debug(MenuSwing.class.getName(), "run",
                "Menu page ran for account \"" + page.getAccount().toString() + "\"");
        showGraphic();
    }

    @Override
    public void showGraphic() {
        label.setFocusable(false);
        label.setFont(new Font("MV Boli", Font.PLAIN, 25));
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.CENTER);

        for (JButton button : buttons) {
            button.setFocusable(false);
            button.setFont(new Font("MV Boli", Font.PLAIN, 25));
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
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttons[0]) {
            mainFrame.dispose();
            page.getManager().gotoPersonalPage(page.getAccount());
        } else if (e.getSource() == buttons[1]) {
            mainFrame.dispose();
            page.getManager().goToTimeLinePage(page.getAccount());
        } else if (e.getSource() == buttons[2]) {
            mainFrame.dispose();
            page.getManager().goToExplorerPage(page.getAccount());
        } else if (e.getSource() == buttons[3]) {
            mainFrame.dispose();
            page.getManager().goToMessagesPage(page.getAccount());
        } else if (e.getSource() == buttons[4]) {
            mainFrame.dispose();
            page.getManager().goToSettingPage(page.getAccount());
        } else if (e.getSource() == buttons[5]) {
            mainFrame.dispose();
            page.getManager().quit(page.getAccount());
        } else if (e.getSource() == buttons[6]) {
            mainFrame.dispose();
            page.getManager().exit(page.getAccount());
        }
    }
}

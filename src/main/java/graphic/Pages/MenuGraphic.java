package graphic.Pages;

import graphic.GraphicPages;
import graphic.MyFrame;
import logic.pages.MenuPage;
import utility.AppProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGraphic extends GraphicPages implements ActionListener {

    private MenuPage menuPage;
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

    public MenuGraphic(MenuPage menuPage) {
        super();
        this.menuPage = menuPage;
        for (JButton button : buttons)
            button.addActionListener(this);
    }

    public MenuPage getMenuPage() {
        return menuPage;
    }

    public void setMenuPage(MenuPage menuPage) {
        this.menuPage = menuPage;
    }

    @Override
    public void run() {

        myLogger.debug(MenuGraphic.class.getName(), "run",
                "Menu page ran for account \"" + menuPage.getAccount().toString() + "\"");
        processShowMenuPageFrame();
    }

    private void processShowMenuPageFrame() {
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
            menuPage.getManager().gotoPersonalPage(menuPage.getAccount());
        } else if (e.getSource() == buttons[1]) {
            menuPage.getManager().goToTimeLinePage(menuPage.getAccount());
        } else if (e.getSource() == buttons[2]) {
            menuPage.getManager().goToExplorerPage(menuPage.getAccount());
        } else if (e.getSource() == buttons[3]) {
            menuPage.getManager().goToMessagesPage(menuPage.getAccount());
        } else if (e.getSource() == buttons[4]) {
            menuPage.getManager().goToSettingPage(menuPage.getAccount());
        } else if (e.getSource() == buttons[5]) {
            menuPage.getManager().quit(menuPage.getAccount());
        } else if (e.getSource() == buttons[6]) {
            menuPage.getManager().exit(menuPage.getAccount());
        }
    }
}

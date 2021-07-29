package graphic.pages.explorer;

import logic.Account;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Locale;

public class SearchPanel extends JPanel {
    private JList<String> myJList;
    private ArrayList<Account> accounts;
    private DefaultListModel<String> defaultListModel;

    public SearchPanel(ArrayList<Account> accounts) {
        this.accounts = accounts;
        this.myJList = new JList<>();
        this.myJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                myJListMouseClick();
            }
        });
        this.defaultListModel = new DefaultListModel<>();
        binData();
    }

    private void myJListMouseClick() {
//        Account account = page.getManager().searchByUserName(myJList.getSelectedValue());
//        page.getManager().goToInfoPage(account.getPersonalPage().getInfo(), page.getAccount());
    }

    public <T> ArrayList<String> getStars(ArrayList<T> list) {
        ArrayList<String> stars = new ArrayList<>();
        for (T t : list) {
            stars.add(t.toString());
        }
        return stars;
    }

    private void binData() {
        for (String star : getStars(accounts)) {
            defaultListModel.addElement(star);
        }
        myJList.setModel(defaultListModel);
        myJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void searchFilter(String searchTerm) {
        DefaultListModel<String> filteredItems = new DefaultListModel<>();
        ArrayList<String> stars = getStars(accounts);

        stars.forEach((star) -> {
            String starName = stars.toString().toLowerCase(Locale.ROOT);
            if (starName.contains(searchTerm.toLowerCase())) {
                filteredItems.addElement(star);
            }
        });

        defaultListModel = filteredItems;
        myJList.setModel(defaultListModel);
    }

    public void showGraphic() {

    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public JList<String> getMyJList() {
        return myJList;
    }

    public void setMyJList(JList<String> myJList) {
        this.myJList = myJList;
    }

    public DefaultListModel<String> getDefaultListModel() {
        return defaultListModel;
    }

    public void setDefaultListModel(DefaultListModel<String> defaultListModel) {
        this.defaultListModel = defaultListModel;
    }
}

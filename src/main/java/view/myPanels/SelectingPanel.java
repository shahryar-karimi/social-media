package view.myPanels;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.List;

public class SelectingPanel extends JPanel {
    private final MyScrollPane<String> selectingSP;
    private final JScrollPane selectedSP;
    private final JLabel selectedLbl;
    private final JLabel selectingLbl;
    private final JTextArea selectedTextArea;
    private final HashSet<String> selectedAccountsSet;

    public SelectingPanel(List<String> list) {
        selectedLbl = new JLabel();
        selectingLbl = new JLabel();
        selectedSP = new JScrollPane();
        selectedTextArea = new JTextArea();
        selectedAccountsSet = new HashSet<>();
        selectingSP = new MyScrollPane<>(list, false) {
            @Override
            public void listClicked(MouseEvent e) {
                String userName = getMyJList().getSelectedValue();
                if (selectedAccountsSet.contains(userName)) {
                    selectedAccountsSet.remove(userName);
                } else {
                    selectedAccountsSet.add(userName);
                }
                String text = "";
                for (String selectedValues : selectedAccountsSet) {
                    text += selectedValues + "\n";
                }
                selectedTextArea.setText(text);
            }
        };
        showGraphic();
    }

    private void showGraphic() {
        selectedTextArea.setEditable(false);
        selectedSP.setViewportView(selectedTextArea);

        selectedLbl.setText("Selected people : ");

        selectingLbl.setText("Select people you want to add : ");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(selectedLbl)
                                        .addComponent(selectedSP, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(selectingLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(selectingSP))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(selectedLbl)
                                        .addComponent(selectingLbl))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(selectedSP, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                        .addComponent(selectingSP))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    public HashSet<String> getSelectedAccountsSet() {
        return selectedAccountsSet;
    }

    public MyScrollPane<String> getSelectingSP() {
        return selectingSP;
    }

    public JLabel getSelectedLbl() {
        return selectedLbl;
    }

    public JLabel getSelectingLbl() {
        return selectingLbl;
    }
}

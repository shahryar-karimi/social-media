package view.panels;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.List;

public class SelectingPanel extends JPanel {
    private MyScrollPane<String> selectingSP;
    private JScrollPane selectedSP;
    private JLabel selectedLbl;
    private JLabel selectingLbl;
    private JTextArea selectedTextArea;
    private HashSet<String> selectedAccountsSet;

    public SelectingPanel(List<String> list) {
        selectedLbl = new javax.swing.JLabel();
        selectingLbl = new javax.swing.JLabel();
        selectedSP = new javax.swing.JScrollPane();
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(selectedLbl)
                                        .addComponent(selectedSP, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(selectingLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(selectingSP))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(selectedLbl)
                                        .addComponent(selectingLbl))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(selectedSP, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                        .addComponent(selectingSP))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    public HashSet<String> getSelectedAccountsSet() {
        return selectedAccountsSet;
    }

    public MyScrollPane<String> getSelectingSP() {
        return selectingSP;
    }
}

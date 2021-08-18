package view.pages.personalPage.creatingList;

import view.pages.personalPage.creatingList.events.CreateListEvent;
import view.pages.personalPage.creatingList.listener.CreateListListener;
import view.panels.SelectingPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class CreatingListFrame extends JFrame {
    private final CreateListListener listener;
    private JButton createBtn;
    private JLabel header;
    private JTextField nameField;
    private JLabel nameLbl;
    private SelectingPanel selectingPanel;

    public CreatingListFrame(CreateListListener listener) {
        this.listener = listener;
        showGraphic();
    }

    private void showGraphic() {
        LinkedList<String> followings = listener.getController().getPage().getAccount().getFollowingsUserName();
        createBtn = new JButton();
        nameField = new JTextField();
        selectingPanel = new SelectingPanel(followings);
        nameLbl = new JLabel();
        header = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        createBtn.setText("create");
        createBtn.addActionListener(this::createList);
        createBtn.setEnabled(false);

        selectingPanel.getSelectingSP().getMyJList().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                createBtn.setEnabled(!selectingPanel.getSelectedAccountsSet().isEmpty() && !nameField.getText().isBlank());
            }
        });

        nameLbl.setText("Name : ");

        nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                createBtn.setEnabled(!selectingPanel.getSelectedAccountsSet().isEmpty() && !nameField.getText().isBlank());
            }
        });

        header.setFont(new Font("Lucida Grande", Font.BOLD, 18));
        header.setHorizontalAlignment(SwingConstants.CENTER);
        header.setText("Creating List");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(header)
                                                .addGap(190, 190, 190))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(nameLbl)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(nameField))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(selectingPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(createBtn)))
                                                .addGap(0, 6, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(createBtn)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(header)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(nameLbl))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(selectingPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        this.pack();
        this.setVisible(true);
    }

    private void createList(ActionEvent e) {
        CreateListEvent event = new CreateListEvent(this, nameField.getText(), selectingPanel.getSelectedAccountsSet());
        String msg = listener.eventOccurred(event);
        JOptionPane.showMessageDialog(null, msg, "Creating list", JOptionPane.PLAIN_MESSAGE);
        dispose();
    }
}

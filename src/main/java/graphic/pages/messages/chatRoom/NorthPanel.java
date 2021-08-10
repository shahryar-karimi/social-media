package graphic.pages.messages.chatRoom;

import logic.Account;

import javax.swing.*;

public class NorthPanel extends JPanel {
    private JLabel userName;
    private JLabel lastSeen;
    private final Account account;
    private final Account listener;
    
    public NorthPanel(Account account, Account listener) {
        this.account = account;
        this.listener = listener;
        showGraphic();
    }

    private void showGraphic() {
        userName = new JLabel();
        lastSeen = new JLabel();

        userName.setText(listener.getUserName());

        lastSeen.setText(listener.getLastSeen(account));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(235, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(lastSeen, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(userName, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                                .addGap(203, 203, 203))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(userName)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lastSeen))
        );
    }

    public void updateGraphic() {
        userName.setText(listener.getUserName());
        lastSeen.setText(listener.getLastSeen(account));
    }
}

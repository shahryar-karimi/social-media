package graphic.pages.messages.chatRoom;

import javax.swing.*;

public class SouthPanel extends JPanel {
    private JButton sendBtn;
    private JTextArea textArea;

    public SouthPanel() {
        showGraphic();
    }

    private void showGraphic() {
        sendBtn = new JButton();
        JScrollPane jScrollPane1 = new JScrollPane();
        textArea = new JTextArea();

        sendBtn.setText("send");

        textArea.setColumns(20);
        textArea.setRows(1);
        jScrollPane1.setViewportView(textArea);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sendBtn)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(sendBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1))
                                .addContainerGap())
        );
    }

    public JButton getSendBtn() {
        return sendBtn;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void updateGraphic() {
        sendBtn.setEnabled(!textArea.getText().isBlank());
    }

    public void refresh() {
        textArea.setText("");
        updateGraphic();
    }
}

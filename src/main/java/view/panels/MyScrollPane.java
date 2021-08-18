package view.panels;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public abstract class MyScrollPane<T> extends JScrollPane {
    private JList<String> myJList;
    private DefaultListModel<String> defaultListModel;
    private List<T> list;
    private final boolean isSingleSelection;

    public MyScrollPane(List<T> list, boolean isSingleSelection) {
        super();
        this.list = list;
        this.isSingleSelection = isSingleSelection;
        myJList = new JList<>();
        myJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                listClicked(e);
            }
        });
        defaultListModel = new DefaultListModel<>();
        bindData();
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setViewportView(myJList);
    }

    private void setSelection() {
        if (isSingleSelection) myJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        else myJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

    public abstract void listClicked(MouseEvent e);

    public void bindData() {
        defaultListModel.clear();
        for (T t : list) {
            defaultListModel.addElement(t.toString());
        }
        setMyJList();
    }

    public void setMyJList() {
        myJList.setModel(defaultListModel);
        setSelection();
    }

    public JList<String> getMyJList() {
        return myJList;
    }

    public DefaultListModel<String> getDefaultListModel() {
        return defaultListModel;
    }

    public void setMyJList(JList<String> myJList) {
        this.myJList = myJList;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void setDefaultListModel(DefaultListModel<String> defaultListModel) {
        this.defaultListModel = defaultListModel;
    }

    public void updateGraphic() {
        defaultListModel.clear();
        bindData();
    }
}

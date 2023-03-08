package com.team43.app.frontend.manager;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.team43.app.frontend.Controller;

import java.awt.BorderLayout;
import java.util.List;

public class ViewEditMenuPanel extends JPanel implements TableModelListener {
    Controller controller;
    JTable table;
    JScrollPane scrollPane;

    public ViewEditMenuPanel(Controller controller) {
        this.controller = controller;
        String[] colNames = {
            "menu_id",
            "name",
            "type",
            "price",
            "ingredient_amount",
        };

        List<List<String>> invenList = controller.viewMenuItems();
        Object[][] data = new Object[invenList.size()][5];
        for (int i = 0; i < invenList.size(); ++i) {
            data[i] = invenList.get(i).toArray();
        }

        table = new JTable(data, colNames);
        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.getModel().addTableModelListener(this);

        setLayout(new BorderLayout());
        add(scrollPane);
    }

    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        // int column = e.getColumn();
        TableModel model = (TableModel)e.getSource();
        // String columnName = model.getColumnName(column);
        // Object data = model.getValueAt(row, column);
        onInventoryItemUpdate(model, row);
    }

    void onInventoryItemUpdate(TableModel model, int row) {
        controller.editMenuItem(
            Integer.valueOf((String) model.getValueAt(row, 0)),
            (String) model.getValueAt(row, 1),
            (String) model.getValueAt(row, 2),
            Double.valueOf((String) model.getValueAt(row, 3)),
            Integer.valueOf((String) model.getValueAt(row, 4))
        );
    }
}

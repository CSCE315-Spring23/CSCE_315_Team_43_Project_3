package com.team43.app.frontend.manager;

import javax.swing.JPanel;
import javax.swing.JTable;

import com.team43.app.frontend.Controller;

import java.awt.BorderLayout;
import java.util.List;

public class ViewEditInventoryPanel extends JPanel {
    Controller controller;
    JTable table;

    public ViewEditInventoryPanel(Controller controller) {
        this.controller = controller;
        String[] colNames = {
            "inventory_id",
            "name",
            "price",
            "quantity",
            "measurement_type",
        };

        List<List<String>> invenList = controller.getInventory();
        Object[][] data = new Object[invenList.size()][5];
        for (int i = 0; i < invenList.size(); ++i) {
            data[i] = invenList.get(i).toArray();
        }

        table = new JTable(data, colNames);

        setLayout(new BorderLayout());
        add(table.getTableHeader(), BorderLayout.PAGE_START);
        add(table, BorderLayout.CENTER);
    }
}

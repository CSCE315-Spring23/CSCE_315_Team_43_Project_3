package com.team43.app.frontend.manager;

import javax.swing.JPanel;
import javax.swing.JTable;

import com.team43.app.frontend.Controller;

import java.awt.BorderLayout;

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

        Object[][] data = controller.getInventory();

        table = new JTable(data, colNames);

        setLayout(new BorderLayout());
        add(table.getTableHeader(), BorderLayout.PAGE_START);
        add(table, BorderLayout.CENTER);
    }
}

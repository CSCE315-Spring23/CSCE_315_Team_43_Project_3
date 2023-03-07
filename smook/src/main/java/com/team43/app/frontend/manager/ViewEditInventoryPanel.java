package com.team43.app.frontend.manager;

import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.BorderLayout;

public class ViewEditInventoryPanel extends JPanel {
    JTable table;

    public ViewEditInventoryPanel() {
        String[] colNames = {
            "Inventory_ID",
            "Name",
            "Price",
            "Quantity",
            "Measurement_Type",
        };

        Object[][] data = {
            {1, "Oranges", 12, 100, "units"},
            {2, "Pears", 30, 80, "units"},
            {3, "Apples", 3, 50, "units"},
        };

        table = new JTable(data, colNames);

        setLayout(new BorderLayout());
        add(table.getTableHeader(), BorderLayout.PAGE_START);
        add(table, BorderLayout.CENTER);
    }
}

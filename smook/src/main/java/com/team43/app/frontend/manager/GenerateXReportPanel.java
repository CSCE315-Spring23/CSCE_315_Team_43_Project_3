package com.team43.app.frontend.manager;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.util.List;

import com.team43.app.frontend.Controller;

public class GenerateXReportPanel extends JPanel {
    Controller controller;
    JButton generateXReportButton = new JButton("Generate X report");
    JPanel selection = new JPanel(new FlowLayout(FlowLayout.TRAILING));
    JTable table;
    JScrollPane scrollPane;
    JPanel tableSlot = new JPanel(new FlowLayout(FlowLayout.TRAILING));

    GenerateXReportPanel(Controller controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        selection.add(generateXReportButton);

        add(selection, BorderLayout.NORTH);
        add(tableSlot, BorderLayout.CENTER);

        generateXReportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateSalesReportClicked();
            }
        });
    }

    void generateSalesReportClicked() {
        String[] colNames = {
            "menu_id",
            "name",
            "type",
            "price",
            "ingredient_amount",
            "number_sales",
        };

        List<List<String>> salesList = controller.generateXReport();
        Object[][] data = new Object[salesList.size()][6];
        for (int i = 0; i < salesList.size(); ++i) {
            data[i] = salesList.get(i).toArray();
        }

        table = new JTable(data, colNames);
        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        tableSlot.setLayout(new BorderLayout());
        tableSlot.add(scrollPane);
        tableSlot.validate();
    }
}

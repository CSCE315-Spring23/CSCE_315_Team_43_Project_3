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
import javax.swing.JTextField;
import java.util.List;

import com.team43.app.frontend.Controller;

public class GenerateSalesReportPanel extends JPanel {
    Controller controller;
    JTextField startDateField = new JTextField("2022-01-01");
    JTextField endDateField = new JTextField("2022-12-31");
    JButton generateSalesReportButton = new JButton("Generate sales report");
    JPanel selection = new JPanel(new FlowLayout(FlowLayout.TRAILING));
    JTable table;
    JScrollPane scrollPane;
    JPanel tableSlot = new JPanel(new FlowLayout(FlowLayout.TRAILING));

    GenerateSalesReportPanel(Controller controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        selection.add(new JLabel("Start Date: ", JLabel.TRAILING));
        selection.add(startDateField);
        selection.add(new JLabel("End Date: ", JLabel.TRAILING));
        selection.add(endDateField);
        selection.add(new JLabel("Dates must be in YYYY-MM-DD format (e.g. 2023-03-20)", JLabel.TRAILING));
        selection.add(generateSalesReportButton);

        add(selection, BorderLayout.NORTH);
        add(tableSlot, BorderLayout.CENTER);

        generateSalesReportButton.addActionListener(new ActionListener() {
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

        List<List<String>> salesList = controller.generateSalesReport(startDateField.getText(), endDateField.getText());
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

package com.team43.app.frontend.manager;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.team43.app.frontend.Controller;

public class GenerateSalesReportPanel extends JPanel {
    Controller controller;
    JTextField startDateField = new JTextField();
    JTextField endDateField = new JTextField();
    JButton generateSalesReportButton = new JButton("Generate sales report");
    GenerateSalesReportPanel(Controller controller) {
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Start Date: ", JLabel.TRAILING));
        add(startDateField);
        add(new JLabel("End Date: ", JLabel.TRAILING));
        add(endDateField);
        add(new JLabel("Dates must be in YYYY-MM-DD format (e.g. 2023-03-20)", JLabel.TRAILING));
        add(generateSalesReportButton);

        generateSalesReportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateSalesReportClicked();
            }
        });
    }

    void generateSalesReportClicked() {
        System.out.println("generateSalesReportClicked");
    }
}

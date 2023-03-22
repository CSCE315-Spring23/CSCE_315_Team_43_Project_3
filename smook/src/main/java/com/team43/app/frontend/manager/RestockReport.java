package com.team43.app.frontend.manager;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.team43.app.frontend.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class RestockReport extends JPanel{
    // JButton logout;
    JTable table;
    JScrollPane scrollPane;
    Controller controller;
    JLabel title;

    // JTextField startDate = new JTextField("2022-01-01");
    // JButton submitRange = new JButton("Check Range");
    //JPanel selection = new JPanel(new FlowLayout(FlowLayout.TRAILING));
    JPanel tableSlot = new JPanel(new FlowLayout(FlowLayout.TRAILING));
    

    public RestockReport(Controller control) {
        this.controller = control;

        //setPreferredSize(new Dimension(600,24));
        String[] colNames = {
            "Inventory_Items",
            "Quantity",
            "Average_Usage"
        };
        HashMap<String,ArrayList<Float>> report = control.getRestock();
        //System.out.println("Retrieved " + ); // REMOVE
        Object[][] data = new Object[report.size()][3];
        int i = 0;
        for (HashMap.Entry<String,ArrayList<Float>> entry : report.entrySet()) {
            data[i][0] = entry.getKey();
            data[i][1] = entry.getValue().get(0);
            data[i][2] = entry.getValue().get(1);
            i++;
        }
        if (report.keySet().size() == 0){
            data = new Object[1][3];
            data[0][0] = "No";
            data[0][1] = "Items";
            data[0][2] = "Needing Restocking";
        }
        setLayout(new BorderLayout());
        add(tableSlot, BorderLayout.CENTER);
        table = new JTable(data, colNames);
        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        tableSlot.removeAll();
        tableSlot.add(scrollPane, BorderLayout.CENTER);
        tableSlot.revalidate();
    }
}

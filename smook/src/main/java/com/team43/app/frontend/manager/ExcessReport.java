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
import java.util.ArrayList;
import java.util.HashMap;

public class ExcessReport extends JPanel{
    JButton logout;
    JTable table;
    JScrollPane scrollPane;
    Controller controller;


    JTextField startDate = new JTextField("2022-01-01");
    JButton submitRange = new JButton("Check Range");
    JPanel selection = new JPanel(new FlowLayout(FlowLayout.TRAILING));
    JPanel tableSlot = new JPanel(new FlowLayout(FlowLayout.TRAILING));
    

    public ExcessReport(Controller control) {
        this.controller = control;

        //setPreferredSize(new Dimension(600,24));

        setLayout(new BorderLayout());
        selection.add(new JLabel("start date: ", JLabel.TRAILING));
        selection.add(startDate);
        selection.add(submitRange);
        add(selection, BorderLayout.NORTH);
        add(tableSlot, BorderLayout.CENTER);

        submitRange.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performExcessReport();
            }
        });
    }

    void performExcessReport() {
        String[] colNames = {
            "Inventory_Item",
            "Percent sold since inputed date"
        };
        String[] Date = startDate.getText().split("-");
        HashMap<String,Float> report = controller.getExcess(Integer.parseInt(Date[0]),Integer.parseInt(Date[1]),Integer.parseInt(Date[2]));
        Object[][] data = new Object[report.size()][2];
        int i = 0;
        for (HashMap.Entry<String, Float> entry : report.entrySet()) {
            data[i][0] = entry.getKey();
            data[i][1] = entry.getValue();
            i++;
        }
        table = new JTable(data, colNames);
        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        tableSlot.removeAll();
        tableSlot.add(scrollPane, BorderLayout.CENTER);
        tableSlot.revalidate();
    }
}

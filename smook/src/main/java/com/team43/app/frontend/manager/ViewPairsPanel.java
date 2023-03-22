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

public class ViewPairsPanel extends JPanel{
    JButton logout;
    JTable table;
    JScrollPane scrollPane;
    Controller controller;


    JTextField startDate = new JTextField("2022-01-01");
    JTextField endDate = new JTextField("2022-12-31");
    JButton submitRange = new JButton("Check Range");
    JPanel selection = new JPanel(new FlowLayout(FlowLayout.TRAILING));
    JPanel tableSlot = new JPanel(new FlowLayout(FlowLayout.TRAILING));
    

    public ViewPairsPanel(Controller control) {
        this.controller = control;

        setPreferredSize(new Dimension(600,24));

        setLayout(new BorderLayout());
        selection.add(new JLabel("start date: ", JLabel.TRAILING));
        selection.add(startDate);
        selection.add(new JLabel("end date: ", JLabel.TRAILING));
        selection.add(endDate);
        selection.add(submitRange);
        add(selection, BorderLayout.NORTH);
        add(tableSlot, BorderLayout.CENTER);

        submitRange.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performPairCheck();
            }
        });

        
        // logout = new JButton("Logout");
        // add(logout);
        // logout.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         controller.navigatePageBack();
        //     }
        // });
    }

    void performPairCheck() {
        String[] colNames = {
            "menu_id_1",
            "menu_id_2",
            "purchasefrequency",
        };

        List<List<Integer>> pairList = controller.getPairs(startDate.getText(), endDate.getText());
        Object[][] data = new Object[pairList.size()][5];
        for (int i = 0; i < pairList.size(); ++i) {
            data[i] = pairList.get(i).toArray();
        }

        table = new JTable(data, colNames);
        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        tableSlot.removeAll();
        tableSlot.add(scrollPane, BorderLayout.CENTER);
        tableSlot.validate();
    }
}

package com.team43.app.frontend.manager;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.team43.app.frontend.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.util.List;
import java.util.ArrayList;

public class ViewPairsPanel extends JPanel{
    JButton logout;
    JTable table;
    JScrollPane scrollPane;
    Controller controller;

    public ViewPairsPanel(Controller control) {
        this.controller = control;
        String[] colNames = {
            "menu_id_1",
            "menu_id_2",
            "purchasefrequency",
        };

        List<List<Integer>> pairList = controller.getPairs();
        Object[][] data = new Object[pairList.size()][5];
        for (int i = 0; i < pairList.size(); ++i) {
            data[i] = pairList.get(i).toArray();
        }

        table = new JTable(data, colNames);
        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        setLayout(new BorderLayout());
        add(scrollPane);
        // logout = new JButton("Logout");
        // add(logout);
        // logout.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         controller.navigatePageBack();
        //     }
        // });
    }
}

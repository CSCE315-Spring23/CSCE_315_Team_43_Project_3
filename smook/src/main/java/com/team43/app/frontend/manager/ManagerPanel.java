package com.team43.app.frontend.manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.team43.app.frontend.Controller;

public class ManagerPanel extends JPanel implements ActionListener {
    JButton endOfDayButton = new JButton("End of Day");
    JButton orderInventoryButton = new JButton("Order Inventory");
    JButton viewEditInventoryButton = new JButton("View / Edit Inventory");

    ViewEditInventoryPanel viewEditInventoryPanel;
    Controller controller;

    public ManagerPanel(Controller controller) {
        this.controller = controller;
        setLayout(new FlowLayout());

        endOfDayButton.setBounds(100, 50, 100, 20);
        add(endOfDayButton);
        add(orderInventoryButton);
        add(viewEditInventoryButton);

        endOfDayButton.setActionCommand("endOfDay");
        orderInventoryButton.setActionCommand("orderInventory");
        viewEditInventoryButton.setActionCommand("viewEditInventory");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println(command);
        if (command == "endOfDay") {
            // Static Class that records all inventory subtractions for a given day
            // ManagerBackend.endOfDay();
        } else if (command == "orderInventory") {
            // Show "Order Inventory" frame
            // ManagerBackend.orderInventory();

        } else if (command == "viewEditInventory") {
            // Show "View/Edit Inventory" frame
            // viewEditInventoryPanel = new ViewEditInventoryPanel();
            System.out.print("command");
            controller.add("ViewEditInventoryPanel", new ViewEditInventoryPanel());
            controller.navigatePage("ViewEditInventoryPanel");
            // ManagerBackend.viewEditInventory();
        }
    }
}

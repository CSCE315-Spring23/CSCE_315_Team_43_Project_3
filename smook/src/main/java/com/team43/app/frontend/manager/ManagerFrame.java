package com.team43.app.frontend.manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ManagerFrame extends JFrame implements ActionListener {
    JButton endOfDayButton = new JButton("End of Day");
    JButton orderInventoryButton = new JButton("Order Inventory");
    JButton viewEditInventoryButton = new JButton("View / Edit Inventory");

    ManagerFrame() {
        // TODO: make these constant?
        endOfDayButton.setActionCommand("endOfDay");
        orderInventoryButton.setActionCommand("orderInventory");
        viewEditInventoryButton.setActionCommand("viewEditInventory");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command == "endOfDay") {
            // Static Class that records all inventory subtractions for a given day
            ManagerBackend.endOfDay();
        } else if (command == "orderInventory") {
            // Show "Order Inventory" frame
            ManagerBackend.orderInventory();
        } else if (command == "viewEditInventory") {
            // Show "View/Edit Inventory" frame
            ManagerBackend.viewEditInventory();
        }
    }
}

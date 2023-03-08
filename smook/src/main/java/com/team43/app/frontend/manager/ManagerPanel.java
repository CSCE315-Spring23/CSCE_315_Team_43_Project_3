package com.team43.app.frontend.manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.team43.app.frontend.Controller;

public class ManagerPanel extends JPanel {
    JButton endOfDayButton = new JButton("End of Day");
    JButton orderInventoryButton = new JButton("Order Inventory");
    JButton viewEditInventoryButton = new JButton("View / Edit Inventory");

    ViewEditInventoryPanel viewEditInventoryPanel;
    Controller controller;

    public ManagerPanel(Controller controller) {
        this.controller = controller;
        setLayout(new FlowLayout());

        add(endOfDayButton);
        add(orderInventoryButton);
        add(viewEditInventoryButton);

        endOfDayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inventorySelectionClicked("endOfDay");
            }
        });

        orderInventoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inventorySelectionClicked("orderInventory");
            }
        });

        viewEditInventoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inventorySelectionClicked("viewEditInventory");
            }
        });
    }

    public void inventorySelectionClicked(String name) {
        if (name == "endOfDay") {
            // Static Class that records all inventory subtractions for a given day
            // ManagerBackend.endOfDay();
        } else if (name == "orderInventory") {
            // Show "Order Inventory" frame
            controller.add("OrderInventoryPanel", new OrderInventoryPanel());
            controller.navigatePage("OrderInventoryPanel");
        } else if (name == "viewEditInventory") {
            // Show "View/Edit Inventory" frame
            controller.add("ViewEditInventoryPanel", new ViewEditInventoryPanel());
            controller.navigatePage("ViewEditInventoryPanel");
        }
    }
}

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
    JButton viewEditMenuButton = new JButton("View / Edit Menu");
    JButton logout;
    ViewEditInventoryPanel viewEditInventoryPanel;
    Controller controller;

    public ManagerPanel(Controller control) {
        this.controller = control;
        setLayout(new FlowLayout());

        add(endOfDayButton);
        add(orderInventoryButton);
        add(viewEditInventoryButton);
        add(viewEditMenuButton);
        // logout = new JButton("Logout");
        // add(logout);
        // logout.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         controller.navigatePageBack();
        //     }
        // });

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

        viewEditMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inventorySelectionClicked("viewEditMenu");
            }
        });
    }

    public void inventorySelectionClicked(String name) {
        if (name == "endOfDay") {
            // Static Class that records all inventory subtractions for a given day
            // ManagerBackend.endOfDay();
        } else if (name == "orderInventory") {
            // Show "Order Inventory" frame
            controller.add("OrderInventoryPanel", new OrderInventoryPanel(controller));
            controller.navigatePage("OrderInventoryPanel");
        } else if (name == "viewEditInventory") {
            // Show "View/Edit Inventory" frame
            controller.add("ViewEditInventoryPanel", new ViewEditInventoryPanel(controller));
            controller.navigatePage("ViewEditInventoryPanel");
        } else if (name == "viewEditMenu") {
            // Show "View/Edit Inventory" frame
            controller.add("ViewEditMenuPanel", new ViewEditMenuPanel(controller));
            controller.navigatePage("ViewEditMenuPanel");
        }
    }
}

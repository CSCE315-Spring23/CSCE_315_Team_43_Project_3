package com.team43.app.frontend.manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import com.team43.app.frontend.Controller;

public class ManagerPanel extends JPanel {
    JButton endOfDayButton = new JButton("End of Day");
    JButton orderInventoryButton = new JButton("Order Inventory");
    JButton viewEditInventoryButton = new JButton("View / Edit Inventory");
    JButton viewEditMenuButton = new JButton("View / Edit Menu");
    JButton addMenuItem = new JButton("Add Menu Item");
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
        add(addMenuItem);
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
        addMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inventorySelectionClicked("addMenuItem");
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
        } else if (name == "addMenuItem") {
            String inputStr = JOptionPane.showInputDialog(this,
                        "Customer name?", null);
            String[] attributes = inputStr.split(",");
            //name,type,price,ingredient_amount, ingredients
            String nameS = attributes[0];
            String type = attributes[1];
            double price = Double.parseDouble(attributes[2]);
            Integer ingredient_amount = Integer.parseInt(attributes[3]);
            ArrayList<Integer> ingredient_list = new ArrayList<Integer>();
            for (int i = 4; i < attributes.length; i++) {
                ingredient_list.add(Integer.parseInt(attributes[i]));
            }
            controller.addMenuItem(nameS, type, price, ingredient_amount, ingredient_list);
            
        }
    }
}

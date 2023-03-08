package com.team43.app.frontend.manager;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.team43.app.backend.manager.OrderList;
import com.team43.app.frontend.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.GridLayout;

public class OrderInventoryPanel extends JPanel {
    JTextField inventoryIdField = new JTextField();
    JTextField orderIdField = new JTextField();
    JTextField quantityField = new JTextField();

    ArrayList<OrderList> orderCart = new ArrayList<OrderList>();
    JButton addOrderButton = new JButton("Add order");
    JButton orderButton = new JButton("Order");

    Controller controller;

    public OrderInventoryPanel(Controller controller) {
        this.controller = controller;
        setLayout(new GridLayout(4, 2));

        add(new JLabel("inventory_id: ", JLabel.TRAILING));
        add(inventoryIdField);
        add(new JLabel("order_id: ", JLabel.TRAILING));
        add(orderIdField);
        add(new JLabel("quantity: ", JLabel.TRAILING));
        add(quantityField);
        add(addOrderButton);
        add(orderButton);

        addOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addOrderClicked();
            }
        });

        orderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                orderClicked();
            }
        });
    }

    // Add order to the cart and clear all fields
    void addOrderClicked() {
        OrderList orderList = new OrderList(
            Integer.parseInt(inventoryIdField.getText()),
            Integer.parseInt(orderIdField.getText()),
            Double.parseDouble(quantityField.getText())
        );
        inventoryIdField.setText(null);
        orderIdField.setText(null);
        quantityField.setText(null);
        System.out.println(orderList);
        orderCart.add(orderList);
    }

    // Order all items from the cart and empty the cart
    void orderClicked() {
        // Backend.order_items(olist);
        orderCart.clear();
    }
}

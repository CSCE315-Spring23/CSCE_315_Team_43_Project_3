package com.team43.app.frontend;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.team43.app.backend.manager.OrderList;
import com.team43.app.frontend.server.ServerPanel;

public class Controller {
    Stack<JPanel> pageStack;
    HashMap<String, JPanel> pages;
    JPanel currentPage;
    JFrame mainFrame;
    Model model;

    Controller(JFrame mainFrame) {
        this.mainFrame = mainFrame;
        pageStack = new Stack<JPanel>();
        pages = new HashMap<String, JPanel>();
        model = new Model();
    }

    /**
    * Adds a panel to the list of pages
    * @param    name    the name of the key for the panel
    * @param    panel   the panel we are adding to
    */
    public void add(String name, JPanel panel) {
        if (!pages.containsKey(name)) {
            pages.put(name, panel);
        }
    }

    /**
    * Set the current page to the page matching the key provided
    * @param    name    the name of the key for the panel
    */
    public void navigatePage(String name) {
        if (currentPage != null) {
            // Hide this page and push it
            currentPage.setVisible(false);
            pageStack.push(currentPage);
        }
        currentPage = pages.get(name);
        mainFrame.add(currentPage);
        currentPage.setVisible(true);
    }

    /**
    * Navigate backwards in the page stack
    */
    public void navigatePageBack() {
        currentPage.setVisible(false);
        JPanel panel = pageStack.pop();
        panel.setVisible(true);
    }

    /**
    * Shows the panel according to the role
    * @param    role    the role we are pulling the panels from
    */
    public void showPageFromRole(String role) {
        if (role == null) {
            // Login failure; show red text error
        } else if (role.equals("manager")) {
            // Show manager frame
            navigatePage("ManagerPanel");
        } else if (role.equals("employee")) {
            // Show employee frame
            navigatePage("ServerPanel");
        }
    }
    /**
    * Add a list of orders to the database
    * @param    orderList   a provided list of orders
    */
    public void orderItems(List<OrderList> orderList) {
        model.db.orderItems(orderList);
    }

    /**
    * Pull inventory from the database
    * @return a list containing every inventory item, with each item being represented by a list of strings
    */
    public List<List<String>> getInventory() {
        return model.db.viewInventory();
    }

    /**
    * Update an inventory item based on variable parameters
    * @param    inventoryId     the id of the item we are modifying
    * @param    name            the name of the item
    * @param    price           the price of the item
    * @param    quantity        the amount of the item in the inventory
    * @param    measurementType the type of measurement used to measure the quantity
    */
    public void editInventoryItem(int inventoryId, String name, double price, double quantity, String measurementType) {
        model.db.editInventoryItem(inventoryId, name, price, quantity, measurementType);
    }

    public void newServer() {
        JPanel serverPanel = pages.get("ServerPanel");
        mainFrame.remove(serverPanel);
        serverPanel = new ServerPanel(mainFrame, this);
        mainFrame.add(serverPanel);
        //mainFrame.showPanel(serverPanel);
        mainFrame.revalidate();
    }
}

package com.team43.app.frontend;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
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

    public void switchTo(String name){
        // if (currentPage != null) {
            // Hide this page and push it
            currentPage.setVisible(false);
            pageStack.push(currentPage);
        // }
        currentPage = pages.get(name);
        mainFrame.add(currentPage);
        currentPage.setVisible(true);
        mainFrame.validate();
    }
    /**
    * Navigate backwards in the page stack
    */
    public void navigatePageBack() {
        currentPage.setVisible(false);
        JPanel panel = pageStack.pop();
        panel.setVisible(true);
        mainFrame.validate();
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

    public List<List<String>> viewMenuItems() {
        return model.db.viewMenuItems();
    }

    public List<List<String>> generateSalesReport(String startDate, String endDate) {
        return model.db.getSalesReport(startDate, endDate);
    }

    public List<List<String>> generateXReport() {
        return model.db.getXReport();
    }

    public List<List<String>> generateZReport() {
        model.serverBackend.finishTransactions();
        return model.db.getZReport();
    }

    public void finishTransactions() {
        model.serverBackend.finishTransactions();
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

    public void editMenuItem(int menuId, String name, String type, double price, int ingredient_amount) {
        model.db.updateMenuItem(menuId, name, type, price, ingredient_amount);
    }

    public void addMenuItem(String name, String type, double price, int ingredient_amount, ArrayList<Integer> inventoryIds) {
        model.db.addMenuItem(name, type, price, ingredient_amount, inventoryIds);
    }

    public void newServer() {
        JPanel serverPanel = pages.get("ServerPanel");
        mainFrame.remove(serverPanel);
        serverPanel = new ServerPanel(mainFrame, this);
        mainFrame.add(serverPanel);
        //mainFrame.showPanel(serverPanel);
        mainFrame.revalidate();
    }

    public List<List<Integer>> getPairs(String start, String end) {
        return model.db.findPairs(start, end);
    }
    public HashMap<String, Float> getExcess(int month, int day, int year) {
        return model.db.getExcess(month, day, year);
    }
    public HashMap<String, ArrayList<Float>> getRestock() {
        return model.db.getRestock();
    }
}

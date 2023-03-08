package com.team43.app.frontend;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.team43.app.frontend.manager.ManagerPanel;

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

    public void add(String name, JPanel panel) {
        if (!pages.containsKey(name)) {
            pages.put(name, panel);
        }
    }

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

    public void navigatePageBack() {
        currentPage.setVisible(false);
        JPanel panel = pageStack.pop();
        panel.setVisible(true);
    }

    // Shows the panel according to the role
    public void showPageFromRole(String role) {
        if (role == null) {
            // Login failure; show red text error
        } else if (role.equals("manager")) {
            // Show manager frame
            navigatePage("ManagerPanel");
        } else if (role.equals("employee")) {
            // Show employee frame
            // serverFrame = new ServerFrame();
            // showPanel(serverFrame);
        }
    }

    public List<List<String>> getInventory() {
        return model.db.view_inventory();
    }
}

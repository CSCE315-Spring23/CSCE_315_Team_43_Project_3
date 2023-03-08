package com.team43.app.frontend;


import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import com.team43.app.LoginPanel;
import com.team43.app.frontend.manager.ManagerPanel;
import com.team43.app.frontend.server.ServerPanel;

public class MainFrame extends JFrame {
    Model model;
    Controller controller;

    public MainFrame(int width, int height) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        controller = new Controller(this);
        model = new Model();
        controller.add("LoginPanel", new LoginPanel(controller));
        controller.add("ManagerPanel", new ManagerPanel(controller));
        controller.add("ServerPanel", new ServerPanel(this, controller));
        setSize(width, height);
        setVisible(true);
        controller.navigatePage("LoginPanel");
    }

    // Hides the login panel and shows panel provided
    void showPanel(JPanel panel) {
        // loginPanel.setVisible(false);
        add(panel);
        panel.setVisible(true);
        revalidate();
        repaint();
    }

    // Shows the panel according to the role
    public void showPanelFromRole(String role) {
        if (role == null) {
            // Login failure; show red text error
        } else if (role.equals("manager")) {
            // Show manager frame
            controller.navigatePage("ManagerPanel");
        } else if (role.equals("employee")) {
            // Show employee frame
            controller.navigatePage("ServerPanel");
        }
    }

    public void newServer() {
        controller.newServer();
    }
}

package com.team43.app.frontend;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import com.team43.app.LoginPanel;
import com.team43.app.frontend.manager.ManagerPanel;
import com.team43.app.frontend.server.ServerPanel;

public class MainFrame extends JFrame {
    LoginPanel loginPanel;
    ManagerPanel managerPanel;
    ServerPanel serverPanel;

    public MainFrame(int width, int height) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        loginPanel = new LoginPanel(this);
        add(loginPanel);
        setSize(width, height);
        setVisible(true);
    }

    // Hides the login panel and shows panel provided
    void showPanel(JPanel panel) {
        loginPanel.setVisible(false);
        revalidate();
        repaint();
        add(panel);
        panel.setVisible(true);
    }

    // Shows the panel according to the role
    public void showPanelFromRole(String role) {
        if (role == null) {
            // Login failure; show red text error
        } else if (role.equals("manager")) {
            // Show manager frame
            managerPanel = new ManagerPanel();
            showPanel(managerPanel);
        } else if (role.equals("employee")) {
            // Show employee frame
            serverPanel = new ServerPanel(this);
            add(serverPanel);
            showPanel(serverPanel);
        }
    }
    public void newServer() {
        remove(serverPanel);
        serverPanel = new ServerPanel(this);
        add(serverPanel);
        showPanel(serverPanel);
    }
}

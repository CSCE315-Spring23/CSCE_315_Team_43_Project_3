package com.team43.app.frontend;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import com.team43.app.LoginPanel;
import com.team43.app.frontend.manager.ManagerPanel;
import com.team43.app.frontend.server.ServerFrame;

public class MainFrame extends JFrame {
    // LoginPanel loginPanel;
    // ManagerPanel managerPanel;
    // ServerFrame serverFrame;

    Controller controller;

    public MainFrame(int width, int height) {
        super();
        setLayout(new BorderLayout());
        controller = new Controller(this);
        controller.add("LoginPanel", new LoginPanel(controller));
        controller.add("ManagerPanel", new ManagerPanel(controller));
        controller.navigatePage("LoginPanel");
        // controller.add("ServerFrame", new ServerFrame(controller));
        setSize(width, height);
        setVisible(true);
    }
}

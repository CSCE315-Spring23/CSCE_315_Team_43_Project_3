package com.team43.app.frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.team43.app.frontend.manager.ManagerFrame;
import com.team43.app.frontend.server.ServerFrame;
public class MainFrame extends JFrame implements ActionListener {
    JTextField usernameField;
    JTextField passwordField;
    JButton loginButton = new JButton("Login");

    ManagerFrame managerFrame;
    ServerFrame serverFrame;

    MainFrame(int width, int height) {
        super();
        add(usernameField);
        add(passwordField);
        add(loginButton);

        setSize(width, height);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String role = Backend.login(usernameField.getText(), passwordField.getText());
        if (role == null) {
            // Login failure; show red text error
        } else if (role == "manager") {
            // Show manager frame
            managerFrame = new ManagerFrame();
            // TODO: set a better layout
            managerFrame.setLayout(null);
            managerFrame.setVisible(true);
        } else if (role == "employee") {
            // Show employee frame
            serverFrame = new ServerFrame();
            serverFrame.setLayout(null);
            serverFrame.setVisible(true);
        }
    }
}

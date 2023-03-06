package com.team43.app.frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;


import com.team43.app.frontend.manager.ManagerFrame;
import com.team43.app.frontend.server.ServerFrame;
public class MainFrame extends JFrame {
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("Login");

    ManagerFrame managerFrame;
    ServerFrame serverFrame;

    public MainFrame(int width, int height) {
        super();
        setLayout(new GridLayout(3, 2));
        // usernameField.setBounds(50, 50, 150, 20);
        add(new JLabel("Username: ", JLabel.TRAILING));
        add(usernameField);
        // passwordField.setBounds(50, 50, 150, 20);
        add(new JLabel("Password: ", JLabel.TRAILING));
        add(passwordField);
        // loginButton.setBounds(50, 100, 95, 30);
        add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginClicked();
            }
        });

        setSize(width, height);
        setVisible(true);
    }

    void loginClicked() {
        System.out.println(usernameField.getText());
        System.out.println(passwordField.getPassword());
        // String role = Backend.login(usernameField.getText(), passwordField.getText());
        String role = new String("manager");
        if (role == null) {
            // Login failure; show red text error
        } else if (role == "manager") {
            // Show manager frame
            // managerFrame = new ManagerFrame();
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

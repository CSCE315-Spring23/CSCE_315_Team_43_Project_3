package com.team43.app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

import com.team43.app.frontend.Controller;
import com.team43.app.frontend.MainFrame;

import javax.swing.JPasswordField;
import javax.swing.JLabel;

public class LoginPanel extends JPanel {
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("Login");

    MainFrame parent;
    Controller controller;

    public LoginPanel(Controller controller) {
        this.controller = controller;
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Username: ", JLabel.TRAILING));
        add(usernameField);
        add(new JLabel("Password: ", JLabel.TRAILING));
        add(passwordField);
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginClicked();
            }
        });

        setVisible(true);
    }

    void loginClicked() {
        // String role = Backend.login(usernameField.getText(), passwordField.getText());
        String role = usernameField.getText();
        parent.showPanelFromRole(role);
    }

}

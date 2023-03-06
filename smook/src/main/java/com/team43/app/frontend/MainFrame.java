package com.team43.app.frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.*;

// import com.team43.app.frontend.manager.ManagerFrame;
import com.team43.app.frontend.server.ServerFrame;

public class MainFrame extends JFrame{
    private JTextField usernameField;
    private JTextField passwordField;
    private JPanel contentPane;

    // ManagerFrame managerFrame;
    ServerFrame serverFrame;

    public MainFrame(int width, int height) {
        System.out.println("fml");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(width, height);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        setContentPane(contentPane);
        contentPane.setLayout(null);

        usernameField= new JTextField();
        usernameField.setBounds(width/2,height/4,width/8,height/8);
        contentPane.add(usernameField);
        usernameField.setColumns(10);

        passwordField = new JTextField();
        passwordField.setBounds(width/2,2*height/4,width/8,height/8);
        contentPane.add(passwordField);
        passwordField.setColumns(10);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(width/2,3*height/4,width/8,height/8);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String role = "employee";//Backend.login(usernameField.getText(), passwordField.getText());
                if (role == null) {
                    // Login failure; show red text error
                    System.out.println("Bruh....");
                } else if (role == "manager") {
                    // Show manager frame
                    // managerFrame = new ManagerFrame();
                    // // TODO: set a better layout
                    // managerFrame.setLayout(null);
                    // managerFrame.setVisible(true);
                } else if (role == "employee") {
                    // Show employee frame
                    serverFrame = new ServerFrame(1200,800);
                    // passwordField.setVisible(false);
                    // usernameField.setVisible(false);
                    serverFrame.setVisible(true);
                }
            }
        });
        loginButton.addActionListener(null);
        contentPane.add(loginButton);
        // contentPane.setVisible(true);
    }
    
}

package com.team43.app.frontend.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Container;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.Font;

public class ServerFrame extends JFrame {
    JButton b1;
    JPanel contentPane;

    public ServerFrame(int width, int height) {
        System.out.println("Hola");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(width, height);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //Initial Layout
        JPanel Transaction = new JPanel();
		Transaction.setBounds(0, 0, width/2, height);
		contentPane.add(Transaction);
		Transaction.setLayout(null);

        JPanel Items = new JPanel();
		Items.setBounds(width/2, 0, width/2, height);
		contentPane.add(Items);
		Items.setLayout(new GridLayout(0, 2, 0, 0));

        //First labels
        JLabel lblNewLabel_2 = new JLabel("Order");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(41, 11, width/2,height/11);
		Transaction.add(lblNewLabel_2);

        JLabel lblNewLabel = new JLabel("Items");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 9, 448, 14);
		Items.add(lblNewLabel);

        //
    }
}

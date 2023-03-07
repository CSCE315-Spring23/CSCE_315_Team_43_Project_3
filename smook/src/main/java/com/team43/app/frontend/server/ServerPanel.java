package com.team43.app.frontend.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Container;
import java.awt.Color;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.Font;
import java.util.ArrayList;

import com.team43.app.frontend.server.*;

public class ServerPanel extends JPanel implements ActionListener {
    JButton b1;
    // JPanel contentPane;
    JPanel item_type;
    JPanel Transaction;
    JPanel Items;
    JLabel lblNewLabel;
    JLabel lblNewLabel_2;
    int width;
    int height;
    // ArrayList<JButton> item_t;
    // ArrayList<JButton> smoothies;
    ServerController controll;
    public ServerPanel(ServerController s) {
        controll = s;
        System.out.println("Hola");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        width = controll.getWidth();
        height = controll.getHeight();
        // setBorder(new EmptyBorder(5, 5, 5, 5));
        //setContentPane(contentPane);
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        //Initial Layout
        Transaction = new JPanel();
		Transaction.setBounds(0, 0, width/2, height);
		add(Transaction);
		Transaction.setLayout(new BoxLayout(Transaction, BoxLayout.PAGE_AXIS));

        Items = new JPanel();
		Items.setBounds(width/2, 0, width/2, height);
		add(Items);
		Items.setLayout(new BoxLayout(Items, BoxLayout.PAGE_AXIS));

        //First labels
        lblNewLabel_2 = new JLabel("Order");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(41, 11, width/2,height/12);
		Transaction.add(lblNewLabel_2);

        lblNewLabel = new JLabel("Items");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(41, 11, width/2, height/12);
		Items.add(lblNewLabel);

        /*To Do: 
        **Add 2 panels (One for item type and one for item. Fill those panels using arrayLists). 
        **Remove grid layout from item panel since the previously mentioned panels will be below it and have that grid layout. 
        **Maybe use a different (vertical) layout. But grid is probably best(for now).
    */
        setItemType();
        //item_type.setBounds(width/2, height/12, width/2, 10*height/12);
        //item_type.setBorder(new LineBorder(new Color(200,0,0),3));
        // Items.add(item_type);
    }
    //Add a function to add an item for controller
    public void setItemType() {
        item_type = new JPanel(new GridLayout(0, 1, 0, 0));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

    }
}

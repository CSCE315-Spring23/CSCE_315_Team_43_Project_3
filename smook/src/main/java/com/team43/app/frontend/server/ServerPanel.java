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

import com.team43.app.frontend.server.Back;

public class ServerPanel extends JPanel {
    JButton b1;
    // JPanel contentPane;
    JPanel item_type;
    JPanel Transaction;
    JPanel Items;
    JLabel Order;
    JLabel ItemsL;
    int width =1200;
    int height=800;
    JScrollPane lister;
    Back backend;
    ArrayList<JButton> item_t;
    ArrayList<JButton> smoothiesB;
    String stage;
    public ServerPanel() {
        System.out.println("Hola");
        setLayout(new GridLayout(1,0));
        item_t = new ArrayList<JButton>();
        smoothiesB = new ArrayList<JButton>();
        Transaction = new JPanel();
		add(Transaction);

        Items = new JPanel();
        Items.setLayout(new BoxLayout(Items, BoxLayout.PAGE_AXIS));
        Items.setBorder(new LineBorder(getBackground(), 3));
		add(Items);

        lister = new JScrollPane();
        //First labels
        Order = new JLabel("Input");
        Order.setFont(new Font("Tahoma", Font.PLAIN, 30));
		Transaction.add(Order,BorderLayout.PAGE_START);
        Transaction.add(lister,BorderLayout.CENTER);


        addItemTitle();
        setUpTypes();

    }
    //Add a function to add an item for controller
    public void setUpTypes() {
        backend = new Back();
        ArrayList<String> str = backend.getCategories();
        stage = "type";
        for (int i = 0; i<str.size(); i++){
            JButton toAdd = new JButton(str.get(i));
            final String name = str.get(i);
            toAdd.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setUpSmoothies(name);
                }
            });
            Items.add(toAdd);
            item_t.add(toAdd);
        }
        Items.validate();
    }

    public void addItemTitle() {
        ItemsL = new JLabel("Items");
        ItemsL.setFont(new Font("Tahoma", Font.PLAIN, 30));
        // ItemsL.setHorizontalAlignment(SwingConstants.CENTER);
		// ItemsL.setSize(width/2,20);
		Items.add(ItemsL);
    }

    public void setUpSmoothies(String str){
        ArrayList<String> smoothies = backend.getItemsInCategory(str);
        for (int i = 0; i<item_t.size(); i++){
            item_t.get(i).setVisible(false);
        }
        stage = "smoothie";
        for (int i = 0; i<smoothies.size(); i++){
            JButton toAdd = new JButton(smoothies.get(i));
            final String name = smoothies.get(i);
            toAdd.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println(name);
                }
            });
            Items.add(toAdd);
            smoothiesB.add(toAdd);
        }
        validate();
    }
}

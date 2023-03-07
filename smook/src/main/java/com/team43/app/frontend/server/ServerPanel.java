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
import java.util.Arrays;

import com.team43.app.frontend.server.*;

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
    JPanel lister;
    Back backend;
    ArrayList<JButton> item_t;
    ArrayList<JButton> smoothiesB;
    ArrayList<JLabel> items_ordered;
    String stage;
    ArrayList<Order> cOrder;
    JLabel header;
    public ServerPanel() {
        System.out.println("Hola");
        setLayout(new GridLayout(1,0));
        item_t = new ArrayList<JButton>();
        smoothiesB = new ArrayList<JButton>();
        cOrder = new ArrayList<Order>();
        items_ordered = new ArrayList<JLabel>();
        Transaction = new JPanel();
		add(Transaction);
        stage = "";
        Items = new JPanel();
        Items.setLayout(new BoxLayout(Items, BoxLayout.PAGE_AXIS));
        Items.setBorder(new LineBorder(getBackground(), 3));
		add(Items);

        lister = new JPanel();
        lister.setLayout(new BoxLayout(lister, BoxLayout.PAGE_AXIS));
        lister.setBorder(new LineBorder(getBackground(), 3));
        //First labels
        Order = new JLabel("Current Transaction");
        Order.setFont(new Font("Tahoma", Font.PLAIN, 30));
		Transaction.add(Order,BorderLayout.PAGE_START);
        Transaction.add(lister,BorderLayout.CENTER);
        header = new JLabel("Smoothie name|Quantity|Price|AddOns");
        lister.add(header);


        addItemTitle();
        setUpTypes();

    }
    //Add a function to add an item for controller
    public void setUpTypes() {
        stage = "type";
        addItemTitle();
        for (int i = 0; i<smoothiesB.size(); i++){
            smoothiesB.get(i).setVisible(false);
        }
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
        if (stage.equals("smoothie")){
            ItemsL.setText("Pick a smoothie");
        }
        else if (stage.equals("type")){
            ItemsL.setText("Pick Category");
        }
        else 
        ItemsL = new JLabel("Pick Category");
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
        addItemTitle();
        for (int i = 0; i<smoothies.size(); i++){
            JButton toAdd = new JButton(smoothies.get(i));
            final String name = smoothies.get(i);
            toAdd.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addSmoothie(name);
                    setSize();
                }
            });
            Items.add(toAdd);
            smoothiesB.add(toAdd);
        }
        validate();
    }
    public void addSmoothie(String str){
        cOrder.add(new Order(str));
        displayOrder();
    }
    public void setSize() {
        ArrayList<String> sizes = new ArrayList<String>(Arrays.asList("Small","Medium","Large","Cancel"));
        for (int i = 0; i<smoothiesB.size(); i++){
            smoothiesB.get(i).setVisible(false);
        }
        ItemsL.setText(cOrder.get(cOrder.size()-1).getName() + ": Choose a size");
        for (int i = 0; i<sizes.size(); i++){
            JButton toAdd = new JButton(sizes.get(i));
            final String name = sizes.get(i);
            toAdd.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    getSize(name);
                }
            });
            Items.add(toAdd);
            smoothiesB.add(toAdd);
        }
        validate();
    }
    public void getSize(String size){
        if (size.equals("Cancel")){
            if (cOrder.size()>0)
            cOrder.remove(cOrder.size()-1);
        }
        else
        cOrder.get(cOrder.size()-1).setSize(size);
        displayOrder();
        if (items_ordered.size()>0)
        items_ordered.get(items_ordered.size()-1).setForeground(Color.black);
        setUpTypes();
    }
    public void displayOrder() {
        for (int i = 0; i<items_ordered.size(); i++){
            lister.remove(items_ordered.get(i));
        }
        items_ordered.clear();
        for (int i = 0; i<cOrder.size(); i++){
            JLabel toAdd = new JLabel(cOrder.get(i).toString());
            if (i == cOrder.size()-1)
            toAdd.setForeground(Color.red);
            items_ordered.add(toAdd);
            lister.add(toAdd);
        }
        if (cOrder.size()>0)
        System.out.println(cOrder.toString());
        lister.validate();
        Transaction.validate();
        validate();
    }
}

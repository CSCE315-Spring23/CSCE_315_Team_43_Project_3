package com.team43.app.frontend.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.BorderLayout;
import java.awt.GridLayout;
//import java.awt.Container;
import java.awt.Color;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;

import com.team43.app.backend.server.*;
import com.team43.app.frontend.MainFrame;

public class ServerPanel extends JPanel {
    JButton b1;
    // JPanel contentPane;
    JPanel item_type;
    Transaction transaction;
    JPanel Items;
    JLabel Order;
    JLabel ItemsL;
    JPanel lister;
    //Back backend;
    ServerBackend backend;
    ArrayList<JButton> item_t;
    ArrayList<JButton> smoothiesB;
    ArrayList<JLabel> items_ordered;
    String stage;
    ArrayList<Order> cOrder;
    JLabel header;
    JButton submit;
    MainFrame parent;

    public ServerPanel(MainFrame p) {

        //initialization
        parent = p;
        setLayout(new GridLayout(1,0));
        item_t = new ArrayList<JButton>();
        smoothiesB = new ArrayList<JButton>();
        cOrder = new ArrayList<Order>();
        items_ordered = new ArrayList<JLabel>();
        transaction = new Transaction();
		add(transaction);

        stage = "";

        Items = new JPanel();
        Items.setLayout(new BoxLayout(Items, BoxLayout.PAGE_AXIS));
        Items.setBorder(new LineBorder(getBackground(), 3));
		add(Items);

        header = new JLabel("<HTML><U>Smoothie name|Size|Price|AddOns</U></HTML>");
        transaction.addOrderToPanel(header);
        addItemTitle();
        setUpTypes();
        submit = transaction.getButton();
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submitOrder();
            }
        });
    }
    //Add a function to add an item for controller
    public void setUpTypes() {
        stage = "type";
        addItemTitle();
        for (int i = 0; i<smoothiesB.size(); i++){
            smoothiesB.get(i).setVisible(false);
        }
        backend = new ServerBackend();
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
        stage = "size";
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
        else{
        cOrder.get(cOrder.size()-1).setSize(size);
        backend.addItem(cOrder.get(cOrder.size()-1).getName());
        cOrder.get(cOrder.size()-1).setPrice(backend.getItemPrice(cOrder.size()-1));
        backend.completeItem();
        }
        displayOrder();
        if (items_ordered.size()>0)
        items_ordered.get(items_ordered.size()-1).setForeground(Color.black);
        setUpTypes();
    }
    public void displayOrder() {
        checkPrice();
        for (int i = 0; i<items_ordered.size(); i++){
            transaction.removeOrder(items_ordered.get(i));
        }
        items_ordered.clear();
        for (int i = 0; i<cOrder.size(); i++){
            JLabel toAdd = new JLabel(cOrder.get(i).toString());
            if (i == cOrder.size()-1)
            toAdd.setForeground(Color.red);
            items_ordered.add(toAdd);
            transaction.addOrderToPanel(toAdd);
        }
        if (cOrder.size()>0)
        System.out.println(cOrder.toString());
        transaction.validate();
        validate();
    }
    public void checkPrice() {
        double price = 0.0;
        for (int i = 0; i<cOrder.size(); i++){
            price += cOrder.get(i).getPrice();
        }
        transaction.changePrice(Math.round(price*100.0)/100.0);
    }
    public void submitOrder() {
        if (stage.equals("size")){
            JOptionPane.showMessageDialog(this, "All Items Must be finished");
            return;
        }
        String name = JOptionPane.showInputDialog(this,
                        "Customer name?", null);
        backend.completeTransaction(name);
                
        parent.newServer();
    }
}

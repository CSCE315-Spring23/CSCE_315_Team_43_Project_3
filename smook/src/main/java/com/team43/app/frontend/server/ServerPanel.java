package com.team43.app.frontend.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.BorderLayout;
import java.awt.GridLayout;
//import java.awt.Container;
import java.awt.Color;

import javax.swing.*;
import javax.swing.border.*;

import org.omg.IOP.CodeSets;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;

import com.team43.app.backend.server.*;
import com.team43.app.frontend.Controller;
import com.team43.app.frontend.MainFrame;

public class ServerPanel extends JPanel {
    JButton b1;
    JButton logout;
    // JPanel contentPane;
    JPanel item_type;
    Transaction transaction;
    //JPanel Items;
    Items items;
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
    JFrame parent;
    Controller controller;
    Substitution subs;

    public ServerPanel(JFrame p, Controller control) {
        this.controller = control;
        backend = new ServerBackend();
        //initialization
        parent = p;
        setLayout(new GridLayout(1,0));
        item_t = new ArrayList<JButton>();
        smoothiesB = new ArrayList<JButton>();
        cOrder = new ArrayList<Order>();
        items_ordered = new ArrayList<JLabel>();
        transaction = new Transaction(backend);
		add(transaction);
        subs = null;
        stage = "";

        items = new Items(backend,this);
        //Items.setLayout(new BoxLayout(Items, BoxLayout.PAGE_AXIS));
        //Items.setBorder(new LineBorder(getBackground(), 3));
		add(items);
        // header = new JLabel("<HTML><U>Smoothie name|Size|Price|AddOns</U></HTML>");
        // transaction.addOrderToPanel(header);
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
        displayOrder();
        if (items_ordered.size()>0)
        items_ordered.get(items_ordered.size()-1).setForeground(Color.black);
        if (subs!=null)
        remove(subs);
        add(items);
        stage = "type";
        addItemTitle();
        for (int i = 0; i<smoothiesB.size(); i++){
            smoothiesB.get(i).setVisible(false);
        }
        stage = "type";
        ArrayList<String> str = backend.getCategories();
        for (int i = 0; i<str.size(); i++){
            JButton toAdd = new JButton(str.get(i));
            final String name = str.get(i);
            toAdd.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setUpSmoothies(name);
                }
            });
            items.add(toAdd);
            item_t.add(toAdd);
        }
        items.validate();
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
		items.add(ItemsL);
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
            items.add(toAdd);
            smoothiesB.add(toAdd);
        }
        validate();
    }
    public void addSmoothie(String str){
        cOrder.add(new Order(str));
        displayOrder();
    }
    public void setSize() {
        ArrayList<String> sizes = new ArrayList<String>(Arrays.asList("Small","Medium","Large"));
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
            items.add(toAdd);
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
        backend.setSize(size);
        //backend.completeItem();
        //cOrder.get(cOrder.size()-1).setPrice(backend.getItemPrice(cOrder.size()-1));
        }
        displayOrder();
        //setUpTypes();
        setUpSubs();
    }
    public void displayOrder() {
        checkPrice();
        // for (int i = 0; i<items_ordered.size(); i++){
        //     transaction.removeOrder(items_ordered.get(i));
        // }
        transaction.removeOrders();
        items_ordered.clear();
        for (int i = 0; i<cOrder.size(); i++){
            JLabel toAdd = new JLabel(cOrder.get(i).toString());
            if (i == cOrder.size()-1 && stage != "delete")
            toAdd.setForeground(Color.red);
            items_ordered.add(toAdd);
            transaction.addOrderToPanel(toAdd,cOrder.get(i));
            final int j = i;
        }
        for (int i = 0; i<transaction.deleteButtons.size(); i++){
            final int j = i;
            System.out.println(j);
            transaction.deleteButtons.get(i).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    deleteOrder(j);
                    backend.removeItem(j);
                }
            });
        }
        if (cOrder.size()>0)
        System.out.println(cOrder.toString());
        transaction.revalidate();
        validate();
    }
    public void deleteOrder(int i) {
        System.out.println("Hola");
        cOrder.remove(i);
        displayOrder();
        transaction.listerValidate();
        transaction.revalidate();
        // revalidate();
        stage = "delete";
        //setUpTypes();
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
                
        controller.newServer();
    }
    public void setUpSubs() {
        remove(items);
        //items.setVisible(false);
        subs = new Substitution(cOrder.get(cOrder.size()-1).getName(), backend, this);
        add(subs);
        validate();
    }
    public void addSubs(String str){
        cOrder.get(cOrder.size()-1).addSub(str);
    }
}

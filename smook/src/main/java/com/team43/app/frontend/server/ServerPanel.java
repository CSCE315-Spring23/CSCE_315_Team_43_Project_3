/**

    The ServerPanel class provides a graphical user interface for the smoothie ordering system.
    It allows users to select categories, smoothies, sizes, and other options for their orders.
    It interacts with the ServerBackend class and the Controller class to handle orders and to
    communicate with the backend of the application.
    This class extends the JPanel class and is used by the MainFrame class to display the server interface.
    */
    package com.team43.app.frontend.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.*;

import org.omg.IOP.CodeSets;

import com.team43.app.backend.server.*;
import com.team43.app.frontend.Controller;
import com.team43.app.frontend.MainFrame;

public class ServerPanel extends JPanel {

/**
 * Button to cancel the order
 */
JButton b1;

/**
 * Button to logout of the server
 */
JButton logout;

/**
 * Panel for displaying the types of items (smoothies, etc.) available for order
 */
JPanel item_type;

/**
 * An object for holding a user's transaction information
 */
Transaction transaction;

/**
 * Panel for displaying the items available for order
 */
Items items;

/**
 * A label for displaying the order
 */
JLabel Order;

/**
 * A label for displaying the list of items available for order
 */
JLabel ItemsL;

/**
 * Panel for displaying the items that are currently on the user's order list
 */
JPanel lister;

/**
 * Object for handling the backend of the server application
 */
ServerBackend backend;

/**
 * List of buttons for selecting the types of items available for order
 */
ArrayList<JButton> item_t;

/**
 * List of buttons for selecting specific smoothies available for order
 */
ArrayList<JButton> smoothiesB;

/**
 * List of labels for displaying items on the user's current order list
 */
ArrayList<JLabel> items_ordered;

/**
 * String that indicates the current stage of the order process (selecting category, smoothie, size, etc.)
 */
String stage;

/**
 * List of orders that have been placed
 */
ArrayList<Order> currentOrder;

/**
 * A label for displaying the server header
 */
JLabel header;

/**
 * Button for submitting an order
 */
JButton submit;

/**
 * The parent frame that contains the ServerPanel object
 */
JFrame parent;

/**
 * Object for handling communication between the server and the backend
 */
Controller controller;

/**
 * Object for handling substitutions
 */
Substitution subs;

/**
 * Creates a new instance of the ServerPanel class and initializes the graphical user interface 
 * with necessary components and listeners.
 * 
 * @param p The parent frame that contains the ServerPanel object
 * @param control Object for handling communication between the server and the backend
 */
public ServerPanel(JFrame p, Controller control) {

    this.controller = control;
    backend = new ServerBackend();
    parent = p;
    setLayout(new GridLayout(1,0));
    item_t = new ArrayList<JButton>();
    smoothiesB = new ArrayList<JButton>();
    currentOrder = new ArrayList<Order>();
    items_ordered = new ArrayList<JLabel>();
    transaction = new Transaction(backend);
    add(transaction);
    subs = null;
    stage = "";

        items = new Items(backend,this);
		add(items);
        addItemTitle();
        setUpTypes();
        submit = transaction.getButton();
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submitOrder();
            }
        });
    }
/**

    Method that sets up the types of smoothies to be displayed on the UI.
    It first displays the orders, changes the color of the last added item to black, removes any existing subs,
    adds the items, sets the stage to type and adds the title.
    It then loops through the categories and creates a button for each.
    The button is then added to the items ArrayList and the item_t ArrayList.
    Lastly, it validates the items.
    */
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
    
    /**
    
        Method that sets the title of the smoothie items to be displayed on the UI depending on the stage of the process.
        If the stage is "smoothie", the label text is set to "Pick a smoothie".
        If the stage is "type", the label text is set to "Pick Category".
        Otherwise, a new label with the text "Pick Category" is created.
        The font is set to "Tahoma" and size is set to 30.
        Lastly, the label is added to the items.
        */
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
    
    /**
    
        Method that sets up the smoothie items to be displayed on the UI.
        It first loops through the item_t ArrayList and sets their visibility to false.
        The stage is then set to "smoothie" and the item title is set.
        It then loops through the smoothies ArrayList and creates a button for each.
        The button is then added to the items ArrayList and the smoothiesB ArrayList.
        Lastly, it validates the UI.
        @param str String representing the category of smoothie to be displayed
        */

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
/**

    This method adds a smoothie to the order.
    @param str a string representing the name of the smoothie to be added to the order.
    */
    public void addSmoothie(String str){
        currentOrder.add(new Order(str));
        displayOrder();
        }
    
    /**
    
        This method sets up the size selection for the last smoothie added to the order.
        */
        public void setSize() {
        ArrayList<String> sizes = new ArrayList<String>(Arrays.asList("Small","Medium","Large"));
        for (int i = 0; i<smoothiesB.size(); i++){
        smoothiesB.get(i).setVisible(false);
        }
        stage = "size";
        ItemsL.setText(currentOrder.get(currentOrder.size()-1).getName() + ": Choose a size");
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
    
    /**
    
        This method sets the size of the last smoothie added to the order.
        @param size a string representing the size of the smoothie.
        */
        public void getSize(String size){
        if (size.equals("Cancel")){
        if (currentOrder.size()>0)
        currentOrder.remove(currentOrder.size()-1);
        }
        else{
        currentOrder.get(currentOrder.size()-1).setSize(size);
        backend.addItem(currentOrder.get(currentOrder.size()-1).getName());
        backend.setSize(size);
        //backend.completeItem();
        //currentOrder.get(currentOrder.size()-1).setPrice(backend.getItemPrice(currentOrder.size()-1));
        }
        displayOrder();
        //setUpTypes();
        setUpSubs();
        }
    
    /**
    
        This method displays the current order in the GUI.
        */
        public void displayOrder() {
        checkPrice();
        // for (int i = 0; i<items_ordered.size(); i++){
        // transaction.removeOrder(items_ordered.get(i));
        // }
        transaction.removeOrders();
        items_ordered.clear();
        for (int i = 0; i<currentOrder.size(); i++){
        JLabel toAdd = new JLabel(currentOrder.get(i).toString());
        if (i == currentOrder.size()-1 && stage != "delete")
        toAdd.setForeground(Color.red);
        items_ordered.add(toAdd);
        transaction.addOrderToPanel(toAdd,currentOrder.get(i));
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
        if (currentOrder.size()>0)
        System.out.println(currentOrder.toString());
        transaction.revalidate();
        validate();
        }
    
    /**
    
        This method deletes an order from the current order list.
        @param i an integer representing the index of the order to be deleted.
        */
        public void deleteOrder(int i) {
        System.out.println("Hola");
        currentOrder.remove(i);
        displayOrder();
        transaction.listerValidate();
        transaction.revalidate();
        // revalidate();
        stage = "delete";
        //setUpTypes();
        }
    
    /**
    
        This method calculates and displays the total price of the current order.
        */
    public void checkPrice() {
        double price = 0.0;
        for (int i = 0; i<currentOrder.size(); i++){
            price += currentOrder.get(i).getPrice();
        }
        transaction.changePrice(Math.round(price*100.0)/100.0);
    }
/**

    This method is used to submit the order for processing. If the order stage is "size",
    the method will display a message box indicating that all items must be finished before
    submitting the order. If the stage is not "size", the method will prompt the user for the
    customer's name and complete the transaction in the backend. After the transaction is
    completed, the method will call the newServer() method in the controller.
    */
    public void submitOrder() {
        if (stage.equals("size")){
        JOptionPane.showMessageDialog(this, "All Items Must be finished");
        return;
        }
        String name = JOptionPane.showInputDialog(this, "Customer name?", null);
        backend.completeTransaction(name);
        controller.newServer();
        }
    
    /**
    
        This method is used to set up the substitutions panel for the current order. It removes
        the items panel and adds a new substitution panel to the GUI. The new substitution panel
        is created using the name of the current order, the backend, and the current instance of
        the GUI. After the substitution panel is added, the method calls the validate() method
        to update the GUI.
        */
        public void setUpSubs() {
        remove(items);
        //items.setVisible(false);
        subs = new Substitution(currentOrder.get(currentOrder.size()-1).getName(), backend, this);
        add(subs);
        validate();
        }
    
    /**
    
        This method is used to add a substitution to the current order. The substitution is added
        to the last item in the current order using the addSub() method in the Order class.
        @param str the name of the substitution to add to the current order
        */
    public void addSubs(String str){
        currentOrder.get(currentOrder.size()-1).addSub(str);
    }
}
package com.team43.app.frontend.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.util.ArrayList;
import java.awt.Font;

import javax.sound.sampled.Line;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import com.team43.app.backend.manager.jdbcpostgreSQL;
import com.team43.app.backend.server.*;

/**

    The Transaction class extends JPanel and represents the UI panel for the current transaction of orders.
    */
    public class Transaction extends JPanel {

        /**
            The label for the title of the current transaction panel.
            */
            JLabel Title;
    
        /**
            The panel for displaying the list of orders in the current transaction.
            */
            JPanel lister;
    
        /**
            The label for displaying the total price of the current transaction.
            */
            JLabel price;
    
        /**
            The button for submitting the current transaction and finishing the order.
            */
            JButton submit;
    
        /**
            The panel for displaying the footer of the current transaction panel.
            */
            JPanel footer;
    
        /**
            The label for displaying the total price text in the footer of the current transaction panel.
            */
            JLabel Total;
    
        /**
            The button for ending the current day of transactions.
            */
            JButton logout;
    
        /**
            The instance of the ServerBackend class responsible for handling the server operations.
            */
            ServerBackend backend;
    
        /**
            The list of buttons for deleting orders from the current transaction panel.
            */
            public ArrayList<JButton> deleteButtons;
    
        /**
    
            Creates a new Transaction panel with the specified server backend instance.
    
            @param b The server backend instance for handling server operations.
            */
            public Transaction(ServerBackend b) {
            backend = b;
            setLayout(new BorderLayout(0, 0));
    
            deleteButtons = new ArrayList<JButton>();
            Title = new JLabel("Current Transaction");
            Title.setFont(new Font("Tahoma", Font.PLAIN, 30));
            Title.setHorizontalAlignment(SwingConstants.CENTER);
            add(Title, BorderLayout.NORTH);
    
            lister = new JPanel();
            add(lister, BorderLayout.CENTER);
            lister.setLayout(new BoxLayout(lister, BoxLayout.PAGE_AXIS));
            //lister.setLayout(new BoxLayout(lister, BoxLayout.Y_AXIS));
            //lister.setBorder(new LineBorder(Color.black,3));
    
            footer = new JPanel();
            add(footer, BorderLayout.SOUTH);
    
            Total = new JLabel("Total Price:");
            footer.add(Total);
    
            price = new JLabel("$0.00");
            footer.add(price);
    
            submit = new JButton("Finish Order");
            submit.setHorizontalAlignment(SwingConstants.RIGHT);
            footer.add(submit);
    
            logout = new JButton("End Day");
            logout.setHorizontalAlignment(SwingConstants.RIGHT);
            footer.add(logout);
            logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            // controller.navigatePageBack();
            backend.finishTransactions();
            System.exit(0);
            }
            });
            addHead();
            }
    
        /**
            Adds the header row to the list of orders in the current transaction panel.
            */
            public void addHead() {
            JPanel head = new JPanel();
            JLabel header = new JLabel("<HTML><U>Smoothie name|Size|Price|Delete</U></HTML>");
            header.setFont(new Font("Tahoma", Font.BOLD, 24));
            head.add(header);
            head.setMaximumSize(new Dimension(lister.getWidth(),40));
            lister.add(head);
            }
    
        /**
            Changes the price displayed in the footer of the current transaction panel.
            @param p The new price to be displayed in the footer.
            */
            public void changePrice(double p){
            price.setText("$" + p);
            price.validate();
            footer.validate();
            validate();
            }
    
        /**
            Adds a new order to the list of orders in the current transaction panel.
            @param order_name The label for the order to be added.
            @param orderedItem The Order object representing the order to be added.
            */
    public void addOrderToPanel(JLabel order_name, Order orderedItem) {
        order_name.setFont(new Font("Tahoma", Font.PLAIN, 17));
        JPanel list_element = new JPanel();
        list_element.add(order_name);
        JButton trash = new JButton("X");
        list_element.add(trash);
        //trash.setVisible(false);
        deleteButtons.add(trash);
        lister.add(list_element);
        list_element.setMaximumSize(new Dimension(lister.getWidth(),35));
        for (int i = 0; i < orderedItem.numSubs(); i++){
            JLabel subsituition = new JLabel("+" + orderedItem.getSub(i));
            //subsituition.setHorizontalAlignment(SwingConstants.CENTER);
            lister.add(subsituition);
        }
        lister.validate();
    }
/**

    This method revalidates the component "lister".
    It ensures that any changes to the lister's components are reflected in the GUI.
    */
    public void listerValidate() {
        lister.revalidate();
        }
    
    /**
    
        This method removes all orders from the "lister" component and performs necessary actions to update the GUI.
        It removes all components from the lister, revalidates and repaints the lister, adds a header to the lister,
        revalidates the parent container, and clears the deleteButtons list.
        */
        public void removeOrders() {
        lister.removeAll();
        lister.revalidate();
        lister.repaint();
        addHead();
        revalidate();
        deleteButtons.clear();
        }
    
    /**
    
        This method returns the submit button object.
        @return the submit button object.
        */
        public JButton getButton(){
        return submit;
        }
}

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


public class Transaction extends JPanel {

	/**
	 * Create the panel.
	 */
    JLabel Title;
    JPanel lister;
    JLabel price;
    JButton submit;
    JPanel footer;
    JLabel Total;
    JButton logout;
    ServerBackend backend;

    public ArrayList<JButton> deleteButtons;

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
    public void addHead() {
        JPanel head = new JPanel();
        JLabel header = new JLabel("<HTML><U>Smoothie name|Size|Price|Delete</U></HTML>");
        header.setFont(new Font("Tahoma", Font.BOLD, 24));
        head.add(header);
        head.setMaximumSize(new Dimension(lister.getWidth(),40));
        lister.add(head);
    }
    public void changePrice(double p){
        price.setText("$" + p);
        price.validate();
        footer.validate();
        validate();
    }
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
    // public void setTextOfOrder(String s){

    // }
    // public void removeOrder(JLabel order_name){
    //     lister.remove(order_name);
    // }
    public void listerValidate() {
        lister.revalidate();
    }
    public void removeOrders() {
        lister.removeAll();
        lister.revalidate();
        lister.repaint();
        addHead();
        revalidate();
        deleteButtons.clear();
    }
    public JButton getButton(){
        return submit;
    }
}

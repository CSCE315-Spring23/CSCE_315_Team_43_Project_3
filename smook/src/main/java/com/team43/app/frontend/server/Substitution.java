/**

    The com.team43.app.frontend.server package contains classes that handle
    the user interface of the server application.
    */
    package com.team43.app.frontend.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.GridLayout;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import com.team43.app.backend.server.*;

/**

    The Substitution class is a JPanel that allows the user to substitute

    ingredients for a selected item.
    */
    public class Substitution extends JPanel {

    /**
        A unique identifier for serialization.
        */
        private static final long serialVersionUID = 1L;

    /**
        The title label for the Substitution panel.
        */
        JLabel title;

    /**
        A JPanel to display the current ingredients of the selected item.
        */
        JPanel lister;

    /**
        A JPanel to display the substitute ingredients for the selected item.
        */
        JPanel subs2;

    /**
        A JPanel to display the current quantities of the selected item's ingredients.
        */
        JPanel current2;

    /**
        A JScrollPane to allow the user to scroll through the current ingredients.
        */
        JScrollPane Current;

    /**
        A label for the current ingredient section.
        */
        JLabel current_label;

    /**
        A JScrollPane to allow the user to scroll through the substitute ingredients.
        */
        JScrollPane Subs;

    /**
        A label for the substitute ingredient section.
        */
        JLabel subs_label;

    /**
        A button to submit the selected substitutions.
        */
        JButton submit;

    /**
        The ServerBackend object used to manage the server's database.
        */
        ServerBackend backend;

    /**
        The ServerPanel object used to manage the server's user interface.
        */
        ServerPanel mainP;

    /**
        A JSpinner object used to select the quantity of an ingredient.
        */
        private JSpinner spinner;

    /**
        An ArrayList of Strings containing the names of the selected item's ingredients.
        */
        private ArrayList<String> items;

    /**
        An ArrayList of JSpinner objects corresponding to the quantities of the selected item's ingredients.
        */
        private ArrayList<JSpinner> spins;

    /**
        An ArrayList of Strings containing the names of the substitute ingredients selected by the user.
        */
        private ArrayList<String> newItems;

    /**

        Creates a new Substitution panel for the specified item.

        @param item the name of the item to substitute ingredients for.

        @param b the ServerBackend object used to manage the server's database.

        @param m the ServerPanel object used to manage the server's user interface.
        */
	public Substitution(String item, ServerBackend b, ServerPanel m) {
		//Substitution panel title
		backend = b;
        mainP = m;
		items = new ArrayList<String>();
		spins = new ArrayList<JSpinner>();
		newItems = new ArrayList<String>();
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		title = new JLabel(item);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", Font.PLAIN, 30));
		add(title);
		title.setAlignmentX(this.CENTER_ALIGNMENT);
		
		lister = new JPanel();
		add(lister);
		//lister.setLayout(new BoxLayout(lister, BoxLayout.PAGE_AXIS));
		lister.setLayout(new GridLayout(0,1));
        lister.setBorder(new EmptyBorder(10,10,10,10));
        
        Current = new JScrollPane();
        lister.add(Current);
        
        current_label = new JLabel("Current Ingredients");
        current_label.setFont(new Font("Rockwell", Font.BOLD, 20));
        current_label.setHorizontalAlignment(SwingConstants.CENTER);
        Current.setColumnHeaderView(current_label);
        
        Subs = new JScrollPane();
        lister.add(Subs);
        
        subs_label = new JLabel("Subsitutions");
        subs_label.setFont(new Font("Rockwell", Font.BOLD, 20));
        subs_label.setHorizontalAlignment(SwingConstants.CENTER);
        Subs.setColumnHeaderView(subs_label);
        
        subs2 = new JPanel();
        subs2.setLayout(new BoxLayout(subs2, BoxLayout.PAGE_AXIS));
        Subs.setViewportView(subs2);
        
        current2 = new JPanel();
        current2.setLayout(new BoxLayout(current2, BoxLayout.PAGE_AXIS));
        Current.setViewportView(current2);
        
        submit = new JButton("Add order");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submit();
            }
        });
        add(submit);
        
        setUpCurrent();
        setUpSubs();
	}
/**

    This method sets up the display of the current ingredients and their quantities.
    It uses the backend's getItemIngredients() method to get the current ingredients for the item being made.
    For each ingredient, it creates a JPanel and adds a JLabel with the ingredient's name and a JSpinner with the ingredient's quantity.
    The JSpinner is initialized with the quantity of the ingredient using the backend's getIngredientQuantity() method.
    The method then adds the JPanel to the current2 JPanel and validates the current2 and Current JPanels.
    */
    public void setUpCurrent() {
		ArrayList<String> curr = backend.getItemIngredients();
		for (int i = 0; i<curr.size(); i++) {
		JPanel jp = new JPanel();
		JLabel l = new JLabel(curr.get(i));
		spinner = new JSpinner();
		spinner.setValue(backend.getIngredientQuantity(curr.get(i)));
		items.add(curr.get(i));
		jp.add(l);
		spins.add(spinner);
		jp.add(spinner);
		current2.add(jp);
		}
		current2.validate();
		Current.validate();
		}
	
	/**
	
		This method sets up the display of the substitute ingredients.
		It uses the backend's getAllIngredients() method to get all available ingredients.
		For each ingredient, it creates a JButton with the ingredient's name and adds an ActionListener that calls the addItem() method when clicked.
		The method then adds the JButton to the subs2 JPanel and validates the subs2 and Subs JPanels.
		*/
		public void setUpSubs() {
		ArrayList<String> curr = backend.getAllIngredients();
		for (int i = 0; i<curr.size(); i++) {
		final JButton jb = new JButton(curr.get(i));
		jb.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		addItem(jb.getText());
		jb.setVisible(false);
		}
		});
		subs2.add(jb);
		}
		subs2.validate();
		Subs.validate();
		}
	
	/**
	
		This method adds a new item to the current ingredients.
		It creates a JPanel and adds a JLabel with the item's name and a JSpinner with a quantity of 1.
		The method then adds the JPanel to the current2 JPanel, adds the item to the items ArrayList, adds the JSpinner to the spins ArrayList, and validates the current2 and Current JPanels.
		@param str the name of the item to add
		*/
		public void addItem(String str) {
		JPanel jp = new JPanel();
		newItems.add(str);
		items.add(str);
		JLabel l = new JLabel(str);
		spinner = new JSpinner();
		spinner.setValue(1);
		jp.add(l);
		spins.add(spinner);
		jp.add(spinner);
		current2.add(jp);
		current2.validate();
		Current.validate();
		}
	
	/**
	
		This method submits the current order.
		It goes through each item in the order and retrieves its quantity from the JSpinner.
		If the quantity is less than 0, the item's quantity is set to 0 using the backend's adjustItem() method.
		If the quantity is greater than or equal to 0, the item's quantity is adjusted using the backend's adjustItem() method.
		If the item is a new item (i.e. was added using the addItem() method), it is added to the mainP's subs ArrayList using the addSubs() method.
		The backend's completeItem() method is called to mark the current item as completed.
		The current item's price is set using the backend's getItemPrice() method.
		The order is displayed using the mainP's displayOrder() method.
		The current window is set to not visible and the mainP's setUpTypes() method is called to set up the
		*/
	public void submit() {
		int num = items.size();
		for (int i = 0; i<num; i++) {
			try {
				spins.get(i).commitEdit();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if ((Integer)spins.get(i).getValue()<0)
			backend.adjustItem(items.get(i), 0);
			else{
				if (newItems.contains(items.get(i)))
				mainP.addSubs(items.get(i));
				backend.adjustItem(items.get(i), (Integer)spins.get(i).getValue());
		}
	}
		//backend.tester();
		backend.completeItem();
		mainP.currentOrder.get(mainP.currentOrder.size() - 1).setPrice(backend.getItemPrice(mainP.currentOrder.size() - 1));
		mainP.displayOrder();
		
		setVisible(false);
		mainP.setUpTypes();
		//Return to Server Panel control for next drink
	}
}

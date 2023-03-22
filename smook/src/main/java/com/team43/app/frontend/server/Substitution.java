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
public class Substitution extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	JLabel title;
	JPanel lister;
	JPanel subs2;
	JPanel current2;
	JScrollPane Current;
	JLabel current_label;
	JScrollPane Subs;
	JLabel subs_label;
	JButton submit;
	ServerBackend backend;
    ServerPanel mainP;
	private JSpinner spinner;
	private ArrayList<String> items;
	private ArrayList<JSpinner> spins;
	private ArrayList<String> newItems;
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
	public void setUpCurrent() {
		ArrayList<String> curr = backend.getItemIngredients();
		for (int i = 0; i<curr.size(); i++) {
			JPanel jp = new JPanel();
			//jp.setBorder(new LineBorder(Color.black));
//			jp.setLayout(new GridLayout(1,0));
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
	public void addItem(String str) {
		JPanel jp = new JPanel();
		//jp.setBorder(new LineBorder(Color.black));
//		jp.setLayout(new GridLayout(1,0));
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

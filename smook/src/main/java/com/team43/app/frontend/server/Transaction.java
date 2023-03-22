// package com.team43.app.frontend.server;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JButton;

public class Transaction extends JPanel {

    JLabel Title;
    JPanel lister;
    JLabel price;
    JButton submit;
    JPanel footer;
    JLabel Total;

    public Transaction() {
        setLayout(new BorderLayout(0, 0));

        Title = new JLabel("Current Transaction");
        Title.setFont(new Font("Tahoma", Font.PLAIN, 30));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        add(Title, BorderLayout.NORTH);

        lister = new JPanel();
        add(lister, BorderLayout.CENTER);
        lister.setLayout(new BoxLayout(lister, BoxLayout.PAGE_AXIS));
        lister.setBorder(new EmptyBorder(10, 10, 10, 10));

        footer = new JPanel();
        add(footer, BorderLayout.SOUTH);

        Total = new JLabel("Total Price:");
        footer.add(Total);

        price = new JLabel("$0.00");
        footer.add(price);

        submit = new JButton("Finish Order");
        submit.setHorizontalAlignment(SwingConstants.RIGHT);
        footer.add(submit);

    }

    public void changePrice(double p) {
        price.setText("$" + p);
        price.validate();
        footer.validate();
        validate();
    }

    public void addOrderToPanel(JLabel order_name) {
        order_name.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lister.add(order_name);
        lister.validate();
    }

    public void removeOrder(JLabel order_name) {
        lister.remove(order_name);
    }

    public JButton getButton() {
        return submit;
    }
}

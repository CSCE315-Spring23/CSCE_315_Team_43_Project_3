/**

    The Order class represents a smoothie order.
    It contains the smoothie's name, size, price, and any substitutions made.
    @author Team43
    @version 1.0
    @since 2023-03-22
    */
    package com.team43.app.frontend.server;

import java.util.ArrayList;

public class Order {

/**
 * The smoothie name.
 */
private String smoothie;

/**
 * The size of the smoothie.
 */
private String size;

/**
 * The price of the smoothie.
 */
private double price;

/**
 * The list of substitutions made to the smoothie.
 */
private ArrayList<String> subsitutions;

/**
 * Constructs a new Order with the given smoothie name.
 * 
 * @param s the name of the smoothie
 */
public Order(String s){
    smoothie = s;
    size = "unknown";
    price = 0;
    subsitutions = new ArrayList<String>();
}

/**
 * Returns the name of the smoothie.
 * 
 * @return the name of the smoothie
 */
public String getName() {
    return smoothie;
}

/**
 * Sets the size of the smoothie.
 * 
 * @param s the size of the smoothie
 */
public void setSize(String s){
    size = s;
}

/**
 * Adds a substitution to the smoothie.
 * 
 * @param s the substitution to add
 */
public void addSub(String s){
    subsitutions.add(s);
}

/**
 * Returns the substitution at the given index.
 * 
 * @param i the index of the substitution to return
 * @return the substitution at the given index
 */
public String getSub(int i){
    return subsitutions.get(i);
}

/**
 * Returns the number of substitutions made to the smoothie.
 * 
 * @return the number of substitutions made to the smoothie
 */
public int numSubs() {
    return subsitutions.size();
}

/**
 * Returns a string representation of the substitutions made to the smoothie.
 * 
 * @return a string representation of the substitutions made to the smoothie
 */
private String disSub(){
    return "\n\t" + subsitutions.toString();
}

/**
 * Calculates and returns the price of the smoothie.
 * 
 * @return the price of the smoothie
 */
public double getPrice() {
    if (smoothie.split(" ")[0].equals("Gladiator")){
        int subs = subsitutions.size()-2;
        subs = subs<0 ? 0 : subs;
        return Math.round((price/* + .99*subs*/)*100)/100.0;
    }
    return Math.round((price /*+ .99*subsitutions.size()*/)*100)/100.0;
}

/**
 * Sets the price of the smoothie.
 * 
 * @param d the price of the smoothie
 */
public void setPrice(double d) {
    price = Math.round(d*100)/100.0;
}

/**
 * Returns a string representation of the Order object.
 * 
 * @return a string representation of the Order object
 */
public String toString() {
    String sub = subsitutions.size()>0 ? disSub() : "";
    String s = "";
    switch (size){
            case "Small":
                s += "S";
                break;
            case "Medium":
                s += "M";
                break;
            case "Large":
                s += "L";
                break;
            default:
                s+= "?";
                break;
        }
        String priceStr = "";
        if (price <= 0)
        priceStr = "?";
        else
        priceStr = "" + getPrice();
        String toPrint = smoothie + "|" + s + "|" + priceStr + "|";
        return toPrint;
    }
}

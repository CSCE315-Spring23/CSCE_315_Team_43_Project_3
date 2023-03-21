package com.team43.app.frontend.server;

import java.util.ArrayList;

public class Order {
    private String smoothie;
    private String size;
    private double price;
    private ArrayList<String> subsitutions;

    public Order(String s) {
        smoothie = s;
        size = "unkown";
        price = 0;
        subsitutions = new ArrayList<String>();
    }

    public String getName() {
        return smoothie;
    }

    public void setSize(String s) {
        size = s;
    }

    public void addSub(String s) {
        subsitutions.add(s);
    }

    public String getSub(int i) {
        return subsitutions.get(i);
    }

    private String disSub() {
        return "\n\t" + subsitutions.toString();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double d) {
        price = d;
    }

    public String toString() {
        String sub = subsitutions.size() > 0 ? disSub() : "";
        String s = "";
        switch (size) {
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
                s += "?";
                break;
        }
        String priceStr = "";
        if (price <= 0)
            priceStr = "?";
        else
            priceStr = "" + price;
        return smoothie + "|" + s + "|" + priceStr + "|" + sub;
    }
}

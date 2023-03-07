package com.team43.app.frontend.server;
import java.util.ArrayList;
import com.team43.app.frontend.server.Back;
public class Order {
    private String smoothie;
    private char size;
    private double price;
    private ArrayList<String> subsitutions;
    public Order(String s){
        smoothie = s;
        size = '?';
        price = Back.getPrice(smoothie);
        subsitutions = new ArrayList<String>();
    }
    public void setSize(char s){
        size = s;
    }
    public void addSub(String s){
        subsitutions.add(s);
    }
    public String getSub(int i){
        return subsitutions.get(i);
    }
    private String disSub(){
        return "\n\t" + subsitutions.toString();
    }
    public String toString() {
        return smoothie + "|" + size + "|" + price;
    }
}

package com.team43.app.frontend.server;
import java.util.ArrayList;
//import com.team43.app.frontend.server.Back;
public class Order {
    private String smoothie;
    private String size;
    private double price;
    private ArrayList<String> subsitutions;
    public Order(String s){
        smoothie = s;
        size = "unkown";
        price = 0;
        subsitutions = new ArrayList<String>();
    }
    public String getName() {
        return smoothie;
    }
    public void setSize(String s){
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
    public double getPrice() {
        if (smoothie.split(" ")[0].equals("Gladiator")){
            int subs = subsitutions.size()-2;
            subs = subs<0 ? 0 : subs;
            return Math.round((price + .99*subs)*100)/100.0;
        }
        return Math.round((price + .99*subsitutions.size())*100)/100.0;
    }
    public void setPrice(double d) {
        price = Math.round(d*100)/100.0;
    }
    public String toString() {
        String sub = subsitutions.size()>0 ? disSub() : "";
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
                s+= "?";
                break;
        }
        String priceStr = "";
        if (price <= 0)
        priceStr = "?";
        else
        priceStr = "" + getPrice();
        String toPrint = smoothie + "|" + s + "|" + priceStr + "|";
        if (subsitutions.size() == 0){
            toPrint += ",";
        }
        for (int i = 0; i<subsitutions.size(); i++){
            toPrint += subsitutions.get(i) + ",";
        }
        return toPrint.substring(0,toPrint.length()-1);
    }
}

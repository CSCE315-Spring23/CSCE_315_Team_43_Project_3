package com.team43.app.frontend.server;

import java.util.*;
//This is a local testing version of all server backend functions
public class Back {
    public Back() {

    }
    public ArrayList<String> getCategories() {
        ArrayList<String> arr = new ArrayList<String>(Arrays.asList("Feel Energized","Get Fit","Manage Weight","Be Well","Stay on Track","Enjoy a Treat","Snacks"));
        return arr;
    }
    public ArrayList<String> getItemsInCategory(String category){
        ArrayList<String> arr = new ArrayList<String>(Arrays.asList("Smoothie1","Smoothie2","Smoothie3","Smoothie1","Smoothie2","Smoothie3","Smoothie1","Smoothie2","Smoothie3","Smoothie1","Smoothie2","Smoothie3","Smoothie1","Smoothie2","Smoothie3"));
        return arr;
    }
    public static double getPrice(String item){
        return 6.69;
    }
}
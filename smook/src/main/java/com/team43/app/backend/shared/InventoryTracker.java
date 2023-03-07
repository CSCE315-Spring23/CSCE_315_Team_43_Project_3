package com.team43.app.backend.shared;

import java.util.HashMap;
import java.util.ArrayList;

public class InventoryTracker {
    // key = id, value = items used
    public static HashMap<Integer, Integer> inventoryUsage = new HashMap<Integer, Integer>();
    public static boolean is_initialized = false;

    // initializes all usage counts to 0
    public static void initializeTracker(int num_inventory_items) { 
        for (int id = 1; id <= num_inventory_items; ++id) {
            inventoryUsage.put(id, 0);
        }

        is_initialized = true;
    }

    // resets all usage counts to 0
    public static void resetTracker() {
        for (Integer key : inventoryUsage.keySet()) {
            inventoryUsage.replace(key, 0);
        }
    }

    // updates usage count for given id by given count
    public static void updateItemUsage(int id, int count) {
        inventoryUsage.replace(id, inventoryUsage.get(id) + count);
    }

    // updates multiple usage counts for given ids by corresponding counts
    public static void updateItemUsage(ArrayList<Integer> ids, ArrayList<Integer> counts) {
        // ids must each have a a corresponding count
        if (ids.size() != counts.size()) { return; }

        // update each usage given
        for (int i = 0; i < ids.size(); ++i) {
            updateItemUsage(ids.get(i), counts.get(i));
        }
    }

    // returns usage count of given id
    public static int getItemUsage(int id) {
        return inventoryUsage.get(id);
    }

    // returns usage counts of given ids
    public static ArrayList<Integer> getItemUsage(ArrayList<Integer> ids) {
        ArrayList<Integer> counts = new ArrayList<Integer>();
        for (Integer id : ids) {
            counts.add(getItemUsage(id));
        }
        
        return counts;
    }
}
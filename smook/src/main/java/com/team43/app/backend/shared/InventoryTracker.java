package com.team43.app.backend.shared;

import java.util.ArrayList;
import java.util.HashMap;

public class InventoryTracker {
    // key = id, value = items used
    public static HashMap<Integer, Float> inventoryUsage = new HashMap<Integer, Float>();

    public static boolean is_initialized = false;

    // Initializes all usage counts to 0
    public static void initializeTracker(int num_inventory_items) {
        for (int id = 1; id <= num_inventory_items; ++id) {
            inventoryUsage.put(id, Float.parseFloat("0.0"));
        }

        is_initialized = true;
    }

    // Resets all usage counts to 0
    public static void resetTracker() {
        for (Integer key : inventoryUsage.keySet()) {
            inventoryUsage.replace(key, Float.parseFloat("0.0"));
        }
    }

    // Updates usage count for given id by given count
    public static void updateItemUsage(int id, float count) {
        inventoryUsage.replace(id, inventoryUsage.get(id) + count);
    }

    // Updates multiple usage counts for given ids by corresponding counts
    public static void updateItemUsage(ArrayList<Integer> ids,
            ArrayList<Float> counts) {
        // ids must each have a a corresponding count
        if (ids.size() != counts.size()) {
            return;
        }

        // Update each usage given
        for (int i = 0; i < ids.size(); ++i) {
            updateItemUsage(ids.get(i), counts.get(i));
        }
    }

    // Returns usage count of given id
    public static Float getItemUsage(int id) {
        return inventoryUsage.get(id);
    }

    // Returns usage counts of given ids
    public static ArrayList<Float> getItemUsage(ArrayList<Integer> ids) {
        ArrayList<Float> counts = new ArrayList<Float>();
        for (Integer id : ids) {
            counts.add(getItemUsage(id));
        }

        return counts;
    }
}

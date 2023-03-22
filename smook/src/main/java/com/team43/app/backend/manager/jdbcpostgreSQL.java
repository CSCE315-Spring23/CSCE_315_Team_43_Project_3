package com.team43.app.backend.manager;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Date;


public class jdbcpostgreSQL {
    // Building the connection with your credentials
    Connection conn = null;
    static final String TEAM_NUMBER = "team_43";
    String dbName = "csce315331_" + TEAM_NUMBER;
    String dbConnectionUri = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;

    /**
     * Constructor that creates a new database connection
     * 
     */
    public jdbcpostgreSQL() {
        // Connecting to the database
        try {
            conn = DriverManager.getConnection(dbConnectionUri, dbSetup.user, dbSetup.pswd);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Views the inventory
     * 
     * @return A 2D array of all Inventory objects
     */
    public List<List<String>> viewInventory() {
        List<List<String>> table = new ArrayList<List<String>>();
        try {
            // create a statement object
            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT * FROM inventory ORDER BY inventory_id ASC";
            ResultSet result = stmt.executeQuery(sqlStatement);

            while (result.next()) {
                List<String> elements = new ArrayList<String>();
                elements.add(result.getString("inventory_id"));
                elements.add(result.getString("name"));
                elements.add(result.getString("price"));
                elements.add(result.getString("quantity"));
                elements.add(result.getString("measurement_type"));
                table.add(elements);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return table;
    }

    /**
     * Views an inventory item
     * 
     * @return An array of the Inventory object
     */
    public List<String> get_inventory_item(int inventory_id) {
        List<String> elements = new ArrayList<String>();
        try {
            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT * from inventory where inventory_id = " + inventory_id + ";";
            ResultSet result = stmt.executeQuery(sqlStatement);

            while (result.next()) {
                elements.add(result.getString("inventory_id"));
                elements.add(result.getString("name"));
                elements.add(result.getString("price"));
                elements.add(result.getString("quantity"));
                elements.add(result.getString("measurement_type"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return elements;
    }

    /**
     * Views the menu items
     * @return A 2D array of MenuItem objects
     */
    public List<List<String>> viewMenuItems() {
        List<List<String>> table = new ArrayList<List<String>>();
        try {
            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT * FROM menu_item ORDER BY menu_id ASC";
            ResultSet result = stmt.executeQuery(sqlStatement);

            // OUTPUT
            while (result.next()) {
                List<String> elements = new ArrayList<String>();
                elements.add(result.getString("menu_id"));
                elements.add(result.getString("name"));
                elements.add(result.getString("type"));
                elements.add(result.getString("price"));
                elements.add(result.getString("ingredient_amount"));
                table.add(elements);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return table;
    }

    /**
     * Edits the inventory
     * 
     * @return An array of the edited Inventory object
     */
    public List<String> editInventoryItem(int inventory_id, String name, double price, double quantity,
            String measurement_type) {
        List<String> elements = new ArrayList<String>();
        try {
            Statement stmt = conn.createStatement();
            String sqlStatement = "UPDATE inventory SET name = \'" + name + "\', price = " + price + ", quantity = "
                    + quantity + ", measurement_type = \'" + measurement_type + "\' WHERE inventory_id = "
                    + inventory_id + ";";
            stmt.executeUpdate(sqlStatement);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return elements;
    }

    /**
     * Adds a menu item
     * 
     * @return An array of all Inventory objects
     */
    public List<String> addMenuItem(String name, String type, double price, int ingredient_amount,
            ArrayList<Integer> ingredient_ids) {
        List<String> elements = new ArrayList<String>();
        try {
            List<List<String>> inventory = viewMenuItems();
            int next_index = Integer.parseInt(inventory.get(inventory.size() - 1).get(0)) + 1;

            Statement stmt = conn.createStatement();
            String sqlStatement = "INSERT INTO menu_item (menu_id, name, type, price, ingredient_amount) VALUES ("
                    + next_index + ", \'" + name + "\', \'" + type + "\', " + price + "," + ingredient_amount + ");";
            stmt.executeUpdate(sqlStatement);

            for (int id : ingredient_ids) {
                String insertStmt = "INSERT INTO ingredient_list (inventory_id, menu_id, quantity) VALUES (" + id + ", "
                        + next_index + ", 1);";
                stmt.executeUpdate(insertStmt);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return elements;
    }

    /**
     * Adds an inventory item
     * 
     * @return void
     */
    public void addInventoryItem(String name, double price, double quantity,
    String measurement_type){
        try {
            List<List<String>> inventory = viewInventory();
            int next_index = Integer.parseInt(inventory.get(inventory.size() - 1).get(0)) + 1;

            Statement stmt = conn.createStatement();
            String sqlStatement = "INSERT INTO inventory (inventory_id, name, price, quantity, measurement_type) VALUES ("
                    + next_index + ", \'" + name + "\', " + price + ", " + quantity + ", \'" + measurement_type + "\');";

            System.out.println(sqlStatement);
            stmt.executeUpdate(sqlStatement);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return;
    }

    /**
     * Updates the MenuItem
     * 
     * @param menu_id           MenuItem identifier
     * @param name              Name of menu item
     * @param type              Type of menu item
     * @param price             Cost of menu item
     * @param ingredient_amount Amount of menu item
     * @return Updated menu item
     */
    public List<String> updateMenuItem(int menu_id, String name, String type, double price, int ingredient_amount) {
        List<String> elements = new ArrayList<String>();
        try {
            Statement stmt = conn.createStatement();
            String sqlStatement = "UPDATE menu_item SET name = \'" + name + "\', type = \'" + type + "\', price = "
                    + price + ", ingredient_amount = " + ingredient_amount + " WHERE menu_id = " + menu_id + ";";
            stmt.executeUpdate(sqlStatement);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return elements;
    }

    /**
     * Views the Items Orders
     * 
     * @return A 2D array of all Order objects
     */
    public List<List<String>> viewOrderItems() {
        List<List<String>> table = new ArrayList<List<String>>();
        try {
            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT * FROM order_item ORDER BY order_id ASC";
            ResultSet result = stmt.executeQuery(sqlStatement);

            while (result.next()) {
                List<String> elements = new ArrayList<String>();
                elements.add(result.getString("order_id"));
                // elements.add(result.getString("data_placed"));
                elements.add(result.getString("cost"));
                table.add(elements);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return table;
    }

    /**
     * Add orders to the database
     * @param orders All orders to add
     */
    public void orderItems(List<OrderList> orders) {
        List<List<String>> ingredients = viewInventory();
        try {
            Statement stmt = conn.createStatement();

            // Add a new Order_Item
            // Calculate total cost
            double total_cost = 0;
            for (OrderList order : orders) {
                for (List<String> ingredient : ingredients) {
                    if (Integer.parseInt(ingredient.get(0)) == order.inventory_id)
                        total_cost += Integer.parseInt(ingredient.get(2)) * order.quantity;
                }
            }
            // Find the next index of the order_item
            List<List<String>> order_item_table = viewOrderItems();
            int next_index_order = Integer.parseInt(order_item_table.get(order_item_table.size() - 1).get(0)) + 1;
            String add_order_item = "INSERT INTO order_item (order_id, cost) VALUES (" + next_index_order + ", "
                    + total_cost + ");";
            stmt.executeUpdate(add_order_item);

            // Running a query
            String sqlStatement;
            for (OrderList order : orders) {
                sqlStatement = "INSERT INTO order_list (inventory_id, order_id, quantity) VALUES (" + order.inventory_id
                        + "," + next_index_order + ", " + order.quantity + ");";
                stmt.executeUpdate(sqlStatement);
                List<String> item = get_inventory_item(order.inventory_id);
                double new_quan = Double.parseDouble(item.get(3)) + order.quantity;
                editInventoryItem(order.inventory_id, item.get(1), Double.parseDouble(item.get(2)), new_quan,
                        item.get(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

  /**
   * Finds the most frequent pairs
   * @param start string describing start of range to use in yyyy-mm-dd format
   * @param end   string describing end of range to use in yyyy-mm-dd format
   * @return      a list of the most frequent pairs 
   */
  public List<List<Integer>> findPairs(String start, String end) {
    List<List<Integer>> table = new ArrayList<List<Integer>>();
    try {
      // create a statement object
      Statement stmt = conn.createStatement();

      // Running a query
      String sqlStatement = "SELECT O1.menu_id AS menu_id_1, O2.menu_id AS menu_id_2, COUNT(*) AS PurchaseFrequency FROM transaction_item AS O1 INNER JOIN transaction_item AS O2 ON O1.transaction_id = O2.transaction_id AND O1.menu_id < O2.menu_id WHERE O1.transaction_id IN (SELECT transaction_id FROM transaction WHERE transaction.time_of_purchase BETWEEN CAST('" + start + "' AS DATE) AND CAST('" + end + "' AS DATE)) AND O2.transaction_id IN (SELECT transaction_id FROM transaction WHERE transaction.time_of_purchase BETWEEN CAST('2022-05-01' AS DATE) AND CAST('2022-10-05' AS DATE)) GROUP BY menu_id_1, menu_id_2 ORDER BY PurchaseFrequency DESC;";

      // send statement to DBMS
      ResultSet result = stmt.executeQuery(sqlStatement);

      // OUTPUT
      while (result.next()) {
        List<Integer> elements = new ArrayList<Integer>();
        elements.add(result.getInt("menu_id_1"));
        elements.add(result.getInt("menu_id_2"));
        elements.add(result.getInt("purchasefrequency"));
        table.add(elements);
      }
      return table;
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
    return null;
  }

    /**
     * Views the Sales
     * 
     * @return A 2D array of all sales objects
     */
    public List<List<String>> getSalesReport(String startDate, String endDate){
        try {
            Statement stmt = conn.createStatement();

            // Find lowest transaction id
            String sqlStatement = "SELECT * from transaction WHERE time_of_purchase between '" + startDate + "' and '" + startDate + "';";
            ResultSet start = stmt.executeQuery(sqlStatement);
            int lowestID = 0;
            while (start.next()) {
                if (Integer.parseInt(start.getString("transaction_id")) < lowestID)
                    lowestID = Integer.parseInt(start.getString("transaction_id"));
            }

            // Find the highest transaction id
            String sqlStatement2 = "SELECT * from transaction WHERE time_of_purchase between '" + endDate + "' and '" + endDate + "';";
            ResultSet end = stmt.executeQuery(sqlStatement);
            int highestID = 0;
            while (end.next()) {
                if (Integer.parseInt(end.getString("transaction_id")) > highestID)
                    highestID = Integer.parseInt(end.getString("transaction_id"));
            }

            // Create a new table to hold the sales report
            String dropTable = "DROP table sales_report;";
            stmt.executeUpdate(dropTable);
            String newTable = "CREATE TABLE sales_report AS SELECT menu_id, name, type, price, ingredient_amount, 0 AS amount_ordered FROM menu_item;";
            stmt.executeUpdate(newTable);

            // Update the amount_ordered value in the sales report table
            String getTransactionItems = "SELECT * FROM transaction_item WHERE transaction_id BETWEEN " + lowestID + " AND " + highestID + ";";
            ResultSet getTable = stmt.executeQuery(getTransactionItems);
            String updateItemCount = "";
            List<Integer> items = new ArrayList<Integer>();
            HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
            while(getTable.next()){
                // Increment the amount_ordered in the sales report table
                // items.add(Integer.parseInt(getTable.getString("menu_id")));
                // if the key already exists, increment it
                if (hm.containsKey(Integer.parseInt(getTable.getString("menu_id"))))
                    hm.put(Integer.parseInt(getTable.getString("menu_id")), hm.get(Integer.parseInt(getTable.getString("menu_id"))) + 1);
                // otherwise create the key with a starting value of one
                else
                    hm.put(Integer.parseInt(getTable.getString("menu_id")), 1);
            }
            // Iterate through the hashmap and update the sales report values
            for (Map.Entry<Integer,Integer> mapElement : hm.entrySet()) {
                int key = mapElement.getKey();
                String increment = "UPDATE sales_report SET amount_ordered = " + mapElement.getValue() + " WHERE menu_id = " + key + ";";
                stmt.executeUpdate(increment);   
            }
            // Return the full table
            List<List<String>> sales_report_table = new ArrayList<List<String>>();
            String sqlStatement3 = "SELECT * FROM sales_report ORDER BY menu_id ASC";
            ResultSet sales_table = stmt.executeQuery(sqlStatement3);

            while (sales_table.next()) {
                List<String> elements = new ArrayList<String>();
                elements.add(sales_table.getString("menu_id"));
                elements.add(sales_table.getString("name"));
                elements.add(sales_table.getString("type"));
                elements.add(sales_table.getString("price"));
                elements.add(sales_table.getString("ingredient_amount"));
                elements.add(sales_table.getString("amount_ordered"));
                sales_report_table.add(elements);
            }
            return sales_report_table;
            } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }

    /**
     * Views the X Report
     * 
     * @return A 2D array of all report objects
     */
    public List<List<String>> getXReport(){
        try {
            Statement stmt = conn.createStatement();

            // Today
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 1);
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            String formatted = format1.format(cal.getTime());

            return getSalesReport(formatted, formatted);
            } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }

    /**
     * Views the Z Report
     * 
     * @return A 2D array of all report objects
     */
    public List<List<String>> getZReport(){
        try {
            Statement stmt = conn.createStatement();

            // Today
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 1);
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            String formatted = format1.format(cal.getTime());

            return getSalesReport(formatted, formatted);
            } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }

    /**
     * Ends the psql connection
     * @return If the connection was correctly closed
     */
    public boolean closeConnection() {
        try {
            conn.close();
            System.out.println("Connection Closed.");
        } catch (Exception e) {
            System.out.println("Connection NOT Closed.");
            return false;
        }
        return true;
    }
}

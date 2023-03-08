package com.team43.app.backend.manager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class jdbcpostgreSQL {
  // Building the connection with your credentials
  Connection conn = null;
  static final String TEAM_NUMBER = "team_43";
  String dbName = "csce315331_" + TEAM_NUMBER;
  String dbConnectionUri = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
  
  public jdbcpostgreSQL(){
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
   * @return A 2D array of all Inventory objects
   */
  public List<List<String>> viewInventory(){
    List<List<String>> table = new ArrayList<List<String>>();
    try {
      // create a statement object
      Statement stmt = conn.createStatement();

      // Running a query
      String sqlStatement = "SELECT * FROM inventory ORDER BY inventory_id ASC";

      // send statement to DBMS
      ResultSet result = stmt.executeQuery(sqlStatement);


      // OUTPUT
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

  public List<String> get_inventory_item(int inventory_id){
    List<String> elements = new ArrayList<String>();
    try {
      // create a statement object
      Statement stmt = conn.createStatement();

      // Running a query
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
  public List<List<String>> viewMenuItems(){
    List<List<String>> table = new ArrayList<List<String>>();
    try {
      // create a statement object
      Statement stmt = conn.createStatement();

      // Running a query
      String sqlStatement = "SELECT * FROM menu_item ORDER BY menu_id ASC";

      // send statement to DBMS
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
  public List<String> editInventoryItem(int inventory_id, String name, double price, double quantity, String measurement_type){
    List<String> elements = new ArrayList<String>();
    try {
      // create a statement object
      Statement stmt = conn.createStatement();

      // Running a query
      String sqlStatement = "UPDATE inventory SET name = \'" + name + "\', price = " + price + ", quantity = " + quantity + ", measurement_type = \'" + measurement_type + "\' WHERE inventory_id = " + inventory_id + ";";

      int result = stmt.executeUpdate(sqlStatement);
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
    return elements;
  }
  public List<String> addMenuItem(int menu_id, String name, String type, double price, int ingredient_amount){
    List<String> elements = new ArrayList<String>();
    try {
      // create a statement object
      Statement stmt = conn.createStatement();

      // Running a query
      String sqlStatement = "INSERT INTO menu_item (menu_id, name, type, price, ingredient_amount) VALUES (" + menu_id + ", \'" + name + "\', \'" + type + "\', " + price + "," + ingredient_amount + ");";

      int result = stmt.executeUpdate(sqlStatement);
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
    return elements;
  }
  
  /**
   * Updates the MenuItem ()
   * @param menu_id MenuItem identifier
   * @param name Name of menu item
   * @param type Type of menu item
   * @param price Cost of menu item
   * @param ingredient_amount Amount of menu item
   * @return Updated menu item
   */
  public List<String> updateMenuItem(int menu_id, String name, String type, double price, int ingredient_amount){
    List<String> elements = new ArrayList<String>();
    try {
      // create a statement object
      Statement stmt = conn.createStatement();

      // Running a query
      String sqlStatement = "UPDATE menu_item SET name = \'" + name + "\', type = \'" + type + "\', price = " + price + ", ingredient_amount = " + ingredient_amount + " WHERE menu_id = " + menu_id + ";";

      int result = stmt.executeUpdate(sqlStatement);
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
    return elements;
  }

  public List<List<String>> viewOrderItems(){
    List<List<String>> table = new ArrayList<List<String>>();
    try {
      // create a statement object
      Statement stmt = conn.createStatement();

      // Running a query
      String sqlStatement = "SELECT * FROM order_item ORDER BY order_id ASC";

      // send statement to DBMS
      ResultSet result = stmt.executeQuery(sqlStatement);


      // OUTPUT
      while (result.next()) {
        List<String> elements = new ArrayList<String>();
        elements.add(result.getString("order_id"));
        //elements.add(result.getString("data_placed"));
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
  public void orderItems(List<OrderList> orders){
    List<List<String>> ingredients = viewInventory();
    try {
      // create a statement object
      Statement stmt = conn.createStatement();

      // Add a new Order_Item
      // Calculate total cost
      double total_cost = 0;
      for (OrderList order: orders){
        for (List<String> ingredient: ingredients){
          if (Integer.parseInt(ingredient.get(0)) == order.inventory_id)
            total_cost += Integer.parseInt(ingredient.get(2)) * order.quantity;
        }
      }
      // Find the next index of the order_item
      List<List<String>> order_item_table = viewOrderItems();
      int next_index_order = Integer.parseInt(order_item_table.get(order_item_table.size()-1).get(0)) + 1;
      String add_order_item = "INSERT INTO order_item (order_id, cost) VALUES (" + next_index_order + ", " + total_cost + ");";
      stmt.executeUpdate(add_order_item);

      // Running a query
      String sqlStatement;
      for (OrderList order: orders){
        sqlStatement = "INSERT INTO order_list (inventory_id, order_id, quantity) VALUES (" + order.inventory_id + "," + next_index_order + ", " + order.quantity + ");";
        stmt.executeUpdate(sqlStatement);
        List<String> item = get_inventory_item(order.inventory_id);
        double new_quan = Double.parseDouble(item.get(3)) + order.quantity;
        editInventoryItem(order.inventory_id, item.get(1), Double.parseDouble(item.get(2)), new_quan, item.get(4));
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
  }

  /**
   * Ends the psql connection
   * @return A boolean true if the connection was correctly closed. Undefined if errors are present.
   */
  public boolean closeConnection(){
    try {
      conn.close();
      System.out.println("Connection Closed.");
    } catch (Exception e) {
      System.out.println("Connection NOT Closed.");
    }
    return true;
  }
}

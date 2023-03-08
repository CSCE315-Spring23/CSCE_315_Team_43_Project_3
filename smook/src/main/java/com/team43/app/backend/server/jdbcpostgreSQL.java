package com.team43.app.backend.server;

import com.team43.app.backend.server.*; // important
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class jdbcpostgreSQL {

  // javac *.java
  // Windows: java -cp ".;postgresql-42.2.8.jar" jdbcpostgreSQL

  // Building the connection with your credentials
  Connection conn = null;
  String teamNumber = "team_43";
  String dbName = "csce315331_" + teamNumber;
  String dbConnectionString =
      "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;

  public jdbcpostgreSQL() {
    dbSetup myCredentials = new dbSetup();

    // Connecting to the database
    try {
      conn = DriverManager.getConnection(dbConnectionString, dbSetup.user,
                                         dbSetup.pswd);
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
  }

  // returns list of categories in the database
  public ArrayList<String> getCategories() {
    ArrayList<String> categories = new ArrayList<String>();
    try {
      // create a statement object
      Statement stmt = conn.createStatement();

      // Running a query
      String sqlStatement = "SELECT DISTINCT type FROM menu_item";

      // send statement to DBMS
      ResultSet result = stmt.executeQuery(sqlStatement);

      // OUTPUT
      while (result.next()) {
        categories.add(result.getString("type"));
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
    return categories;
  }

  // returns list of items in given category
  public ArrayList<String> getItemsInCategory(String category) {
    ArrayList<String> items = new ArrayList<String>();
    try {
      // create a statement object
      Statement stmt = conn.createStatement();

      // Running a query
      String sqlStatement =
          "SELECT name FROM menu_item WHERE type=\'" + category + "\'";

      // send statement to DBMS
      ResultSet result = stmt.executeQuery(sqlStatement);

      // OUTPUT
      while (result.next()) {
        items.add(result.getString("name"));
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
    return items;
  }

  // returns all ingredients in the database
  public ArrayList<String> getAllIngredients() {
    ArrayList<String> items = new ArrayList<String>();
    try {
      // create a statement object
      Statement stmt = conn.createStatement();

      // Running a query
      String sqlStatement =
          "SELECT name FROM menu_item WHERE name NOT IN ('Small Cup', 'Medium Cup', 'Large Cup', 'Small Straw', 'Large Straw', 'Assorted Snacks', 'Dummy Item')";

      // send statement to DBMS
      ResultSet result = stmt.executeQuery(sqlStatement);

      // OUTPUT
      while (result.next()) {
        items.add(result.getString("name"));
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
    return items;
  }

  // retrieves desired menu item from the database
  public MenuItem getMenuItem(String menu_item) {
    MenuItem item = null;
    try {
      // create a statement object
      Statement stmt = conn.createStatement();

      // Running a query
      String sqlStatement =
          "SELECT * FROM menu_item WHERE name=\'" + menu_item + "\'";

      // send statement to DBMS
      ResultSet result = stmt.executeQuery(sqlStatement);

      // set up initial menu item
      result.first();
      item = new MenuItem(
          result.getInt("menu_id"), result.getString("name"),
          new ArrayList<InventoryItem>(), result.getFloat("price"),
          result.getInt("ingredient_count"), result.getString("type"));

      // build ingredient list
      sqlStatement =
          "SELECT * FROM ingredient_list WHERE menu_id=" + item.getID();
      result = stmt.executeQuery(sqlStatement);

      ArrayList<InventoryItem> ingredients = item.getIngredients();
      while (result.next()) {
        ingredients.add(new InventoryItem(result.getInt("inventory_id"), "", 0,
                                          result.getFloat("quantity")));
      }

      for (InventoryItem ingredient : ingredients) {
        sqlStatement = "SELECT name, price FROM inventory WHERE inventory_id=" +
                       ingredient.getID();
        result = stmt.executeQuery(sqlStatement);

        result.first();
        ingredient.setName(result.getString("name"));
        ingredient.setPrice(result.getFloat("price"));
      }

    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
    return item;
  }

  // returns number of different items in the inventory
  // public int getNumInventoryItems() {
  //   int num_items = 0;
  //   try {
  //     // create a statement object
  //     Statement stmt = conn.createStatement();

  //     // Running a query
  //     Statement pstat = conn.prepareStatement("SELECT COUNT(inventory_id) FROM inventory;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

  //     // send statement to DBMS
  //     ResultSet result = pstat.executeQuery("SELECT COUNT(inventory_id) FROM inventory;");

  //     // OUTPUT
  //     result.first();
  //     num_items = result.getInt("count");
  //   } catch (Exception e) {
  //     e.printStackTrace();
  //     System.err.println(e.getClass().getName() + ": " + e.getMessage());
  //     System.exit(0);
  //   }
  //   return num_items;
  // }

  public int getNumInventoryItems(){
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
    return table.size();
  }
  // updates transaction and transaction_item tables based on given transaction
  public void writeTransactionData(Transaction trans, int emp_id) {
    try {
      // create a statement object
      Statement stmt = conn.createStatement();

      // Running a query
      String sqlStatement =
          "INSERT INTO transaction (transaction_id, employee_id, purchaser_name, price_of_transaction, time_of_purchase) VALUES (" +
          trans.getID() + ", " + emp_id + ", \'" + trans.getPurchaserName() +
          "\', " + trans.getPrice() + ", DEFAULT)";

      // send statement to DBMS
      stmt.executeQuery(sqlStatement);

      for (Integer menu_id : trans.getMenuItemIDs()) {
        sqlStatement =
            "INSERT INTO transaction_item (menu_id, transaction_id) VALUES (" +
            menu_id + ", " + trans.getID() + ")";
        stmt.executeQuery(sqlStatement);

        // build single insert then execute instead?
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
  }

  // returns id of most recent transaction
  public int getLastTransactionID() {
    int trans_id = 0;
    try {
      // create a statement object
      Statement stmt = conn.createStatement();

      // Running a query
      String sqlStatement = "SELECT MAX(transaction_id) FROM transaction";

      // send statement to DBMS
      ResultSet result = stmt.executeQuery(sqlStatement);

      // OUTPUT
      result.first();
      trans_id = result.getInt("max");
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
    return trans_id;
  }

  // returns current inventory quantities
  private HashMap<Integer, Float> getCurrentInventory() {
    HashMap<Integer, Float> inventory = new HashMap<Integer, Float>();
    try {
      // create a statement object
      Statement stmt = conn.createStatement();

      // Running a query
      String sqlStatement = "SELECT inventory_id, quantity FROM inventory";

      // send statement to DBMS
      ResultSet result = stmt.executeQuery(sqlStatement);

      // OUTPUT
      while (result.next()) {
        inventory.put(result.getInt("inventory_id"),
                      result.getFloat("quantity"));
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
    return inventory;
  }

  // updates inventory based on given usage stats
  public void updateInventory(HashMap<Integer, Float> usage) {
    HashMap<Integer, Float> inventory = getCurrentInventory();
    try {
      // create a statement object
      Statement stmt = conn.createStatement();

      for (Integer id : usage.keySet()) {
        if (usage.get(id) < 0.0001) {
          continue;
        }

        String sqlStatement = "UPDATE inventory SET quantity=" +
                              (inventory.get(id) - usage.get(id)) +
                              " WHERE inventory_id=" + id;
        stmt.executeQuery(sqlStatement);
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
  }

  public boolean close_connection() {
    // closing the connection
    try {
      conn.close();
      System.out.println("Connection Closed.");
    } catch (Exception e) {
      System.out.println("Connection NOT Closed.");
    } // end try catch
    return true;
  }
} // end Class
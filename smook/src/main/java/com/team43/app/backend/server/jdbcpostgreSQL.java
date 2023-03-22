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
      result.next();
      item = new MenuItem(
          result.getInt("menu_id"), result.getString("name"),
          new ArrayList<InventoryItem>(), result.getFloat("price"),
          result.getInt("ingredient_amount"), result.getString("type"));

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

        result.next();
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
  public int getNumInventoryItems() {
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
      stmt.executeUpdate(sqlStatement);

      int trans_item_id = getNextTransactionItemID();
      for (Integer menu_id : trans.getMenuItemIDs()) {
        sqlStatement =
            "INSERT INTO transaction_item (transaction_item_id, menu_id, transaction_id) VALUES (" +
            trans_item_id++ + ", " + menu_id + ", " + trans.getID() + ")";
        stmt.executeUpdate(sqlStatement);
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
      result.next();
      trans_id = result.getInt("max") + 1;
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
    return trans_id;
  }

  // returns id of next transaction item
  private int getNextTransactionItemID() {
    int trans_id = 0;
    try {
      // create a statement object
      Statement stmt = conn.createStatement();

      // Running a query
      String sqlStatement =
          "SELECT MAX(transaction_item_id) FROM transaction_item";

      // send statement to DBMS
      ResultSet result = stmt.executeQuery(sqlStatement);

      // OUTPUT
      result.next();
      trans_id = result.getInt("max") + 1;
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
    return trans_id;
  }

  // returns current inventory quantities
  public HashMap<Integer, Float> getCurrentInventory() {
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
        stmt.executeUpdate(sqlStatement);
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
  }

  // returns current inventory usage tracked for today
  public HashMap<Integer, Float> getCurrentUsage() {
    HashMap<Integer, Float> usage = new HashMap<Integer, Float>();
    try {
      // create a statement object
      Statement stmt = conn.createStatement();

      // Running a query
      String sqlStatement =
          "SELECT inventory_id, usage FROM inventory_usage WHERE date=CURRENT_DATE";

      // send statement to DBMS
      ResultSet result = stmt.executeQuery(sqlStatement);

      // OUTPUT
      while (result.next()) {
        usage.put(result.getInt("inventory_id"), result.getFloat("usage"));
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
    return usage;
  }

  // updates inventory usage for today to match given usage statistics
  public void updateInventoryUsage(HashMap<Integer, Float> usage) {
    try {
      // create a statement object
      Statement stmt = conn.createStatement();

      for (Integer id : usage.keySet()) {
        String sqlStatement =
            "INSERT INTO inventory_usage (inventory_id, usage, date) VALUES (" +
            id + ", " + usage.get(id) +
            ", CURRENT_DATE) ON CONFLICT (date, inventory_id) DO UPDATE SET usage=" +
            usage.get(id) + " WHERE date=CURRENT_DATE AND inventory_id=" + id;
        stmt.executeUpdate(sqlStatement);
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
  }

  // returns inventory usage of all items currently in the inventory since the
  // given date
  public HashMap<Integer, Float> getUsageSinceDate(String date) {
    HashMap<Integer, Float> usage = new HashMap<Integer, Float>();
    try {
      // create a statement object
      Statement stmt = conn.createStatement();

      // Running a query
      String sqlStatement =
          "SELECT inventory_id, SUM (usage) AS total FROM inventory_usage WHERE date BETWEEN CAST(\'" +
          date +
          "\') CURRENT_DATE AND inventory_id IN (SELECT inventory_id FROM inventory WHERE inventory.name!=\'Dummy Item\'') GROUP BY inventory_id";

      // send statement to DBMS
      ResultSet result = stmt.executeQuery(sqlStatement);

      // OUTPUT
      while (result.next()) {
        usage.put(result.getInt("inventory_id"), result.getFloat("total"));
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
    return usage;
  }

  // returns map of id to name for all important inventory items
  public HashMap<Integer, String> getInventoryNames() {
    HashMap<Integer, String> names = new HashMap<Integer, String>();
    try {
      // create a statement object
      Statement stmt = conn.createStatement();

      // Running a query
      String sqlStatement =
          "SELECT inventory_id, name FROM inventory WHERE name!=\'Dummy Item\'";

      // send statement to DBMS
      ResultSet result = stmt.executeQuery(sqlStatement);

      // OUTPUT
      while (result.next()) {
        names.put(result.getInt("inventory_id"), result.getString("name"));
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
    return names;
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
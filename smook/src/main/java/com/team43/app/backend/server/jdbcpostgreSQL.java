package com.team43.app.backend.server;

import com.team43.app.backend.server.*; // important
import java.sql.*;
import java.util.ArrayList;
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
          "SELECT name FROM menu_item WHERE type='" + category + "'";

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

  // TODO
  public MenuItem getMenuItem(String menu_item) {}

  // returns number of different items in the inventory
  public int getNumInventoryItems() {
    int num_items = 0;
    try {
      // create a statement object
      Statement stmt = conn.createStatement();

      // Running a query
      String sqlStatement = "SELECT COUNT(inventory_id) FROM inventory";

      // send statement to DBMS
      ResultSet result = stmt.executeQuery(sqlStatement);

      // OUTPUT
      num_items = result.getInt("count");
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
    return num_items;
  }

  // TODO
  public void writeTransactionData(Transaction trans) {}

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
      trans_id = result.getInt("max");
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
    return trans_id;
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
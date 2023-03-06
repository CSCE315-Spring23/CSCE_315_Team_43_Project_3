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
  String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
  
  public jdbcpostgreSQL(){
    dbSetup myCredentials = new dbSetup();

    // Connecting to the database
    try {
      conn = DriverManager.getConnection(dbConnectionString, dbSetup.user, dbSetup.pswd);
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
  }

  public List<List<String>> view_inventory(){
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
  public List<List<String>> view_menu_items(){
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
  public List<String> edit_inventory_item(int inventory_id, String name, double price, double quantity, String measurement_type){
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
  public List<String> add_menu_item(int menu_id, String name, String type, double price, int ingredient_amount){
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
  public List<String> update_menu_item(int menu_id, String name, String type, double price, int ingredient_amount){
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
  public void order_items(List<OrderList> orders){
    
  }

  public boolean close_connection(){
    // closing the connection
    try {
      conn.close();
      System.out.println("Connection Closed.");
    } catch (Exception e) {
      System.out.println("Connection NOT Closed.");
    } // end try catch
    return true;
  }
}// end Class

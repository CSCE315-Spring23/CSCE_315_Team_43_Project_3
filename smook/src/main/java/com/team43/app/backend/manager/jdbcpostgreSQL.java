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
      String sqlStatement = "SELECT * FROM inventory";

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

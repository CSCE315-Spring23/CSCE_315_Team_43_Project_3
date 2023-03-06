import java.sql.*;
import java.util.*;

public class ManagerBackend {

  public static void main(String args[]) {
    jdbcpostgreSQL data = new jdbcpostgreSQL();
    List<List<String>> inventory = data.view_inventory();
    data.close_connection();

    for (List<String> row : inventory) {
      for (String item : row) {
        System.out.print(item + " ");
      }
      System.out.println();
    }
  }
}// end Class
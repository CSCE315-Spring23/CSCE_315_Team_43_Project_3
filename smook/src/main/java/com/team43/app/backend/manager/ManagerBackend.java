import java.io.Externalizable;
import java.sql.*;
import java.util.*;

public class ManagerBackend {

  public static void main(String args[]) {
    jdbcpostgreSQL data = new jdbcpostgreSQL();
    data.edit_inventory_item(64, "Turbinado", 1, 135, "oz");
    List<List<String>> inventory = data.view_inventory();
    data.add_menu_item(131, "Snacks3", "Other", 1.49, 1);
    data.update_menu_item(131, "Snacks-extra", "Other", 0, 0);
    List<List<String>> menu_items = data.view_menu_items();
    data.close_connection();

    for (List<String> row : inventory) {
      for (String item : row) {
        System.out.print(item + " ");
      }
      System.out.println();
    }
    for (List<String> row : menu_items) {
      for (String item : row) {
        System.out.print(item + " ");
      }
      System.out.println();
    }
  }
}// end Class
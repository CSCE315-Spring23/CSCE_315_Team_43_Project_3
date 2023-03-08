package com.team43.app.backend.manager;

import java.io.Externalizable;
import java.sql.*;
import java.util.*;

public class ManagerBackend {
  public static void main(String args[]) {
    // Create the object
    jdbcpostgreSQL data = new jdbcpostgreSQL();

    OrderList order1 = new OrderList(1, 100, 100);
    OrderList order2 = new OrderList(2, 101, 100);
    List<OrderList> orders = new ArrayList<OrderList>();
    data.order_items(orders);
    data.edit_inventory_item(64, "Turbinado", 1, 135, "oz");
    List<List<String>> inventory = data.view_inventory();
    // data.add_menu_item(135, "Snacks3", "Other", 1.49, 1);
    // data.update_menu_item(131, "Snacks-extra", "Other", 0, 0);
    // List<List<String>> menu_items = data.view_menu_items();

    
    data.close_connection();

    // for (List<String> row : inventory) {
    //   for (String item : row) {
    //     System.out.print(item + " ");
    //   }
    //   System.out.println();
    // }
    // for (List<String> row : menu_items) {
    //   for (String item : row) {
    //     System.out.print(item + " ");
    //   }
    //   System.out.println();
    // }
  }
}// end Class
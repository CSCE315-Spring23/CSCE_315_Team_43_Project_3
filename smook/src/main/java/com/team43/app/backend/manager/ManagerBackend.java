// package com.team43.app.backend.manager;

import java.io.Externalizable;
import java.sql.*;
import java.util.*;

public class ManagerBackend {
  public static void main(String args[]) {
    // Create the object
    jdbcpostgreSQL data = new jdbcpostgreSQL();

    List<List<String>> sales_table = data.getSalesReport("2022-01-03", "2022-01-03");
    
    data.closeConnection();

    for (List<String> row : sales_table){
      for (String elem : row){
        System.out.print(elem + " ");
      }
      System.out.println();
    }
  }
}

package com.team43.app.backend.server;

public class ServerBackend {
  int employee_id;
  Transaction curr_trans;

  public ServerBackend() {}

  public ServerBackend(int emp_id) {
    employee_id = emp_id;
    curr_trans = null;
  }
}
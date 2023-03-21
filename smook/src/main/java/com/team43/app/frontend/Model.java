package com.team43.app.frontend;

import com.team43.app.backend.manager.*;
import com.team43.app.backend.server.ServerBackend;

public class Model {
    public jdbcpostgreSQL db;
    public ServerBackend serverBackend;

    public Model() {
        db = new jdbcpostgreSQL();
        serverBackend = new ServerBackend();
    }
}

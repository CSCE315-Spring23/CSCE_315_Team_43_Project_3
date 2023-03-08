package com.team43.app.frontend;

import com.team43.app.backend.manager.*;

public class Model {
    jdbcpostgreSQL db;

    public Model() {
        db = new jdbcpostgreSQL();
    }
}

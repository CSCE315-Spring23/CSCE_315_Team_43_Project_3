#!/bin/bash

cd smook
mvn package
java -cp ".:postgresql-42.2.8.jar" com.team43.app.backend.manager.jdbcpostgreSQL
java -cp target/smook-1.0-SNAPSHOT.jar com.team43.app.App

#!/bin/bash

cd smook
mvn package
# java -cp ".:postgresql-42.2.8.jar" com.team43.app.backend.manager.jdbcpostgreSQL
mvn clean compile assembly:single
java -jar target/smook-1.0-SNAPSHOT-jar-with-dependencies.jar com.team43.app.App
# java -cp target/smook-1.0-SNAPSHOT.jar com.team43.app.App

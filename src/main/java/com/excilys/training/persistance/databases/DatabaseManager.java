package com.excilys.training.persistance.databases;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public enum DatabaseManager {
	computer_database_db(new DbCredentials(ResourceBundle.getBundle("db"))),

	H2_test_db(new DbCredentials(ResourceBundle.getBundle("dbTest")));

	private DatabaseAccess databaseAccess;

	DatabaseManager(DbCredentials credentials) {
		this.databaseAccess = new DatabaseAccess(credentials);
	}
 
	public DatabaseAccess getDatabaseAccess() {
		return databaseAccess;
	}

	public static Connection getConnectionEnvironment() throws SQLException {
		final String environnement = System.getenv("dbName");
		if (Objects.equals(environnement,"db")) {
			return computer_database_db.getDatabaseAccess().getConnection();
		} else {
			return H2_test_db.getDatabaseAccess().getConnection();
		}
	}

	public static void initDatabaseConnnection() {
		for (DatabaseManager databaseManager : values()) {
			databaseManager.databaseAccess.initPool();
		}
	}

	public static void closeDatabaseConnnection() {
		for (DatabaseManager databaseManager : values()) {
			databaseManager.databaseAccess.closePool();
		}
	}
}
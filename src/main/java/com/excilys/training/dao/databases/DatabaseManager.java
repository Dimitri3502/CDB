package com.excilys.training.dao.databases;

import java.sql.Connection;
import java.sql.SQLException;
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
		if (System.getenv("dbName").equals("dbTest")) {
			return H2_test_db.getDatabaseAccess().getConnection();
		} else {
			return computer_database_db.getDatabaseAccess().getConnection();
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

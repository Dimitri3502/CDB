package com.excilys.training.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DbConn {

	/**
	 * Get database connection
	 *
	 * @return a Connection object
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		Connection conn = null;

		// load the properties file
		ResourceBundle bundle = ResourceBundle.getBundle("db");

		// assign db parameters
		final String url = bundle.getString("url");
		final String user = bundle.getString("user");
		final String password = bundle.getString("password");

		// create a connection to the database
		conn = DriverManager.getConnection(url, user, password);
		return conn;
	}

}

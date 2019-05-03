package com.excilys.training.dao.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DbConn {
	
	// get db.properties
	private static String environ = System.getenv("dbName");
	private static ResourceBundle bundle = ResourceBundle.getBundle(environ);

	// assign db parameters
	private static final String driver = bundle.getString("driver");
	private static final String url = bundle.getString("url");
	private static final String user = bundle.getString("user");
	private static final String password = bundle.getString("password");
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Get database connection
	 *
	 * @return a Connection object
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		// create a connection to the database
		return DriverManager.getConnection(url, user, password);

	}

}

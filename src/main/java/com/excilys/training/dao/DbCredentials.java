package com.excilys.training.dao;

import java.util.ResourceBundle;

public class DbCredentials {
	private String host;
	private String user;
	private String pass;
	private String dbName;
	private String port;
	private String driver;
	private String url;

	public DbCredentials(String host, String user, String pass, String dbName, String port) {
		super();
		this.host = host;
		this.user = user;
		this.pass = pass;
		this.dbName = dbName;
		this.port = port;
	}
	public DbCredentials(ResourceBundle bundle) {
		super();
		this.host = bundle.getString("host");
		this.user = bundle.getString("user");
		this.pass = bundle.getString("password");
		this.dbName = bundle.getString("dbName");
		this.port = bundle.getString("port");
		this.driver = bundle.getString("driver");
		this.url = bundle.getString("url");
	}
	public String toURI() {
		final StringBuilder sb = new StringBuilder();
		return sb.append("jdbc:mysql://")
				.append(host).append(":").append(port).append("/")
				.append(dbName)
				.append("?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC").toString();
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}

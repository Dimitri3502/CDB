package com.excilys.training.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseAccess {
	private DbCredentials credentials;
	private HikariDataSource hikariDataSource;

	public DatabaseAccess(DbCredentials credentials) {
		super();
		this.credentials = credentials;
	}

	private void setupHikariCP() {
		final HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setMaximumPoolSize(10);
		hikariConfig.setJdbcUrl(credentials.toURI());
		hikariConfig.setUsername(credentials.getUser());
		hikariConfig.setPassword(credentials.getPass());
		hikariConfig.setDriverClassName(credentials.getDriver());
		hikariConfig.setMaxLifetime(600_000L);
		hikariConfig.setIdleTimeout(300_000L);
		hikariConfig.setLeakDetectionThreshold(300_000L);
		hikariConfig.setConnectionTimeout(10_000L);
		this.hikariDataSource = new HikariDataSource(hikariConfig);
	}

	public void initPool() {
		setupHikariCP();
	}

	public void closePool() {
		this.hikariDataSource.close();
	}

	public Connection getConnection() throws SQLException {
		if (this.hikariDataSource == null) {
			System.out.println("not connected");
			setupHikariCP();
		}

		return this.hikariDataSource.getConnection();
	}
}

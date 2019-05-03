package com.excilys.training.dao.databases;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseAccess {
	private DbCredentials credentials;
	private HikariDataSource hikariDataSource;
	private final Logger logger = LogManager.getLogger(getClass());

	public DatabaseAccess(DbCredentials credentials) {
		super();
		this.credentials = credentials;
	}

	private void setupHikariCP() {
		final HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setMaximumPoolSize(10);
		hikariConfig.setJdbcUrl(credentials.getUrl());
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
			logger.info("Connection à " + credentials.getUrl());
			setupHikariCP();
		}

		return this.hikariDataSource.getConnection();
	}
}

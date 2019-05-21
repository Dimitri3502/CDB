package com.excilys.training.configuration;

import java.util.Objects;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.excilys.training.persistance.databases.DbCredentials;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = {
"com.excilys.training.utils",
"com.excilys.training.service",
"com.excilys.training.persistance",
"com.excilys.training.mapper",
"com.excilys.training.validator"})
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
	return new DataSourceTransactionManager(dataSource);
    }

	@Bean
	public DbCredentials DbCredentials() {
		final String environnement = System.getenv("dbName");
		if (Objects.equals(environnement, "db")) {
			return new DbCredentials(ResourceBundle.getBundle("db"));
		} else {
			return new DbCredentials(ResourceBundle.getBundle("dbTest"));
		}
	}

	@Bean
	public HikariConfig hikariConfig(DbCredentials credentials) {
		final HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setMaximumPoolSize(20);
		hikariConfig.setJdbcUrl(credentials.getUrl());
		hikariConfig.setUsername(credentials.getUser());
		hikariConfig.setPassword(credentials.getPass());
		hikariConfig.setDriverClassName(credentials.getDriver());
		hikariConfig.setMaxLifetime(600_000L);
		hikariConfig.setIdleTimeout(300_000L);
		hikariConfig.setLeakDetectionThreshold(300_000L);
		hikariConfig.setConnectionTimeout(10_000L);
		return hikariConfig;
	}

	@Bean
	public HikariDataSource hikariDataSource(HikariConfig hikariConfig) {
		return new HikariDataSource(hikariConfig);
	}

	@Bean
	public JdbcTemplate jdbcTemplate(HikariDataSource hikariDataSource) {
		return new JdbcTemplate(hikariDataSource);
	}

}

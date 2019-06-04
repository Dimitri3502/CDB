package com.excilys.training.persistance.h2database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import com.excilys.training.persistance.conf.PersistanceConfig;
import com.excilys.training.persistance.databases.DbCredentials;

@Configuration
@ComponentScan("com.excilys.training.persistance.h2database")
@Import(PersistanceConfig.class)
public class H2Config {
	private String driver = "org.h2.Driver";
	private String user = "admincdb";
	private String password = "qwerty1234";
	private String url = "jdbc:h2:mem:db;DB_CLOSE_DELAY=-1";

	@Bean
	@Primary
	public DbCredentials DbCredentials() {
		return new DbCredentials(user, password, driver, url);

	}

}
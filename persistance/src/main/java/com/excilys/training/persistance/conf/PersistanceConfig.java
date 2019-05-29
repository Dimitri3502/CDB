package com.excilys.training.persistance.conf;

import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.excilys.training.persistance.databases.DbCredentials;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
"com.excilys.training.persistance")
public class PersistanceConfig {

	@Bean
	   public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
	      LocalContainerEntityManagerFactoryBean em 
	        = new LocalContainerEntityManagerFactoryBean();
	      em.setDataSource(dataSource);
	      em.setPackagesToScan(new String[] { "com.excilys.training.core" });
	 
	      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	      em.setJpaVendorAdapter(vendorAdapter);
	      em.setJpaProperties(additionalProperties());
	 
	      return em;
	   }
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
	    JpaTransactionManager transactionManager = new JpaTransactionManager();
	    transactionManager.setEntityManagerFactory(emf);
	 
	    return transactionManager;
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
	    return new PersistenceExceptionTranslationPostProcessor();
	}
	 
	Properties additionalProperties() {
	    Properties properties = new Properties();
	    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	        
	    return properties;
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

}

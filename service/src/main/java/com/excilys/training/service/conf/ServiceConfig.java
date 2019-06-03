package com.excilys.training.service.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.excilys.training.persistance.conf.PersistanceConfig;

@Configuration
@ComponentScan(basePackages = { "com.excilys.training.service" })
@Import(PersistanceConfig.class)
public class ServiceConfig {

}

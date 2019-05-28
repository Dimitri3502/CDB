package com.excilys.training.persistance.h2database;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.excilys.training.persistance.conf.PersistanceConfig;

@Configuration
@ComponentScan("com.excilys.training.persistance.h2database")
@Import(PersistanceConfig.class)
public class H2Config {

}
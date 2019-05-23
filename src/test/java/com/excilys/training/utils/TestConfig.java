package com.excilys.training.utils;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.excilys.training.configuration.AppConfig;

@Configuration
@ComponentScan("com.excilys.training.utils")
@Import(AppConfig.class)
public class TestConfig {
}
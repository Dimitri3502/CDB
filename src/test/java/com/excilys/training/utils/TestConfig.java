package com.excilys.training.utils;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import com.excilys.training.configuration.AppConfig;
import com.excilys.training.service.ComputerService;

@Configuration
@ComponentScan("com.excilys.training.utils")
@Import(AppConfig.class)
public class TestConfig {

}
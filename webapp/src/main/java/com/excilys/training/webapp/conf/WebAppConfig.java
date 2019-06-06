package com.excilys.training.webapp.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.excilys.training.binding.conf.BindingConfig;
import com.excilys.training.service.conf.ServiceConfig;


@Configuration
@ComponentScan(basePackages = { "com.excilys.training.webapp" })
@Import({ServiceConfig.class, BindingConfig.class})
public class WebAppConfig {

}

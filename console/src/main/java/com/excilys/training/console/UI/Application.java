package com.excilys.training.console.UI;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.excilys.training.binding.conf.BindingConfig;
import com.excilys.training.service.conf.ServiceConfig;

@Configuration
@ComponentScan(basePackages = { "com.excilys.training.console" })
@Import({ServiceConfig.class, BindingConfig.class})
public class Application {

	public static void main(final String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
		
		Ui vue = context.getBean("ui", Ui.class);
		
		vue.start();

	}
}

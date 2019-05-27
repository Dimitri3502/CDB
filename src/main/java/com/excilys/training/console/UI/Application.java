package com.excilys.training.console.UI;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.training.webapp.conf.WebMvcConfiguration;

public class Application {

	public static void main(final String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebMvcConfiguration.class);
		
		Ui vue = context.getBean("ui", Ui.class);
		
		vue.start();

	}
}

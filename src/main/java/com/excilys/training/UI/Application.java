package com.excilys.training.UI;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.training.configuration.WebMvcConfiguration;

public class Application {

	public static void main(final String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebMvcConfiguration.class);
		
		Ui vue = context.getBean("ui", Ui.class);
		
		vue.start();

	}
}

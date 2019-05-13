package com.excilys.training.UI;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.training.configuration.AppSpringConfig;

public class Application {

	public static void main(final String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppSpringConfig.class);
		
		Ui vue = context.getBean("ui", Ui.class);
		
		vue.start();

	}
}

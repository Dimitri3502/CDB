package com.excilys.training.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MyWebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) {
		// Create the 'root' Spring application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(AppSpringConfig.class);

		// Manage the lifecycle of the root application context
		container.addListener(new ContextLoaderListener(rootContext));
		
		final DispatcherServlet dispatcherServlet = new DispatcherServlet(new GenericWebApplicationContext());
		ServletRegistration.Dynamic appServlet = container.addServlet("dispatcher", dispatcherServlet);
		appServlet.setLoadOnStartup(1);
		appServlet.addMapping("/");
		}
}
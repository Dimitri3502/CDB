package com.excilys.training.binding.conf;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.excilys.training.binding.mapper.FindCompanyById;

@Configuration
@ComponentScan(basePackages = { "com.excilys.training.binding" })
public class BindingTestConfig {
	@Bean
	public FindCompanyById companyService() {
		return Mockito.mock(FindCompanyById.class);
	}
}
package com.excilys.training.binding.conf;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.excilys.training.binding.mapper.FindCompanyById;

@Configuration
@Import(BindingConfig.class)
public class BindingTestConfig {
	@Bean
	public FindCompanyById companyService() {
		return Mockito.mock(FindCompanyById.class);
	}
}
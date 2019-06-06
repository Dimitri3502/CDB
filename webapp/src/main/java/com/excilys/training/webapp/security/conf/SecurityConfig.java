package com.excilys.training.webapp.security.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@ComponentScan(basePackages = { "com.excilys.training.webapp.security" })
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http.authorizeRequests().antMatchers("/").permitAll();
//		http.authorizeRequests()
//			.antMatchers(HttpMethod.POST, "/**").hasAnyRole("USER")
//			.antMatchers("/addComputer/**").hasAnyRole("USER")
//			.antMatchers("/secure/**").hasAnyRole("ADMIN")
//			.antMatchers("/**").authenticated()
//		.and().formLogin().permitAll()
//		.and().logout().permitAll();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(encodePWD());
	}

	@Bean
	public RoleHierarchy roleHierarchy() {
	  RoleHierarchyImpl r = new RoleHierarchyImpl();
	  r.setHierarchy("ROLE_ADMIN > ROLE_USER");
	  r.setHierarchy("ROLE_USER > ROLE_READ");
	  return r;
	}
	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
}

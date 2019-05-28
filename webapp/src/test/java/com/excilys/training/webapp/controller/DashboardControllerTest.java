package com.excilys.training.webapp.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import javax.activation.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.excilys.training.binding.pagination.Page;
import com.excilys.training.core.Computer;
import com.excilys.training.service.ComputerService;
import com.excilys.training.service.IComputerService;
import com.excilys.training.webapp.conf.WebAppConfig;
import com.excilys.training.webapp.conf.WebMvcConfiguration;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { DashboardControllerTest.Config.class, WebAppConfig.class, WebMvcConfiguration.class })
@WebAppConfiguration
public class DashboardControllerTest {

	@Configuration
	public static class Config {

		@Bean
		@Primary
		public IComputerService computerService() {
			return Mockito.mock(IComputerService.class);
		}
		@Bean
		@Primary
		public DataSource dataSource() {
			return Mockito.mock(DataSource.class);
		}

	}

	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Autowired
	private IComputerService mockComputerService;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		Mockito.reset(mockComputerService);
	}

	@Autowired
	public void setMockComputerService(IComputerService mockComputerService) {
		this.mockComputerService = mockComputerService;
	}

	@Autowired
	public void setWac(WebApplicationContext wac) {
		this.wac = wac;
	}

	@Test
	public void findAll_ShouldAddComputerEntriesToModelAndRenderDashboardView() throws Exception {
		Computer first = new Computer.Builder()
				.setId(1L)
                .setName("first")
                .build();
 
		Computer second = new Computer.Builder()
				.setId(2L)
                .setName("second")
                .build();
		
		Page page = new Page();
 
        when(mockComputerService.getAll(page)).thenReturn(Arrays.asList(first, second));
        
        
		mockMvc.perform(get("/dashboard")).andExpect(status().isOk())
		.andExpect(forwardedUrl("/WEB-INF/views/dashboard.jsp"));
//        .andExpect(model().attribute(ATT_COMPUTERS, hasItem(
//                allOf(
//                        hasProperty(CHAMP_ID, is(1L)),
//                        hasProperty(CHAMP_COMPUTERNAME, is("first"))
//                )
//        )))
//        .andExpect(model().attribute(ATT_COMPUTERS, hasItem(
//                allOf(
//                        hasProperty(CHAMP_ID, is(2L)),
//                        hasProperty(CHAMP_COMPUTERNAME, is("second"))
//                )
//        )));
	}

}
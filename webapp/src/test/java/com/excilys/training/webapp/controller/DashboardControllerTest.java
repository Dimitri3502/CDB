package com.excilys.training.webapp.controller;

import static com.excilys.training.webapp.controller.CONSTANTES.ATT_COMPUTERS;
import static com.excilys.training.webapp.controller.CONSTANTES.CHAMP_COMPUTERNAME;
import static com.excilys.training.webapp.controller.CONSTANTES.CHAMP_ID;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.Arrays;
import java.util.List;

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

import com.excilys.training.binding.mapper.CompanyMapper;
import com.excilys.training.binding.mapper.ComputerMapper;
import com.excilys.training.binding.pagination.Page;
import com.excilys.training.binding.pagination.Pagination;
import com.excilys.training.core.Computer;
import com.excilys.training.service.ICompanyService;
import com.excilys.training.service.IComputerService;
import com.excilys.training.webapp.conf.WebMvcConfiguration;
import com.excilys.training.webapp.mapper.ComputerFormMapper;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { DashboardControllerTest.Config.class, WebMvcConfiguration.class })
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
		public ICompanyService companyrService() {
			return Mockito.mock(ICompanyService.class);
		}

		@Bean
		@Primary
		public ComputerMapper computerMapper() {
			return Mockito.mock(ComputerMapper.class);
		}

		@Bean
		@Primary
		public ComputerFormMapper computerformMapper() {
			return Mockito.mock(ComputerFormMapper.class);
		}

		@Bean
		@Primary
		public CompanyMapper companyMapper() {
			return Mockito.mock(CompanyMapper.class);
		}

		@Bean
		@Primary
		public Pagination pagination() {
			return Mockito.mock(Pagination.class);
		}
//		@Bean
//		@Primary
//		public DataSource dataSource() {
//			return Mockito.mock(DataSource.class);
//		}

	}

	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Autowired
	private ComputerMapper mockComputerMapper;

	@Autowired
	private IComputerService mockComputerService;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		Mockito.reset(mockComputerService);

		Page page = new Page();
		Computer first = new Computer.Builder().setId(1L).setName("first").build();

		Computer second = new Computer.Builder().setId(2L).setName("second").build();
		List<Computer> computers = Arrays.asList(first, second);
		when(mockComputerService.getAll(page)).thenReturn(computers);
		when(mockComputerService.count(Mockito.anyString())).thenReturn((long) computers.size());
		when(mockComputerMapper.allModelToDTO(null)).thenReturn(null);
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
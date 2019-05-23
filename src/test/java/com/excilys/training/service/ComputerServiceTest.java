package com.excilys.training.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.excilys.training.configuration.AppConfig;
import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.mapper.ComputerMapper;
import com.excilys.training.model.Company;
import com.excilys.training.model.Computer;
import com.excilys.training.utils.TestDatabase;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class ComputerServiceTest {
	
	@Autowired
	private ComputerService computerService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private  ComputerMapper computerMapper;
	
	@Autowired
	private TestDatabase testDatabase;	
	
	@Before
	public void setUp() throws Exception {
		testDatabase.reload();
	}

	@Test
	public void testGetAllIntInt() {

		int limit = 2, offset = 10;
		List<Computer> theComputerList = computerService.getAll(limit, offset);
		assertTrue("getAll renvoie null", theComputerList.size() <= 2);
	}

	@Test
	public void testFindById() {
		Computer actual = computerService.findById(3L);
		Company company = new Company(2L, "Thinking Machines");
		Computer expected = new Computer(3L, "CM-200", null, null, company);
		assertEquals(expected, actual);

	}

	@Test
	public void testCreate() {

		CompanyDTO companyDTO = new CompanyDTO("2", "Thinking Machines");
		ComputerDTO expected = new ComputerDTO("3", "test", "2001-02-03", "2001-02-04", companyDTO);
		long newId = computerService.create(computerMapper.dtoToModel(expected));
		expected.setId(Long.toString(newId));
		ComputerDTO actual = computerMapper.modelToDto(computerService.findById(newId));
		assertEquals(expected, actual);

	}

}

package com.excilys.training.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.mapper.ComputerMapper;
import com.excilys.training.model.Company;
import com.excilys.training.model.Computer;
import com.excilys.training.utils.TestConfig;
import com.excilys.training.utils.TestDatabase;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class ComputerServiceTest {
	
	@Autowired
	private ComputerService computerService;
	
	@Autowired
	private CompanyService companyService;
	
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

		Company company = new Company(2L, "Thinking Machines");
		Computer expected = new Computer(3L, "test", Parse("2001-02-03"), Parse("2001-02-04"), company);
		long newId = computerService.create(expected);
		expected.setId(newId);
		Computer actual = computerService.findById(newId);
		assertEquals(expected, actual);

	}
	
	public LocalDateTime Parse( String s) {
		return LocalDateTime.of(LocalDate.parse(s), LocalTime.of(12, 00));
	}

}

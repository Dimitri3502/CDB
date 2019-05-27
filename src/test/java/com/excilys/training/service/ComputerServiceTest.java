package com.excilys.training.service;

import static com.excilys.training.binding.validator.ValidatorUtils.Parse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.excilys.training.core.Company;
import com.excilys.training.core.Computer;
import com.excilys.training.persistance.exception.ComputerNotFoundException;
import com.excilys.training.persistance.h2database.H2Config;
import com.excilys.training.persistance.h2database.TestDatabase;
import com.excilys.training.service.conf.ServiceConfig;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {H2Config.class, ServiceConfig.class})
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
	public void testFindById() throws ComputerNotFoundException {
		Computer actual = computerService.findById(3L);
		Company company = new Company(2L, "Thinking Machines");
		Computer expected = new Computer(3L, "CM-200", null, null, company);
		assertEquals(expected, actual);

	}

	@Test
	public void testCreate() throws ComputerNotFoundException {

		Company company = new Company(2L, "Thinking Machines");
		Computer expected = new Computer(3L, "test", Parse("2001-02-03"), Parse("2001-02-04"), company);
		long newId = computerService.create(expected);
		expected.setId(newId);
		Computer actual = computerService.findById(newId);
		assertEquals(expected, actual);

	}

}

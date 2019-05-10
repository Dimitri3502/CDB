package com.excilys.training.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.excilys.training.TestDatabase;
import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.mapper.ComputerMapper;
import com.excilys.training.model.Company;
import com.excilys.training.model.Computer;

public class ComputerServiceTest {

	protected static ComputerService computerService;
	protected static CompanyService companyService;
	private final ComputerMapper computerMapper = ComputerMapper.getInstance();
	
	@Before
	public void setUpBeforeClass() throws Exception {
		TestDatabase.getInstance().reload();
		computerService = ComputerService.getInstance();
		companyService = CompanyService.getInstance();
	}

	@Test
	public void testGetAllIntInt() {

		int limit = 2, offset = 10;
		List<Computer> theComputerList = computerService.getAll(limit, offset);
		assertTrue("getAll renvoie null", theComputerList.size() <= 2);
	}

	@Test
	public void testFindById() {
		Optional<Computer> actual = computerService.findById(3L);
		Company company = new Company(2L, "Thinking Machines");
		Computer expected = new Computer(3L, "CM-200", null, null, company);
		assertEquals(expected, actual.get());

	}

	@Test
	public void testCreate() {

		CompanyDTO companyDTO = new CompanyDTO("2", "Thinking Machines");
		ComputerDTO expected = new ComputerDTO("3", "test", "2001-02-03", "2001-02-04", companyDTO);
		long newId = computerService.create(computerMapper.dtoToModel(expected));
		expected.setId(Long.toString(newId));
		ComputerDTO actual = computerMapper.modelToDto(computerService.findById(newId).get());
		assertEquals(expected, actual);

	}

}

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

public class ComputerServiceTest {

	protected static ComputerService computerService;
	protected static CompanyService companyService;

	@Before
	public void setUpBeforeClass() throws Exception {
		TestDatabase.getInstance().reload();
		computerService = ComputerService.getInstance();
		companyService = CompanyService.getInstance();
	}

	@Test
	public void testGetAllIntInt() {

		int limit = 2, offset = 10;
		List<ComputerDTO> theComputerDtoList = computerService.getAll(limit, offset);
		assertTrue("getAll renvoie null", theComputerDtoList.size() <= 2);
	}

	@Test
	public void testFindById() {
		Optional<ComputerDTO> actual = computerService.findById(3L);
		CompanyDTO companyDTO = new CompanyDTO("2", "Thinking Machines");
		ComputerDTO expected = new ComputerDTO("3", "CM-200", null, null, companyDTO);
		assertEquals(expected, actual.get());

	}

	@Test
	public void testCreate() {

		CompanyDTO companyDTO = new CompanyDTO("2", "Thinking Machines");
		ComputerDTO expected = new ComputerDTO("3", "test", "2001-02-03", "2001-02-04", companyDTO);
		long newId = computerService.create(expected);
		expected.setId(Long.toString(newId));
		Optional<ComputerDTO> actual = computerService.findById(newId);
		assertEquals(expected, actual.get());

	}

}

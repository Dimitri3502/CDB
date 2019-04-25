package com.excilys.training.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.excilys.training.dao.CompanyDAO;
import com.excilys.training.dao.ComputerDAO;
import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.exception.InvalidDateValueException;
import com.excilys.training.exception.InvalidDiscontinuedDate;
import com.excilys.training.exception.NotFoundException;
import com.excilys.training.mapper.CompanyMapper;
import com.excilys.training.mapper.ComputerMapper;

class ComputerServiceTest {

	protected static ComputerService computerService;
	protected static CompanyService companyService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		computerService = ComputerService.getInstance();
		companyService = CompanyService.getInstance();
	}

	@Test
	void testGetAllIntInt() {
		
		int limit = 2, offset = 10;
		List<ComputerDTO>  theComputerDtoList = computerService.getAll(limit, offset);
		assertTrue(theComputerDtoList.size()<=2, "getAll renvoie null");
	}

	@Test
	void testFindById() {
		Optional<ComputerDTO> actual = computerService.findById(3L);
		CompanyDTO companyDTO = new CompanyDTO("2",null);
		ComputerDTO expected = new ComputerDTO("3","CM-200",null,null,companyDTO);
		assertEquals(expected, actual.get());
		
	}

	@Test
	void testCreate() {
		
		CompanyDTO companyDTO = new CompanyDTO("2", null);
		ComputerDTO expected = new ComputerDTO("3","test", "2001-02-03","2001-02-04",companyDTO);
		long newId = computerService.create(expected);
		expected.setId(Long.toString(newId));
		Optional<ComputerDTO> actual = computerService.findById(newId);
		assertEquals(expected, actual.get());
		
	}
	

}

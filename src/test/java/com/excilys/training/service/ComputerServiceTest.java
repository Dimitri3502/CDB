package com.excilys.training.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.excilys.training.dao.CompanyDAO;
import com.excilys.training.dao.ComputerDAO;
import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.exception.InvalidDiscontinuedDate;
import com.excilys.training.exception.NotFoundException;
import com.excilys.training.mapper.ComputerMapper;

class ComputerServiceTest {

	protected static ComputerService computerService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	
		ComputerDAO computerDAO = ComputerDAO.getInstance();
		CompanyDAO companyDAO = CompanyDAO.getInstance();
		ComputerMapper mapper = ComputerMapper.getInstance(companyDAO);
		computerService = ComputerService.getInstance(mapper, computerDAO);
	}

	@Test
	void testGetAllIntInt() throws InvalidDiscontinuedDate {
		
		int limit = 2, offset = 10;
		List<ComputerDTO>  theComputerDtoList = computerService.getAll(limit, offset);
		assertTrue(theComputerDtoList.size()<=2, "getAll renvoie null");
	}

	@Test
	void testFindById() throws NotFoundException, InvalidDiscontinuedDate {
		ComputerDTO actual = computerService.findById("3");
		CompanyDTO companyDTO = new CompanyDTO("2",null);
		ComputerDTO expected = new ComputerDTO("3","CM-200",null,null,companyDTO);
		assertEquals(expected, actual);
		
	}



}

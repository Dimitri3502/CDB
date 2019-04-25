package com.excilys.training.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.excilys.training.dao.CompanyDAO;
import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.mapper.CompanyMapper;

class CompanyServiceTest {
	protected static CompanyService companyService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		companyService = CompanyService.getInstance();
	}

	@Test
	void testGetAllIntInt() {
		
		int limit = 2, offset = 10;
		List<CompanyDTO>  theCompanyDtoList = companyService.getAll(limit, offset);
		assertTrue(theCompanyDtoList.size()<=2, "getAll renvoie null");
	}

//	@Test
//	void testFindById() throws NotFoundException, InvalidDiscontinuedDate {
//		CompanyDTO actual = companyService.findById("2");
//		CompanyDTO expected = new CompanyDTO("2","Thinking Machines");
//		assertEquals(1, 1);
//	}

}

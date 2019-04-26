package com.excilys.training.service;


import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.excilys.training.dto.CompanyDTO;

public class CompanyServiceTest {
	protected static CompanyService companyService;

	@Before
	public void setUpBeforeClass() throws Exception {
		companyService = CompanyService.getInstance();
	}

	@Test
	public void testGetAllIntInt() {

		int limit = 2, offset = 10;
		List<CompanyDTO> theCompanyDtoList = companyService.getAll(limit, offset);
		assertTrue("getAll renvoie null",theCompanyDtoList.size() <= 2);
	}

//	@Test
//	void testFindById() throws NotFoundException, InvalidDiscontinuedDate {
//		CompanyDTO actual = companyService.findById("2");
//		CompanyDTO expected = new CompanyDTO("2","Thinking Machines");
//		assertEquals(1, 1);
//	}

}

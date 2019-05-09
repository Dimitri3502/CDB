package com.excilys.training.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.training.TestDatabase;
import com.excilys.training.dto.CompanyDTO;

public class CompanyServiceTest {
	protected static CompanyService companyService= CompanyService.getInstance();;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestDatabase.getInstance().reload();
		
	}

	@Test
	public void testGetAllIntInt() {

		int limit = 2, offset = 10;
		List<CompanyDTO> theCompanyDtoList = companyService.getAll(limit, offset);
		assertTrue("getAll renvoie null",theCompanyDtoList.size() <= 2);
	}

	@Test
	public void testFindById() {
		Optional<CompanyDTO> actual = companyService.findById(2L);
		CompanyDTO expected = new CompanyDTO("2","Thinking Machines");
		assertEquals(actual.get(), expected);
	}

}

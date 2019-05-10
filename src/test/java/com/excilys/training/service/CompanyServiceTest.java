package com.excilys.training.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.training.TestDatabase;
import com.excilys.training.model.Company;

public class CompanyServiceTest {
	protected static CompanyService companyService= CompanyService.getInstance();;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestDatabase.getInstance().reload();
		
	}

	@Test
	public void testGetAllIntInt() {

		int limit = 2, offset = 10;
		List<Company> theCompanyList = companyService.getAll(limit, offset);
		assertTrue("getAll renvoie null",theCompanyList.size() <= 2);
	}

	@Test
	public void testFindById() {
		Optional<Company> actual = companyService.findById(2L);
		Company expected = new Company(2L,"Thinking Machines");
		assertEquals(actual.get(), expected);
	}

}

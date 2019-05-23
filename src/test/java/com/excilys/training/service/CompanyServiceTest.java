package com.excilys.training.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.excilys.training.model.Company;
import com.excilys.training.utils.TestConfig;
import com.excilys.training.utils.TestDatabase;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class CompanyServiceTest {
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
		List<Company> theCompanyList = companyService.getAll(limit, offset);
		assertTrue("getAll renvoie null",theCompanyList.size() <= 2);
	}

	@Test
	public void testFindById() {
		Company actual = companyService.findById(2L);
		Company expected = new Company(2L,"Thinking Machines");
		assertEquals(actual, expected);
	}
	@Test
	public final void testDelete() {
		Long id=2L;
		companyService.findById(id);
		companyService.delete(id);
		
		// insert into computer (id,name,introduced,discontinued,company_id) 
		// values (  2,'CM-2a',null,null,2);
		assertTrue(companyService.findById(id) == null);
	}
}

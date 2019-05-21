package com.excilys.training.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.excilys.training.configuration.WebMvcConfiguration;
import com.excilys.training.configuration.AppConfig;
import com.excilys.training.persistance.CompanyDAO;
import com.excilys.training.persistance.ComputerDAO;
import com.excilys.training.utils.TestDatabase;
import com.excilys.training.utils.UTDatabase;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class CompanyDAOTest {

	@Autowired
	private CompanyDAO companyDAO;

	@Autowired
	private ComputerDAO computerDAO;
	
    @Autowired
    private UTDatabase database;
	
	@Autowired
	private TestDatabase testDatabase;

	@Before
	public void setUp() throws Exception {
		testDatabase.reload();

	}

	@Test
	public final void testFindByIdLong() {
		Long id = 1L;
		assertEquals(database.findCompanyById(id),companyDAO.findById(id));
	}

	@Test
	public final void testGetAllIntInt() {
		int offset=0 , limit=5;
		assertEquals(database.findAllCompanies(limit, offset), companyDAO.getAll(limit, offset));
	}
	
	@Test
	public final void testDelete() {
		Long id=2L;
		companyDAO.findById(id);
		companyDAO.delete(id);
		
		// insert into computer (id,name,introduced,discontinued,company_id) 
		// values (  2,'CM-2a',null,null,2);
		assertTrue(companyDAO.findById(id) == null);
		assertTrue(computerDAO.findById(id) == null);
	}

}

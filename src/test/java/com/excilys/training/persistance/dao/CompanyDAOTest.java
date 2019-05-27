package com.excilys.training.persistance.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.excilys.training.persistance.CompanyDAO;
import com.excilys.training.persistance.ComputerDAO;
import com.excilys.training.persistance.conf.PersistanceConfig;
import com.excilys.training.persistance.exception.CompanyNotFoundException;
import com.excilys.training.persistance.h2database.H2Config;
import com.excilys.training.persistance.h2database.TestDatabase;
import com.excilys.training.persistance.h2database.UTDatabase;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { H2Config.class, PersistanceConfig.class })
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
	public final void testFindByIdLong() throws CompanyNotFoundException {
		Long id = 1L;
		assertEquals(database.findCompanyById(id),companyDAO.findById(id));
	}

	@Test
	public final void testGetAllIntInt() {
		int offset=0 , limit=5;
		assertEquals(database.findAllCompanies(limit, offset), companyDAO.getAll(limit, offset));
	}
	

}

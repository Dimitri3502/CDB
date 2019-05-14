package com.excilys.training.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.excilys.training.TestDatabase;
import com.excilys.training.configuration.AppSpringConfig;
import com.excilys.training.model.Company;
import com.excilys.training.persistance.CompanyDAO;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppSpringConfig.class)
public class CompanyDAOTest {

	@Autowired
	private CompanyDAO companyDAO;

	@Autowired
	private TestDatabase testDatabase;

	@Before
	public void setUp() throws Exception {
		testDatabase.reload();

	}

	@Test
	public final void testFindByIdLong() {
		Long id = 1L;
		final Company expected = new Company(id, "Apple Inc.");
		final Company actual = companyDAO.findById(id);
		assertEquals(expected, actual);
	}

	@Test
	public final void testGetAllIntInt() {
		final Company expected1 = new Company(1L, "Apple Inc.");
		final Company expected2 = new Company(1L, "Thinking Machines");
		List<Company> expected = new ArrayList<Company>();
		expected.add(expected1);
		expected.add(expected2);
		List<Company> actual = companyDAO.getAll(2, 0);
		assertEquals(expected, actual);
	}
//	@Test
//	public final void testDelete() {
//		Long id=2L;
//		companyDAO.findById(id);
//		companyDAO.delete(id);
//		assertFalse(companyDAO.findById(id).isPresent());
//	}

}

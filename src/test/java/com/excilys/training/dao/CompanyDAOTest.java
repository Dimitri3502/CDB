package com.excilys.training.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Component;

import com.excilys.training.TestDatabase;
import com.excilys.training.model.Company;
import com.excilys.training.persistance.CompanyDAO;

@Component
public class CompanyDAOTest {

	private CompanyDAO companyDAO;
	
	

	@Before
	public void setUp() throws Exception {
		TestDatabase.getInstance().reload();
		
	}

	@Test
	public final void testFindByIdLong() {
		Long id = 1L;
		final Company expected = new Company(id,"Apple Inc.");
		final Company actual = companyDAO.findById(id).get();
		assertEquals(expected, actual);
	}

	@Test
	public final void testGetAllIntInt() {
		final Company expected1 = new Company(1L,"Apple Inc.");
		final Company expected2 = new Company(1L,"Thinking Machines");
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

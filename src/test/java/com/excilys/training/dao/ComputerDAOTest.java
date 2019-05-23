package com.excilys.training.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import com.excilys.training.configuration.WebMvcConfiguration;
import com.excilys.training.configuration.AppConfig;
import com.excilys.training.model.Computer;
import com.excilys.training.persistance.ComputerDAO;
import com.excilys.training.utils.TestDatabase;
import com.excilys.training.utils.UTDatabase;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class ComputerDAOTest {

	@ClassRule
	public static final SpringClassRule springClassRule = new SpringClassRule();
	@Rule
	public final SpringMethodRule springMethodRule = new SpringMethodRule();

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
	@Parameters(method = "provideId")
	public final void testFindByIdLong(Long id) {
		assertEquals(database.findComputerById(id), computerDAO.findById(id));
	}

	public Object provideId() {
		return new Long[] { 1L,2L,3L };
	}
	
	public Object[] providePageLimit() {
		return new Object[][] { { 1, 10 }, { 9, 10 }, { 3, 5 }, { 2, 10 } };
	}

	@Test
	@Parameters(method = "providePageLimit")
	public final void testGetAllIntInt(int offset, int limit) {
		final List<Computer> expected = database.findAllComputers(limit, offset);
		final List<Computer> actual = computerDAO.getAll(limit, offset);
		assertEquals(expected, actual);
	}

	@Test
	public final void testDelete() {
		Long id = 2L;
		computerDAO.findById(id);
		computerDAO.delete(id);

		// insert into computer (id,name,introduced,discontinued,computer_id)
		// values ( 2,'CM-2a',null,null,2);
		assertTrue(computerDAO.findById(id) == null);
		assertTrue(computerDAO.findById(id) == null);
	}

}

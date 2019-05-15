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

import com.excilys.training.TestDatabase;
import com.excilys.training.UTDatabase;
import com.excilys.training.configuration.AppSpringConfig;
import com.excilys.training.model.Computer;
import com.excilys.training.persistance.ComputerDAO;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(classes = AppSpringConfig.class)
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
	public final void testFindByIdLong() {
		Long id = 1L;
		assertEquals(database.findComputerById(id), computerDAO.findById(id));
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

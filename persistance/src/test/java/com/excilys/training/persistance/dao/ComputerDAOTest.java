package com.excilys.training.persistance.dao;

import static org.junit.Assert.assertEquals;

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

import com.excilys.training.binding.exception.ComputerNotFoundException;
import com.excilys.training.core.Computer;
import com.excilys.training.persistance.ComputerDAOimpl;
import com.excilys.training.persistance.conf.PersistanceConfig;
import com.excilys.training.persistance.h2database.H2Config;
import com.excilys.training.persistance.h2database.TestDatabase;
import com.excilys.training.persistance.h2database.UTDatabase;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(classes = { H2Config.class, PersistanceConfig.class })
public class ComputerDAOTest {

	@ClassRule
	public static final SpringClassRule springClassRule = new SpringClassRule();
	@Rule
	public final SpringMethodRule springMethodRule = new SpringMethodRule();

	@Autowired
	private ComputerDAOimpl computerDAO;

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
	public final void testFindByIdLong(Long id) throws ComputerNotFoundException {
		assertEquals(database.findComputerById(id), computerDAO.findById(id));
	}

	public Object provideId() {
		return new Long[] { 1L, 2L, 3L };
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

	@Test(expected = ComputerNotFoundException.class)
	public final void testDelete() throws ComputerNotFoundException {
		Long id = 2L;
		computerDAO.findById(id);
		computerDAO.delete(id);
		computerDAO.findById(id);

	}

}

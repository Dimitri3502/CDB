package com.excilys.training.binding.validator;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.excilys.training.binding.conf.BindingConfig;
import com.excilys.training.binding.dto.CompanyDTO;
import com.excilys.training.binding.dto.ComputerDTO;
import com.excilys.training.console.conf.ConsoleConfig;
import com.excilys.training.console.validator.ComputerDTOValidator;
import com.excilys.training.persistance.h2database.H2Config;
import com.excilys.training.persistance.h2database.TestDatabase;
import com.excilys.training.service.conf.ServiceConfig;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { H2Config.class, BindingConfig.class, ServiceConfig.class, ConsoleConfig.class })
public class ComputerDTOValidatorTest {

	@Autowired
	private ComputerDTOValidator ComputerDTOValidator;

	@Autowired
	private TestDatabase testDatabase;

	@Before
	public void setUp() throws Exception {
		testDatabase.reload();

	}

	@Test
	public void testCheckDateFail() {
		ComputerDTO toValidate = new ComputerDTO();
		toValidate.setName("name");
		toValidate.setCompanyDTO(new CompanyDTO());
		toValidate.setIntroducedDate("01-01-1969");
		assertEquals(false, ComputerDTOValidator.check(toValidate).isValid());
	}

	@Test
	public void testCheckIdFail() {
		ComputerDTO toValidate = new ComputerDTO();
		toValidate.setName("name");
		toValidate.setCompanyDTO(new CompanyDTO("500", null));
		assertEquals(false, ComputerDTOValidator.check(toValidate).isValid());
	}
}

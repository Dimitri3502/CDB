package com.excilys.training.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.excilys.training.TestDatabase;
import com.excilys.training.configuration.AppSpringConfig;
import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.dto.ComputerDTO;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppSpringConfig.class)
public class WebValidatorTest {
	
	@Autowired
	private WebValidator webValidator;
	
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
		assertEquals(false, webValidator.check(toValidate).isValid());
	}
	@Test
	public void testCheckIdFail() {
		ComputerDTO toValidate = new ComputerDTO();
		toValidate.setName("name");
		toValidate.setCompanyDTO(new CompanyDTO("500",null));
		assertEquals(false, webValidator.check(toValidate).isValid());
	}
	}

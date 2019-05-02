package com.excilys.training.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.excilys.training.TestDatabase;
import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.dto.ComputerDTO;

public class WebValidatorTest {
	private final WebValidator webValidator = WebValidator.getInstance();

	@Before
	public void setUp() throws Exception {
		TestDatabase.getInstance().reload();
		
	}

	@Test
	public void testValidationComputerDTO() {
		fail("Not yet implemented");
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

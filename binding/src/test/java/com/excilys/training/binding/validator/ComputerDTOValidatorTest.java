package com.excilys.training.binding.validator;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.excilys.training.binding.conf.BindingTestConfig;
import com.excilys.training.binding.dto.CompanyDTO;
import com.excilys.training.binding.dto.ComputerDTO;
import com.excilys.training.binding.exception.CompanyNotFoundException;
import com.excilys.training.binding.mapper.FindCompanyById;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { BindingTestConfig.class})
public class ComputerDTOValidatorTest {

	@Autowired
	private ComputerDTOValidator ComputerDTOValidator;
	
	@Autowired
	private FindCompanyById companyServiceMock;

	@Before
	public void setUp() throws Exception {
//		when(companyServiceMock.findById(500L)).do(new Company());
		Mockito.doThrow(CompanyNotFoundException.class).when(companyServiceMock).findById(500L);
	}
	@Test
	public void testCheckDateFail() {
		ComputerDTO toValidate = new ComputerDTO();
		toValidate.setName("name");
		toValidate.setCompanyDTO(new CompanyDTO());
		toValidate.setIntroduced("01-01-1989");
//		toValidate.setDiscontinuedDate("01-01-1979");
		assertEquals(true, ComputerDTOValidator.check(toValidate).isValid());
	}

	@Test
	public void testCheckIdFail() {
		ComputerDTO toValidate = new ComputerDTO();
		toValidate.setName("name");
		toValidate.setCompanyDTO(new CompanyDTO(500L, null));
		assertEquals(false, ComputerDTOValidator.check(toValidate).isValid());
	}
}

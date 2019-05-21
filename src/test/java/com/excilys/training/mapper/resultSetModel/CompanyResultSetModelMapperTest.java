package com.excilys.training.mapper.resultSetModel;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.excilys.training.configuration.AppConfig;
import com.excilys.training.model.Company;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class CompanyResultSetModelMapperTest {
	
	@Autowired
	private CompanyResultSetModelMapper companyResultSetModelMapper;
	
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static ResultSet mockResultSet;
    
	@Before
	public void setUp() throws Exception {
		mockResultSet = Mockito.mock(ResultSet.class);
	}

	@Test
	public void testMapResultSet() throws SQLException {
		Long id =2L;
		String name = "test";
		Mockito.when(mockResultSet.getLong(COLUMN_ID)).thenReturn(id);
		Mockito.when(mockResultSet.getString(COLUMN_NAME)).thenReturn(name);

		Company company = companyResultSetModelMapper.mapRow(mockResultSet,1);
		Company expectedCompany =  new Company(id,name);

		assertEquals(expectedCompany, company);

	}

}

package com.excilys.training.mapper.resultSetModel;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.training.TestDatabase;
import com.excilys.training.dao.CompanyDAO;
import com.excilys.training.model.Company;

import org.mockito.Mockito;

public class CompanyResultSetModelMapperTest {

	private CompanyResultSetModelMapper companyResultSetModelMapper = CompanyResultSetModelMapper.getInstance();
	
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static ResultSet mockResultSet;
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		mockResultSet = Mockito.mock(ResultSet.class);
	}

	@Test
	public void testMapResultSet() throws SQLException {
		Long id =2L;
		String name = "test";
		Mockito.when(mockResultSet.getLong(COLUMN_ID)).thenReturn(id);
		Mockito.when(mockResultSet.getString(COLUMN_NAME)).thenReturn(name);

		Company company = companyResultSetModelMapper.map(mockResultSet);
		Company expectedCompany =  new Company(id,name);

		assertEquals(expectedCompany, company);

	}

}

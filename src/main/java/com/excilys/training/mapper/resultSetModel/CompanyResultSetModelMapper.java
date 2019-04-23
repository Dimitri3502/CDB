package com.excilys.training.mapper.resultSetModel;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.training.dao.CompanyDAO;
import com.excilys.training.model.Company;

public class CompanyResultSetModelMapper extends ResultSetModelMapper<Company> {
	
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static CompanyResultSetModelMapper instance = null;
    
    private CompanyResultSetModelMapper() {
    	
    }
    
	public final static CompanyResultSetModelMapper getInstance()  {
		if (CompanyResultSetModelMapper.instance == null) {
             
              if (CompanyResultSetModelMapper.instance == null) {
            	  CompanyResultSetModelMapper.instance = new CompanyResultSetModelMapper();
              }
         }
         return CompanyResultSetModelMapper.instance;
	}
    
	@Override
	public Company map(ResultSet rs) throws SQLException {
		Company company = new Company();
		company.setName(rs.getString(COLUMN_NAME));
		company.setId(rs.getLong(COLUMN_ID));
		return company;
	}

}

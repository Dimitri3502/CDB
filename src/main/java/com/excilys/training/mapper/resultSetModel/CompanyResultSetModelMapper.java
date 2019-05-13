package com.excilys.training.mapper.resultSetModel;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.excilys.training.model.Company;

@Component
public class CompanyResultSetModelMapper extends ResultSetModelMapper<Company> {
	
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    
	@Override
	public Company map(ResultSet rs) throws SQLException {
		Company company = new Company();
		company.setName(rs.getString(COLUMN_NAME));
		company.setId(rs.getLong(COLUMN_ID));
		return company;
	}

}

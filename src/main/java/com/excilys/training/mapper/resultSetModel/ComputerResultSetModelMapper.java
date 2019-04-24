package com.excilys.training.mapper.resultSetModel;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.training.dao.ComputerDAO;
import com.excilys.training.exception.InvalidDiscontinuedDate;
import com.excilys.training.model.Company;
import com.excilys.training.model.Computer;

public class ComputerResultSetModelMapper extends ResultSetModelMapper<Computer> {
	
    private static final String COLUMN_COMPANY_ID = "company_id";
    private static final String COLUMN_COMPANY_NAME = "company_name";
    private static final String COLUMN_DISCONTINUED = "discontinued";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_INTRODUCED = "introduced";
    private static final String COLUMN_NAME = "name";
    private static ComputerResultSetModelMapper instance = null;
    
    private ComputerResultSetModelMapper() {
    	
    }
    
	public final static ComputerResultSetModelMapper getInstance()  {
		if (ComputerResultSetModelMapper.instance == null) {
             
              if (ComputerResultSetModelMapper.instance == null) {
            	  ComputerResultSetModelMapper.instance = new ComputerResultSetModelMapper();
              }
            
         }
         return ComputerResultSetModelMapper.instance;
	}
    
	@Override
	public Computer map(ResultSet rs) throws SQLException {
		Computer computer = new Computer();
		computer.setName(rs.getString(COLUMN_NAME));
		computer.setId(rs.getLong(COLUMN_ID));
		if (rs.getDate(COLUMN_INTRODUCED) != null) {
			computer.setIntroducedDate(rs.getTimestamp(COLUMN_INTRODUCED).toLocalDateTime());
		}
		if (rs.getDate(COLUMN_DISCONTINUED) != null) {
			computer.setDiscontinuedDate(rs.getTimestamp(COLUMN_DISCONTINUED).toLocalDateTime());
		}
		Company aCompany = new Company();
		if (rs.getString(COLUMN_COMPANY_ID) != null) {
			aCompany.setId(rs.getLong(COLUMN_COMPANY_ID));
		}
		if (rs.getString(COLUMN_COMPANY_NAME) != null) {
			aCompany.setName(rs.getString(COLUMN_COMPANY_NAME));
		}
		computer.setCompany(aCompany);
		return computer;
	}

}

package com.excilys.training.persistance.resultSetModel;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.training.core.Company;
import com.excilys.training.core.Computer;

@Component
public class ComputerResultSetModelMapper implements RowMapper<Computer> {
	
    private static final String COLUMN_COMPANY_ID = "company_id";
    private static final String COLUMN_COMPANY_NAME = "company_name";
    private static final String COLUMN_DISCONTINUED = "discontinued";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_INTRODUCED = "introduced";
    private static final String COLUMN_NAME = "name";

	@Override
	public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
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

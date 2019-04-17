package com.excilys.training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.excilys.training.model.Company;

public class CompanyDAO extends Dao<Company>{

	@Override
	public List<Company> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Company findById(long id) {
		Company Company = new Company();
		try {
			Connection cnx = DbConn.getConnection();
			PreparedStatement stmt = cnx.prepareStatement("SELECT * FROM Company where id=?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Company.setName(rs.getString("name"));
				Company.setId(rs.getLong("id"));
			}
			cnx.close();
		} catch (SQLException ex) {
			Logger.getLogger(Company.class.getName()).log(Level.SEVERE, null, ex);
		}
		return Company;
	}
	@Override
	public boolean create(Company obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Company obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Company obj) {
		// TODO Auto-generated method stub
		return false;
	}

}

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
import com.excilys.training.model.Computer;

public class CompanyDAO extends Dao<Company>{
    private static final String SQL_FIND_BY_ID = "SELECT id, name FROM company WHERE id = ?";
    private static final String SQL_FIND_ALL = "SELECT id, name FROM company";
    private static final String SQL_CREATE = "INSERT INTO company (name) VALUES (?)";
    private static final String SQL_UPDATE = "UPDATE company SET(name) VALUES (?) WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM company WHERE id=?";

    public Company populate(ResultSet rs) {
    	Company aCompany = new Company();
        try {
        	aCompany.setId(rs.getLong("id"));
            aCompany.setName(rs.getString("name"));
            
        } catch (SQLException ex) {
            Logger.getLogger(Company.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aCompany;
    }
            
	@Override
	public List<Company> getAll() {
        List<Company> companies = new ArrayList<Company>();
        try {
            Connection cnx = DbConn.getConnection();
            PreparedStatement stmt = cnx.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	Company aCompany = populate(rs);
            	companies.add(aCompany);

            }
            cnx.close();
        } catch (SQLException ex) {
            Logger.getLogger(Company.class.getName()).log(Level.SEVERE, null, ex);
        }
        return companies;
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

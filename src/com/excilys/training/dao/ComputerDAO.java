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

public class ComputerDAO extends DAO<Computer>{
	

	public static Computer populate(ResultSet rs) {
    	Computer aComputer = new Computer();
        try {
        	aComputer.setId(rs.getLong("id"));
            aComputer.setName(rs.getString("name"));
            if (rs.getDate("introduced")!=null) {
            	aComputer.setIntroducedDate(rs.getDate("introduced").toLocalDate());
            }
            if (rs.getDate("discontinued")!=null) {
            aComputer.setDiscontinuedDate(rs.getDate("discontinued").toLocalDate());
            }
            aComputer.setCompany(CompanyDAO.getById(rs.getLong("company_id")));
            
        } catch (SQLException ex) {
            Logger.getLogger(Computer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aComputer;
    }
    
	public List<Computer> getAll() {
        List<Computer> computers = new ArrayList<Computer>();
        try {
            Connection cnx = DbConn.getConnection();
            PreparedStatement stmt = cnx.prepareStatement("SELECT * FROM computer ORDER BY id");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	Computer aComputer = populate(rs);
            	computers.add(aComputer);

            }
            cnx.close();
        } catch (SQLException ex) {
            Logger.getLogger(Computer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return computers;
	}

	@Override
	public boolean create(Computer obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Computer obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Computer obj) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Computer findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}

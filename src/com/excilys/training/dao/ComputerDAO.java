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
    private static final String SQL_FIND_BY_ID = "SELECT A.id AS id,A.name AS name ,A.introduced AS introduced ,A.discontinued AS discontinued ,B.id AS company_id ,B.name AS company_name FROM computer AS A LEFT JOIN company AS B ON A.company_id = B.id WHERE A.id = ?";
    private static final String SQL_FIND_ALL = "SELECT A.id AS id,A.name AS name ,A.introduced AS introduced ,A.discontinued AS discontinued ,B.id AS company_id ,B.name AS company_name FROM computer AS A LEFT JOIN company AS B ON A.company_id = B.id ORDER BY A.id";
    private static final String SQL_CREATE = "INSERT INTO computer (name, introduced,discontinued,company_id) VALUES (?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE computer SET(name, introduced,discontinued,company_id) VALUES (?,?,?,?) WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM computer WHERE id=?";

	public Computer populate(ResultSet rs) {
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
            Company aCompany = new Company();
            if (rs.getString("company_id")!=null) {
            	aCompany.setId(rs.getLong("company_id"));
            }
            if (rs.getString("company_name")!=null) {
            	aCompany.setName(rs.getString("company_name"));
            }
            aComputer.setCompany(aCompany);
            
        } catch (SQLException ex) {
            Logger.getLogger(Computer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aComputer;
    }
    
	public List<Computer> getAll() {
        List<Computer> computers = new ArrayList<Computer>();
        try {
            Connection cnx = DbConn.getConnection();
            PreparedStatement stmt = cnx.prepareStatement(SQL_FIND_ALL);
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

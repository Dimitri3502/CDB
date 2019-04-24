package com.excilys.training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.excilys.training.mapper.resultSetModel.CompanyResultSetModelMapper;
import com.excilys.training.model.Company;

public class CompanyDAO extends Dao<Company>{
    private static final String SQL_FIND_BY_ID = "SELECT id, name FROM company WHERE id = ?";
    private static final String SQL_FIND_ALL = "SELECT id, name FROM company";
    private static final String SQL_CREATE = "INSERT INTO company (name) VALUES (?)";
    private static final String SQL_UPDATE = "UPDATE company SET(name) VALUES (?) WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM company WHERE id=?";
    private static final String SQL_FIND_ALL_PAGINED = "SELECT id,name FROM company ORDER BY id LIMIT ? OFFSET ?";

	private static CompanyDAO instance = null;

	private CompanyDAO() {
		// TODO Auto-generated constructor stub
	}
	public final static CompanyDAO getInstance()  {
		if (CompanyDAO.instance == null) {
             
              if (CompanyDAO.instance == null) {
            	  CompanyDAO.instance = new CompanyDAO();
              }
            
         }
         return CompanyDAO.instance;
	}
	
    public Company populate(ResultSet rs) {
    	Company company = null;
        try {
			CompanyResultSetModelMapper companyResultSetModelMapper = CompanyResultSetModelMapper.getInstance();
			company = companyResultSetModelMapper.map(rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(Company.class.getName()).log(Level.SEVERE, null, ex);
        }
        return company;
    }
            
	@Override
	public List<Company> getAll() {
        List<Company> companies = new ArrayList<Company>();
        try(Connection cnx = DbConn.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	Company aCompany = populate(rs);
            	companies.add(aCompany);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Company.class.getName()).log(Level.SEVERE, null, ex);
        }
        return companies;
	}

	@Override
	public Company findById(long id) {
		try(Connection cnx = DbConn.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SQL_FIND_BY_ID);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				CompanyResultSetModelMapper companyResultSetModelMapper = CompanyResultSetModelMapper.getInstance();
				return companyResultSetModelMapper.map(rs);
			}
		} catch (SQLException ex) {
			Logger.getLogger(Company.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
	
	@Override
	public List<Company> getAll(int limit, int offset) {
        List<Company> companies = new ArrayList<Company>();
        try(Connection cnx = DbConn.getConnection()) {
            PreparedStatement stmt = cnx.prepareStatement(SQL_FIND_ALL_PAGINED);
            stmt.setLong(1, limit);
            stmt.setLong(2, offset);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	Company aCompany = populate(rs);
            	companies.add(aCompany);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Company.class.getName()).log(Level.SEVERE, null, ex);
        }
        return companies;
	}
	
	@Override
	public long create(Company obj) {
		return 0;
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
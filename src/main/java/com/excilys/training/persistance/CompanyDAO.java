package com.excilys.training.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.excilys.training.mapper.resultSetModel.CompanyResultSetModelMapper;
import com.excilys.training.model.Company;
import com.excilys.training.persistance.databases.DatabaseManager;

public class CompanyDAO extends Dao<Company> {
	private static final String SQL_FIND_BY_ID = "SELECT id, name FROM company WHERE id = ?";
	private static final String SQL_FIND_ALL = "SELECT id, name FROM company";
	private static final String SQL_CREATE = "INSERT INTO company (name) VALUES (?)";
	private static final String SQL_UPDATE = "UPDATE company SET(name) VALUES (?) WHERE id=?";
	private static final String SQL_DELETE = "DELETE FROM company WHERE id=?";
	private static final String SQL_FIND_ALL_PAGINED = "SELECT id,name FROM company ORDER BY id LIMIT ? OFFSET ?";
	private static final String DELETE_COMPUTERS_BY_COMPANY_ID_QUERY = "DELETE FROM computer WHERE computer.company_id = ?";

	private static CompanyDAO instance = null;
	private final Logger logger = LogManager.getLogger(getClass());

	private CompanyDAO() {
		// TODO Auto-generated constructor stub
	}

	public final static CompanyDAO getInstance() {
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
			logger.warn("populate ", ex);
		}
		return company;
	}

	@Override
	public List<Company> getAll() {
		List<Company> companies = new ArrayList<Company>();
		try (Connection cnx = DatabaseManager.getConnectionEnvironment();
				PreparedStatement stmt = cnx.prepareStatement(SQL_FIND_ALL);) {
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Company aCompany = populate(rs);
				companies.add(aCompany);

			}
		} catch (SQLException ex) {
			logger.warn("getAll Companies failed", ex);
		}
		return companies;
	}

	@Override
	public Optional<Company> findById(long id) {
		try (Connection cnx = DatabaseManager.getConnectionEnvironment();
				PreparedStatement stmt = cnx.prepareStatement(SQL_FIND_BY_ID);) {
			
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				CompanyResultSetModelMapper companyResultSetModelMapper = CompanyResultSetModelMapper.getInstance();
				return Optional.of(companyResultSetModelMapper.map(rs));
			} else {
				logger.warn("Company findById(" + id + ") not found");
				return Optional.empty();
			}
		} catch (SQLException ex) {
			logger.warn("Company findById(" + id + ")", ex);
			return Optional.empty();
		}
	}

	@Override
	public List<Company> getAll(int limit, int offset) {
		List<Company> companies = new ArrayList<Company>();
		try (Connection cnx = DatabaseManager.getConnectionEnvironment();
				PreparedStatement stmt = cnx.prepareStatement(SQL_FIND_ALL_PAGINED);) {
			
			stmt.setLong(1, limit);
			stmt.setLong(2, offset);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Company aCompany = populate(rs);
				companies.add(aCompany);

			}
		} catch (SQLException ex) {
			logger.warn("findAll(" + offset + "," + limit + ")", ex);
		}
		return companies;
	}

	@Override
	public long create(Company obj) {
		return 0;
	}

	@Override
	public boolean delete(Long id) {

		try (Connection cnx = DatabaseManager.getConnectionEnvironment();
				PreparedStatement deleteComputers = cnx.prepareStatement(DELETE_COMPUTERS_BY_COMPANY_ID_QUERY);) {
			cnx.setAutoCommit(false);
			
			deleteComputers.setLong(1, id);
			deleteComputers.executeUpdate();

			PreparedStatement deleteCompany = cnx.prepareStatement(SQL_DELETE);
			deleteCompany.setLong(1, id);
			deleteCompany.executeUpdate();

			cnx.commit();
			cnx.setAutoCommit(true);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.warn("delete(" + id + ")", e);
			return false;
		}
		
	}

	@Override
	public boolean update(Company obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Company obj) {
		// TODO Auto-generated method stub
		return false;
	}

}

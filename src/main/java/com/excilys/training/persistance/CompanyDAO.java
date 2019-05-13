package com.excilys.training.persistance;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.excilys.training.mapper.resultSetModel.CompanyResultSetModelMapper;
import com.excilys.training.model.Company;

@Component()
public class CompanyDAO extends Dao<Company> {
	private static final String SQL_FIND_BY_ID = "SELECT id, name FROM company WHERE id = ?";
	private static final String SQL_FIND_ALL = "SELECT id, name FROM company";
	private static final String SQL_CREATE = "INSERT INTO company (name) VALUES (?)";
	private static final String SQL_UPDATE = "UPDATE company SET(name) VALUES (?) WHERE id=?";
	private static final String SQL_DELETE = "DELETE FROM company WHERE id=?";
	private static final String SQL_FIND_ALL_PAGINED = "SELECT id,name FROM company ORDER BY id LIMIT ? OFFSET ?";
	private static final String DELETE_COMPUTERS_BY_COMPANY_ID_QUERY = "DELETE FROM computer WHERE computer.company_id = ?";

	private final Logger logger = LogManager.getLogger(getClass());
	private final CompanyResultSetModelMapper companyResultSetModelMapper;
	private final JdbcTemplate jdbcTemplate;

	public CompanyDAO(CompanyResultSetModelMapper companyResultSetModelMapper, JdbcTemplate jdbcTemplate) {
		super();
		this.companyResultSetModelMapper = companyResultSetModelMapper;
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Company> getAll() {
		return jdbcTemplate.query(SQL_FIND_ALL, new CompanyResultSetModelMapper());
	}

	@Override
	public Company findById(long id) {
		return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[] { id }, new CompanyResultSetModelMapper());
	}

	@Override
	public List<Company> getAll(int limit, int offset) {
		return jdbcTemplate.query(SQL_FIND_ALL_PAGINED, new Object[] { limit, offset },
				new CompanyResultSetModelMapper());
	}

	@Override
	public long create(Company obj) {
		return 0;
	}

//	@Override
//	public boolean delete(Long id) {
//
//		try (Connection cnx = dataSource.getConnection()) {
//
//			try (PreparedStatement deleteComputers = cnx.prepareStatement(DELETE_COMPUTERS_BY_COMPANY_ID_QUERY)) {
//				cnx.setAutoCommit(false);
//
//				deleteComputers.setLong(1, id);
//				deleteComputers.executeUpdate();
//
//				PreparedStatement deleteCompany = cnx.prepareStatement(SQL_DELETE);
//				deleteCompany.setLong(1, id);
//				deleteCompany.executeUpdate();
//
//				cnx.commit();
//				cnx.setAutoCommit(true);
//				return true;
//			} catch (Exception e) {
//				cnx.rollback();
//				throw e;
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			logger.warn("delete(" + id + ")", e);
//			return false;
//		}
//
//	}

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

package com.excilys.training.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.training.exception.CompanyNotDeletedException;
import com.excilys.training.mapper.resultSetModel.CompanyResultSetModelMapper;
import com.excilys.training.model.Company;

@Component()
@Transactional(readOnly = true)
public class CompanyDAO extends Dao<Company> {
	private static final String SQL_FIND_BY_ID = "SELECT id, name FROM company WHERE id = ?";
	private static final String SQL_FIND_ALL = "SELECT id, name FROM company";
	private static final String SQL_CREATE = "INSERT INTO company (name) VALUES (?)";
	private static final String SQL_UPDATE = "UPDATE company SET(name) VALUES (?) WHERE id=?";
	private static final String SQL_DELETE = "DELETE FROM company WHERE id=?";
	private static final String SQL_FIND_ALL_PAGINED = "SELECT id,name FROM company ORDER BY id LIMIT ? OFFSET ?";

	private final Logger logger = LogManager.getLogger(getClass());
	private final CompanyResultSetModelMapper companyResultSetModelMapper;
	private final JdbcTemplate jdbcTemplate;
	private final DataSource dataSource;

	public CompanyDAO(CompanyResultSetModelMapper companyResultSetModelMapper, JdbcTemplate jdbcTemplate,
			DataSource dataSource) {
		super();
		this.companyResultSetModelMapper = companyResultSetModelMapper;
		this.jdbcTemplate = jdbcTemplate;
		this.dataSource = dataSource;
	}

	@Override
	public List<Company> getAll() {
		try {
			return jdbcTemplate.query(SQL_FIND_ALL, companyResultSetModelMapper);
		} catch (DataAccessException e) {
			logger.warn(e.getMessage());
			return null;
		}

	}

	@Override
	public Company findById(long id) {
		try {
			return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[] { id }, companyResultSetModelMapper);
		} catch (DataAccessException e) {
			logger.warn(e.getMessage());
			return null;
		}

	}

	@Override
	public List<Company> getAll(int limit, int offset) {
		try {
			return jdbcTemplate.query(SQL_FIND_ALL_PAGINED, new Object[] { limit, offset },
					companyResultSetModelMapper);
		} catch (DataAccessException e) {
			logger.warn(e.getMessage());
			return null;
		}

	}

	@Override
	public long create(Company obj) {
		return 0;
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

	@Override
	@Transactional
	public boolean delete(Long id) throws CompanyNotDeletedException{
		try {
			final Object[] args = { id };
			jdbcTemplate.update(SQL_DELETE, args);
			return true;
		} catch (DataAccessException e) {
			logger.warn("deleteById(" + id + ")", e);
			throw new CompanyNotDeletedException(id);
		}
		
	}

}

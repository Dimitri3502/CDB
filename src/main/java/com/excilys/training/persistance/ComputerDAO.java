package com.excilys.training.persistance;

import java.sql.PreparedStatement;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.excilys.training.controller.web.Page;
import com.excilys.training.exception.InvalidDiscontinuedDate;
import com.excilys.training.mapper.resultSetModel.ComputerResultSetModelMapper;
import com.excilys.training.model.Computer;
import com.excilys.training.persistance.ENUMS.OrderByChamp;
import com.excilys.training.persistance.ENUMS.OrderByDirection;

@Component()
public class ComputerDAO extends Dao<Computer> {

	private static final String SQL_FIND_BY_ID = "SELECT A.id AS id,A.name AS name ,A.introduced AS introduced ,A.discontinued AS discontinued ,B.id AS company_id ,B.name AS company_name FROM computer AS A LEFT JOIN company AS B ON A.company_id = B.id WHERE A.id = ?";
	private static final String SQL_FIND_ALL = "SELECT A.id AS id,A.name AS name ,A.introduced AS introduced ,A.discontinued AS discontinued ,B.id AS company_id ,B.name AS company_name FROM computer AS A LEFT JOIN company AS B ON A.company_id = B.id ORDER BY A.id";
	private static final String SQL_CREATE = "INSERT INTO computer (name, introduced,discontinued,company_id) VALUES (?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE computer SET name = ?, introduced = ?,discontinued = ?,company_id = ? WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM computer WHERE id=?";
	private static final String SQL_FIND_ALL_PAGINED = "SELECT A.id AS id,A.name AS name ,A.introduced AS introduced ,A.discontinued AS discontinued ,B.id AS company_id ,B.name AS company_name FROM computer AS A LEFT JOIN company AS B ON A.company_id = B.id ORDER BY id LIMIT ? OFFSET ?";
	private static final String SQL_COUNT = "SELECT COUNT(id) AS count FROM computer";
	private static final String SQL_COUNT_NAME = "SELECT COUNT(computer.id) AS count FROM computer "
			+ "LEFT JOIN company ON computer.company_id = company.id "
			+ "WHERE UPPER(computer.name) LIKE UPPER(?) OR UPPER(company.name) LIKE UPPER(?) ";
	private static final String SELECT_BY_NAME_OR_COMPANY_QUERY = "SELECT A.id AS id,A.name AS name ,A.introduced AS introduced ,A.discontinued AS discontinued ,"
			+ "B.id AS company_id ,B.name AS company_name FROM computer AS A "
			+ "LEFT JOIN company AS B ON A.company_id = B.id "
			+ "WHERE UPPER(A.name) LIKE UPPER(?) OR UPPER(B.name) LIKE UPPER(?) "
			+ "ORDER BY :order_by: IS NULL, :order_by:  :order_direction: LIMIT ? OFFSET ?";

	private final Logger logger = LogManager.getLogger(getClass());
	private final ComputerResultSetModelMapper computerResultSetModelMapper;
	private final JdbcTemplate jdbcTemplate;

	public ComputerDAO(ComputerResultSetModelMapper computerResultSetModelMapper, JdbcTemplate jdbcTemplate) {
		super();
		this.computerResultSetModelMapper = computerResultSetModelMapper;
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Computer> getAll() {
		try {
			return jdbcTemplate.query(SQL_FIND_ALL, computerResultSetModelMapper);
		} catch (DataAccessException e) {
			logger.warn(e.getMessage());
			return null;
		}

	}

	@Override
	public long create(Computer computer) {
		SQLComputer sqlComputer = SQLComputer.from(computer);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(createStmt(sqlComputer), keyHolder);
		return keyHolder.getKey().longValue();
	}

	private PreparedStatementCreator createStmt(SQLComputer sqlComputer) {
		return con -> {
			PreparedStatement stmt = con.prepareStatement(SQL_CREATE, new String[] { "id" });
			stmt.setString(1, sqlComputer.getName());
			stmt.setTimestamp(2, sqlComputer.getIntroduced());
			stmt.setTimestamp(3, sqlComputer.getDiscontinued());
			stmt.setObject(4, sqlComputer.getCompanyId());
			return stmt;
		};
	}

	public Long count(String name) {
		name = name != null ? "%" + name + "%" : "%%";
		try {
			return jdbcTemplate.queryForObject(SQL_COUNT_NAME, new Object[] { name, name }, Long.class);
		} catch (DataAccessException e) {
			logger.warn(e.getMessage());
			return null;
		}

	}

	public Long count() {
		try {
			return jdbcTemplate.queryForObject(SQL_COUNT, Long.class);
		} catch (DataAccessException e) {
			logger.warn(e.getMessage());
			return null;
		}

	}

	@Override
	public boolean delete(Long id) {
		jdbcTemplate.update(SQL_DELETE, new Object[] { id });
		return true;
	}

	@Override
	public boolean delete(Computer computer) {
		return delete(computer.getId());

	}

	@Override
	public boolean update(Computer computer) {
		SQLComputer sqlComputer = SQLComputer.from(computer);
		Object[] parameters = new Object[] { sqlComputer.getName(), sqlComputer.getIntroduced(),
				sqlComputer.getDiscontinued(), sqlComputer.getCompanyId(), computer.getId() };
		jdbcTemplate.update(SQL_UPDATE, parameters);
		return true;
	}

	@Override
	public Computer findById(long id) {
		try {
			return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[] { id }, computerResultSetModelMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.warn(e.getMessage());
			return null;
		}

	}

	@Override
	public List<Computer> getAll(int limit, int offset) {
		try {
			return jdbcTemplate.query(SQL_FIND_ALL_PAGINED, new Object[] { limit, offset },
					computerResultSetModelMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.warn(e.getMessage());
			return null;
		}

	}

	public List<Computer> getAll(Page page){
		try {
			String selectByNameOrCompany = SELECT_BY_NAME_OR_COMPANY_QUERY.replace(":order_by:", map(page.getOrderBy()))
					.replace(":order_direction:", map(page.getOrderDirection()));
			
			return jdbcTemplate.query(selectByNameOrCompany,
					new Object[] { page.getSearch(), page.getSearch(), page.getLimit(), page.getOffset() },
					computerResultSetModelMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.warn(e.getMessage());
			return null;
		}

	}

	private String map(OrderByDirection c) {
		switch (c) {
		default:
		case ASC:
			return "ASC";
		case DESC:
			return "DESC";
		}
	}

	private String map(OrderByChamp c) {
		switch (c) {
		default:
		case ID:
			return "A.id";
		case NAME:
			return "A.name";
		case INTRODUCED:
			return "A.introduced";
		case DISCONTINUED:
			return "A.discontinued";
		case COMPANY:
			return "B.name";
		}
	}

}

package com.excilys.training.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.excilys.training.exception.InvalidDiscontinuedDate;
import com.excilys.training.mapper.resultSetModel.ComputerResultSetModelMapper;
import com.excilys.training.model.Computer;
import com.excilys.training.servlets.Page;

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
	private static final String SQL_LAST_INSERT_ID = "SELECT LAST_INSERT_ID(id) FROM computer";
	private static final String SELECT_BY_NAME_OR_COMPANY_QUERY = "SELECT A.id AS id,A.name AS name ,A.introduced AS introduced ,A.discontinued AS discontinued ,"
			+ "B.id AS company_id ,B.name AS company_name FROM computer AS A "
			+ "LEFT JOIN company AS B ON A.company_id = B.id "
			+ "WHERE UPPER(A.name) LIKE UPPER(?) OR UPPER(B.name) LIKE UPPER(?) "
			+ "ORDER BY :order_by: IS NULL, :order_by:  :order_direction: LIMIT ? OFFSET ?";


	private final Logger logger = LogManager.getLogger(getClass());
	private final ComputerResultSetModelMapper computerResultSetModelMapper;
	private final DataSource dataSource;
	
	public ComputerDAO(ComputerResultSetModelMapper computerResultSetModelMapper, DataSource dataSource) {
		super();
		this.computerResultSetModelMapper = computerResultSetModelMapper;
		this.dataSource = dataSource;
	}

	public Computer populate(ResultSet rs) throws InvalidDiscontinuedDate {
		Computer computer = null;
		try {
			computer = computerResultSetModelMapper.map(rs);
		} catch (SQLException ex) {
			logger.warn("populate ", ex);
		}
		return computer;
	}

	public List<Computer> getAll() throws InvalidDiscontinuedDate {
		List<Computer> computers = new ArrayList<Computer>();
		try (Connection cnx = dataSource.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(SQL_FIND_ALL);) {

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Computer aComputer = populate(rs);
				computers.add(aComputer);

			}
		} catch (SQLException ex) {
			logger.warn("getAll Computers failed", ex);
		}
		return computers;
	}

	@Override
	public long create(Computer computer) {
		Long lastInsertedId = null;
		try (Connection cnx = dataSource.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);) {
			SQLComputer sqlComputer = SQLComputer.from(computer);
			stmt.setString(1, sqlComputer.getName());
			stmt.setTimestamp(2, sqlComputer.getIntroduced());
			stmt.setTimestamp(3, sqlComputer.getDiscontinued());
			stmt.setObject(4, sqlComputer.getCompanyId());
			stmt.toString();
			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				lastInsertedId = rs.getLong(1);
			}
		} catch (SQLException ex) {
			logger.warn("create(" + computer.toString() + ")", ex);
		}
		return lastInsertedId;
	}

	public long count(String name) {
		Long computersNumber = null;
		try (Connection cnx = dataSource.getConnection();
			PreparedStatement stmt = cnx.prepareStatement(SQL_COUNT_NAME)) {
			name = name != null ? "%" + name + "%" : "%%";
			stmt.setString(1, name);
			stmt.setString(2, name);
			stmt.execute();

			ResultSet rs = stmt.getResultSet();
			if (rs.next()) {
				computersNumber = rs.getLong(1);
			}
		} catch (SQLException ex) {
			logger.warn("count()", ex);
		}
		return computersNumber;
	}

	public long count() {
		Long computersNumber = null;
		try (Connection cnx = dataSource.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(SQL_COUNT);) {

			stmt.execute();

			ResultSet rs = stmt.getResultSet();
			if (rs.next()) {
				computersNumber = rs.getLong(1);
			}
		} catch (SQLException ex) {
			logger.warn("count()", ex);
		}
		return computersNumber;
	}

	public long getLastIdInserted() {
		Long lastIdInserted = null;
		try (Connection cnx = dataSource.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(SQL_LAST_INSERT_ID);) {

			stmt.execute();

			ResultSet rs = stmt.getResultSet();
			if (rs.next()) {
				lastIdInserted = rs.getLong(1);
			}
		} catch (SQLException ex) {
			logger.warn("getLastIdInserted()", ex);
		}
		return lastIdInserted;
	}

	@Override
	public boolean delete(Computer computer) {
		try (Connection cnx = dataSource.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(SQL_DELETE);) {

			stmt.setLong(1, computer.getId());
			stmt.executeUpdate();
			return true;
		} catch (SQLException ex) {
			logger.warn("delete(" + computer.toString() + ")", ex);
			return false;
		}

	}

	@Override
	public boolean update(Computer computer) {
		try (Connection cnx = dataSource.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(SQL_UPDATE);) {

			SQLComputer sqlComputer = SQLComputer.from(computer);
			stmt.setString(1, sqlComputer.getName());
			stmt.setTimestamp(2, sqlComputer.getIntroduced());
			stmt.setTimestamp(3, sqlComputer.getDiscontinued());
			stmt.setObject(4, sqlComputer.getCompanyId());
			stmt.setObject(5, computer.getId());
			stmt.executeUpdate();
			stmt.toString();
		} catch (SQLException ex) {
			logger.warn("update(" + computer.toString() + ")", ex);
		}
		return false;
	}

	@Override
	public Optional<Computer> findById(long id) {

		try (Connection cnx = dataSource.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(SQL_FIND_BY_ID);) {

			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
			
				return Optional.of(computerResultSetModelMapper.map(rs));
			} else {
				logger.warn("findById(" + id + ") not found");
				return Optional.empty();
			}

		} catch (SQLException ex) {
			logger.warn("Company findById(" + id + ")", ex);
			throw new RuntimeException(ex);
		}

	}

	@Override
	public List<Computer> getAll(int limit, int offset) throws InvalidDiscontinuedDate {
		List<Computer> computers = new ArrayList<Computer>();
		try (Connection cnx = dataSource.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(SQL_FIND_ALL_PAGINED);) {

			stmt.setLong(1, limit);
			stmt.setLong(2, offset);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Computer aComputer = populate(rs);
				computers.add(aComputer);
			}
		} catch (SQLException ex) {
			logger.warn("getAll(" + offset + "," + limit + ")", ex);
		}
		return computers;
	}

	public List<Computer> getAll(Page page)
			throws InvalidDiscontinuedDate {

		List<Computer> computers = new ArrayList<Computer>();
		try (Connection cnx = dataSource.getConnection()) {
			String selectByNameOrCompany = SELECT_BY_NAME_OR_COMPANY_QUERY.replace(":order_by:", map(page.getOrderBy()))
					.replace(":order_direction:", map(page.getOrderDirection()));
			PreparedStatement stmt = cnx.prepareStatement(selectByNameOrCompany);
			stmt.setString(1, page.getSearch());
			stmt.setString(2, page.getSearch());
			stmt.setLong(3, page.getLimit());
			stmt.setLong(4, page.getOffset());
			logger.debug(stmt.toString());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Computer aComputer = populate(rs);
				computers.add(aComputer);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.warn("getAll(" + page.getOffset() + ", " + page.getLimit() + ", " + page.getSearch() + ", " + page.getOrderBy() + ", " + page.getOrderDirection() + ")",
					ex);
		}
		return computers;
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
			return "company_name";
		}
	}
}

package com.excilys.training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.excilys.training.dao.databases.DatabaseManager;
import com.excilys.training.exception.InvalidDiscontinuedDate;
import com.excilys.training.mapper.resultSetModel.ComputerResultSetModelMapper;
import com.excilys.training.model.Computer;

public class ComputerDAO extends Dao<Computer> {
	private static final String SQL_FIND_BY_ID = "SELECT A.id AS id,A.name AS name ,A.introduced AS introduced ,A.discontinued AS discontinued ,B.id AS company_id ,B.name AS company_name FROM computer AS A LEFT JOIN company AS B ON A.company_id = B.id WHERE A.id = ?";
	private static final String SQL_FIND_ALL = "SELECT A.id AS id,A.name AS name ,A.introduced AS introduced ,A.discontinued AS discontinued ,B.id AS company_id ,B.name AS company_name FROM computer AS A LEFT JOIN company AS B ON A.company_id = B.id ORDER BY A.id";
	private static final String SQL_CREATE = "INSERT INTO computer (name, introduced,discontinued,company_id) VALUES (?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE computer SET name = ?, introduced = ?,discontinued = ?,company_id = ? WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM computer WHERE id=?";
	private static final String SQL_FIND_ALL_PAGINED = "SELECT A.id AS id,A.name AS name ,A.introduced AS introduced ,A.discontinued AS discontinued ,B.id AS company_id ,B.name AS company_name FROM computer AS A LEFT JOIN company AS B ON A.company_id = B.id ORDER BY id LIMIT ? OFFSET ?";
	private static final String SQL_COUNT = "SELECT COUNT(id) AS count FROM computer";
	
	private static ComputerDAO instance = null;
	private final Logger logger = LogManager.getLogger(getClass());
	
	private ComputerDAO() {
		// TODO Auto-generated constructor stub
	}

	public final static ComputerDAO getInstance() {
		if (ComputerDAO.instance == null) {

			if (ComputerDAO.instance == null) {
				ComputerDAO.instance = new ComputerDAO();
			}

		}
		return ComputerDAO.instance;
	}

	public Computer populate(ResultSet rs) throws InvalidDiscontinuedDate {
		Computer computer = null;
		try {
			ComputerResultSetModelMapper computerResultSetModelMapper = ComputerResultSetModelMapper.getInstance();
			computer = computerResultSetModelMapper.map(rs);
		} catch (SQLException ex) {
			logger.warn("populate ", ex);
		}
		return computer;
	}

	public List<Computer> getAll() throws InvalidDiscontinuedDate {
		List<Computer> computers = new ArrayList<Computer>();
		try (Connection cnx = DatabaseManager.getConnectionEnvironment()) {
			PreparedStatement stmt = cnx.prepareStatement(SQL_FIND_ALL);
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
		try (Connection cnx = DatabaseManager.getConnectionEnvironment()) {
			PreparedStatement stmt;
			SQLComputer sqlComputer = SQLComputer.from(computer);
			stmt = cnx.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
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

	public long count() {
		Long computersNumber = null;
		try (Connection cnx = DatabaseManager.getConnectionEnvironment()) {
			PreparedStatement stmt;
			stmt = cnx.prepareStatement(SQL_COUNT);
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
	
	@Override
	public boolean delete(Computer computer) {
		try (Connection cnx = DatabaseManager.getConnectionEnvironment()) {
			PreparedStatement stmt = cnx.prepareStatement(SQL_DELETE);
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
		try (Connection cnx = DatabaseManager.getConnectionEnvironment()) {
			PreparedStatement stmt;
			SQLComputer sqlComputer = SQLComputer.from(computer);
			stmt = cnx.prepareStatement(SQL_UPDATE);
			stmt.setString(1, sqlComputer.getName());
			stmt.setTimestamp(2, sqlComputer.getIntroduced());
			stmt.setTimestamp(3, sqlComputer.getDiscontinued());
			stmt.setObject(4, sqlComputer.getId());
			stmt.setObject(5, computer.getId());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			logger.warn("update(" + computer.toString() + ")", ex);
		}
		return false;
	}

	@Override
	public Optional<Computer> findById(long id) {

		try (Connection cnx = DatabaseManager.getConnectionEnvironment()) {

			PreparedStatement stmt = cnx.prepareStatement(SQL_FIND_BY_ID);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				ComputerResultSetModelMapper computerResultSetModelMapper = ComputerResultSetModelMapper.getInstance();
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
		try (Connection cnx = DatabaseManager.getConnectionEnvironment()) {
			PreparedStatement stmt = cnx.prepareStatement(SQL_FIND_ALL_PAGINED);
			stmt.setLong(1, limit);
			stmt.setLong(2, offset);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Computer aComputer = populate(rs);
				computers.add(aComputer);
			}
		} catch (SQLException ex) {
			 logger.warn("findAll(" + offset + "," + limit + ")", ex);
		}
		return computers;
	}
}

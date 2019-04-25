package com.excilys.training.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.excilys.training.exception.ComputerNotFoundException;
import com.excilys.training.exception.InvalidDiscontinuedDate;
import com.excilys.training.exception.NotFoundException;
import com.excilys.training.mapper.resultSetModel.ComputerResultSetModelMapper;
import com.excilys.training.model.Company;
import com.excilys.training.model.Computer;

public class ComputerDAO extends Dao<Computer> {
	private static final String SQL_FIND_BY_ID = "SELECT A.id AS id,A.name AS name ,A.introduced AS introduced ,A.discontinued AS discontinued ,B.id AS company_id ,B.name AS company_name FROM computer AS A LEFT JOIN company AS B ON A.company_id = B.id WHERE A.id = ?";
	private static final String SQL_FIND_ALL = "SELECT A.id AS id,A.name AS name ,A.introduced AS introduced ,A.discontinued AS discontinued ,B.id AS company_id ,B.name AS company_name FROM computer AS A LEFT JOIN company AS B ON A.company_id = B.id ORDER BY A.id";
	private static final String SQL_CREATE = "INSERT INTO computer (name, introduced,discontinued,company_id) VALUES (?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE computer SET name = ?, introduced = ?,discontinued = ?,company_id = ? WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM computer WHERE id=?";
	private static final String SQL_FIND_ALL_PAGINED = "SELECT A.id AS id,A.name AS name ,A.introduced AS introduced ,A.discontinued AS discontinued ,B.id AS company_id ,B.name AS company_name FROM computer AS A LEFT JOIN company AS B ON A.company_id = B.id ORDER BY id LIMIT ? OFFSET ?";

	private static ComputerDAO instance = null;

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
			Logger.getLogger(Computer.class.getName()).log(Level.SEVERE, null, ex);
		}
		return computer;
	}

	public List<Computer> getAll() throws InvalidDiscontinuedDate {
		List<Computer> computers = new ArrayList<Computer>();
		try (Connection cnx = DbConn.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SQL_FIND_ALL);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Computer aComputer = populate(rs);
				computers.add(aComputer);

			}
		} catch (SQLException ex) {
			Logger.getLogger(Computer.class.getName()).log(Level.SEVERE, null, ex);
		}
		return computers;
	}

	@Override
	public long create(Computer computer) {
		Long lastInsertedId = null;
		try (Connection cnx = DbConn.getConnection()) {
			PreparedStatement stmt;
			stmt = cnx.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, computer.getName());
			stmt.setTimestamp(2, Timestamp.valueOf(computer.getIntroducedDate()));
			stmt.setTimestamp(3, Timestamp.valueOf(computer.getDiscontinuedDate()));
			stmt.setLong(4, computer.getCompany().getId());
			stmt.toString();
			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				lastInsertedId = rs.getLong(1);
			}
		} catch (SQLException ex) {
			Logger.getLogger(Computer.class.getName()).log(Level.SEVERE, null, ex);
		}
		return lastInsertedId;
	}

	@Override
	public boolean delete(Computer obj) {
		try (Connection cnx = DbConn.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SQL_DELETE);
			stmt.setLong(1, obj.getId());
			stmt.executeUpdate();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(Computer.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
		
	}

	@Override
	public boolean update(Computer computer) {
		try (Connection cnx = DbConn.getConnection()) {
			PreparedStatement stmt;
			stmt = cnx.prepareStatement(SQL_UPDATE);
			stmt.setString(1, computer.getName());
			stmt.setTimestamp(2, Timestamp.valueOf(computer.getIntroducedDate()));
			stmt.setTimestamp(3, Timestamp.valueOf(computer.getDiscontinuedDate()));
			stmt.setLong(4, computer.getCompany().getId());
			stmt.setLong(5, computer.getId());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(Computer.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	@Override
	public Optional<Computer> findById(long id) {

		try (Connection cnx = DbConn.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(SQL_FIND_BY_ID);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				ComputerResultSetModelMapper computerResultSetModelMapper = ComputerResultSetModelMapper.getInstance();
				return Optional.of(computerResultSetModelMapper.map(rs));
			} else {
				return Optional.empty();
			}

		} catch (SQLException ex) {
			Logger.getLogger(Computer.class.getName()).log(Level.SEVERE, null, ex);
			throw new RuntimeException(ex); 
		}
		

	}

	@Override
	public List<Computer> getAll(int limit, int offset) throws InvalidDiscontinuedDate {
		List<Computer> computers = new ArrayList<Computer>();
		try (Connection cnx = DbConn.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SQL_FIND_ALL_PAGINED);
			stmt.setLong(1, limit);
			stmt.setLong(2, offset);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Computer aComputer = populate(rs);
				computers.add(aComputer);
			}
		} catch (SQLException ex) {
			Logger.getLogger(Company.class.getName()).log(Level.SEVERE, null, ex);
		}
		return computers;
	}
}

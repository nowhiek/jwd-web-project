package by.epam.jwd.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.bean.Faculty;
import by.epam.jwd.dao.FacultyDAO;
import by.epam.jwd.dao.connectionpool.exception.ConnectionPoolException;
import by.epam.jwd.dao.connectionpool.factory.ConnectionPoolFactory;
import by.epam.jwd.dao.connectionpool.impl.SqlConnectionPoolImpl;
import by.epam.jwd.dao.exception.DAOException;
import by.epam.jwd.dao.exception.DAOSqlException;

public class FacultyDAOImpl implements FacultyDAO {
	
	private final static String GET_ALL_FACULTIES = "SELECT * FROM Faculty";
	private final static String ADD_FACULTY = "INSERT INTO Faculty (faculty_name) VALUES (?)";
	private final static String GET_FACULTY_BY_ID = "SELECT * from Faculty WHERE id = ?";
	private final static String GET_FACULTY_BY_NAME = "SELECT * from Faculty WHERE faculty_name = ?";
	private final static String UPDATE_FACULTY = "UPDATE Faculty SET faculty_name = ? WHERE id = ?";
	private final static String REMOVE_FACULTY_BY_ID = "DELETE FROM Faculty WHERE id = ?";
	
	private ConnectionPoolFactory connectionPoolFactory = ConnectionPoolFactory.getInstance();
	private SqlConnectionPoolImpl connectionPool = connectionPoolFactory.getSqlConnectionPoolDAO();
	private static Logger logger = LogManager.getLogger(FacultyDAOImpl.class);
	
	private static final String FACULTY_ID_COLUMN = "id";
	private static final String FACULTY_NAME_COLUMN = "faculty_name";
	
	@Override
	public List<Faculty> getAll() throws DAOException {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		List<Faculty> result = new ArrayList<>();
		
		try {
			connection = connectionPool.takeConnection();
			statement = connection.createStatement();
		
			rs = statement.executeQuery(GET_ALL_FACULTIES);
			
			while (rs.next()) {
				result.add(buildFacultyFromDB(rs));
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [GET_ALL : FacultyDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, statement, rs);
		}
		
		return result;
	}

	@Override
	public Faculty getFacultyById(int id) throws DAOException {
		Faculty faculty = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_FACULTY_BY_ID);
			ps.setInt(1, id);
		
			rs = ps.executeQuery();
		
			if (rs.next()) {
				faculty = buildFacultyFromDB(rs);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {	
			logger.log(Level.ERROR, "SQLException in [GET_FACULTY_BY_ID : FacultyDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return faculty;
	}

	@Override
	public Faculty getFacultyByName(String facultyName) throws DAOException {
		Faculty faculty = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_FACULTY_BY_NAME);
			ps.setString(1, facultyName);
		
			rs = ps.executeQuery();
		
			if (rs.next()) {
				faculty = buildFacultyFromDB(rs);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [GET_FACULTY_BY_NAME : FacultyDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);			
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return faculty;
	}

	@Override
	public boolean update(Faculty faculty) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(UPDATE_FACULTY);
			
			ps.setString(1, faculty.getFacultyName());
			ps.setInt(2, faculty.getId());
			
			ps.executeUpdate();
			flag = true;
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [UPDATE : FacultyDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
		
		return flag;
	}

	@Override
	public boolean create(Faculty faculty) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(ADD_FACULTY);
		
			ps.setString(1, faculty.getFacultyName());
			
			ps.executeUpdate();
			flag = true;
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [CREATE : FacultyDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
		
		return flag;
	}

	@Override
	public boolean remove(Faculty faculty) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(REMOVE_FACULTY_BY_ID);
			
			ps.setInt(1, faculty.getId());
			
			ps.executeUpdate();
			flag = true;
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [REMOVE : FacultyDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
		
		return flag;
	}

	private Faculty buildFacultyFromDB(ResultSet set) throws SQLException {
		Faculty faculty = null;
		
		int id = set.getInt(FACULTY_ID_COLUMN);
		String name = set.getString(FACULTY_NAME_COLUMN);
		
		faculty = new Faculty(id, name);
		
		return faculty;
	}
}

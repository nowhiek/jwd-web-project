package by.epam.jwd.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.epam.jwd.bean.Faculty;
import by.epam.jwd.dao.FacultyDAO;
import by.epam.jwd.dao.connectionpool.ConnectionPool;
import by.epam.jwd.dao.connectionpool.ConnectionPoolManager;
import by.epam.jwd.dao.connectionpool.exception.ConnectionPoolException;
import by.epam.jwd.dao.exception.DAOException;

public class FacultyDAOImpl implements FacultyDAO {
	
	private final static String GET_ALL_FACULTIES = "SELECT * FROM Faculty";
	private final static String ADD_FACULTY = "INSERT INTO Faculty (faculty_name) VALUES (?)";
	private final static String GET_FACULTY_BY_ID = "SELECT * from Faculty WHERE id = ?";
	private final static String GET_FACULTY_BY_NAME = "SELECT * from Faculty WHERE faculty_name = ?";
	private final static String UPDATE_FACULTY = "UPDATE Faculty SET faculty_name = ? WHERE id = ?";
	private final static String REMOVE_FACULTY_BY_NAME = "DELETE FROM Faculty WHERE faculty_name = ?";
	
	private ConnectionPool connectionPool = ConnectionPoolManager.getInstance().getConnectionPool();

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
			throw new DAOException("SQL can't get information.", e);
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
			throw new DAOException("SQL can't get information.", e);
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
			
			throw new DAOException("SQL can't get information.", e);
			
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
			throw new DAOException("SQL can't get information.", e);
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
			throw new DAOException("SQL can't get information.", e);
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
			ps = connection.prepareStatement(REMOVE_FACULTY_BY_NAME);
			
			ps.setString(1, faculty.getFacultyName());
			
			ps.executeUpdate();
			flag = true;
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			throw new DAOException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
		
		return flag;
	}

	private Faculty buildFacultyFromDB(ResultSet set) throws SQLException {
		Faculty faculty = null;
		
		int id = set.getInt(1);
		String name = set.getString(2);
		
		faculty = new Faculty(id, name);
		
		return faculty;
	}
}

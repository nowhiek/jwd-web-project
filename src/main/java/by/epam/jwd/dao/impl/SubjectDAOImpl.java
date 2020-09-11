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

import by.epam.jwd.bean.Subject;
import by.epam.jwd.dao.SubjectDAO;
import by.epam.jwd.dao.connectionpool.exception.ConnectionPoolException;
import by.epam.jwd.dao.connectionpool.factory.ConnectionPoolFactory;
import by.epam.jwd.dao.connectionpool.impl.SqlConnectionPoolImpl;
import by.epam.jwd.dao.exception.DAOException;
import by.epam.jwd.dao.exception.DAOSqlException;

public class SubjectDAOImpl implements SubjectDAO {

	private final static String GET_ALL = "SELECT * FROM Subject";
	private final static String GET_SUBJECT_BY_ID = "SELECT * from Subject WHERE id = ?";
	private final static String GET_SUBJECT_BY_NAME = "SELECT * from Subject WHERE subject_name = ?";
	private final static String ADD_SUBJECT = "INSERT INTO Subject (subject_name) VALUES (?)";
	private final static String UPDATE_SUBJECT = "UPDATE Subject SET subject_name = ? WHERE id = ?";
	private final static String REMOVE_SUBJECT_BY_ID = "DELETE FROM Subject WHERE id = ?";
	
	private ConnectionPoolFactory connectionPoolFactory = ConnectionPoolFactory.getInstance();
	private SqlConnectionPoolImpl connectionPool = connectionPoolFactory.getSqlConnectionPoolDAO();
	private static Logger logger = LogManager.getLogger(SubjectDAOImpl.class);
	
	private static final String SUBJECT_ID_COLUMN = "id";
	private static final String SUBJECT_NAME_COLUMN = "subject_name";
	
	@Override
	public List<Subject> getAll() throws DAOException {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		List<Subject> result = new ArrayList<>();
		
		try {
			connection = connectionPool.takeConnection();
			statement = connection.createStatement();
		
			rs = statement.executeQuery(GET_ALL);
			
			while (rs.next()) {
				result.add(buildSubjectFromDB(rs));
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [GET_ALL : SubjectDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, statement, rs);
		}
		
		return result;
	}

	@Override
	public Subject getSubjectById(int id) throws DAOException {
		Subject subject = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_SUBJECT_BY_ID);
			ps.setInt(1, id);
		
			rs = ps.executeQuery();
		
			if (rs.next()) {
				subject = buildSubjectFromDB(rs);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {	
			logger.log(Level.ERROR, "SQLException in [GET_SUBJECT_BY_ID : SubjectDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return subject;
	}

	@Override
	public Subject getSubjectByName(String subjectName) throws DAOException {
		Subject subject = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_SUBJECT_BY_NAME);
			ps.setString(1, subjectName);
		
			rs = ps.executeQuery();
		
			if (rs.next()) {
				subject = buildSubjectFromDB(rs);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {	
			logger.log(Level.ERROR, "SQLException in [GET_SUBJECT_BY_NAME : SubjectDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return subject;
	}

	@Override
	public boolean update(Subject subject) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(UPDATE_SUBJECT);
			
			ps.setString(1, subject.getSubjectName());
			ps.setInt(2, subject.getId());
			
			ps.executeUpdate();
			flag = true;
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [UPDATE : SubjectDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
		
		return flag;
	}

	@Override
	public boolean create(Subject subject) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(ADD_SUBJECT);
					
			ps.setString(1, subject.getSubjectName());
			
			ps.executeUpdate();
			flag = true;
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [CREATE : SubjectDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
		
		return flag;
	}

	@Override
	public boolean remove(Subject subject) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(REMOVE_SUBJECT_BY_ID);
			
			ps.setInt(1, subject.getId());
			
			ps.executeUpdate();
			flag = true;
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [REMOVE : SubjectDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
		
		return flag;
	}

	private Subject buildSubjectFromDB(ResultSet set) throws SQLException {
		Subject subject = null;
		
		int id = set.getInt(SUBJECT_ID_COLUMN);
		String name = set.getString(SUBJECT_NAME_COLUMN);

		subject = new Subject(id, name);
		
		return subject;
	}
}

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

import by.epam.jwd.bean.Result;
import by.epam.jwd.dao.ResultDAO;
import by.epam.jwd.dao.connectionpool.exception.ConnectionPoolException;
import by.epam.jwd.dao.connectionpool.factory.ConnectionPoolFactory;
import by.epam.jwd.dao.connectionpool.impl.SqlConnectionPoolImpl;
import by.epam.jwd.dao.exception.DAOException;
import by.epam.jwd.dao.exception.DAOSqlException;

public class ResultDAOImpl implements ResultDAO {

	private final static String GET_ALL_RESULTS = "SELECT * FROM Result";
	private final static String ADD_RESULT = "INSERT INTO Result (id_matriculant, points, is_accepted) VALUES (?, ?, ?)";
	private final static String GET_RESULT_BY_ID = "SELECT * from Result WHERE id = ?";
	private final static String GET_RESULT_BY_ID_MATRICULANT = "SELECT * from Result WHERE id_matriculant = ?";
	private final static String UPDATE_RESULT = "UPDATE Result SET id_matriculant = ?, points = ?, is_accepted = ? WHERE id = ?";
	private final static String REMOVE_RESULT_BY_ID_MATRICULANT = "DELETE FROM Result WHERE id_matriculant = ?";
	
	private ConnectionPoolFactory connectionPoolFactory = ConnectionPoolFactory.getInstance();
	private SqlConnectionPoolImpl connectionPool = connectionPoolFactory.getSqlConnectionPoolDAO();
	private static Logger logger = LogManager.getLogger(ResultDAOImpl.class);
	
	private static final String RESULT_ID_COLUMN = "id";
	private static final String RESULT_ID_MATRICULANT_COLUMN = "id_matriculant";
	private static final String RESULT_POINTS_COLUMN = "points";
	private static final String RESULT_IS_ACCEPTED_COLUMN = "is_accepted";
	
	@Override
	public List<Result> getAll() throws DAOException {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		List<Result> result = new ArrayList<>();
		
		try {
			connection = connectionPool.takeConnection();
			statement = connection.createStatement();
		
			rs = statement.executeQuery(GET_ALL_RESULTS);
			
			while (rs.next()) {
				result.add(buildResultFromDB(rs));
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [GET_ALL : ResultDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, statement, rs);
		}
		
		return result;
	}

	@Override
	public Result getResultById(int id) throws DAOException {
		Result result = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_RESULT_BY_ID);
			ps.setInt(1, id);
		
			rs = ps.executeQuery();
		
			if (rs.next()) {
				result = buildResultFromDB(rs);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {	
			logger.log(Level.ERROR, "SQLException in [GET_RESULT_BY_ID : ResultDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return result;
	}

	@Override
	public Result getResultByMatriculant(int idMatriculant) throws DAOException {
		Result result = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_RESULT_BY_ID_MATRICULANT);
			ps.setInt(1, idMatriculant);
		
			rs = ps.executeQuery();
		
			if (rs.next()) {
				result = buildResultFromDB(rs);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {	
			logger.log(Level.ERROR, "SQLException in [GET_RESULTS_BY_MATRICULANT : ResultDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return result;
	}

	@Override
	public boolean update(Result result) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(UPDATE_RESULT);
			
			ps.setInt(1, result.getIdMatriculant());
			ps.setInt(2, result.getPoints());
			ps.setBoolean(3, result.isAccepted());
			ps.setInt(4, result.getId());
			
			ps.executeUpdate();
			flag = true;
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [UPDATE : ResultDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
		
		return flag;
	}

	@Override
	public boolean create(Result result) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(ADD_RESULT);
			
			ps.setInt(1, result.getIdMatriculant());
			ps.setInt(2, result.getPoints());
			ps.setBoolean(3, result.isAccepted());
			
			ps.executeUpdate();
			flag = true;
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [CREATE : ResultDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
		
		return flag;
	}

	@Override
	public boolean remove(Result result) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(REMOVE_RESULT_BY_ID_MATRICULANT);
			
			ps.setInt(1, result.getIdMatriculant());
			
			ps.executeUpdate();
			flag = true;
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [REMOVE : ResultDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
		
		return flag;
	}

	private Result buildResultFromDB(ResultSet set) throws SQLException {
		Result result = null;
		
		int id = set.getInt(RESULT_ID_COLUMN);
		int idMatriculant = set.getInt(RESULT_ID_MATRICULANT_COLUMN);
		int points = set.getInt(RESULT_POINTS_COLUMN);
		boolean isAccepted = set.getBoolean(RESULT_IS_ACCEPTED_COLUMN);
		
		result = new Result(id, idMatriculant, points, isAccepted);
		
		return result;
	}
}

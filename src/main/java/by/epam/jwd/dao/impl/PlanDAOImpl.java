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

import by.epam.jwd.bean.Plan;
import by.epam.jwd.dao.PlanDAO;
import by.epam.jwd.dao.connectionpool.exception.ConnectionPoolException;
import by.epam.jwd.dao.connectionpool.factory.ConnectionPoolFactory;
import by.epam.jwd.dao.connectionpool.impl.SqlConnectionPoolImpl;
import by.epam.jwd.dao.exception.DAOException;
import by.epam.jwd.dao.exception.DAOSqlException;

public class PlanDAOImpl implements PlanDAO {
	
	private final static String GET_ALL = "SELECT * FROM Plan";
	private final static String ADD_PLAN = "INSERT INTO Plan (count_places) VALUES (?)";
	private final static String GET_PLAN_BY_ID = "SELECT * from Plan WHERE id = ?";
	private final static String GET_PLAN_BY_COUNT_PLACES = "SELECT * from Plan WHERE count_places = ?";
	private final static String UPDATE_PLAN = "UPDATE PLAN SET count_places = ? WHERE id = ?";
	private final static String REMOVE_PLAN_BY_ID = "DELETE FROM Plan WHERE id = ?";
	
	private ConnectionPoolFactory connectionPoolFactory = ConnectionPoolFactory.getInstance();
	private SqlConnectionPoolImpl connectionPool = connectionPoolFactory.getSqlConnectionPoolDAO();
	private static Logger logger = LogManager.getLogger(PlanDAOImpl.class);
	
	private static final String PLAN_ID_COLUMN = "id";
	private static final String PLAN_COUNT_PLACES_COLUMN = "count_places";
	
	@Override
	public List<Plan> getAll() throws DAOException {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		List<Plan> result = new ArrayList<>();
		
		try {
			connection = connectionPool.takeConnection();
			statement = connection.createStatement();
		
			rs = statement.executeQuery(GET_ALL);
			
			while (rs.next()) {
				result.add(buildPlanFromDB(rs));
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [GET_ALL : PlanDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, statement, rs);
		}
		
		return result;
	}
	
	@Override
	public Plan getPlanById(int id) throws DAOException {
		Plan plan = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_PLAN_BY_ID);
			ps.setInt(1, id);
		
			rs = ps.executeQuery();
		
			if (rs.next()) {
				plan = buildPlanFromDB(rs);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [GET_PLAN_BY_ID : PlanDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return plan;
	}

	@Override
	public Plan getPlanByCountPlaces(int count) throws DAOException {
		Plan plan = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_PLAN_BY_COUNT_PLACES);
			ps.setInt(1, count);
		
			rs = ps.executeQuery();
		
			if (rs.next()) {
				plan = buildPlanFromDB(rs);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [GET_PLAN_BY_COUNT_PLACES : PlanDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);			
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return plan;
	}

	@Override
	public boolean update(Plan plan) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(UPDATE_PLAN);
			
			ps.setInt(1, plan.getCountPlaces());
			ps.setInt(2, plan.getId());
			
			ps.executeUpdate();
			flag = true;
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [UPDATE : PlanDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
		
		return flag;
	}

	@Override
	public boolean create(Plan plan) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(ADD_PLAN);
		
			ps.setInt(1, plan.getCountPlaces());
			
			ps.executeUpdate();
			flag = true;
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [CREATE : PlanDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
		
		return flag;
	}

	@Override
	public boolean remove(Plan plan) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(REMOVE_PLAN_BY_ID);
			
			ps.setInt(1, plan.getId());
			
			ps.executeUpdate();
			flag = true;
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [REMOVE : PlanDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
		
		return flag;
	}

	private Plan buildPlanFromDB(ResultSet set) throws SQLException {
		Plan plan = null;
		
		int id = set.getInt(PLAN_ID_COLUMN);
		int countPlaces = set.getInt(PLAN_COUNT_PLACES_COLUMN);
		
		plan = new Plan(id, countPlaces);
		
		return plan;
	}
}

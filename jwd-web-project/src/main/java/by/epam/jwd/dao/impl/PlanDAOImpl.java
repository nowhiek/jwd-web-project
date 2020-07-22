package by.epam.jwd.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.epam.jwd.bean.Plan;
import by.epam.jwd.dao.PlanDAO;
import by.epam.jwd.dao.connectionpool.ConnectionPool;
import by.epam.jwd.dao.connectionpool.ConnectionPoolManager;
import by.epam.jwd.dao.connectionpool.exception.ConnectionPoolException;
import by.epam.jwd.dao.exception.DAOException;

public class PlanDAOImpl implements PlanDAO {
	
	private final static String GET_ALL = "SELECT * FROM Plan";
	private final static String ADD_PLAN = "INSERT INTO Plan (count_places) VALUES (?)";
	private final static String GET_PLAN_BY_ID = "SELECT * from Plan WHERE id = ?";
	private final static String GET_PLAN_BY_COUNT_PLACES = "SELECT * from Plan WHERE count_places = ?";
	private final static String UPDATE_PLAN = "UPDATE PLAN SET count_places = ? WHERE id = ?";
	private final static String REMOVE_PLAN_BY_ID = "DELETE FROM Plan WHERE id = ?";
	
	private ConnectionPool connectionPool = ConnectionPoolManager.getInstance().getConnectionPool();

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
			throw new DAOException("SQL can't get information.", e);
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
			
			throw new DAOException("SQL can't get information.", e);
			
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
			
			throw new DAOException("SQL can't get information.", e);
			
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
			throw new DAOException("SQL can't get information.", e);
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
			throw new DAOException("SQL can't get information.", e);
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
			throw new DAOException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
		
		return flag;
	}

	private Plan buildPlanFromDB(ResultSet set) throws SQLException {
		Plan plan = null;
		
		int id = set.getInt(1);
		int countPlaces = set.getInt(2);
		
		plan = new Plan(id, countPlaces);
		
		return plan;
	}
}

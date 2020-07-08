package by.epam.jwd.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.epam.jwd.bean.Role;
import by.epam.jwd.bean.Status;
import by.epam.jwd.bean.User;
import by.epam.jwd.dao.UserDAO;
import by.epam.jwd.dao.connectionpool.ConnectionPool;
import by.epam.jwd.dao.connectionpool.ConnectionPoolManager;
import by.epam.jwd.dao.connectionpool.exception.ConnectionPoolException;
import by.epam.jwd.dao.exception.DAOException;

public class UserDAOImpl implements UserDAO {
	
	private ConnectionPool connectionPool = ConnectionPoolManager.getInstance().getConnectionPool();
	
	private final static String GET_ALL_USERS = "SELECT * FROM User WHERE id_role = 1";
	private final static String ADD_USER = "INSERT INTO User (id_role, user_login, user_password, user_email, user_status, is_banned) VALUES (?, ?, ?, ?, ?, ?)";
	private final static String GET_USER_BY_ID = "SELECT * from User WHERE id = ?";
	private final static String GET_USER_BY_LOGIN = "SELECT * from User WHERE user_login = ?";
	private final static String UPDATE_USER = "UPDATE User SET id_role = ?, user_login = ?, user_password = ?, user_email = ?, is_banned = ? WHERE id = ?";
	private final static String REMOVE_USER_BY_NAME = "DELETE FROM User WHERE user_login = ?";
	
	@Override
	public List<User> getAll() throws DAOException {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		List<User> result = new ArrayList<>();
		
		try {
			connection = connectionPool.takeConnection();
			statement = connection.createStatement();
		
			rs = statement.executeQuery(GET_ALL_USERS);
			
			while (rs.next()) {
				result.add(buildUserFromDB(rs));
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
	public User getUserById(int id) throws DAOException {
		User user = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_USER_BY_ID);
			ps.setInt(1, id);
		
			rs = ps.executeQuery();
		
			if (rs.next()) {
				user = buildUserFromDB(rs);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			
			throw new DAOException("SQL can't get information.", e);
			
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return user;
	}
	
	@Override
	public User getUserByLogin(String login) throws DAOException {
		User user = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(GET_USER_BY_LOGIN);
			ps.setString(1, login);
		
			rs = ps.executeQuery();
			
			if (rs.next()) {
				user = buildUserFromDB(rs);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			throw new DAOException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return user;
	}

	@Override
	public boolean update(User user) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(UPDATE_USER);
			
			ps.setInt(1, user.getUserRole().getId());
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getUserPassword());
			ps.setString(4, user.getUserEmail());
			ps.setBoolean(5, user.getUserIsBanned());
			ps.setInt(6, user.getId());
			
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
	public boolean create(User user) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(ADD_USER);
			
			ps.setInt(1, 1);
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getUserPassword());
			ps.setString(4, user.getUserEmail());
			ps.setInt(5, 0);
			ps.setBoolean(6, user.getUserIsBanned());
			
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
	public boolean remove(User user) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(REMOVE_USER_BY_NAME);
			
			ps.setString(1, user.getUserName());
			
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
	public boolean isUserExist(User user) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(GET_USER_BY_LOGIN);
			
			ps.setString(1, user.getUserName());
			
			ResultSet rs = null;
			rs = ps.executeQuery();
			
			if (rs.getRow() > 0) {
				return true;
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			throw new DAOException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
		
		return false;
	}
	
	private User buildUserFromDB(ResultSet set) throws SQLException {
		User user = null;
		
		int id = set.getInt(1);
		Role role = Role.getRole(set.getInt(2));
		String name = set.getString(3);
		String password = set.getString(4);
		String email = set.getString(5);
		Status status = Status.getStatus(set.getInt(6));
		boolean ban = intToBoolean(set.getInt(7));
		
		user = new User(id, name, password, email, role, status, ban);
		
		return user;
	}	

	private boolean intToBoolean(int input) {
	    if((input==0)||(input==1)) {
	        return input!=0;
	    }else {
	        throw new IllegalArgumentException("Входное значение может быть равно только 0 или 1 !");
	    }
	}
}

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

import by.epam.jwd.bean.Role;
import by.epam.jwd.bean.Status;
import by.epam.jwd.bean.User;
import by.epam.jwd.dao.UserDAO;
import by.epam.jwd.dao.connectionpool.exception.ConnectionPoolException;
import by.epam.jwd.dao.connectionpool.factory.ConnectionPoolFactory;
import by.epam.jwd.dao.connectionpool.impl.SqlConnectionPoolImpl;
import by.epam.jwd.dao.exception.DAOException;
import by.epam.jwd.dao.exception.DAOSqlException;

public class UserDAOImpl implements UserDAO {
	
	private final static String GET_ALL_USERS = "SELECT * FROM User WHERE id_role = 1";
	private final static String ADD_USER = "INSERT INTO User (id_role, user_login, user_password, user_email, user_status, is_banned) VALUES (?, ?, ?, ?, ?, ?)";
	private final static String GET_USER_BY_ID = "SELECT * from User WHERE id = ?";
	private final static String GET_USER_BY_LOGIN = "SELECT * from User WHERE user_login = ?";
	private final static String GET_USER_BY_EMAIL = "SELECT * FROM User Where user_email = ?";
	private final static String UPDATE_USER = "UPDATE User SET id_role = ?, user_login = ?, user_password = ?, user_email = ?, is_banned = ? WHERE id = ?";
	private final static String REMOVE_USER_BY_NAME = "DELETE FROM User WHERE user_login = ?";
	
	private ConnectionPoolFactory connectionPoolFactory = ConnectionPoolFactory.getInstance();
	private SqlConnectionPoolImpl connectionPool = connectionPoolFactory.getSqlConnectionPoolDAO();
	private static Logger logger = LogManager.getLogger(UserDAOImpl.class);
	
	private static final String USER_ID_COLUMN = "id";
	private static final String USER_ID_ROLE_COLUMN = "id_role";
	private static final String USER_LOGIN_COLUMN = "user_login";
	private static final String USER_PASSWORD_COLUMN = "user_password";
	private static final String USER_EMAIL_COLUMN = "user_email";
	private static final String USER_STATUS_COLUMN = "user_status";
	private static final String USER_IS_BANNED_COLUMN = "is_banned";
	
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
			logger.log(Level.ERROR, "SQLException in [GET_ALL : UserDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
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
			logger.log(Level.ERROR, "SQLException in [GET_USER_BY_ID : UserDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
			
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
			logger.log(Level.ERROR, "SQLException in [GET_USER_BY_LOGIN : UserDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return user;
	}
	
	@Override
	public User getUserByEmail(String email) throws DAOException {
		User user = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(GET_USER_BY_EMAIL);
			ps.setString(1, email);
		
			rs = ps.executeQuery();
			
			if (rs.next()) {
				user = buildUserFromDB(rs);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [GET_USER_BY_EMAIL : UserDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
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
			logger.log(Level.ERROR, "SQLException in [UPDATE : UserDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
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
			logger.log(Level.ERROR, "SQLException in [CREATE : UserDAOImpl]", e);
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
			logger.log(Level.ERROR, "SQLException in [REMOVE : UserDAOImpl]", e);
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
			logger.log(Level.ERROR, "SQLException in [IS_USER_EXIST : UserDAOImpl]", e);
			throw new DAOException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
		
		return false;
	}
	
	private User buildUserFromDB(ResultSet set) throws SQLException {
		User user = null;
		
		int id = set.getInt(USER_ID_COLUMN);
		Role role = Role.getRole(set.getInt(USER_ID_ROLE_COLUMN));
		String name = set.getString(USER_LOGIN_COLUMN);
		String password = set.getString(USER_PASSWORD_COLUMN);
		String email = set.getString(USER_EMAIL_COLUMN);
		Status status = Status.getStatus(set.getInt(USER_STATUS_COLUMN));
		boolean ban = intToBoolean(set.getInt(USER_IS_BANNED_COLUMN));
		
		user = new User(id, name, password, email, role, status, ban);
		
		return user;
	}	

	private boolean intToBoolean(int input) {
	    if((input == 0)||(input == 1)) {
	        return input!=0;
	    } else {
	        throw new IllegalArgumentException("Input value can be only 0 or 1");
	    }
	}
}

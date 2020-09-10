package by.epam.jwd.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.bean.UserDetail;
import by.epam.jwd.dao.UserDetailDAO;
import by.epam.jwd.dao.connectionpool.exception.ConnectionPoolException;
import by.epam.jwd.dao.connectionpool.factory.ConnectionPoolFactory;
import by.epam.jwd.dao.connectionpool.impl.SqlConnectionPoolImpl;
import by.epam.jwd.dao.exception.DAOException;
import by.epam.jwd.dao.exception.DAOSqlException;

public class UserDetailDAOImpl implements UserDetailDAO {
	
	private final static String GET_USER_DETAIL_BY_ID = "SELECT * FROM UserDetail WHERE id = ?";
	private final static String GET_USER_DETAIL_BY_ID_USER = "SELECT * FROM UserDetail WHERE id_user = ?;";
	private final static String ADD_USER_DETAIL = "INSERT INTO UserDetail (id_user, first_name, second_name, birthday, sex, serial_pasport, number_pasport) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private final static String UPDATE_USER_DETAIL = "UPDATE UserDetail SET first_name = ?, second_name = ?, birthday = ?, sex = ?, serial_pasport = ?, number_pasport = ? WHERE id_user = ?";
	private final static String REMOVE_USER_DETAIL_BY_USER = "DELETE FROM UserDetail WHERE id_user = ?";
	
	private ConnectionPoolFactory connectionPoolFactory = ConnectionPoolFactory.getInstance();
	private SqlConnectionPoolImpl connectionPool = connectionPoolFactory.getSqlConnectionPoolDAO();
	private static Logger logger = LogManager.getLogger(UserDetailDAOImpl.class);
	 
	private static final String USER_DETAIL_ID_COLUMN = "id";
	private static final String USER_DETAIL_ID_USER_COLUMN = "id_user";
	private static final String USER_DETAIL_FIRST_NAME_COLUMN = "first_name";
	private static final String USER_DETAIL_SECOND_NAME_COLUMN = "second_name";
	private static final String USER_DETAIL_BIRTHDAY_COLUMN = "birthday";
	private static final String USER_DETAIL_SEX_COLUMN = "sex";
	private static final String USER_DETAIL_SERIAL_PASSPORT_COLUMN = "serial_pasport";
	private static final String USER_DETAIL_NUMBER_PASSPORT_COLUMN = "number_pasport";
	
	@Override
	public UserDetail getUserDetailById(int id) throws DAOException {
		UserDetail detail = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_USER_DETAIL_BY_ID);
			ps.setInt(1, id);
		
			rs = ps.executeQuery();
		
			if (rs.next()) {
				detail = buildUserDetailFromDB(rs);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [GET_USER_DETAIL_BY_ID : UserDetailDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
			
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return detail;
	}

	@Override
	public UserDetail getUserDetailByIdUser(int id) throws DAOException {
		UserDetail detail = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_USER_DETAIL_BY_ID_USER);
			ps.setInt(1, id);
		
			rs = ps.executeQuery();
		
			if (rs.next()) {
				detail = buildUserDetailFromDB(rs);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [GET_USER_DETAIL_BY_ID_USER : UserDetailDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
			
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return detail;
	}

	@Override
	public boolean create(UserDetail detail) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(ADD_USER_DETAIL);
			
			ps.setInt(1, detail.getIdUser());
			ps.setString(2, detail.getFirstName());
			ps.setString(3, detail.getSecondName());
			ps.setDate(4, detail.getBirthday());
			ps.setString(5, detail.getSex());
			ps.setString(6, detail.getSerialPassport());
			ps.setInt(7, detail.getNumberPassport());
			
			ps.executeUpdate();
			flag = true;
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [CREATE : UserDetailDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
		
		return flag;
	}

	@Override
	public boolean update(UserDetail detail) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(UPDATE_USER_DETAIL);
			
			ps.setString(1, detail.getFirstName());
			ps.setString(2, detail.getSecondName());
			ps.setDate(3, detail.getBirthday());
			ps.setString(4, detail.getSex());
			ps.setString(5, detail.getSerialPassport());
			ps.setInt(6, detail.getNumberPassport());
			ps.setInt(7, detail.getIdUser());
			
			
			ps.executeUpdate();
			flag = true;
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [UPDATE : UserDetailDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
		
		return flag;
	}
	
	@Override
	public boolean remove(UserDetail detail) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(REMOVE_USER_DETAIL_BY_USER);
			
			ps.setInt(1, detail.getIdUser());
			
			ps.executeUpdate();
			flag = true;
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [REMOVE : UserDetailDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
		
		return flag;
	}
	
	private UserDetail buildUserDetailFromDB(ResultSet set) throws SQLException {
		UserDetail detail = null;
		
		int id = set.getInt(USER_DETAIL_ID_COLUMN);
		int idUser = set.getInt(USER_DETAIL_ID_USER_COLUMN);
		String firstName = set.getString(USER_DETAIL_FIRST_NAME_COLUMN);
		String secondName = set.getString(USER_DETAIL_SECOND_NAME_COLUMN);
		Date birthday = set.getDate(USER_DETAIL_BIRTHDAY_COLUMN);
		String sex = set.getString(USER_DETAIL_SEX_COLUMN);
		String serial = set.getString(USER_DETAIL_SERIAL_PASSPORT_COLUMN);
		int number = set.getInt(USER_DETAIL_NUMBER_PASSPORT_COLUMN);
		
		detail = new UserDetail(id, idUser, firstName, secondName, birthday, sex, serial, number);
		
		return detail;
	}
}
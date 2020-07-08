package by.epam.jwd.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.jwd.bean.UserDetail;
import by.epam.jwd.dao.UserDetailDAO;
import by.epam.jwd.dao.connectionpool.ConnectionPool;
import by.epam.jwd.dao.connectionpool.ConnectionPoolManager;
import by.epam.jwd.dao.connectionpool.exception.ConnectionPoolException;
import by.epam.jwd.dao.exception.DAOException;

public class UserDetailDAOImpl implements UserDetailDAO {

	private ConnectionPool connectionPool = ConnectionPoolManager.getInstance().getConnectionPool();
	
	private final static String GET_USER_DETAIL_BY_ID = "SELECT * FROM UserDetail WHERE id = ?";
	private final static String GET_USER_DETAIL_BY_ID_USER = "SELECT * FROM UserDetail WHERE id_user = ?;";
	private final static String ADD_USER_DETAIL = "INSERT INTO UserDetail (id_user, first_name, second_name, birthday, sex, serial_pasport, number_pasport) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private final static String UPDATE_USER_DETAIL = "UPDATE UserDetail SET first_name = ?, second_name = ?, birthday = ?, sex = ?, serial_pasport = ?, number_pasport = ? WHERE id_user = ?";
	private final static String REMOVE_USER_DETAIL_BY_USER = "DELETE FROM UserDetail WHERE id_user = ?";
	
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
			
			throw new DAOException("SQL can't get information.", e);
			
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
			
			throw new DAOException("SQL can't get information.", e);
			
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
			System.out.print(e);
			throw new DAOException("SQL can't get information.", e);
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
			throw new DAOException("SQL can't get information.", e);
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
			throw new DAOException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
		
		return flag;
	}
	
	private UserDetail buildUserDetailFromDB(ResultSet set) throws SQLException {
		UserDetail detail = null;
		
		int id = set.getInt(1);
		int idUser = set.getInt(2);
		String firstName = set.getString(3);
		String secondName = set.getString(4);
		Date birthday = set.getDate(5);
		String sex = set.getString(6);
		String serial = set.getString(7);
		int number = set.getInt(8);
		
		detail = new UserDetail(id, idUser, firstName, secondName, birthday, sex, serial, number);
		
		return detail;
	}
}

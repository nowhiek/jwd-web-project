package by.epam.jwd.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.epam.jwd.bean.Matriculant;
import by.epam.jwd.dao.MatriculantDAO;
import by.epam.jwd.dao.connectionpool.ConnectionPool;
import by.epam.jwd.dao.connectionpool.ConnectionPoolManager;
import by.epam.jwd.dao.connectionpool.exception.ConnectionPoolException;
import by.epam.jwd.dao.exception.DAOException;

public class MatriculantDAOImpl implements MatriculantDAO {

	private final static String GET_ALL_MATRICULANTS = "SELECT * FROM Matriculant";
	private final static String ADD_MATRICULANT = "INSERT INTO Matriculant (id_user, id_specialty, certificate, is_activated) VALUES (?, ?, ?, ?)";
	private final static String GET_MATRICULANT_BY_ID = "SELECT * from Matriculant WHERE id = ?";
	private final static String GET_MATRICULANT_BY_USER = "SELECT * from Matriculant WHERE id_user = ?";
	private final static String UPDATE_MATRICULANT = "UPDATE Matriculant SET  id_specialty = ?, certificate = ?, is_activated = ? WHERE id_user = ?";
	private final static String REMOVE_MATRICULANT_BY_ID_USER = "DELETE FROM Matriculant WHERE id_user = ?";
	
	private ConnectionPool connectionPool = ConnectionPoolManager.getInstance().getConnectionPool();
	
	@Override
	public List<Matriculant> getAll() throws DAOException {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		List<Matriculant> result = new ArrayList<>();
		
		try {
			connection = connectionPool.takeConnection();
			statement = connection.createStatement();
		
			rs = statement.executeQuery(GET_ALL_MATRICULANTS);
			
			while (rs.next()) {
				result.add(buildMatriculantFromDB(rs));
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
	public Matriculant getMatriculantById(int id) throws DAOException {
		Matriculant matriculant = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_MATRICULANT_BY_ID);
			ps.setInt(1, id);
		
			rs = ps.executeQuery();
		
			if (rs.next()) {
				matriculant = buildMatriculantFromDB(rs);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {	
			throw new DAOException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return matriculant;
	}

	@Override
	public Matriculant getMatriculantByUser(int idUser) throws DAOException {
		Matriculant matriculant = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_MATRICULANT_BY_USER);
			ps.setInt(1, idUser);
		
			rs = ps.executeQuery();
		
			if (rs.next()) {
				matriculant = buildMatriculantFromDB(rs);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {	
			throw new DAOException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return matriculant;
	}

	@Override
	public boolean update(Matriculant matriculant) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(UPDATE_MATRICULANT);
			
			ps.setInt(1, matriculant.getIdSpecialty());
			ps.setInt(2, matriculant.getCertificate());
			ps.setBoolean(3, matriculant.isActivated());
			ps.setInt(4, matriculant.getIdUser());
			
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
	public boolean create(Matriculant matriculant) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(ADD_MATRICULANT);
			
			ps.setInt(1, matriculant.getIdUser());
			ps.setInt(2, matriculant.getIdSpecialty());
			ps.setInt(3, matriculant.getCertificate());
			ps.setBoolean(4, matriculant.isActivated());
			
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
	public boolean remove(Matriculant matriculant) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(REMOVE_MATRICULANT_BY_ID_USER);
			
			ps.setInt(1, matriculant.getIdUser());
			
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
	
	private Matriculant buildMatriculantFromDB(ResultSet set) throws SQLException {
		Matriculant matriculant = null;
		
		int id = set.getInt(1);
		int idUser = set.getInt(2);
		int idSpecialty = set.getInt(3);
		int certificate = set.getInt(4);
		boolean isActivated = set.getBoolean(5);
		
		matriculant = new Matriculant(id, idUser, idSpecialty, certificate, isActivated);
		
		return matriculant;
	}
}

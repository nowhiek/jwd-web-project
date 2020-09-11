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

import by.epam.jwd.bean.Matriculant;
import by.epam.jwd.bean.Plan;
import by.epam.jwd.bean.Specialty;
import by.epam.jwd.dao.MatriculantDAO;
import by.epam.jwd.dao.connectionpool.exception.ConnectionPoolException;
import by.epam.jwd.dao.connectionpool.factory.ConnectionPoolFactory;
import by.epam.jwd.dao.connectionpool.impl.SqlConnectionPoolImpl;
import by.epam.jwd.dao.exception.DAOException;
import by.epam.jwd.dao.exception.DAOSqlException;

public class MatriculantDAOImpl implements MatriculantDAO {

	private final static String GET_ALL_MATRICULANTS = "SELECT * FROM Matriculant";
	private final static String GET_ALL_MATRICULANTS_BY_SPECIALTY = "SELECT * FROM Matriculant WHERE id_specialty = ?";
	private final static String GET_ALL_MATRICULANTS_BY_PROPERTY = "SELECT * FROM Matriculant WHERE is_activated = ?";
	private final static String ADD_MATRICULANT = "INSERT INTO Matriculant (id_user, id_specialty, certificate, is_activated) VALUES (?, ?, ?, ?)";
	private final static String GET_MATRICULANT_BY_ID = "SELECT * from Matriculant WHERE id = ?";
	private final static String GET_MATRICULANT_BY_USER = "SELECT * from Matriculant WHERE id_user = ?";
	private final static String GET_LIST_MATRICULANTS_BY_SPECIALTY = "SELECT matriculant.id, matriculant.id_user, matriculant.id_specialty, sum(result_mark)*10 + matriculant.certificate as certificate, matriculant.is_activated from Matriculant inner join Exam on matriculant.id = exam.id_matriculant WHERE id_specialty = ? group by id_matriculant order by certificate DESC limit ?";
	private final static String UPDATE_MATRICULANT = "UPDATE Matriculant SET  id_specialty = ?, certificate = ?, is_activated = ? WHERE id_user = ?";
	private final static String REMOVE_MATRICULANT_BY_ID_USER = "DELETE FROM Matriculant WHERE id_user = ?";
	
	private ConnectionPoolFactory connectionPoolFactory = ConnectionPoolFactory.getInstance();
	private SqlConnectionPoolImpl connectionPool = connectionPoolFactory.getSqlConnectionPoolDAO();
	private static Logger logger = LogManager.getLogger(MatriculantDAOImpl.class);
	
	private static final String MATRICULANT_ID_COLUMN = "id";
	private static final String MATRICULANT_ID_USER_COLUMN = "id_user";
	private static final String MATRICULANT_ID_SPECIALTY_COLUMN = "id_specialty";
	private static final String MATRICULANT_CERTIFICATE_COLUMN = "certificate";
	private static final String MATRICULANT_IS_ACTIVATED_COLUMN = "is_activated";
	
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
			logger.log(Level.ERROR, "SQLException in [GET_ALL : MatriculantDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, statement, rs);
		}
		
		return result;
	}
	
	@Override
	public List<Matriculant> getAllMatriculantsBySpecialty(Specialty specialty) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Matriculant> result = new ArrayList<Matriculant>();
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_ALL_MATRICULANTS_BY_SPECIALTY);
			ps.setInt(1, specialty.getId());
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				result.add(buildMatriculantFromDB(rs));
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [GET_ALL_MATRICULANTS_BY_SPECIALTY : MatriculantDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return result;
	}
	
	@Override
	public List<Matriculant> getListMatriculantsBySpecialty(Specialty specialty, Plan plan) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Matriculant> result = new ArrayList<Matriculant>();
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_LIST_MATRICULANTS_BY_SPECIALTY);
			ps.setInt(1, specialty.getId());
			ps.setInt(2, plan.getCountPlaces());
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				result.add(buildMatriculantFromDB(rs));
				
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [GET_LIST_MATRICULANTS_BY_SPECIALTY : MatriculantDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return result;
	}
	
	@Override
	public List<Matriculant> getAllMatriculantsByProperty(boolean active) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Matriculant> result = new ArrayList<Matriculant>();
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_ALL_MATRICULANTS_BY_PROPERTY);
			ps.setBoolean(1, active);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				result.add(buildMatriculantFromDB(rs));
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [GET_ALL_MATRICULANTS_BY_PROPERTY : MatriculantDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
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
			logger.log(Level.ERROR, "SQLException in [GET_MATRICULANT_BY_ID : MatriculantDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
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
			logger.log(Level.ERROR, "SQLException in [GET_MATRICULANTS_BY_USER : MatriculantDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
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
			logger.log(Level.ERROR, "SQLException in [UPDATE : MatriculantDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
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
			logger.log(Level.ERROR, "SQLException in [CREATE : MatriculantDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
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
			logger.log(Level.ERROR, "SQLException in [REMOVE : MatriculantDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
		
		return flag;
	}
	
	private Matriculant buildMatriculantFromDB(ResultSet set) throws SQLException {
		Matriculant matriculant = null;
		
		int id = set.getInt(MATRICULANT_ID_COLUMN);
		int idUser = set.getInt(MATRICULANT_ID_USER_COLUMN);
		int idSpecialty = set.getInt(MATRICULANT_ID_SPECIALTY_COLUMN);
		int certificate = set.getInt(MATRICULANT_CERTIFICATE_COLUMN);
		boolean isActivated = set.getBoolean(MATRICULANT_IS_ACTIVATED_COLUMN);
		
		matriculant = new Matriculant(id, idUser, idSpecialty, certificate, isActivated);
		
		return matriculant;
	}
}

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

import by.epam.jwd.bean.SpecialtyHasSubject;
import by.epam.jwd.dao.SpecialtyHasSubjectDAO;
import by.epam.jwd.dao.connectionpool.exception.ConnectionPoolException;
import by.epam.jwd.dao.connectionpool.factory.ConnectionPoolFactory;
import by.epam.jwd.dao.connectionpool.impl.SqlConnectionPoolImpl;
import by.epam.jwd.dao.exception.DAOException;
import by.epam.jwd.dao.exception.DAOSqlException;

public class SpecialtyHasSubjectDAOImpl implements SpecialtyHasSubjectDAO {

	private final static String GET_ALL = "SELECT * FROM Specailty_has_Subject";
	private final static String GET_ALL_BY_SPECIALTY_ID = "SELECT * FROM Specailty_has_Subject WHERE id_specialty = ?";
	private final static String GET_COUNT_SUBJECTS_BY_ID_SPECIALTY = "SELECT COUNT(*) FROM Specailty_has_Subject WHERE id_specialty = ?";
	private final static String GET_SPECIALTY_HAS_SUBJECT_BY_ID = "SELECT * from Specailty_has_Subject WHERE id = ?";
	private final static String GET_SPECIALTY_HAS_SUBJECT_BY_ID_SPECIALTY = "SELECT * from Specailty_has_Subject WHERE id_specialty = ?";
	private final static String GET_SPECIALTY_HAS_SUBJECT_BY_ID_SUBJECT = "SELECT * from Specailty_has_Subject WHERE id_subject = ?";
	private final static String ADD_SPECIALTY_HAS_SUBJECT = "INSERT INTO Specailty_has_Subject (id_specialty, id_subject) VALUES (?, ?)";
	private final static String UPDATE_SPECIALTY_HAS_SUBJECT = "UPDATE Specailty_has_Subject SET id_specialty = ?, id_subject = ? WHERE id = ?";
	private final static String REMOVE_SPECIALTY_HAS_SUBJECT_BY_ID = "DELETE FROM Specailty_has_Subject WHERE id = ?";
	
	private ConnectionPoolFactory connectionPoolFactory = ConnectionPoolFactory.getInstance();
	private SqlConnectionPoolImpl connectionPool = connectionPoolFactory.getSqlConnectionPoolDAO();
	private static Logger logger = LogManager.getLogger(SpecialtyHasSubjectDAOImpl.class);
	
	private static final String SPECIALTY_HAS_SUBJECT_ID_COLUMN = "id";
	private static final String SPECIALTY_HAS_SUBJECT_ID_SPECIALTY_COLUMN = "id_specialty";
	private static final String SPECIALTY_HAS_SUBJECT_ID_SUBJECT_COLUMN = "id_subject";
	
	@Override
	public List<SpecialtyHasSubject> getAll() throws DAOException {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		List<SpecialtyHasSubject> result = new ArrayList<>();
		
		try {
			connection = connectionPool.takeConnection();
			statement = connection.createStatement();
		
			rs = statement.executeQuery(GET_ALL);
			
			while (rs.next()) {
				result.add(buildSpecialtyHasSubjectFromDB(rs));
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [GET_ALL : SpecialtyHasSubjectDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, statement, rs);
		}
		
		return result;
	}
	
	@Override
	public SpecialtyHasSubject getSpecialtyHasSubjectById(int id) throws DAOException {
		SpecialtyHasSubject has = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_SPECIALTY_HAS_SUBJECT_BY_ID);
			ps.setInt(1, id);
		
			rs = ps.executeQuery();
		
			if (rs.next()) {
				has = buildSpecialtyHasSubjectFromDB(rs);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {	
			logger.log(Level.ERROR, "SQLException in [GET_SPECIALTY_HAS_SUBJECT_BY_ID : SpecialtyHasSubjectDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return has;
	}
	
	@Override
	public List<SpecialtyHasSubject> getAllBySpecialtyId(int idSpecialty) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<SpecialtyHasSubject> result = new ArrayList<>();
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_ALL_BY_SPECIALTY_ID);
			ps.setInt(1, idSpecialty);
		
			rs = ps.executeQuery();
			
			while (rs.next()) {
				result.add(buildSpecialtyHasSubjectFromDB(rs));
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [GET_ALL_BY_SPECIALTY_ID : SpecialtyHasSubjectDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return result;
	}
	
	@Override
	public int getCountSubjectsByByIdSpecialty(int idSpecialty) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int count = 0;
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_COUNT_SUBJECTS_BY_ID_SPECIALTY);
			ps.setInt(1, idSpecialty);
		
			rs = ps.executeQuery();
			
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [GET_COUNT_SUBJECTS_BY_ID_SPECIALTY : SpecialtyHasSubjectDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return count;
	}

	@Override
	public SpecialtyHasSubject getSpecialtyHasSubjectByIdSpecialty(int idSpecialty) throws DAOException {
		SpecialtyHasSubject has = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_SPECIALTY_HAS_SUBJECT_BY_ID_SPECIALTY);
			ps.setInt(1, idSpecialty);
		
			rs = ps.executeQuery();
		
			if (rs.next()) {
				has = buildSpecialtyHasSubjectFromDB(rs);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {	
			logger.log(Level.ERROR, "SQLException in [GET_SPECIALTY_HAS_SUBJECT_BY_ID_SPECIALTY: SpecialtyHasSubjectDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return has;
	}

	@Override
	public SpecialtyHasSubject getSpecialtyHasSubjectByIdSubject(int idSubject) throws DAOException {
		SpecialtyHasSubject has = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_SPECIALTY_HAS_SUBJECT_BY_ID_SUBJECT);
			ps.setInt(1, idSubject);
		
			rs = ps.executeQuery();
		
			if (rs.next()) {
				has = buildSpecialtyHasSubjectFromDB(rs);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {	
			logger.log(Level.ERROR, "SQLException in [GET_SPECIALTY_HAS_SUBJECT_BY_ID_SUBJECT : SpecialtyHasSubjectDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return has;
	}

	@Override
	public boolean update(SpecialtyHasSubject has) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(UPDATE_SPECIALTY_HAS_SUBJECT);
			
			ps.setInt(1, has.getIdSpecialty());
			ps.setInt(2, has.getIdSubject());
			ps.setInt(3, has.getId());
			
			ps.executeUpdate();
			flag = true;
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [UPDATE : SpecialtyHasSubjectDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
		
		return flag;
	}

	@Override
	public boolean create(SpecialtyHasSubject has) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(ADD_SPECIALTY_HAS_SUBJECT);
		
			ps.setInt(1, has.getIdSpecialty());
			ps.setInt(2, has.getIdSubject());
			
			ps.executeUpdate();
			flag = true;
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [CREATE : SpecialtyHasSubjectDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
		
		return flag;
	}

	@Override
	public boolean remove(SpecialtyHasSubject has) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(REMOVE_SPECIALTY_HAS_SUBJECT_BY_ID);
			
			ps.setInt(1, has.getId());
			
			ps.executeUpdate();
			flag = true;
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [REMOVE : SpecialtyHasSubjectDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
		
		return flag;
	}
	
	private SpecialtyHasSubject buildSpecialtyHasSubjectFromDB(ResultSet set) throws SQLException {
		SpecialtyHasSubject has = null;
		
		int id = set.getInt(SPECIALTY_HAS_SUBJECT_ID_COLUMN);
		int idSpecialty = set.getInt(SPECIALTY_HAS_SUBJECT_ID_SPECIALTY_COLUMN);
		int idSubject = set.getInt(SPECIALTY_HAS_SUBJECT_ID_SUBJECT_COLUMN);
		
		has = new SpecialtyHasSubject(id, idSpecialty, idSubject);
		
		return has;
	}
}

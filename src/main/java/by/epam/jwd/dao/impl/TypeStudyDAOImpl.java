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

import by.epam.jwd.bean.Faculty;
import by.epam.jwd.bean.Specialty;
import by.epam.jwd.bean.TypeStudy;
import by.epam.jwd.dao.TypeStudyDAO;
import by.epam.jwd.dao.connectionpool.exception.ConnectionPoolException;
import by.epam.jwd.dao.connectionpool.factory.ConnectionPoolFactory;
import by.epam.jwd.dao.connectionpool.impl.SqlConnectionPoolImpl;
import by.epam.jwd.dao.exception.DAOException;
import by.epam.jwd.dao.exception.DAOSqlException;

public class TypeStudyDAOImpl implements TypeStudyDAO {
	
	private final static String GET_ALL_TYPE_STUDIES = "SELECT * FROM TypeStudy";
	private final static String GET_ALL_BY_SPECIALTY = "select typestudy.id, typestudy.study_name from specialty inner join typestudy on specialty.id_type = typestudy.id inner join faculty on faculty.id = specialty.id_faculty where specialty.specialty_name = ? and  faculty.id = ?";
	private final static String ADD_TYPE_STUDY = "INSERT INTO TypeStudy (study_name) VALUES (?)";
	private final static String GET_TYPE_STUDY_BY_ID = "SELECT * from TypeStudy WHERE id = ?";
	private final static String GET_TYPE_STUDY_BY_NAME = "SELECT * from TypeStudy WHERE study_name = ?";
	private final static String UPDATE_TYPE_STUDY = "UPDATE TypeStudy SET study_name = ? WHERE id = ?";
	private final static String REMOVE_TYPE_STUDY_BY_ID = "DELETE FROM TypeStudy WHERE id = ?";

	private ConnectionPoolFactory connectionPoolFactory = ConnectionPoolFactory.getInstance();
	private SqlConnectionPoolImpl connectionPool = connectionPoolFactory.getSqlConnectionPoolDAO();
	private static Logger logger = LogManager.getLogger(TypeStudyDAOImpl.class);
	
	private static final String TYPE_ID_COLUMN = "id";
	private static final String TYPE_NAME_COLUMN = "study_name";
	
	@Override
	public List<TypeStudy> getAll() throws DAOException {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		List<TypeStudy> result = new ArrayList<>();
		
		try {
			connection = connectionPool.takeConnection();
			statement = connection.createStatement();
		
			rs = statement.executeQuery(GET_ALL_TYPE_STUDIES);
			
			while (rs.next()) {
				result.add(buildTypeStudyFromDB(rs));
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [GET_ALL : TypeStudyDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, statement, rs);
		}
		
		return result;
	}
	
	@Override
	public List<TypeStudy> getAllBySpecialty(Specialty specialty, Faculty faculty) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<TypeStudy> result = new ArrayList<>();
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_ALL_BY_SPECIALTY);
			ps.setString(1, specialty.getSpecialtyName());
			ps.setInt(2, faculty.getId());
		
			rs = ps.executeQuery();
			
			while (rs.next()) {
				result.add(buildTypeStudyFromDB(rs));
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [GET_ALL_BY_SPECIALTY : TypeStudyDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return result;
	}

	@Override
	public TypeStudy getTypeStudyById(int id) throws DAOException {
		TypeStudy type = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_TYPE_STUDY_BY_ID);
			ps.setInt(1, id);
		
			rs = ps.executeQuery();
		
			if (rs.next()) {
				type = buildTypeStudyFromDB(rs);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [GET_TYPE_STUDY_BY_ID : TypeStudyDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return type;
	}

	@Override
	public TypeStudy getTypeStudyByName(String typeStudyName) throws DAOException {
		TypeStudy type = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_TYPE_STUDY_BY_NAME);
			ps.setString(1, typeStudyName);
		
			rs = ps.executeQuery();
		
			if (rs.next()) {
				type = buildTypeStudyFromDB(rs);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [GET_TYPE_STUDY_BY_NAME : TypeStudyDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);	
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return type;
	}

	@Override
	public boolean update(TypeStudy type) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(UPDATE_TYPE_STUDY);
			
			ps.setString(1, type.getStudyName());
			ps.setInt(2, type.getId());
			
			ps.executeUpdate();
			flag = true;
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [UPDATE : TypeStudyDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
		
		return flag;
	}

	@Override
	public boolean create(TypeStudy type) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(ADD_TYPE_STUDY);
		
			ps.setString(1, type.getStudyName());
			
			ps.executeUpdate();
			flag = true;
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [CREATE : TypeStudyDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
		
		return flag;
	}

	@Override
	public boolean remove(TypeStudy type) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(REMOVE_TYPE_STUDY_BY_ID);
			
			ps.setInt(1, type.getId());
			
			ps.executeUpdate();
			flag = true;
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [REMOVE : TypeStudyDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
		
		return flag;
	}

	private TypeStudy buildTypeStudyFromDB(ResultSet set) throws SQLException {
		TypeStudy type = null;
		
		int id = set.getInt(TYPE_ID_COLUMN);
		String name = set.getString(TYPE_NAME_COLUMN);
		
		type = new TypeStudy(id, name);
		
		return type;
	}
}

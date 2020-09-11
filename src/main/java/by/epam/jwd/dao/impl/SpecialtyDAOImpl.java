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
import by.epam.jwd.dao.SpecialtyDAO;
import by.epam.jwd.dao.connectionpool.exception.ConnectionPoolException;
import by.epam.jwd.dao.connectionpool.factory.ConnectionPoolFactory;
import by.epam.jwd.dao.connectionpool.impl.SqlConnectionPoolImpl;
import by.epam.jwd.dao.exception.DAOException;
import by.epam.jwd.dao.exception.DAOSqlException;

public class SpecialtyDAOImpl implements SpecialtyDAO {

	private final static String GET_ALL_SPECIALITIES = "SELECT * FROM Specialty";
	private final static String GET_ALL_GROUP_BY_SPECIALTY_NAME = "SELECT * FROM Specialty GROUP BY specialty_name";
	private final static String GET_SPECIALTY_BY_FACULTY = "select specialty.id, specialty.id_faculty, specialty.id_type, specialty.id_plan, specialty.specialty_name, specialty.count_year_study, specialty.qualification from specialty inner join typestudy on specialty.id_type = typestudy.id inner join faculty on faculty.id = specialty.id_faculty where specialty.specialty_name = ? and faculty.id = ?";
	private final static String GET_ALL_BY_FACULTY_ID = "SELECT * FROM Specialty WHERE id_faculty = ? order by id_faculty" ;
	private final static String GET_SPECIALTIES_BY_FACULTY_TYPESTUDY = "SELECT specialty.id, specialty.id_type, specialty.id_faculty, specialty.id_plan, specialty.specialty_name, specialty.count_year_study, specialty.qualification FROM Specialty inner join typestudy on specialty.id_type = typestudy.id inner join faculty on faculty.id = specialty.id_faculty WHERE specialty.id_type = ? and specialty.id_faculty = ?";
	private final static String ADD_SPECIALTY = "INSERT INTO Specialty (id_type, id_faculty, id_plan, specialty_name, count_year_study, qualification) VALUES (?, ?, ?, ?, ?, ?)";
	private final static String GET_SPECIALTY_BY_ID = "SELECT * from Specialty WHERE id = ?";
	private final static String GET_SPECIALTY_BY_NAME = "SELECT * from Specialty WHERE specialty_name = ?";
	private final static String UPDATE_SPECIALTY = "UPDATE Specialty SET id_type = ?, id_faculty = ?, id_plan = ?, specialty_name = ?, count_year_study = ?, qualification = ? WHERE id = ?";
	private final static String REMOVE_SPECIALTY_BY_ID = "DELETE FROM Specialty WHERE id = ?";
	
	private ConnectionPoolFactory connectionPoolFactory = ConnectionPoolFactory.getInstance();
	private SqlConnectionPoolImpl connectionPool = connectionPoolFactory.getSqlConnectionPoolDAO();
	private static Logger logger = LogManager.getLogger(SpecialtyDAOImpl.class);
	
	private static final String SPECIALTY_ID_COLUMN = "id";
	private static final String SPECIALTY_ID_TYPE_COLUMN = "id_type";
	private static final String SPECIALTY_ID_FACULTY_COLUMN = "id_faculty";
	private static final String SPECIALTY_ID_PLAN_COLUMN = "id_plan";
	private static final String SPECIALTY_SPECIALTY_NAME_COLUMN = "specialty_name";
	private static final String SPECIALTY_COUNT_YEAR_STUDY_COLUMN = "count_year_study";
	private static final String SPECIALTY_QUALIFICATION_COLUMN = "qualification";
	
	@Override
	public List<Specialty> getAll() throws DAOException {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		List<Specialty> result = new ArrayList<>();
		
		try {
			connection = connectionPool.takeConnection();
			statement = connection.createStatement();
		
			rs = statement.executeQuery(GET_ALL_SPECIALITIES);
			
			while (rs.next()) {
				result.add(buildSpecialtyFromDB(rs));
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [GET_ALL : SpecialtyDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, statement, rs);
		}
		
		return result;
	}

	@Override
	public List<Specialty> getAllGroupBySpecialtyName() throws DAOException {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		List<Specialty> result = new ArrayList<>();
		
		try {
			connection = connectionPool.takeConnection();
			statement = connection.createStatement();
		
			rs = statement.executeQuery(GET_ALL_GROUP_BY_SPECIALTY_NAME);
			
			while (rs.next()) {
				result.add(buildSpecialtyFromDB(rs));
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [GET_ALL_GROUP_BY_SPECIALTY_NAME : SpecialtyDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, statement, rs);
		}
		
		return result;
	}
	
	@Override
	public List<Specialty> getSpecialtyByFaculty(Specialty specialty, Faculty faculty) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Specialty> result = new ArrayList<>();
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_SPECIALTY_BY_FACULTY);
			ps.setString(1, specialty.getSpecialtyName());
			ps.setInt(2, faculty.getId());
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				result.add(buildSpecialtyFromDB(rs));
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [GET_SPECIALTY_BY_FACULTY : SpecialtyDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return result;
	}
	
	@Override
	public List<Specialty> getAllByFacultyId(Faculty faculty) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Specialty> result = new ArrayList<>();
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_ALL_BY_FACULTY_ID);
			ps.setInt(1, faculty.getId());
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				result.add(buildSpecialtyFromDB(rs));
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [GET_ALL_BY_FACULTY_ID : SpecialtyDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return result;
	}
	
	@Override
	public List<Specialty> getSpecialtiesByFacultyTypeStudy(Faculty faculty, TypeStudy type) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Specialty> result = new ArrayList<>();
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_SPECIALTIES_BY_FACULTY_TYPESTUDY);
			ps.setInt(1, type.getId());
			ps.setInt(2, faculty.getId());
			
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				result.add(buildSpecialtyFromDB(rs));
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [GET_SPECIALTIES_BY_FACULTY_TYPE_STUDY : SpecialtyDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return result;
	}
	
	@Override
	public Specialty getSpecialtyById(int id) throws DAOException {
		Specialty specialty = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_SPECIALTY_BY_ID);
			ps.setInt(1, id);
		
			rs = ps.executeQuery();
		
			if (rs.next()) {
				specialty = buildSpecialtyFromDB(rs);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOSqlException("Pool can't get connection.", e);
		} catch (SQLException e) {	
			logger.log(Level.ERROR, "SQLException in [GET_SPECIALTY_BY_ID : SpecialtyDAOImpl]", e);
			throw new DAOException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return specialty;
	}

	@Override
	public Specialty getSpecialtyByName(String specialtyName) throws DAOException {
		Specialty specialty = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_SPECIALTY_BY_NAME);
			ps.setString(1, specialtyName);
		
			rs = ps.executeQuery();
		
			if (rs.next()) {
				specialty = buildSpecialtyFromDB(rs);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {	
			logger.log(Level.ERROR, "SQLException in [GET_SPECIALTY_BY_NAME : SpecialtyDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return specialty;
	}

	@Override
	public boolean update(Specialty specialty) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(UPDATE_SPECIALTY);
			
			ps.setInt(1, specialty.getIdTypeStudy());
			ps.setInt(2, specialty.getIdFaculty());
			ps.setInt(3, specialty.getIdPlan());
			ps.setString(4, specialty.getSpecialtyName());
			ps.setInt(5, specialty.getCountYearStudy());
			ps.setString(6, specialty.getQualification());
			ps.setInt(7, specialty.getId());
			
			ps.executeUpdate();
			flag = true;
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [UPDATE : SpecialtyDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
		
		return flag;
	}

	@Override
	public boolean create(Specialty specialty) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(ADD_SPECIALTY);
		
			ps.setInt(1, specialty.getIdTypeStudy());
			ps.setInt(2, specialty.getIdFaculty());
			ps.setInt(3, specialty.getIdPlan());
			ps.setString(4, specialty.getSpecialtyName());
			ps.setInt(5, specialty.getCountYearStudy());
			ps.setString(6, specialty.getQualification());
			
			ps.executeUpdate();
			flag = true;
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [CREATE : SpecialtyDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
		
		return flag;
	}

	@Override
	public boolean remove(Specialty specialty) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(REMOVE_SPECIALTY_BY_ID);
			
			ps.setInt(1, specialty.getId());
			
			ps.executeUpdate();
			flag = true;
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [REMOVE : SpecialtyDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
		
		return flag;
	}

	private Specialty buildSpecialtyFromDB(ResultSet set) throws SQLException {
		Specialty specialty = null;
		
		int id = set.getInt(SPECIALTY_ID_COLUMN);
		int idType = set.getInt(SPECIALTY_ID_TYPE_COLUMN);
		int idFaculty = set.getInt(SPECIALTY_ID_FACULTY_COLUMN);
		int idPlan = set.getInt(SPECIALTY_ID_PLAN_COLUMN);
		String name = set.getString(SPECIALTY_SPECIALTY_NAME_COLUMN);
		int countYear = set.getInt(SPECIALTY_COUNT_YEAR_STUDY_COLUMN);
		String qualification = set.getString(SPECIALTY_QUALIFICATION_COLUMN);
		
		specialty = new Specialty(id, idType, idFaculty, idPlan, name, countYear, qualification);
		
		return specialty;
	}
}

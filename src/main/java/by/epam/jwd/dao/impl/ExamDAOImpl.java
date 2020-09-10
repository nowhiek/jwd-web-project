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

import by.epam.jwd.bean.Exam;
import by.epam.jwd.dao.ExamDAO;
import by.epam.jwd.dao.connectionpool.exception.ConnectionPoolException;
import by.epam.jwd.dao.connectionpool.factory.ConnectionPoolFactory;
import by.epam.jwd.dao.connectionpool.impl.SqlConnectionPoolImpl;
import by.epam.jwd.dao.exception.DAOException;
import by.epam.jwd.dao.exception.DAOSqlException;

public class ExamDAOImpl implements ExamDAO {

	private final static String GET_ALL = "SELECT * FROM Exam";
	private final static String GET_EXAM_BY_ID = "SELECT * from Exam WHERE id = ?";
	private final static String GET_EXAM_BY_ID_USER = "SELECT * from Exam WHERE id_user = ?";
	private final static String GET_EXAM_BY_ID_MATRICULANT = "SELECT * from Exam WHERE id_matriculant = ?";
	private final static String GET_EXAM_BY_ID_SPECIALTY_HAS_SUBJECT = "SELECT * from Exam WHERE id_specialty_has_subject = ?";
	private final static String ADD_EXAM = "INSERT INTO Exam (id_user, id_matriculant, id_specialty_has_subject, result_mark) VALUES (?, ?, ?, ?)";
	private final static String UPDATE_EXAM = "UPDATE Exam SET id_user = ?, id_matriculant = ?, id_specialty_has_subject = ?, result_mark = ? WHERE id = ?";
	private final static String REMOVE_EXAM_BY_ID = "DELETE FROM Exam WHERE id = ?";
	
	private ConnectionPoolFactory connectionPoolFactory = ConnectionPoolFactory.getInstance();
	private SqlConnectionPoolImpl connectionPool = connectionPoolFactory.getSqlConnectionPoolDAO();
	private static Logger logger = LogManager.getLogger(ExamDAOImpl.class);
	
	private static final String EXAM_ID_COLUMN = "id";
	private static final String EXAM_ID_USER_COLUMN = "id_user";
	private static final String EXAM_ID_MATRICULANT_COLUMN = "id_matriculant";
	private static final String EXAM_ID_SPECIALTY_HAS_SUBJECT_COLUMN = "id_specialty_has_subject";
	private static final String EXAM_RESULT_MARK_COLUMN = "result_mark";
	
	
	@Override
	public List<Exam> getAll() throws DAOException {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		List<Exam> result = new ArrayList<>();
		
		try {
			connection = connectionPool.takeConnection();
			statement = connection.createStatement();
		
			rs = statement.executeQuery(GET_ALL);
			
			while (rs.next()) {
				result.add(buildExamFromDB(rs));
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [GET_ALL : ExamDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, statement, rs);
		}
		
		return result;
	}
	
	@Override
	public List<Exam> getAllExamsByIdMatriculant(int idMatriculant) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Exam> result = new ArrayList<Exam>();
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_EXAM_BY_ID_MATRICULANT);
			ps.setInt(1, idMatriculant);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				result.add(buildExamFromDB(rs));
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [GET_ALL_EXAMS_BY_ID_MATRICULANT : ExamDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return result;
	}

	@Override
	public Exam getExamById(int id) throws DAOException {
		Exam exam = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_EXAM_BY_ID);
			ps.setInt(1, id);
		
			rs = ps.executeQuery();
		
			if (rs.next()) {
				exam = buildExamFromDB(rs);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {	
			logger.log(Level.ERROR, "SQLException in [GET_EXAM_BY_ID : ExamDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return exam;
	}
	
	@Override
	public Exam getExamByIdUser(int idUser) throws DAOException {
		Exam exam = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_EXAM_BY_ID_USER);
			ps.setInt(1, idUser);
		
			rs = ps.executeQuery();
		
			if (rs.next()) {
				exam = buildExamFromDB(rs);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {	
			logger.log(Level.ERROR, "SQLException in [GET_EXAM_BY_ID_USER : ExamDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return exam;
	}

	@Override
	public Exam getExamByIdMatriculant(int idMatriculant) throws DAOException {
		Exam exam = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_EXAM_BY_ID_MATRICULANT);
			ps.setInt(1, idMatriculant);
		
			rs = ps.executeQuery();
		
			if (rs.next()) {
				exam = buildExamFromDB(rs);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {	
			logger.log(Level.ERROR, "SQLException in [GET_EXAM_BY_ID_MATRIUCLANT : ExamDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return exam;
	}

	@Override
	public Exam getExamByIdSpecialtyHasSubject(int idSpecialtyHasSubject) throws DAOException {
		Exam exam = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(GET_EXAM_BY_ID_SPECIALTY_HAS_SUBJECT);
			ps.setInt(1, idSpecialtyHasSubject);
		
			rs = ps.executeQuery();
		
			if (rs.next()) {
				exam = buildExamFromDB(rs);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {	
			logger.log(Level.ERROR, "SQLException in [GET_EXAM_BY_ID_SPECIALTY_HAS_SUBJECT : ExamDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps, rs);
		}
		
		return exam;
	}

	@Override
	public boolean update(Exam exam) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(UPDATE_EXAM);
			
			ps.setInt(1, exam.getIdUser());
			ps.setInt(2, exam.getIdMatriculant());
			ps.setInt(3, exam.getIdSpecialtyHasSubject());
			ps.setInt(4, exam.getResultMark());
			ps.setInt(5, exam.getId());
			
			ps.executeUpdate();
			flag = true;
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [UPDATE : ExamDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
		
		return flag;
	}

	@Override
	public boolean create(Exam exam) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			connection = connectionPool.takeConnection();	
			ps = connection.prepareStatement(ADD_EXAM);
		
			ps.setInt(1, exam.getIdUser());
			ps.setInt(2, exam.getIdMatriculant());
			ps.setInt(3, exam.getIdSpecialtyHasSubject());
			ps.setInt(4, exam.getResultMark());
			
			ps.executeUpdate();
			flag = true;
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [CREATE : ExamDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
		
		return flag;
	}

	@Override
	public boolean remove(Exam exam) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(REMOVE_EXAM_BY_ID);
			
			ps.setInt(1, exam.getId());
			
			ps.executeUpdate();
			flag = true;
		} catch (ConnectionPoolException e) {
			throw new DAOException("Pool can't get connection.", e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in [REMOVE : ExamDAOImpl]", e);
			throw new DAOSqlException("SQL can't get information.", e);
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
		
		return flag;
	}
	
	private Exam buildExamFromDB(ResultSet set) throws SQLException {
		Exam exam = null;
		
		int id = set.getInt(EXAM_ID_COLUMN);
		int idUser = set.getInt(EXAM_ID_USER_COLUMN);
		int idMatriculant = set.getInt(EXAM_ID_MATRICULANT_COLUMN);
		int idSpecialtyHasSubject = set.getInt(EXAM_ID_SPECIALTY_HAS_SUBJECT_COLUMN);
		int resultMark = set.getInt(EXAM_RESULT_MARK_COLUMN);
		
		exam = new Exam(id, idUser, idMatriculant, idSpecialtyHasSubject, resultMark);
		
		return exam;
	}
}

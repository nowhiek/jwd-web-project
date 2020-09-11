package by.epam.jwd.service.impl;

import java.util.List;

import by.epam.jwd.bean.Exam;
import by.epam.jwd.dao.ExamDAO;
import by.epam.jwd.dao.exception.DAOException;
import by.epam.jwd.dao.factory.impl.SqlDAOFactory;
import by.epam.jwd.service.ExamService;
import by.epam.jwd.service.exception.ExamServiceException;
import by.epam.jwd.service.exception.ServiceException;

public class ExamServiceImpl implements ExamService {

	private SqlDAOFactory sqlDAOFactory = SqlDAOFactory.getInstance();
	private ExamDAO examDAO = sqlDAOFactory.getExamDAO();
	
	@Override
	public List<Exam> getAll() throws ServiceException {
		List<Exam> list;
		
		try {
			list = examDAO.getAll();
		} catch(DAOException e) {
			throw new ExamServiceException("DAOException in [GET_ALL : EXAM_DAO]");
		}
		
		return list;
	}
	
	@Override
	public List<Exam> getAllExamsByIdMatriculant(int idMatriculant) throws ServiceException {
		List<Exam> list;
		
		try {
			list = examDAO.getAllExamsByIdMatriculant(idMatriculant);
		} catch(DAOException e) {
			throw new ExamServiceException("DAOException in [GET_ALL_EXAMS_BY_ID_MATRICULANT : EXAM_DAO]");
		}
		
		return list;
	}

	@Override
	public Exam findExamById(int id) throws ServiceException {
		Exam exam = null;
		
        try {
        	exam = examDAO.getExamById(id);
        } catch (DAOException e) {
            throw new ExamServiceException("DAOException in [FIND_EXAM_BY_ID : EXAM_DAO]");
        }
        
		return exam;
	}

	@Override
	public Exam findExamByIdUser(int idUser) throws ServiceException {
		Exam exam = null;
		
        try {
        	exam = examDAO.getExamByIdUser(idUser);
        } catch (DAOException e) {
            throw new ExamServiceException("DAOException in [FIND_EXAM_BY_ID_USER : EXAM_DAO]");
        }
        
		return exam;
	}

	@Override
	public Exam findExamByIdMatriculant(int idMatriculant) throws ServiceException {
		Exam exam = null;
		
        try {
        	exam = examDAO.getExamByIdMatriculant(idMatriculant);
        } catch (DAOException e) {
            throw new ExamServiceException("DAOException in [FIND_EXAM_BY_ID_MATRICULANT : EXAM_DAO]");
        }
        
		return exam;
	}

	@Override
	public Exam findExamByIdSpecialtyHasSubject(int idSpecialtyHasSubject) throws ServiceException {
		Exam exam = null;
		
        try {
        	exam = examDAO.getExamByIdSpecialtyHasSubject(idSpecialtyHasSubject);
        } catch (DAOException e) {
            throw new ExamServiceException("DAOException in [FIND_EXAM_BY_ID_SPECIALTY_HAS_SUBJECT : EXAM_DAO]");
        }
        
		return exam;
	}

	@Override
	public boolean update(Exam exam) throws ServiceException {
		boolean flag;
		
		try {
			flag = examDAO.update(exam);
		} catch (DAOException e) {
			throw new ExamServiceException("DAOException in [UPDATE : EXAM_DAO]", e);
		}
		
		return flag;
	}

	@Override
	public boolean create(Exam exam) throws ServiceException {
		boolean flag;
		
		try {
			flag = examDAO.create(exam);
		} catch (DAOException e) {
			throw new ExamServiceException("DAOException in [CREATE : EXAM_DAO]", e);
		}
		
		return flag;
	}

	@Override
	public boolean remove(Exam exam) throws ServiceException {
		boolean flag;
		
		try {
			flag = examDAO.remove(exam);
		} catch (DAOException e) {
			throw new ExamServiceException("DAOException in [REMOVE : EXAM_DAO]", e);
		}
		
		return flag;
	}
}

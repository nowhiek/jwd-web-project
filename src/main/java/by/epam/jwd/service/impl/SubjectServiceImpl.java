package by.epam.jwd.service.impl;

import java.util.List;

import by.epam.jwd.bean.Subject;
import by.epam.jwd.dao.SubjectDAO;
import by.epam.jwd.dao.exception.DAOException;
import by.epam.jwd.dao.factory.impl.SqlDAOFactory;
import by.epam.jwd.service.SubjectService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.exception.SubjectServiceException;

public class SubjectServiceImpl implements SubjectService {

	private SqlDAOFactory sqlDAOFactory = SqlDAOFactory.getInstance();
	private SubjectDAO subjectDAO = sqlDAOFactory.getSubjectDAO();
	
	@Override
	public List<Subject> getAll() throws ServiceException {
		List<Subject> list;
		
		try {
			list = subjectDAO.getAll();
		} catch(DAOException e) {
			throw new SubjectServiceException("DAOException in [GET_ALL : SUBJECT_DAO]", e);
		}
		
		return list;
	}

	@Override
	public Subject findSubjectById(int id) throws ServiceException {
		Subject subject = null;
		
        try {
        	subject = subjectDAO.getSubjectById(id);
        } catch (DAOException e) {
        	throw new SubjectServiceException("DAOException in [FIND_SUBJECT_BY_ID : SUBJECT_DAO]", e);
        }
        
		return subject;
	}

	@Override
	public Subject findSubjectByName(String subjectName) throws ServiceException {
		Subject subject = null;
		
        try {
        	subject = subjectDAO.getSubjectByName(subjectName);
        } catch (DAOException e) {
        	throw new SubjectServiceException("DAOException in [FIND_SUBJECT_BY_NAME : SUBJECT_DAO]", e);
        }
        
		return subject;
	}

	@Override
	public boolean update(Subject subject) throws ServiceException {
		boolean flag;
		
		try {
			flag = subjectDAO.update(subject);
		} catch (DAOException e) {
			throw new SubjectServiceException("DAOException in [UPDATE : SUBJECT_DAO]", e);
		}
		
		return flag;
	}

	@Override
	public boolean create(Subject subject) throws ServiceException {
		boolean flag;
		
		try {
			flag = subjectDAO.create(subject);
		} catch (DAOException e) {
			throw new SubjectServiceException("DAOException in [CREATE : SUBJECT_DAO]", e);
		}
		
		return flag;
	}

	@Override
	public boolean remove(Subject subject) throws ServiceException {
		boolean flag;
		
		try {
			flag = subjectDAO.remove(subject);
		} catch (DAOException e) {
			throw new SubjectServiceException("DAOException in [REMOVE : SUBJECT_DAO]", e);
		}
		
		return flag;
	}
}

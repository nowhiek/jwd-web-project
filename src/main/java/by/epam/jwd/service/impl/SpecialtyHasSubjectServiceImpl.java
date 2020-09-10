package by.epam.jwd.service.impl;

import java.util.List;

import by.epam.jwd.bean.SpecialtyHasSubject;
import by.epam.jwd.dao.SpecialtyHasSubjectDAO;
import by.epam.jwd.dao.exception.DAOException;
import by.epam.jwd.dao.factory.impl.SqlDAOFactory;
import by.epam.jwd.service.SpecialtyHasSubjectService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.exception.SpecialtyHasSubjectServiceException;

public class SpecialtyHasSubjectServiceImpl implements SpecialtyHasSubjectService {

	private SqlDAOFactory sqlDAOFactory = SqlDAOFactory.getInstance();
	private SpecialtyHasSubjectDAO specialtyHasSubhectDAO = sqlDAOFactory.getSpecialtyHasSubjectDAO();
	
	@Override
	public List<SpecialtyHasSubject> getAll() throws ServiceException {
		List<SpecialtyHasSubject> list;
		
		try {
			list = specialtyHasSubhectDAO.getAll();
		} catch(DAOException e) {
			throw new SpecialtyHasSubjectServiceException("DAOException in [GET_ALL : SPECIALTY_HAS_SUBJECT_DAO]", e);
		}
		
		return list;
	}

	@Override
	public SpecialtyHasSubject findSpecialtyHasSubjectById(int id) throws ServiceException {
		SpecialtyHasSubject has = null;
		
        try {
        	has = specialtyHasSubhectDAO.getSpecialtyHasSubjectById(id);
        } catch (DAOException e) {
        	throw new SpecialtyHasSubjectServiceException("DAOException in [FIND_SPECIALTY_HAS_SUBJECT_BY_ID : SPECIALTY_HAS_SUBJECT_DAO]", e);
        }
        
		return has;
	}
	
	@Override
	public int getCountSubjectsByIdSpecialty(int idSpecialty) throws ServiceException {
		int count = 0;
		
        try {
        	count = specialtyHasSubhectDAO.getCountSubjectsByByIdSpecialty(idSpecialty);
        } catch (DAOException e) {
        	throw new SpecialtyHasSubjectServiceException("DAOException in [GET_COUNT_SUBJECTS_BY_ID_SPECIALTY : SPECIALTY_HAS_SUBJECT_DAO]", e);
        }
        
		return count;
	}

	@Override
	public SpecialtyHasSubject findSpecialtyHasSubjectByIdSpecialty(int idSpecialty) throws ServiceException {
		SpecialtyHasSubject has = null;
		
        try {
        	has = specialtyHasSubhectDAO.getSpecialtyHasSubjectByIdSpecialty(idSpecialty);
        } catch (DAOException e) {
        	throw new SpecialtyHasSubjectServiceException("DAOException in [FIND_SPECIALTY_HAS_SUBJECT_BY_ID_SPECIALTY : SPECIALTY_HAS_SUBJECT_DAO]", e);
        }
        
		return has;
	}

	@Override
	public SpecialtyHasSubject findSpecialtyHasSubjectByIdSubject(int idSubject) throws ServiceException {
		SpecialtyHasSubject has = null;
		
        try {
        	has = specialtyHasSubhectDAO.getSpecialtyHasSubjectByIdSubject(idSubject);
        } catch (DAOException e) {
        	throw new SpecialtyHasSubjectServiceException("DAOException in [FIND_SPECIALTY_HAS_SUBJECT_BY_ID_SUBJECT : SPECIALTY_HAS_SUBJECT_DAO]", e);
        }
        
		return has;
	}

	@Override
	public boolean update(SpecialtyHasSubject has) throws ServiceException {
		boolean flag;
		
		try {
			flag = specialtyHasSubhectDAO.update(has);
		} catch (DAOException e) {
			throw new SpecialtyHasSubjectServiceException("DAOException in [UPDATE : SPECIALTY_HAS_SUBJECT_DAO]", e);
		}
		
		return flag;
	}

	@Override
	public boolean create(SpecialtyHasSubject has) throws ServiceException {
		boolean flag;
		
		try {
			flag = specialtyHasSubhectDAO.create(has);
		} catch (DAOException e) {
			throw new SpecialtyHasSubjectServiceException("DAOException in [CREATE : SPECIALTY_HAS_SUBJECT_DAO]", e);
		}
		
		return flag;
	}

	@Override
	public boolean remove(SpecialtyHasSubject has) throws ServiceException {
		boolean flag;
		
		try {
			flag = specialtyHasSubhectDAO.remove(has);
		} catch (DAOException e) {
			throw new SpecialtyHasSubjectServiceException("DAOException in [REMOVE : SPECIALTY_HAS_SUBJECT_DAO]", e);
		}
		
		return flag;
	}

	@Override
	public List<SpecialtyHasSubject> getAllBySpecialtyId(int idSpecialty) throws ServiceException {
		List<SpecialtyHasSubject> list;
		
		try {
			list = specialtyHasSubhectDAO.getAllBySpecialtyId(idSpecialty);
		} catch(DAOException e) {
			throw new SpecialtyHasSubjectServiceException("DAOException in [GET_ALL_BY_SPECIALTY_ID : SPECIALTY_HAS_SUBJECT_DAO]", e);
		}
		
		return list;
	}
}

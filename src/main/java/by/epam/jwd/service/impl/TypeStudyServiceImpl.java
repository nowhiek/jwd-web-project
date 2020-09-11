package by.epam.jwd.service.impl;

import java.util.List;

import by.epam.jwd.bean.Faculty;
import by.epam.jwd.bean.Specialty;
import by.epam.jwd.bean.TypeStudy;
import by.epam.jwd.dao.TypeStudyDAO;
import by.epam.jwd.dao.exception.DAOException;
import by.epam.jwd.dao.factory.impl.SqlDAOFactory;
import by.epam.jwd.service.TypeStudyService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.exception.TypeStudyServiceException;

public class TypeStudyServiceImpl implements TypeStudyService {
	
	private SqlDAOFactory sqlDAOFactory = SqlDAOFactory.getInstance();
	private TypeStudyDAO typeStudyDAO = sqlDAOFactory.getTypeStudyDAO();
	
	@Override
	public List<TypeStudy> getAll() throws ServiceException {
		List<TypeStudy> list;
		
		try {
			list = typeStudyDAO.getAll();
		} catch(DAOException e) {
			throw new TypeStudyServiceException("DAOException in [GET_ALL : TypeStudyServiceImpl]", e);
		}
		
		return list;
	}
	
	@Override
	public List<TypeStudy> getAllBySpecialty(Specialty specialty, Faculty faculty) throws ServiceException {
		List<TypeStudy> list;
		
		try {
			list = typeStudyDAO.getAllBySpecialty(specialty, faculty);
		} catch(DAOException e) {
			throw new TypeStudyServiceException("DAOException in [GET_ALL_BY_SPECIALTY : TypeStudyServiceImpl]", e);
		}
		
		return list;
	}
	
	@Override
	public boolean create(TypeStudy type) throws ServiceException {
		boolean flag;
		
		try {
			flag = typeStudyDAO.create(type);
		} catch (DAOException e) {
			throw new TypeStudyServiceException("DAOException in [CREATE : TypeStudyServiceImpl]", e);
		}
		
		return flag;
	}
	
	@Override
	public boolean update(TypeStudy type) throws ServiceException {
		boolean flag;
		
		try {
			flag = typeStudyDAO.update(type);
		} catch (DAOException e) {
			throw new TypeStudyServiceException("DAOException in [UPDATE : TypeStudyServiceImpl]", e);
		}
		
		return flag;
	}
	
	@Override
	public boolean remove(TypeStudy type) throws ServiceException {
		boolean flag;
		
		try {
			flag = typeStudyDAO.remove(type);
		} catch (DAOException e) {
			throw new TypeStudyServiceException("DAOException in [REMOVE : TypeStudyServiceImpl]", e);
		}
		
		return flag;
	}
	
	@Override
	public TypeStudy findTypeStudyByName(String name) throws ServiceException {
		TypeStudy type = null;
		
        try {
        	type = typeStudyDAO.getTypeStudyByName(name);
        } catch (DAOException e) {
        	throw new TypeStudyServiceException("DAOException in [FIND_TYPE_STUDY_BY_NAME : TypeStudyServiceImpl]", e);
        }
        
		return type;
	}
	
	@Override
	public TypeStudy findTypeStudyById(int idTypeStudy) throws ServiceException {
		TypeStudy type = null;
		
        try {
        	type = typeStudyDAO.getTypeStudyById(idTypeStudy);
        } catch (DAOException e) {
        	throw new TypeStudyServiceException("DAOException in [FIND_TYPE_STUDY_BY_ID : TypeStudyServiceImpl]", e);
        }
        
		return type;
	}
}

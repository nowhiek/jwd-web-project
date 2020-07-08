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

public class TypeStudyServiceImpl implements TypeStudyService {
	
	private SqlDAOFactory sqlDAOFactory = SqlDAOFactory.getInstance();
	private TypeStudyDAO typeStudyDAO = sqlDAOFactory.getTypeStudyDAO();
	
	@Override
	public List<TypeStudy> getAll() throws ServiceException {
		List<TypeStudy> list;
		
		try {
			list = typeStudyDAO.getAll();
		} catch(DAOException e) {
			throw new ServiceException("Change the discription this exception. [GET_ALL : TYPE_STUDY_DAO]");
		}
		
		return list;
	}
	
	@Override
	public List<TypeStudy> getAllBySpecialty(Specialty specialty, Faculty faculty) throws ServiceException {
		List<TypeStudy> list;
		
		try {
			list = typeStudyDAO.getAllBySpecialty(specialty, faculty);
		} catch(DAOException e) {
			throw new ServiceException("Change the discription this exception. [GET_ALL_BY_SPECIALTY : TYPE_STUDY_DAO]");
		}
		
		return list;
	}
	
	@Override
	public boolean create(TypeStudy type) throws ServiceException {
		boolean flag;
		
		try {
			flag = typeStudyDAO.create(type);
		} catch (DAOException e) {
			throw new ServiceException("I can't create the type study. [CREATE : TYPE_STUDY_DAO]", e);
		}
		
		return flag;
	}
	
	@Override
	public boolean update(TypeStudy type) throws ServiceException {
		boolean flag;
		
		try {
			flag = typeStudyDAO.update(type);
		} catch (DAOException e) {
			throw new ServiceException("I can't update the type study. [UPDATE : TYPE_STUDY_DAO]", e);
		}
		
		return flag;
	}
	
	@Override
	public boolean remove(TypeStudy type) throws ServiceException {
		boolean flag;
		
		try {
			flag = typeStudyDAO.remove(type);
		} catch (DAOException e) {
			throw new ServiceException("I can't remove the specialty. [REMOVE : TYPE_STUDY_DAO]", e);
		}
		
		return flag;
	}
	
	@Override
	public TypeStudy findTypeStudyByName(String name) throws ServiceException {
		TypeStudy type = null;
		
        try {
        	type = typeStudyDAO.getTypeStudyByName(name);
        } catch (DAOException e) {
            throw new ServiceException("I can't find the type study by name. [FIND_TYPE_STUDY_BY_NAME : TYPE_STUDY_DAO]");
        }
        
		return type;
	}
	
	@Override
	public TypeStudy findTypeStudyById(int idTypeStudy) throws ServiceException {
		TypeStudy type = null;
		
        try {
        	type = typeStudyDAO.getTypeStudyById(idTypeStudy);
        } catch (DAOException e) {
            throw new ServiceException("I can't find the type study by id. [FIND_TYPE_STUDY_BY_ID : TYPE_STUDY_DAO]");
        }
        
		return type;
	}
}

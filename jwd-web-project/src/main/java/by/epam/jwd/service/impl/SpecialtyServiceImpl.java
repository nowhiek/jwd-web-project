package by.epam.jwd.service.impl;

import java.util.List;

import by.epam.jwd.bean.Faculty;
import by.epam.jwd.bean.Specialty;
import by.epam.jwd.bean.TypeStudy;
import by.epam.jwd.dao.SpecialtyDAO;
import by.epam.jwd.dao.exception.DAOException;
import by.epam.jwd.dao.factory.impl.SqlDAOFactory;
import by.epam.jwd.service.SpecialtyService;
import by.epam.jwd.service.exception.ServiceException;

public class SpecialtyServiceImpl implements SpecialtyService{

	private SqlDAOFactory sqlDAOFactory = SqlDAOFactory.getInstance();
	private SpecialtyDAO specialtyDAO = sqlDAOFactory.getSpecialtyDAO();
	
	@Override
	public List<Specialty> getAll() throws ServiceException {
		List<Specialty> list;
		
		try {
			list = specialtyDAO.getAll();
		} catch(DAOException e) {
			throw new ServiceException("Change the discription this exception. [GET_ALL : SPECIALTY_DAO]");
		}
		
		return list;
	}
	
	@Override
	public List<Specialty> getAllGroupBySpecialtyName() throws ServiceException {
		List<Specialty> list;
		
		try {
			list = specialtyDAO.getAllGroupBySpecialtyName();
		} catch(DAOException e) {
			throw new ServiceException("Change the discription this exception. [GET_ALL_GROUP_BY_SPECIALTY_NAME : SPECIALTY_DAO]");
		}
		
		return list;
	}
	
	@Override
	public List<Specialty> getSpecialtyByFaculty(Specialty specialty, Faculty faculty) throws ServiceException {
		List<Specialty> list;
		
		try {
			list = specialtyDAO.getSpecialtyByFaculty(specialty, faculty);
		} catch(DAOException e) {
			throw new ServiceException("Change the discription this exception. [GET_SPECIALTY_BY_FACULTY : SPECIALTY_DAO]");
		}
		
		return list;
	}
	
	@Override
	public List<Specialty> getAllByFacultyId(Faculty faculty) throws ServiceException {
		List<Specialty> list;
		
		try {
			list = specialtyDAO.getAllByFacultyId(faculty);
		} catch(DAOException e) {
			throw new ServiceException("Change the discription this exception. [GET_ALL_BY_FACULTY_ID : SPECIALTY_DAO]");
		}
		
		return list;
	}
	
	@Override
	public List<Specialty> getSpecialtiesByFacultyTypeStudy(Faculty faculty, TypeStudy type) throws ServiceException {
		List<Specialty> list;
		
		try {
			list = specialtyDAO.getSpecialtiesByFacultyTypeStudy(faculty, type);
		} catch(DAOException e) {
			throw new ServiceException("Change the discription this exception. [GET_SPECIALTIES_BY_FACULTY_TYPESTUDY : SPECIALTY_DAO]");
		}
		
		return list;
	}

	@Override
	public boolean create(Specialty specialty) throws ServiceException {
		boolean flag;
		
		try {
			flag = specialtyDAO.create(specialty);
		} catch (DAOException e) {
			throw new ServiceException("I can't create the specialty. [CREATE : SPECIALTY_DAO]", e);
		}
		
		return flag;
	}

	@Override
	public boolean update(Specialty specialty) throws ServiceException {
		boolean flag;
		
		try {
			flag = specialtyDAO.update(specialty);
		} catch (DAOException e) {
			throw new ServiceException("I can't update the specialty. [UPDATE : SPECIALTY_DAO]", e);
		}
		
		return flag;
	}

	@Override
	public boolean remove(Specialty specialty) throws ServiceException {
		boolean flag;
		
		try {
			flag = specialtyDAO.remove(specialty);
		} catch (DAOException e) {
			throw new ServiceException("I can't remove the specialty. [REMOVE : SPECIALTY_DAO]", e);
		}
		
		return flag;
	}

	@Override
	public Specialty findSpecialtyByName(String name) throws ServiceException {
		Specialty specialty = null;
		
        try {
        	specialty = specialtyDAO.getSpecialtyByName(name);
        } catch (DAOException e) {
            throw new ServiceException("I can't find the specialty by name. [GET_SPECIALTY_BY_NAME: SPECIALTY_DAO]");
        }
        
		return specialty;
	}

	@Override
	public Specialty findSpecialtyById(int idSpecialty) throws ServiceException {
		Specialty specialty = null;
		
        try {
        	specialty = specialtyDAO.getSpecialtyById(idSpecialty);
        } catch (DAOException e) {
            throw new ServiceException("I can't find the specialty by id. [GET_SPECIALTY_BY_ID : SPECIALTY_DAO]");
        }
        
		return specialty;
	}
}

package by.epam.jwd.service.impl;

import java.util.List;

import by.epam.jwd.bean.Faculty;
import by.epam.jwd.dao.FacultyDAO;
import by.epam.jwd.dao.exception.DAOException;
import by.epam.jwd.dao.factory.impl.SqlDAOFactory;
import by.epam.jwd.service.FacultyService;
import by.epam.jwd.service.exception.ServiceException;

public class FacultyServiceImpl implements FacultyService {

	private SqlDAOFactory sqlDAOFactory = SqlDAOFactory.getInstance();
	private FacultyDAO facultyDAO = sqlDAOFactory.getFacultyDAO();
	
	@Override
	public List<Faculty> getAll() throws ServiceException {
		List<Faculty> list;
		
		try {
			list = facultyDAO.getAll();
		} catch(DAOException e) {
			throw new ServiceException("Change the discription this exception. [GET_ALL : FACULTY_DAO]");
		}
		
		return list;
	}

	@Override
	public boolean create(Faculty faculty) throws ServiceException {
		boolean flag;
		
		try {
			flag = facultyDAO.create(faculty);
		} catch (DAOException e) {
			throw new ServiceException("I can't create the faculty. [CREATE : FACULTY_DAO]", e);
		}
		
		return flag;
	}

	@Override
	public boolean update(Faculty faculty) throws ServiceException {
		boolean flag;
		
		try {
			flag = facultyDAO.update(faculty);
		} catch (DAOException e) {
			throw new ServiceException("I can't update the faculty. [UPDATE : FACULTY_DAO]", e);
		}
		
		return flag;
	}

	@Override
	public Faculty findFacultyByName(String name) throws ServiceException {
		Faculty faculty = null;
		
        try {
        	faculty = facultyDAO.getFacultyByName(name);
        } catch (DAOException e) {
            throw new ServiceException("I can't find the faculty by name. [FIND_FACULTY_BY_NAME : FACULTY_DAO]");
        }
        
		return faculty;
	}

	@Override
	public Faculty findFacultyById(int idFaculty) throws ServiceException {
		Faculty faculty = null;
		
        try {
        	faculty = facultyDAO.getFacultyById(idFaculty);
        } catch (DAOException e) {
            throw new ServiceException("I can't find the faculty by id. [FIND_FACULTY_BY_ID : FACULTY_DAO]");
        }
        
		return faculty;
	}
}
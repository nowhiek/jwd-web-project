package by.epam.jwd.service;

import java.util.List;

import by.epam.jwd.bean.Faculty;
import by.epam.jwd.service.exception.ServiceException;

public interface FacultyService {
	List<Faculty> getAll() throws ServiceException;
	boolean create(Faculty faculty) throws ServiceException;
	boolean update(Faculty faculty) throws ServiceException;
	boolean remove(Faculty faculty) throws ServiceException;
	Faculty findFacultyByName(String name) throws ServiceException;
	Faculty findFacultyById(int idUser) throws ServiceException;
}

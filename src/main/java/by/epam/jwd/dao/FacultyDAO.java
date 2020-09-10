package by.epam.jwd.dao;

import java.util.List;

import by.epam.jwd.bean.Faculty;
import by.epam.jwd.dao.exception.DAOException;

public interface FacultyDAO {
	List<Faculty> getAll() throws DAOException;
	Faculty getFacultyById(int id) throws DAOException;
	Faculty getFacultyByName(String facultyName) throws DAOException;
	boolean update(Faculty faculty) throws DAOException;
	boolean create(Faculty faculty) throws DAOException;
	boolean remove(Faculty faculty) throws DAOException;
}

package by.epam.jwd.dao;

import java.util.List;

import by.epam.jwd.bean.Subject;
import by.epam.jwd.dao.exception.DAOException;

public interface SubjectDAO {
	List<Subject> getAll() throws DAOException;
	Subject getSubjectById(int id) throws DAOException;
	Subject getSubjectByName(String subjectName) throws DAOException;
	boolean update(Subject subject) throws DAOException;
	boolean create(Subject subject) throws DAOException;
	boolean remove(Subject subject) throws DAOException;
}

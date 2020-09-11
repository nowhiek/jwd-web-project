package by.epam.jwd.service;

import java.util.List;

import by.epam.jwd.bean.Subject;
import by.epam.jwd.service.exception.ServiceException;

public interface SubjectService {
	List<Subject> getAll() throws ServiceException;
	Subject findSubjectById(int id) throws ServiceException;
	Subject findSubjectByName(String subjectName) throws ServiceException;
	boolean update(Subject subject) throws ServiceException;
	boolean create(Subject subject) throws ServiceException;
	boolean remove(Subject subject) throws ServiceException;
}

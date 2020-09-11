package by.epam.jwd.service;

import java.util.List;

import by.epam.jwd.bean.Exam;
import by.epam.jwd.service.exception.ServiceException;

public interface ExamService {
	List<Exam> getAll() throws ServiceException;
	List<Exam> getAllExamsByIdMatriculant(int idMatriculant) throws ServiceException;
	Exam findExamById(int id) throws ServiceException;
	Exam findExamByIdUser(int idUser) throws ServiceException;
	Exam findExamByIdMatriculant(int idMatriculant) throws ServiceException;
	Exam findExamByIdSpecialtyHasSubject(int idSpecialtyHasSubject) throws ServiceException;
	boolean update(Exam exam) throws ServiceException;
	boolean create(Exam exam) throws ServiceException;
	boolean remove(Exam exam) throws ServiceException;
}

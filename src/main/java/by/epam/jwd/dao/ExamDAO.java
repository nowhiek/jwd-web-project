package by.epam.jwd.dao;

import java.util.List;

import by.epam.jwd.bean.Exam;
import by.epam.jwd.dao.exception.DAOException;

public interface ExamDAO {
	List<Exam> getAll() throws DAOException;
	List<Exam> getAllExamsByIdMatriculant(int idMatriculant) throws DAOException;
	Exam getExamById(int id) throws DAOException;
	Exam getExamByIdUser(int idUser) throws DAOException;
	Exam getExamByIdMatriculant(int idMatriculant) throws DAOException;
	Exam getExamByIdSpecialtyHasSubject(int idSpecialtyHasSubject) throws DAOException;
	boolean update(Exam exam) throws DAOException;
	boolean create(Exam exam) throws DAOException;
	boolean remove(Exam exam) throws DAOException;
}

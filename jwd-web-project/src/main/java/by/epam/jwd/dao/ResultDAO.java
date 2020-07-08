package by.epam.jwd.dao;

import java.util.List;

import by.epam.jwd.bean.Result;
import by.epam.jwd.dao.exception.DAOException;

public interface ResultDAO {
	List<Result> getAll() throws DAOException;
	Result getResultById(int id) throws DAOException;
	Result getResultByMatriculant(int idMatriculant) throws DAOException;
	boolean update(Result result) throws DAOException;
	boolean create(Result result) throws DAOException;
	boolean remove(Result result) throws DAOException;
}

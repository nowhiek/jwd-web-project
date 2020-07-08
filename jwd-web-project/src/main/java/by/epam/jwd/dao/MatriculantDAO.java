package by.epam.jwd.dao;

import java.util.List;

import by.epam.jwd.bean.Matriculant;
import by.epam.jwd.dao.exception.DAOException;

public interface MatriculantDAO {
	List<Matriculant> getAll() throws DAOException;
	Matriculant getMatriculantById(int id) throws DAOException;
	Matriculant getMatriculantByUser(int idUser) throws DAOException;
	boolean update(Matriculant matriculant) throws DAOException;
	boolean create(Matriculant matriculant) throws DAOException;
	boolean remove(Matriculant matriculant) throws DAOException;
}

package by.epam.jwd.service;

import java.util.List;

import by.epam.jwd.bean.Matriculant;
import by.epam.jwd.service.exception.ServiceException;

public interface MatriculantService {
	List<Matriculant> getAll() throws ServiceException;
	boolean create(Matriculant matriculant) throws ServiceException;
	boolean update(Matriculant matriculant) throws ServiceException;
	boolean remove(Matriculant matriculant) throws ServiceException;
	Matriculant findMatriculantById(int id) throws ServiceException;
	Matriculant findMatriculantByIdUser(int idUser) throws ServiceException;
}

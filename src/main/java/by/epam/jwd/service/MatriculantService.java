package by.epam.jwd.service;

import java.util.List;

import by.epam.jwd.bean.Matriculant;
import by.epam.jwd.bean.Plan;
import by.epam.jwd.bean.Specialty;
import by.epam.jwd.service.exception.ServiceException;

public interface MatriculantService {
	List<Matriculant> getAll() throws ServiceException;
	List<Matriculant> getAllMatriculantsBySpecialty(Specialty specialty) throws ServiceException;
	List<Matriculant> getListMatriculantsBySpecialty(Specialty specialty, Plan plan) throws ServiceException;
	List<Matriculant> getAllMatriculantsByProperty(boolean active) throws ServiceException;
	boolean create(Matriculant matriculant) throws ServiceException;
	boolean update(Matriculant matriculant) throws ServiceException;
	boolean remove(Matriculant matriculant) throws ServiceException;
	Matriculant findMatriculantById(int id) throws ServiceException;
	Matriculant findMatriculantByIdUser(int idUser) throws ServiceException;
}

package by.epam.jwd.service;

import java.util.List;

import by.epam.jwd.bean.Faculty;
import by.epam.jwd.bean.Specialty;
import by.epam.jwd.bean.TypeStudy;
import by.epam.jwd.service.exception.ServiceException;

public interface TypeStudyService {
	List<TypeStudy> getAll() throws ServiceException;
	List<TypeStudy> getAllBySpecialty(Specialty specialty, Faculty faculty) throws ServiceException;
	boolean create(TypeStudy type) throws ServiceException;
	boolean update(TypeStudy type) throws ServiceException;
	boolean remove(TypeStudy type) throws ServiceException;
	TypeStudy findTypeStudyByName(String name) throws ServiceException;
	TypeStudy findTypeStudyById(int idTypeStudy) throws ServiceException;
}

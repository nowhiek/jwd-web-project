package by.epam.jwd.service;

import java.util.List;

import by.epam.jwd.bean.Faculty;
import by.epam.jwd.bean.Specialty;
import by.epam.jwd.bean.TypeStudy;
import by.epam.jwd.service.exception.ServiceException;

public interface SpecialtyService {
	List<Specialty> getAll() throws ServiceException;
	List<Specialty> getAllGroupBySpecialtyName() throws ServiceException;
	List<Specialty> getSpecialtyByFaculty(Specialty specialty, Faculty faculty) throws ServiceException;
	List<Specialty> getAllByFacultyId(Faculty faculty) throws ServiceException;
	List<Specialty> getSpecialtiesByFacultyTypeStudy(Faculty faculty, TypeStudy type) throws ServiceException;
	boolean create(Specialty specialty) throws ServiceException;
	boolean update(Specialty specialty) throws ServiceException;
	boolean remove(Specialty specialty) throws ServiceException;
	Specialty findSpecialtyByName(String name) throws ServiceException;
	Specialty findSpecialtyById(int idSpecialty) throws ServiceException;
}

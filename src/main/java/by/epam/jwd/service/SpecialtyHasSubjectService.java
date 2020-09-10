package by.epam.jwd.service;

import java.util.List;

import by.epam.jwd.bean.SpecialtyHasSubject;
import by.epam.jwd.service.exception.ServiceException;

public interface SpecialtyHasSubjectService {
	List<SpecialtyHasSubject> getAll() throws ServiceException;
	List<SpecialtyHasSubject> getAllBySpecialtyId(int idSpecialty) throws ServiceException;
	int getCountSubjectsByIdSpecialty(int idSpecialty) throws ServiceException;
	SpecialtyHasSubject findSpecialtyHasSubjectById(int id) throws ServiceException;
	SpecialtyHasSubject findSpecialtyHasSubjectByIdSpecialty(int idSpecialty) throws ServiceException;
	SpecialtyHasSubject findSpecialtyHasSubjectByIdSubject(int idSubject) throws ServiceException;
	boolean update(SpecialtyHasSubject has) throws ServiceException;
	boolean create(SpecialtyHasSubject has) throws ServiceException;
	boolean remove(SpecialtyHasSubject has) throws ServiceException;
}

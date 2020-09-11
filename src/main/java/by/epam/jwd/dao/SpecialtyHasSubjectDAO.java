package by.epam.jwd.dao;

import java.util.List;

import by.epam.jwd.bean.SpecialtyHasSubject;
import by.epam.jwd.dao.exception.DAOException;

public interface SpecialtyHasSubjectDAO {
	List<SpecialtyHasSubject> getAll() throws DAOException;
	List<SpecialtyHasSubject> getAllBySpecialtyId(int idSpecialty) throws DAOException;
	int getCountSubjectsByByIdSpecialty(int idSpecialty) throws DAOException;
	SpecialtyHasSubject getSpecialtyHasSubjectById(int id) throws DAOException;
	SpecialtyHasSubject getSpecialtyHasSubjectByIdSpecialty(int idSpecialty) throws DAOException;
	SpecialtyHasSubject getSpecialtyHasSubjectByIdSubject(int idSubject) throws DAOException;
	boolean update(SpecialtyHasSubject has) throws DAOException;
	boolean create(SpecialtyHasSubject has) throws DAOException;
	boolean remove(SpecialtyHasSubject has) throws DAOException;
}

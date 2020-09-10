package by.epam.jwd.dao;

import java.util.List;

import by.epam.jwd.bean.Faculty;
import by.epam.jwd.bean.Specialty;
import by.epam.jwd.bean.TypeStudy;
import by.epam.jwd.dao.exception.DAOException;

public interface SpecialtyDAO {
	List<Specialty> getAll() throws DAOException;
	List<Specialty> getAllGroupBySpecialtyName() throws DAOException;
	List<Specialty> getSpecialtyByFaculty(Specialty specialty, Faculty faculty) throws DAOException;
	List<Specialty> getAllByFacultyId(Faculty faculty) throws DAOException;
	List<Specialty> getSpecialtiesByFacultyTypeStudy(Faculty faculty, TypeStudy type) throws DAOException;
	Specialty getSpecialtyById(int id) throws DAOException;
	Specialty getSpecialtyByName(String specialtyName) throws DAOException;
	boolean update(Specialty specialty) throws DAOException;
	boolean create(Specialty specialty) throws DAOException;
	boolean remove(Specialty specialty) throws DAOException;
}

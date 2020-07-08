package by.epam.jwd.dao;

import java.util.List;

import by.epam.jwd.bean.Faculty;
import by.epam.jwd.bean.Specialty;
import by.epam.jwd.bean.TypeStudy;
import by.epam.jwd.dao.exception.DAOException;

public interface TypeStudyDAO {
	List<TypeStudy> getAll() throws DAOException;
	List<TypeStudy> getAllBySpecialty(Specialty specialty, Faculty faculty) throws DAOException;
	TypeStudy getTypeStudyById(int id) throws DAOException;
	TypeStudy getTypeStudyByName(String typeStudyName) throws DAOException;
	boolean update(TypeStudy type) throws DAOException;
	boolean create(TypeStudy type) throws DAOException;
	boolean remove(TypeStudy type) throws DAOException;
}

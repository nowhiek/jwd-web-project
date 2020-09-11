package by.epam.jwd.dao;

import by.epam.jwd.bean.UserDetail;
import by.epam.jwd.dao.exception.DAOException;

public interface UserDetailDAO {
	UserDetail getUserDetailById(int id) throws DAOException;
	UserDetail getUserDetailByIdUser(int idUser) throws DAOException;
	boolean update(UserDetail detail) throws DAOException;
	boolean create(UserDetail detail) throws DAOException;
	boolean remove(UserDetail detail) throws DAOException;
}

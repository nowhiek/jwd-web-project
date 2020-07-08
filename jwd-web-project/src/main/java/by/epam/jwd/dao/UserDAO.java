package by.epam.jwd.dao;

import java.util.List;

import by.epam.jwd.bean.User;
import by.epam.jwd.dao.exception.DAOException;

public interface UserDAO {
	List<User> getAll() throws DAOException;
	User getUserById(int id) throws DAOException;
	User getUserByLogin(String login) throws DAOException;
	boolean update(User user) throws DAOException;
	boolean create(User user) throws DAOException;
	boolean remove(User user) throws DAOException;
	boolean isUserExist(User user) throws DAOException;
}

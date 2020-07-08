package by.epam.jwd.service;

import java.util.List;

import by.epam.jwd.bean.User;
import by.epam.jwd.service.exception.ServiceException;

public interface UserService {
	List<User> getAll() throws ServiceException;
	boolean create(User user) throws ServiceException;
	boolean update(User user) throws ServiceException;
	boolean remove(User user) throws ServiceException;
	User findUserByLogin(String login) throws ServiceException;
	User findUserByIdUser(int idUser) throws ServiceException;
	boolean isUserExist(User user) throws ServiceException;
}

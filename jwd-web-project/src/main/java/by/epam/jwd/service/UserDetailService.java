package by.epam.jwd.service;

import by.epam.jwd.bean.UserDetail;
import by.epam.jwd.service.exception.ServiceException;

public interface UserDetailService {
	boolean create(UserDetail detail) throws ServiceException;
	boolean update(UserDetail detail) throws ServiceException;
	boolean remove(UserDetail detail) throws ServiceException;
	UserDetail findUserDetailByIdUser(int idUser) throws ServiceException;
}

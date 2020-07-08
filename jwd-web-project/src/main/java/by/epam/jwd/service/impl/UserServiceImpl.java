package by.epam.jwd.service.impl;

import java.util.List;

import by.epam.jwd.bean.User;
import by.epam.jwd.dao.UserDAO;
import by.epam.jwd.dao.exception.DAOException;
import by.epam.jwd.dao.factory.impl.SqlDAOFactory;
import by.epam.jwd.service.UserService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.exception.UserExistServiceException;

public class UserServiceImpl implements UserService {

	private SqlDAOFactory sqlDAOFactory = SqlDAOFactory.getInstance();
	private UserDAO userDAO = sqlDAOFactory.getUserDAO();
	
	@Override
	public List<User> getAll() throws ServiceException {
		List<User> list;
		
		try {
			list = userDAO.getAll();
		} catch(DAOException e) {
			throw new ServiceException("Change the discription this exception. [GET_ALL : USER_DAO]");
		}
		
		return list;
	}
	
	@Override
	public boolean create(User user) throws ServiceException {
		boolean flag;
		
		try {
			User tmp = findUserByLogin(user.getUserName());
			
			if (tmp == null) {
				flag = userDAO.create(user);
			} else {
				throw new UserExistServiceException("This user already exist.");
			}
		} catch (DAOException e) {
			throw new ServiceException("I can't create the user.", e);
		}
		
		return flag;
	}
	
	@Override
	public boolean update(User user) throws ServiceException {
		boolean flag;
		
		try {
			flag = userDAO.update(user);
		} catch (DAOException e) {
			throw new ServiceException("Users were not received.", e);
		}
		
		return flag;
	}
	
	@Override
	public boolean remove(User user) throws ServiceException {
		boolean flag;
		
		try {
			flag = userDAO.remove(user);
		} catch (DAOException e) {
			throw new ServiceException("Users were not received.", e);
		}
		
		return flag;
	}
	
	@Override
	public User findUserByIdUser(int idUser) throws ServiceException {
		User user = null;
		
        try {
        	user = userDAO.getUserById(idUser);
        } catch (DAOException e) {
            throw new ServiceException("Users were not received.");
        }
        
		return user;
	}
	
	@Override
	public User findUserByLogin(String login) throws ServiceException {	
		User user = null;
		
        try {
        	user = userDAO.getUserByLogin(login);
        } catch (DAOException e) {
            throw new ServiceException("Users were not received.");
        }
        
		return user;
	}
	
	@Override
	public boolean isUserExist(User user) throws ServiceException {
		boolean flag;
		
		try {
			flag = userDAO.isUserExist(user);
		} catch (DAOException e) {
			throw new ServiceException("Users were not received.", e);
		}
		
		return flag;
	}	
}

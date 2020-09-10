package by.epam.jwd.service.impl;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import by.epam.jwd.bean.User;
import by.epam.jwd.dao.UserDAO;
import by.epam.jwd.dao.exception.DAOException;
import by.epam.jwd.dao.factory.impl.SqlDAOFactory;
import by.epam.jwd.service.UserService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.exception.UserExistServiceException;
import by.epam.jwd.service.exception.UserServiceException;

public class UserServiceImpl implements UserService {

	private SqlDAOFactory sqlDAOFactory = SqlDAOFactory.getInstance();
	private UserDAO userDAO = sqlDAOFactory.getUserDAO();
	
	@Override
	public List<User> getAll() throws ServiceException {
		List<User> list;
		
		try {
			list = userDAO.getAll();
		} catch(DAOException e) {
			throw new UserServiceException("DAOException in [GET_ALL : USER_DAO]", e);
		}
		
		return list;
	}
	
	@Override
	public boolean create(User user) throws ServiceException {
		boolean flag;
		
		try {
			User tmp = findUserByLogin(user.getUserName());
			
			if (tmp == null) {
				user.setUserPassword(DigestUtils.md5Hex(user.getUserPassword()));
				flag = userDAO.create(user);
			} else {
				throw new UserExistServiceException("This user already exist.");
			}
		} catch (DAOException e) {
			throw new UserServiceException("DAOException in [CREATE : USER_DAO]", e);
		}
		
		return flag;
	}
	
	@Override
	public boolean update(User user) throws ServiceException {
		boolean flag;
		
		try {
			flag = userDAO.update(user);
		} catch (DAOException e) {
			throw new UserServiceException("DAOException in [UPDATE : USER_DAO]", e);
		}
		
		return flag;
	}
	
	@Override
	public boolean remove(User user) throws ServiceException {
		boolean flag;
		
		try {
			flag = userDAO.remove(user);
		} catch (DAOException e) {
			throw new UserServiceException("DAOException in [REMOVE : USER_DAO]", e);
		}
		
		return flag;
	}
	
	@Override
	public User findUserByIdUser(int idUser) throws ServiceException {
		User user = null;
		
        try {
        	user = userDAO.getUserById(idUser);
        } catch (DAOException e) {
        	throw new UserServiceException("DAOException in [FIND_USER_BY_ID_USER : USER_DAO]", e);
        }
        
		return user;
	}
	
	@Override
	public User findUserByLogin(String login) throws ServiceException {	
		User user = null;
		
        try {
        	user = userDAO.getUserByLogin(login);
        } catch (DAOException e) {
        	throw new UserServiceException("DAOException in [FIND_USER_BY_LOGIN : USER_DAO]", e);
        }
        
		return user;
	}
	
	@Override
	public User findUserByEmail(String email) throws ServiceException {	
		User user = null;
		
        try {
        	user = userDAO.getUserByEmail(email);
        } catch (DAOException e) {
        	throw new UserServiceException("DAOException in [FIND_USER_BY_EMAIL : USER_DAO]", e);
        }
        
		return user;
	}
	
	@Override
	public boolean isUserExist(User user) throws ServiceException {
		boolean flag;
		
		try {
			flag = userDAO.isUserExist(user);
		} catch (DAOException e) {
			throw new UserServiceException("DAOException in [IS_USER_EXIST : USER_DAO]", e);
		}
		
		return flag;
	}	
}

package by.epam.jwd.service.impl;

import by.epam.jwd.bean.UserDetail;
import by.epam.jwd.dao.UserDetailDAO;
import by.epam.jwd.dao.exception.DAOException;
import by.epam.jwd.dao.factory.impl.SqlDAOFactory;
import by.epam.jwd.service.UserDetailService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.exception.UserDetailServiceException;

public class UserDetailServiceImpl implements UserDetailService {

	private SqlDAOFactory sqlDAOFactory = SqlDAOFactory.getInstance();
	private UserDetailDAO userDetailDAO = sqlDAOFactory.getUserDetailDAO();
	
	@Override
	public boolean create(UserDetail detail) throws ServiceException {
		boolean flag;
		
		try {
			flag = userDetailDAO.create(detail);
		} catch (DAOException e) {
			throw new UserDetailServiceException("DAOException in [CREATE : UserDetailServiceImpl]", e);
		}
		
		return flag;
	}
	
	@Override
	public boolean update(UserDetail detail) throws ServiceException {
		boolean flag;
		
		try {
			flag = userDetailDAO.update(detail);
		} catch (DAOException e) {
			throw new UserDetailServiceException("DAOException in [UPDATE : UserDetailServiceImpl]", e);
		}
		
		return flag;
	}
	
	@Override
	public boolean remove(UserDetail detail) throws ServiceException {
		boolean flag;
		
		try {
			flag = userDetailDAO.remove(detail);
		} catch (DAOException e) {
			throw new UserDetailServiceException("DAOException in [REMOVE : UserDetailServiceImpl]", e);
		}
		
		return flag;
	}

	@Override
	public UserDetail findUserDetailByIdUser(int id) throws ServiceException {
		UserDetail detail = null;
		
        try {
        	detail = userDetailDAO.getUserDetailByIdUser(id);
        } catch (DAOException e) {
        	throw new UserDetailServiceException("DAOException in [FIND_USER_DETAIL_BY_ID_USER : UserDetailServiceImpl]", e);
        }
        
		return detail;
	}
}
